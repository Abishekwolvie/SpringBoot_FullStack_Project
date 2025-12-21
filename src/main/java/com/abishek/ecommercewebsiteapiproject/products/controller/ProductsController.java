package com.abishek.ecommercewebsiteapiproject.products.controller;

import java.net.URI;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import com.abishek.ecommercewebsiteapiproject.products.exceptions.ProductErrorResponse;
import com.abishek.ecommercewebsiteapiproject.products.exceptions.ProductNotFoundException;
import com.abishek.ecommercewebsiteapiproject.products.productdto.ProductDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.abishek.ecommercewebsiteapiproject.products.model.Brands;
import com.abishek.ecommercewebsiteapiproject.products.model.Product;
import com.abishek.ecommercewebsiteapiproject.products.repository.ProductRepository;
import com.abishek.ecommercewebsiteapiproject.products.service.ProductService;


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
	public ResponseEntity<List<ProductDto>>  getAllProducts(){
		List<ProductDto> allproducts = productservice.getAllProducts();
		if(allproducts.isEmpty()) {
			throw new ProductNotFoundException("Products not found");
		}
		return new ResponseEntity<>(allproducts,HttpStatus.OK);
	}

	//add a product)
	@PostMapping("/products")
	public ResponseEntity<ProductDto> addProducts(@RequestBody ProductDto productdto){


		ProductDto addedproduct = productservice.addProducts(productdto);

		return new ResponseEntity<>(productdto,HttpStatus.CREATED);
	}

	//get a product by id
	@GetMapping("/products/{productid}")
	public  ResponseEntity<ProductDto> findProductById(@PathVariable String productid) {

		
		Optional<ProductDto> product  = productservice.findProductById(productid);

        product.orElseThrow(()->new ProductNotFoundException("Product not found with the given id :"+productid));
		

		return new ResponseEntity<>(product.get(),HttpStatus.OK);
		
	}
	
	//get products by brand name
	@GetMapping("/products/brands/{brandname}")
	public ResponseEntity<List<Product>> findProductByBrandName( @PathVariable String brandname){
		
		List<Product> productsbybrand = productservice.findProductByBrandName(brandname);
		if(productsbybrand == null || productsbybrand.isEmpty()) {
			//return new ArrayList<Product>();
//
            throw new ProductNotFoundException("Product not found with the brand name: "+ brandname);
		}
		return new ResponseEntity<>(productsbybrand,HttpStatus.OK);
	}
	
	//get products by pricerange
	@GetMapping("/products/filterbypricerange/{minrange}/{maxrange}")
	public ResponseEntity<List<Product>> findProductByPriceRange(@PathVariable int minrange,@PathVariable int maxrange){
		
		//@SuppressWarnings("unchecked")
		List<Product> productsbyprice = productservice.findProductByPriceRange(minrange, maxrange);
		if(productsbyprice==null) {
			throw new ProductNotFoundException("Products not found within the range " + minrange +" and "+maxrange);
		}
		
		return new ResponseEntity<>(productsbyprice,HttpStatus.OK);
		
	}
	
	
	//get brands 
	@GetMapping("/products/brands")
	public ResponseEntity<List<Brands>> getbrands(){


		
		List<Brands> brands = productservice.getbrands();
		if(brands.isEmpty()) {
			throw new ProductNotFoundException("Brands not found");
		}
		
		return new ResponseEntity<>(brands,HttpStatus.OK);

	}
	
	@PutMapping("/products")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
		

			Product productupdated = productservice.updateProduct(product);
			
			return new ResponseEntity<Product>(productupdated,HttpStatus.ACCEPTED);

		
	}
	
	@DeleteMapping("/products/{id}")
	public ResponseEntity<Object> deleteproduct(@PathVariable String id){
		

			productservice.deleteproduct(id);
			//return (ResponseEntity<Object>) ResponseEntity.status(HttpStatus.NO_CONTENT);
			return ResponseEntity.noContent().build();
			

		
	}
	
	@GetMapping("products/searchbykeyword/{keyword}")
	public List<Product> searchbykeyword(@PathVariable String keyword){
		
		return productservice.searchbykeyword(keyword);
		
	}



}
