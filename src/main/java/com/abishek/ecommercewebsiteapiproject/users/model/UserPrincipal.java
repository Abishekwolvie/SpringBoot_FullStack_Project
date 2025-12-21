package com.abishek.ecommercewebsiteapiproject.users.model;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails{
	
	private User user;
	
	

	public UserPrincipal(User user) {
		super();
		this.user = user;
	}


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      return  Collections.singleton(new SimpleGrantedAuthority("ROLE_" + user.getRole().toUpperCase()));
    }

    @Override
    public String getPassword() {
//        System.out.println(user.getPassword());
        return user.getPassword();
    }

    @Override
    public String getUsername() {

        return user.getUsername();
    }


}
