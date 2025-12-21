package com.abishek.ecommercewebsiteapiproject.users.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.abishek.ecommercewebsiteapiproject.users.model.User;
import com.abishek.ecommercewebsiteapiproject.users.repository.UserRepository;

@Service
public class UserService {
	
	private UserRepository userrepository;
	private BCryptPasswordEncoder bcryptpasswordencoder;
	

	public UserService(UserRepository userrepository,
			@Qualifier("bcryptpasswordencoder") BCryptPasswordEncoder bcryptpasswordencoder) {
		super();
		this.userrepository = userrepository;
		this.bcryptpasswordencoder = bcryptpasswordencoder; // this is defined in Securityconfiguration.java
	}

	public List<User> getAllUsers() {
		
		
		return userrepository.findAll();
		
	}
	
	public User addnewuser(User user) {
		
//		user.setPassword(bcryptpasswordencoder.encode(user.getPassword()));
		
		
		return userrepository.save(user);
	}
	
	public Optional<User> finduserbyemail(User user) {
		
		return userrepository.findByUsername(user.getUsername());
	}


    public Optional<User> finduserbyemailid(String username) {

        return userrepository.findByUsername(username);
    }
	
	

}
