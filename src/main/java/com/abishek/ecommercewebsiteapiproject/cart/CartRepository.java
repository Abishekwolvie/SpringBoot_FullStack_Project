package com.abishek.ecommercewebsiteapiproject.cart;

import java.util.List;

import com.abishek.ecommercewebsiteapiproject.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import com.abishek.ecommercewebsiteapiproject.cart.model.Cart;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartRepository extends JpaRepository<Cart,String>{

    public List<Cart> findAllByUserid(User id);
	


}
 