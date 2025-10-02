package com.abishek.ecommercewebsiteapiproject.cart.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.abishek.ecommercewebsiteapiproject.cart.CartRepository;
import com.abishek.ecommercewebsiteapiproject.cart.model.Cart;

@Service
public class CartService {
	
	private CartRepository cartRepository;

	public CartService(CartRepository cartRepository) {
		super();
		this.cartRepository = cartRepository;
	}
	
	public List<Cart> getcartProductsByUsername(String userid){
		
		return cartRepository.findAllByUserid(userid);
		
	}
	
	public Cart addtocart(Cart cart) {
		
		Cart cartsaved =cartRepository.save(cart);
		return cartsaved;
	}
	
//	public int deletefromcart(Cart cart) {
//		
//		int res = cartRepository.deleteByProductidAndUserid(cart.getProductid(), cart.getUserid());
//		
//		return res;
//		
//	}

}
