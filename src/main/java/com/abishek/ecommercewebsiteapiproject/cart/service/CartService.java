package com.abishek.ecommercewebsiteapiproject.cart.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.abishek.ecommercewebsiteapiproject.cart.model.CartDTO;
import com.abishek.ecommercewebsiteapiproject.cart.model.CartDTOObj;
import com.abishek.ecommercewebsiteapiproject.cart.model.CartResponseDTO;
import com.abishek.ecommercewebsiteapiproject.products.exceptions.ProductNotFoundException;
import com.abishek.ecommercewebsiteapiproject.products.model.Product;
import com.abishek.ecommercewebsiteapiproject.products.service.ProductService;
import com.abishek.ecommercewebsiteapiproject.users.model.User;
import com.abishek.ecommercewebsiteapiproject.users.service.UserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.abishek.ecommercewebsiteapiproject.cart.CartRepository;
import com.abishek.ecommercewebsiteapiproject.cart.model.Cart;

@Service
public class CartService {
	
	private CartRepository cartRepository;
    private UserService userService;
    private ProductService productService;

	public CartService(CartRepository cartRepository,UserService userService, ProductService productService) {
		super();
		this.cartRepository = cartRepository;
        this.userService = userService;
        this.productService = productService;
	}
	
	public List<CartResponseDTO> getcartProductsByUsername(String userid){

       User user =   userService.finduserbyemailid(userid).orElseThrow(()->new UsernameNotFoundException("Username not found"));

        List<Cart> allByUserid = cartRepository.findAllByUserid(user);

        List<CartResponseDTO> cartresponsedto =  allByUserid.stream().map((cart)->new CartResponseDTO(cart.getCartitemid(),cart.getProductid().getId(),cart.getUserid().getId(),
                cart.getProductid().getBrand()+" "+cart.getProductid().getModel(),cart.getPrice())).collect(Collectors.toList());

        System.out.println(cartresponsedto);



		return cartresponsedto;
		
	}
	
	public CartDTOObj addtocart(CartDTO cartdto) {

        User user = userService.finduserbyemailid(cartdto.userid()).orElseThrow(()->new UsernameNotFoundException("User naot found"));
        Product productById = productService.findProductByIdcartadd(cartdto.productid()).orElseThrow(()->new ProductNotFoundException("Product not found"));
        Cart cart = new Cart(productById.getPrice(),user,productById, LocalDateTime.now());
        Cart cartsaved =cartRepository.save(cart);

        CartDTOObj dtoObj = new CartDTOObj(cartsaved.getPrice(),cartsaved.getCartitemid(),cartsaved.getProductid().getId());
		return dtoObj;
	}
	
    public void deleteproductfromcart(String id){

            cartRepository.deleteById(id);
    }

}
