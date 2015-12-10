package de.maxhoch.onlineshop;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;

import com.sun.prism.impl.BaseMesh.FaceMembers;

import de.maxhoch.model.Customer;
import de.maxhoch.model.Item;

@Named
@RequestScoped
public class SellController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private Item item;

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	
	@PersistenceContext(unitName="onlineshop")
	private EntityManager em;
	

	//Bean Ressource
	@Resource
	private UserTransaction ut;
	
	
	public String persist(SigninController signinController)
	{
		try {
			ut.begin();
			Customer customer=signinController.getCustomer();
			customer=em.find(Customer.class, customer.getId());
			
			item.setSeller(customer);
			
			em.persist(item);
			Logger.getLogger(SellController.class.getCanonicalName()).log(Level.INFO, "Save Item: "+item);
			FacesMessage m = new FacesMessage("Successfully saved: "+item);
			FacesContext.getCurrentInstance().addMessage("sellForm", m);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FacesMessage m = new FacesMessage("Faled to save: "+item);
			FacesContext.getCurrentInstance().addMessage("sellForm", m);
		}
		
		try {
			ut.commit();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				ut.rollback();
			} catch (IllegalStateException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SecurityException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SystemException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		
		return "sell";
		
	}
	
}
