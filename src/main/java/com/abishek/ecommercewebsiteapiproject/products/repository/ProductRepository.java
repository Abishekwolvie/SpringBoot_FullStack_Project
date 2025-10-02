package com.abishek.ecommercewebsiteapiproject.products.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.abishek.ecommercewebsiteapiproject.products.model.Product;

public interface ProductRepository extends JpaRepository<Product,Integer>{
	
	
	public List<Product> findAllByBrand(String brandname);
	
	
	//SELECT P FROM Product  p where concat(Replace(P.brand,' ',''),Replace(P.model,' ','')) like ?1%"
	@Query("SELECT p FROM Product  p where concat(Replace(p.brand,' ',''),Replace(p.model,' ','')) like ?1%")
	public List<Product> searchforproduct(String keyword);
	
	public List<Product> findByPriceBetween(int minrange,int maxrange);
}
