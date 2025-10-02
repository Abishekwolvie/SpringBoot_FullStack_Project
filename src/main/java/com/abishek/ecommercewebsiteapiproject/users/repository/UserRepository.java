package com.abishek.ecommercewebsiteapiproject.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abishek.ecommercewebsiteapiproject.users.model.User;

public interface UserRepository extends JpaRepository<User, Long>
{
	
	 User findByUsername(String email);
}
