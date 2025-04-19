package com.abishek.ecommercewebsiteapiproject.cart;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abishek.ecommercewebsiteapiproject.cart.model.Cart;

import jakarta.transaction.Transactional;

public interface CartRepository extends JpaRepository<Cart,Integer>{
	
	public List<Cart> findAllByUserid(String id);
	
	@Transactional
	int deleteByProductidAndUserid(int productid, String userid);

}
 