package com.abishek.ecommercewebsiteapiproject.products.controller;

import java.net.URI;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.abishek.ecommercewebsiteapiproject.products.model.Brands;
import com.abishek.ecommercewebsiteapiproject.products.model.Product;
import com.abishek.ecommercewebsiteapiproject.products.repository.ProductRepository;
import com.abishek.ecommercewebsiteapiproject.products.service.ProductService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/laptopstore/api/v1")
@CrossOrigin
public class ProductsController {
	
	
	ProductRepository productrepository;
	
	JdbcTemplate jdbcTemplate;
	
	ProductService productservice;
	
	public ProductsController(ProductRepository productrepository, JdbcTemplate jdbcTemplate,ProductService productservice) {
		super();
		this.productrepository = productrepository;
		this.jdbcTemplate = jdbcTemplate;
		this.productservice = productservice;
	}

	//get all products
	@GetMapping("/products")
	public ResponseEntity<List<Product>>     getAllProducts(){
		
		
		return new ResponseEntity<>(productservice.getAllProducts(),HttpStatus.OK);
	}

	//add a product)
	@PostMapping("/products")
	public ResponseEntity<Object> addProducts(@RequestBody Product product){
		
		Product addedproduct = productservice.addProducts(product);
	
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{productid}").
		buildAndExpand(addedproduct.getId()).toUri();
		
		System.out.println(location);
		
		return ResponseEntity.created(location).build();
	}

	//get a product by id
	@GetMapping("/products/{productid}")
	public  ResponseEntity<Product> findProductById(@PathVariable int productid) {
		
		Product product  = productservice.findProductById(productid);
		
		if(product==null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(product,HttpStatus.OK);
		
	}
	
	//get products by brand name
	@GetMapping("/products/brands/{brandname}")
	public ResponseEntity<List<Product>> findProductByBrandName( @PathVariable String brandname){
		
		List<Product> productsbybrand = productservice.findProductByBrandName(brandname);
		if(productsbybrand == null) {
			//return new ArrayList<Product>();
			return new ResponseEntity<>(new ArrayList<Product>(),HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(productsbybrand,HttpStatus.OK);
	}
	
	//get products by pricerange
	@GetMapping("/products/filterbypricerange/{minrange}/{maxrange}")
	public ResponseEntity<List<Product>> findProductByPriceRange(@PathVariable int minrange,@PathVariable int maxrange){
		
		//@SuppressWarnings("unchecked")
		List<Product> productsbyprice = productservice.findProductByPriceRange(minrange, maxrange);
		if(productsbyprice==null) {
			return new ResponseEntity<>(new ArrayList<Product>(),HttpStatus.OK);
		}
		
		return new ResponseEntity<>(productsbyprice,HttpStatus.OK);
		
	}
	
	
	//get brands 
	@GetMapping("/products/brands")
	public ResponseEntity<List<Brands>> getbrands(){
		
		List<Brands> brands = productservice.getbrands();
		
		return new ResponseEntity<>(brands,HttpStatus.OK);

	}
	
	@PutMapping("/products")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
		
		try {
			Product productupdated = productservice.updateProduct(product);
			
			return new ResponseEntity<Product>(productupdated,HttpStatus.ACCEPTED);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	@DeleteMapping("/products/{id}")
	public ResponseEntity<Object> deleteproduct(@PathVariable int id){
		
		try {
			
			productservice.deleteproduct(id);
			//return (ResponseEntity<Object>) ResponseEntity.status(HttpStatus.NO_CONTENT);
			return ResponseEntity.noContent().build();
			
		}catch(Exception e) {
			
			return ResponseEntity.internalServerError().build();
			
		}	
		
	}
	
	@GetMapping("products/searchbykeyword/{keyword}")
	public List<Product> searchbykeyword(@PathVariable String keyword){
		
		return productservice.searchbykeyword(keyword);
		
	}

}
