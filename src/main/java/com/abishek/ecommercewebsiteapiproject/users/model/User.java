package com.abishek.ecommercewebsiteapiproject.users.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue
	private long id;

    public User(String username, String password, long mobile, String role) {
        this.username = username;
        this.password = password;
        this.mobile = mobile;
        this.role = role;
    }

    @Column(nullable = false,unique = true)
	private String username;
	@Column(nullable = false) 
	private String password;
	@Column(nullable = false) 
	private long mobile;
    @Column(name="role",nullable = false)
    private String role;




}
