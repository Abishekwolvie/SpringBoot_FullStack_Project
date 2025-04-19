package com.abishek.ecommercewebsiteapiproject.cart.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.abishek.ecommercewebsiteapiproject.cart.CartRepository;
import com.abishek.ecommercewebsiteapiproject.cart.model.Cart;
import com.abishek.ecommercewebsiteapiproject.cart.service.CartService;



@RestController
@CrossOrigin
public class CartController {
	
	private CartRepository cartRepository;
	private CartService cartservice;
	
	
	public CartController(CartRepository cartRepository,CartService cartservice) {
		super();
		this.cartRepository = cartRepository;
		this.cartservice = cartservice;
	}
	
	//get cart data by userid
	@GetMapping("cart/getcartdetails/{userid}")
	public ResponseEntity<List<Cart>> getcartProductsByUsername(@PathVariable String userid){
		
		List<Cart> cartproductsbyusername = cartservice.getcartProductsByUsername(userid);
		
		return new ResponseEntity<>(cartproductsbyusername,HttpStatus.OK);
		
	}

	//add product to cart
	@PostMapping("products/addtocart")
	public ResponseEntity<Object> addtocart(@RequestBody Cart cart){
		
		Cart cartsaved = cartservice.addtocart(cart);
		
		if(cartsaved!=null) {
			
			URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{carprodid}").buildAndExpand(cartsaved.getProductid()).toUri();
			return ResponseEntity.created(location).build();
		} 
		
		return ResponseEntity.internalServerError().build();
	}
	
	//delete product
	@DeleteMapping("cart/deleteproductfromcart")
	public ResponseEntity<Object> deletefromcart(@RequestBody Cart cart){
		System.out.println(cart);

		
		cartservice.deletefromcart(cart);
	
		return new ResponseEntity<>(cart,HttpStatus.OK);
			
	}

}
