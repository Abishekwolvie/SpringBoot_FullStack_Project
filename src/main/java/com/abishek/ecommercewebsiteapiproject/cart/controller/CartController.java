package com.abishek.ecommercewebsiteapiproject.cart.controller;

import java.util.List;

import com.abishek.ecommercewebsiteapiproject.cart.model.CartDTO;
import com.abishek.ecommercewebsiteapiproject.cart.model.CartDTOObj;
import com.abishek.ecommercewebsiteapiproject.cart.model.CartResponseDTO;
import com.abishek.ecommercewebsiteapiproject.users.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.abishek.ecommercewebsiteapiproject.cart.CartRepository;
import com.abishek.ecommercewebsiteapiproject.cart.model.Cart;
import com.abishek.ecommercewebsiteapiproject.cart.service.CartService;



@RestController
@CrossOrigin
@RequestMapping("/laptopstore/api/v1")
public class CartController {
	
	private CartRepository cartRepository;
	private CartService cartservice;
    private UserService userService;
	
	
	public CartController(CartRepository cartRepository,CartService cartservice,UserService userService) {
		super();
		this.cartRepository = cartRepository;
		this.cartservice = cartservice;
        this.userService=userService;
	}
	
	//get cart data by userid
	@GetMapping("/cart/getcartdetails/{userid}")
	public ResponseEntity<?> getcartProductsByUsername(@PathVariable String userid){

        List<CartResponseDTO> cartproductsbyusername = cartservice.getcartProductsByUsername(userid);

		return new ResponseEntity<>(cartproductsbyusername,HttpStatus.OK);
	}

	//add product to cart
	@PostMapping("/products/addtocart")
	public ResponseEntity<Object> addtocart(@RequestBody CartDTO cart){
		
		CartDTOObj cartsaved = cartservice.addtocart(cart);
		
		if(cartsaved!=null) {
			
			return new ResponseEntity<>(cartsaved,HttpStatus.CREATED);
		} 
		
		return ResponseEntity.internalServerError().build();
	}
	

    @DeleteMapping("/products/removefromcart/{cartitemid}")
    public ResponseEntity<?> removefromcart(@PathVariable  String cartitemid){

        cartservice.deleteproductfromcart(cartitemid);

        return new ResponseEntity<>(HttpStatus.OK);

    }

}
