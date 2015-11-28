package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the customer database table.
 * 
 */
@Entity
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CUSTOMER_ID_GENERATOR", sequenceName="SEQ_CUSTOMER")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CUSTOMER_ID_GENERATOR")
	private Long id;

	private String email;

	private String password;

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
		getPurchases().add(purchas);
		purchas.setBuyer(this);

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
		getOffers().add(offer);
		offer.setSeller(this);

		return offer;
	}

	public Item removeOffer(Item offer) {
		getOffers().remove(offer);
		offer.setSeller(null);

		return offer;
	}

}