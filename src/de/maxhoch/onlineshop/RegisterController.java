package de.maxhoch.onlineshop;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
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

@Named
@RequestScoped
public class RegisterController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName="onlineshop")
	private EntityManager em;
	
	
	//Bean Ressource
	@Resource
	private UserTransaction ut;
	
	
	@Inject
	private de.maxhoch.model.Customer customer;


	public de.maxhoch.model.Customer getCustomer() {
		return customer;
	}


	public void setCustomer(de.maxhoch.model.Customer customer) {
		this.customer = customer;
	}
	
	public String persist()
	{
		try {
			customer.setAddress("Example");
			ut.begin();
			em.persist(customer);
			try {
				ut.commit();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RollbackException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (HeuristicMixedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (HeuristicRollbackException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Logger.getLogger(RegisterController.class.getCanonicalName())
			.log(Level.INFO, "Speicherung: "+customer);
			
			//Show messages
			FacesMessage m=new FacesMessage("Succesfully registered "+customer.getEmail());
			
			FacesContext.getCurrentInstance()
			.addMessage("registerForm", m);
			
			
		} catch (NotSupportedException e) {
			// TODO Auto-generated catch block
			Logger.getLogger(RegisterController.class.getCanonicalName())
			.log(Level.WARNING, "Speicherungsfehler: "+e.getMessage());
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			Logger.getLogger(RegisterController.class.getCanonicalName())
			.log(Level.WARNING, "Speicherungsfehler: "+e.getMessage());
			e.printStackTrace();
		}
		
		return "register";
	}
	
	
}
