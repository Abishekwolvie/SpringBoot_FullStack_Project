package com.abishek.ecommercewebsiteapiproject.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abishek.ecommercewebsiteapiproject.users.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String>
{

	 Optional<User> findByUsername(String email);
}
