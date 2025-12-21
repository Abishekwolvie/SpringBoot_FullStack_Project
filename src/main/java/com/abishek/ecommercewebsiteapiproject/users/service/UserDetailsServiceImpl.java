package com.abishek.ecommercewebsiteapiproject.users.service;

import org.springframework.context.ApplicationContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.abishek.ecommercewebsiteapiproject.users.model.User;
import com.abishek.ecommercewebsiteapiproject.users.model.UserPrincipal;
import com.abishek.ecommercewebsiteapiproject.users.repository.UserRepository;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	UserRepository userrepository;


	public UserDetailsServiceImpl(UserRepository userrepository) {
		super();
		this.userrepository = userrepository;
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> user = userrepository.findByUsername(username);



        User userdetail = user.orElseThrow(()->new UsernameNotFoundException("User not found"));


        //System.out.println(new UserPrincipal(user).getPassword());
		// TODO Auto-generated method stub
		return new UserPrincipal(userdetail);
	}

}
