package com.abishek.ecommercewebsiteapiproject.users.model;

import com.abishek.ecommercewebsiteapiproject.cart.model.Cart;

import com.abishek.ecommercewebsiteapiproject.orders.model.OrderTbl;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

    public User(String username, String password, String mobile, String role) {
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
	private String mobile;
    @Column(name="role",nullable = false)
    private String role;
    @OneToMany(mappedBy = "userid")
    private List<Cart> cart;
    @OneToMany(mappedBy = "userid")
    private List<OrderTbl> order;





}
