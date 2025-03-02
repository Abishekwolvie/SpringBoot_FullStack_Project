package com.abishek.ecommercewebsiteapiproject.users;

import java.util.List;

import com.abishek.ecommercewebsiteapiproject.cart.Cart;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class User {

	@Id
	@GeneratedValue
	private long id;
	
	public User(String name, String email, String password, long mobile) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.mobile = mobile;
	}
	public long getId() {
		return id;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", mobile="
				+ mobile + "]";
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getMobile() {
		return mobile;
	}
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	public User() {
		super();
	}
	@Column(nullable = false) 
	private String name;
	@Column(nullable = false,unique = true)
	private String email;
	@Column(nullable = false) 
	private String password;
	@Column(nullable = false) 
	private long mobile;


	
}
