package com.abishek.ecommercewebsiteapiproject.products;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Product {
	
	public Product( long price, boolean isavailable, int noofunits, int storage, String processor,
			String os, String brand,String model,String graphicscard) {
		super();
		this.price = price;
		this.isavailable = isavailable;
		this.noofunits = noofunits;
		this.storage = storage;
		this.processor = processor;
		this.os = os;
		this.brand = brand;
		this.model=model;
		this.graphicscard=graphicscard;
	}
	public Product() {
		super();
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGraphicscard() {
		return graphicscard;
	}
	public void setGraphicscard(String graphicscard) {
		this.graphicscard = graphicscard;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", price=" + price + ", isavailable=" + isavailable + ", noofunits=" + noofunits
				+ ", storage=" + storage + ", processor=" + processor + ", os=" + os + ", brand=" + brand + ", model="
				+ model + ", graphicscard=" + graphicscard + "]";
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public boolean isIsavailable() {
		return isavailable;
	}
	public void setIsavailable(boolean isavailable) {
		this.isavailable = isavailable;
	}
	public int getNoofunits() {
		return noofunits;
	}
	public void setNoofunits(int noofunits) {
		this.noofunits = noofunits;
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
	@Id
	@GeneratedValue
	private int id;
	private long price;
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	private boolean isavailable;
	private int noofunits;
	private int storage;
	private String processor;
	private String os;
	private String brand;
	private String model;
	private String graphicscard;

}
