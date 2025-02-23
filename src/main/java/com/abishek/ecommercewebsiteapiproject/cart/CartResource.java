package com.abishek.ecommercewebsiteapiproject.cart;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@RestController
@CrossOrigin
public class CartResource {
	
	private CartRepository cartRepository;
	
	
	public CartResource(CartRepository cartRepository) {
		super();
		this.cartRepository = cartRepository;
	}
	
	//get cart data by userid
	@RequestMapping(value="cart/getcartdetails/{userid}", method=RequestMethod.GET)
	public List<Cart> getcartProductsByUsername(@PathVariable String userid){
		
		return cartRepository.findAllByUserid(userid);
		
	}

	//add product to cart
	@RequestMapping(value="products/addtocart", method=RequestMethod.POST)
	public ResponseEntity<Object> addtocart(@RequestBody Cart cart){
		
		Cart cartsaved =cartRepository.save(cart);
		
		if(cartsaved!=null) {
			
			URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{carprodid}").buildAndExpand(cartsaved.getProductid()).toUri();
			return ResponseEntity.created(location).build();
		}
		
		return ResponseEntity.internalServerError().build();
	}

}
