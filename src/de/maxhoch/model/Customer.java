package de.maxhoch.model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;



/**
 * The persistent class for the customer database table.
 * 
 */
@Entity(name="customer")
@Table(name="customer")
@NamedQuery(name="Customer.findAll", query="SELECT c FROM customer c")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	
	@SequenceGenerator(name="CUSTOMER_ID_GENERATOR", sequenceName="SEQ_CUSTOMER", allocationSize=1, initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CUSTOMER_ID_GENERATOR")
	private Long id;
	
	
	
	
	private String email;

	private String password;
	
	@Column(name="\"Address\"")
	private String address;
	
	//bi-directional many-to-one association to Item
	@OneToMany(mappedBy="buyer")
	private List<Item> purchases;

	//bi-directional many-to-one association to Item
	@OneToMany(mappedBy="seller")
	private List<Item> offers;

	public Customer() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Item> getPurchases() {
		return this.purchases;
	}

	public void setPurchases(List<Item> purchases) {
		this.purchases = purchases;
	}

	public Item addPurchas(Item purchas) {
		List<Item> purchases=getPurchases();
		if(purchases==null)	
		{
			purchases=new ArrayList<Item>();
		}
		
		purchases.add(purchas);
		purchas.setBuyer(this);
		setPurchases(purchases);
		return purchas;
	}

	public Item removePurchas(Item purchas) {
		getPurchases().remove(purchas);
		purchas.setBuyer(null);

		return purchas;
	}

	public List<Item> getOffers() {
		return this.offers;
	}

	public void setOffers(List<Item> offers) {
		this.offers = offers;
	}

	public Item addOffer(Item offer) {
		List<Item> offers=getOffers();
		if (offers==null)
		{
			offers=new ArrayList<Item>();
		}
		offers.add(offer);
		offer.setSeller(this);
		setOffers(offers);
		
		return offer;
	}

	public Item removeOffer(Item offer) {
		getOffers().remove(offer);
		offer.setSeller(null);

		return offer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", email=" + email + ", password=" + password + ", purchases=" + purchases
				+ ", offers=" + offers + "]";
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	

}