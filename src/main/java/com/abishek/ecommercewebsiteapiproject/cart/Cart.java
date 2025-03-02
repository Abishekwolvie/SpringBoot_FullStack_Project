package com.abishek.ecommercewebsiteapiproject.cart;

import com.abishek.ecommercewebsiteapiproject.users.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Cart {
	
	@Id
	@GeneratedValue
	private int productid;
	private int storage;
	private String processor;
	private String os;
	


	@Override
	public String toString() {
		return "Cart [productid=" + productid + ", storage=" + storage + ", processor=" + processor + ", os=" + os
				+ ", brand=" + brand + ", model=" + model + ", graphicscard=" + graphicscard + ", userid=" + userid
				+ ", price=" + price + "]";
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public int getProductid() {
		return productid;
	}
	public void setProductid(int productid) {
		this.productid = productid;
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
	public Cart() {
		super();
	}
	public Cart(int storage, String processor, String os, String brand, String model, String graphicscard,
			String userid,long price) {
		super();
		this.storage = storage;
		this.processor = processor;
		this.os = os;
		this.brand = brand;
		this.model = model;
		this.graphicscard = graphicscard;
		this.userid = userid;
		this.price =price;
	}
	private String brand;
	private String model;
	private String graphicscard;
	private String userid;
	private long price;
	
	
	

}
