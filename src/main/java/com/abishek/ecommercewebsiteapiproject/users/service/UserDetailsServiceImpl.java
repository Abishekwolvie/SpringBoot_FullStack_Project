package com.abishek.ecommercewebsiteapiproject.users.service;

import org.springframework.context.ApplicationContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.abishek.ecommercewebsiteapiproject.users.model.User;
import com.abishek.ecommercewebsiteapiproject.users.model.UserPrincipal;
import com.abishek.ecommercewebsiteapiproject.users.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	UserRepository userrepository;


	public UserDetailsServiceImpl(UserRepository userrepository) {
		super();
		this.userrepository = userrepository;
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userrepository.findByUsername(username);

        System.out.println(username);
        System.out.println(user);
		
		if(user==null) {
			System.out.println("404");
			
			throw new UsernameNotFoundException("404");
		}

        //System.out.println(new UserPrincipal(user).getPassword());
		// TODO Auto-generated method stub
		return new UserPrincipal(user);
	}

}
