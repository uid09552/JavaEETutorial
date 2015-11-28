package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the item database table.
 * 
 */
@Entity
@NamedQuery(name="Item.findAll", query="SELECT i FROM Item i")
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ITEM_ID_GENERATOR", sequenceName="SEQ_ITEM")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ITEM_ID_GENERATOR")
	private Long id;

	private String description;

	private byte[] foto;

	private Double price;

	@Temporal(TemporalType.TIMESTAMP)
	private Date sold;

	private String title;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	private Customer buyer;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	private Customer seller;

	public Item() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getFoto() {
		return this.foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getSold() {
		return this.sold;
	}

	public void setSold(Date sold) {
		this.sold = sold;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Customer getBuyer() {
		return this.buyer;
	}

	public void setBuyer(Customer buyer) {
		this.buyer = buyer;
	}

	public Customer getSeller() {
		return this.seller;
	}

	public void setSeller(Customer seller) {
		this.seller = seller;
	}

}