package com.abishek.ecommercewebsiteapiproject.users;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UserResource {
	
	
	private UserRepository userrepository;
	
	public UserResource(UserRepository userrepository) {
		super();
		this.userrepository = userrepository;
	}

	//method to get all users
	@RequestMapping(value="/user", method=RequestMethod.GET)
	public List<User> getAllUsers(){
		return userrepository.findAll();
	}
	
	//method to add user
	@RequestMapping(value="/user", method=RequestMethod.POST)
	public ResponseEntity<Object> addNewUser(@RequestBody User user) {
		User saved = userrepository.save(user);
		return ResponseEntity.created(null).build();
	}
	
	//method to authenticate for login
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public int authenticateUser(@RequestBody User user ) {
		
//		System.out.println(user.getEmail());
//		System.out.println(user.getPassword());
		User findByEmail = userrepository.findByEmail(user.getEmail());
		if(findByEmail!=null) {
			if(user.getEmail().equals(findByEmail.getEmail()) && user.getPassword().equals(findByEmail.getPassword())) {
				return 1;
			}else{
				return 0;
			}
		}
//		System.out.println(findByEmail.toString());
		return 0;
	}
	

}
