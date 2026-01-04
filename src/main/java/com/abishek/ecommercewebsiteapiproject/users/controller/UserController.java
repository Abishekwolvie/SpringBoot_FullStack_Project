package com.abishek.ecommercewebsiteapiproject.users.controller;

import java.util.List;
import java.util.Optional;

import com.abishek.ecommercewebsiteapiproject.service.JwtService;
import com.abishek.ecommercewebsiteapiproject.users.exceptions.ExistingUserException;
import com.abishek.ecommercewebsiteapiproject.users.service.UserDetailsServiceImpl;
import com.abishek.ecommercewebsiteapiproject.users.userdto.ApiResponse;
import com.abishek.ecommercewebsiteapiproject.users.userdto.LoginDto;
import com.abishek.ecommercewebsiteapiproject.users.userdto.UserDto;
import jakarta.validation.Valid;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

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
	@GetMapping("/user")
	public List<User> getAllUsers(){
		return userservice.getAllUsers();
		
	}
	
	//method to add user
	@PostMapping("/register")
	public ResponseEntity<Object> addNewUser(@RequestBody @Valid UserDto userdto) {

        User user = new User(userdto.username(),userdto.password(),userdto.mobile(),userdto.role());

        Optional<User> existinguser = userservice.finduserbyemail(user);

        if(existinguser.isPresent()){
            throw new ExistingUserException("This email "+ existinguser.get().getUsername() + "is already registered with another user");
        }

        user.setPassword(bcryptpasswordencoder.encode(user.getPassword()));
		User saved = userservice.addnewuser(user);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	//method to authenticate for login
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody @Valid LoginDto loginDto ) {


        //This method throws the BadCredentialsException
        Authentication  authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.username(),loginDto.password()));

        if(authentication.isAuthenticated()){
            return new ResponseEntity<>(new ApiResponse(jwtService.generateToken(loginDto.username())),HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		

	}

    @GetMapping("/finduseridbyusername/{username}")
    public ResponseEntity<?> getuseridbyusername(@PathVariable String username){

        if(username!=null){
         String userid =   userservice.getuserid(username);

         return new ResponseEntity<>(userid,HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


	

}
