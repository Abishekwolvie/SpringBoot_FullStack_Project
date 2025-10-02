package com.abishek.ecommercewebsiteapiproject.cart.model;

import com.abishek.ecommercewebsiteapiproject.products.model.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Cart {
	
	@Id
	@GeneratedValue
	private int cartitemid;
	private int storage;
	private String processor;
	private String os;
	
	private String brand;
	private String model;
	private String graphicscard;
	private String userid;
	private long price;
	@OneToOne
	@JoinColumn(name="product_id")
	private Product product;
	public Cart() {
		super();
	}
	public int getCartitemid() {
		return cartitemid;
	}
	public void setCartitemid(int cartitemid) {
		this.cartitemid = cartitemid;
	}
	public int getStorage() {
		return storage;
	}
	public void setStorage(int storage) {
		this.storage = storage;
	}
	public String getProcessor() {
		return processor;
	}
	public void setProcessor(String processor) {
		this.processor = processor;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getGraphicscard() {
		return graphicscard;
	}
	public void setGraphicscard(String graphicscard) {
		this.graphicscard = graphicscard;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Cart(int cartitemid, int storage, String processor, String os, String brand, String model,
			String graphicscard, String userid, long price, Product product) {
		super();
		this.cartitemid = cartitemid;
		this.storage = storage;
		this.processor = processor;
		this.os = os;
		this.brand = brand;
		this.model = model;
		this.graphicscard = graphicscard;
		this.userid = userid;
		this.price = price;
		this.product = product;
	}
	
	
	

}
