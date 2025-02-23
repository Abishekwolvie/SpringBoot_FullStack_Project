package com.abishek.ecommercewebsiteapiproject.products;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer>{
	
	
	public List<Product> findAllByBrand(String brandname);
}
