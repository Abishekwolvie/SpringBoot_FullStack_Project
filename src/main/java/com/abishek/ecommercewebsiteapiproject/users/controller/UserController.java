package com.abishek.ecommercewebsiteapiproject.users.controller;

import java.util.List;

import com.abishek.ecommercewebsiteapiproject.service.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.abishek.ecommercewebsiteapiproject.users.repository.UserRepository;
import com.abishek.ecommercewebsiteapiproject.users.service.UserService;
import com.abishek.ecommercewebsiteapiproject.users.model.*;

@RestController
@CrossOrigin
@RequestMapping("/laptopstore/api/v1")
public class UserController {
	
	
	private UserRepository userrepository;
	
	private UserService userservice;
	
	private BCryptPasswordEncoder bcryptpasswordencoder;

    private AuthenticationManager authenticationManager;

    private JwtService jwtService;

    public UserController(UserRepository userrepository, UserService userservice, BCryptPasswordEncoder bcryptpasswordencoder, AuthenticationManager authenticationManager,JwtService jwtService) {
        this.userrepository = userrepository;
        this.userservice = userservice;
        this.bcryptpasswordencoder = bcryptpasswordencoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    //method to get all users
//	@RequestMapping(value="/user", method=RequestMethod.GET)
	@GetMapping("/user")
	public List<User> getAllUsers(){
		return userservice.getAllUsers();
		
	}
	
	//method to add user
//	@RequestMapping(value="/user", method=RequestMethod.POST)
	@PostMapping("/user")
	public ResponseEntity<Object> addNewUser(@RequestBody User user) {

        user.setPassword(bcryptpasswordencoder.encode(user.getPassword()));
		User saved = userservice.addnewuser(user);
		return new ResponseEntity<>(saved,HttpStatus.CREATED);
	}
	
	//method to authenticate for login
//	@RequestMapping(value="/login", method=RequestMethod.POST)
	@PostMapping("/login")
	public String authenticateUser(@RequestBody User user ) {

        Authentication  authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));

        if(authentication.isAuthenticated()){


            return jwtService.generateToken(user.getUsername());
        }

        return "Error";
		

	}

    @GetMapping("/hello")
    private  String greet(){

        return "Hello";
    }
	

}
