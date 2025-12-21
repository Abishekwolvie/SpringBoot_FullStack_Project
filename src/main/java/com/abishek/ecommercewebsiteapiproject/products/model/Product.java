package com.abishek.ecommercewebsiteapiproject.products.model;

import java.util.Arrays;
import java.util.List;

import com.abishek.ecommercewebsiteapiproject.cart.model.Cart;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {


    public Product(long price, boolean isavailable, int noofunits, int storage, String processor, String os, String brand, String model, String graphicscard,int ram) {
        this.price = price;
        this.isavailable = isavailable;
        this.noofunits = noofunits;
        this.storage = storage;
        this.processor = processor;
        this.os = os;
        this.brand = brand;
        this.model = model;
        this.graphicscard = graphicscard;
        this.ram=ram;
    }

    @Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
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
	private String imageName;
	private String imageType;
	private byte[] image;
    private int ram;
    @OneToMany(mappedBy = "productid")
	private List<Cart> cart;

}
