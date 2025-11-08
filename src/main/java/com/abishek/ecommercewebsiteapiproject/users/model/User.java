package com.abishek.ecommercewebsiteapiproject.users.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue
	private long id;
	public User() {
		super();
	}
	@Column(nullable = false) 
	private String name;
	@Column(nullable = false,unique = true)
	private String username;
	@Column(nullable = false) 
	private String password;
	@Column(nullable = false) 
	private long mobile;
    @Column(name="role",nullable = false)
    private String role;

    public User(long id, String name, String username, String password, long mobile, String role) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.mobile = mobile;
        this.role = role;
    }

    public long getId() {
        return id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", mobile=" + mobile +
                ", role='" + role + '\'' +
                '}';
    }
}
