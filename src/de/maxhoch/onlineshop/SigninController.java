package de.maxhoch.onlineshop;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.UserTransaction;

import de.maxhoch.model.Customer;

@Named
@SessionScoped //Benutzersitzung
public class SigninController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName="onlineshop")
	private EntityManager em;
	
	
	//Bean Ressource
	@Resource
	private UserTransaction ut;
	
	
	private String email;
	
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	private String password;
	
	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	@Inject
	private de.maxhoch.model.Customer customer;


	public de.maxhoch.model.Customer getCustomer() {
		return customer;
	}


	public void setCustomer(de.maxhoch.model.Customer customer) {
		this.customer = customer;
	}
	
	public String find()
	{
		
		TypedQuery <Customer> query=	em.createQuery("SELECT c FROM customer c WHERE c.email= :email AND c.password= :password",Customer.class);
		query.setParameter("email", email );
		query.setParameter("password", password);
		List <Customer> list=query.getResultList();
		if(list!=null && list.size()==1)
		{
			customer=list.get(0);
			Logger.getLogger(SigninController.class.getCanonicalName())
			.log(Level.INFO, "Speicherung: "+customer);
			
			//Show messages
			FacesMessage m=new FacesMessage("Succesfully signed in "+customer.getEmail());
			
			FacesContext.getCurrentInstance()
			.addMessage("signinForm", m);
			
		}
			
		return "signin";
		
	}
	
	
}
