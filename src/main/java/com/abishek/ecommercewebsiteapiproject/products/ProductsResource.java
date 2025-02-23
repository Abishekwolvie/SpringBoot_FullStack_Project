package com.abishek.ecommercewebsiteapiproject.products;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


class ProductRowMapper implements RowMapper{

	@Override
	public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
		Product product = new Product();
		product.setId(rs.getInt("id"));
		product.setBrand(rs.getString("brand"));
		product.setIsavailable(rs.getBoolean("isavailable"));
		product.setModel(rs.getString("model"));
		product.setNoofunits(rs.getInt("noofunits"));
		product.setOs(rs.getString("os"));
		product.setPrice(rs.getLong("price"));
		
		return product;
	}


	
}

@RestController
@CrossOrigin
public class ProductsResource {
	
	ProductRepository productrepository;
	
	JdbcTemplate jdbcTemplate;
	
	public ProductsResource(ProductRepository productrepository, JdbcTemplate jdbcTemplate) {
		super();
		this.productrepository = productrepository;
		this.jdbcTemplate = jdbcTemplate;
	}

	//get all products
	@RequestMapping(value="/products", method=RequestMethod.GET)
	public List<Product> getAllProducts(){
		
		return productrepository.findAll();
	}

	//add a product
	@RequestMapping(value="/products", method=RequestMethod.POST)
	public ResponseEntity<Object> addProducts(@RequestBody Product product){
		
		Product addedproduct = productrepository.save(product);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{productid}").
		buildAndExpand(addedproduct.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}

	//get a product by id
	@RequestMapping(value="/products/{productid}", method=RequestMethod.GET)
	public Product findProductById(@PathVariable String productid) {
		
		Optional<Product> productfindById = productrepository.findById(Integer.parseInt(productid));
		Product product = productfindById.get();
		System.out.println(product);
		
		return product;
		
	}
	
	@RequestMapping(value="/products/brands/{brandname}", method=RequestMethod.GET)
	public List<Product> findProductByBrandName( @PathVariable String brandname){
		
		List<Product> productsByBrand = productrepository.findAllByBrand(brandname);
		if(productsByBrand == null) {
			//return new ArrayList<Product>();
			return (List<Product>) ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<Product>());
		}
		return productsByBrand;
	}
	
	@RequestMapping(value="/products/filterbypricerange/{minrange}/{maxrange}", method=RequestMethod.GET)
	public List<Product> findProductByPriceRange(@PathVariable String minrange,@PathVariable String maxrange){
		
		RowMapper productrowmapper  = (ResultSet rs, int rowNum) ->{
			Product product = new Product();
			product.setId(rs.getInt("id"));
			product.setBrand(rs.getString("brand"));
			product.setIsavailable(rs.getBoolean("isavailable"));
			product.setModel(rs.getString("model"));
			product.setNoofunits(rs.getInt("noofunits"));
			product.setOs(rs.getString("os"));
			product.setPrice(rs.getLong("price"));
			
			return product;
		};
		
		
		
		//@SuppressWarnings("unchecked")
		List<Product> productsbyprice = jdbcTemplate.query("Select * from product where price between ? and ?",productrowmapper,minrange,maxrange);
		//List<Product> productsbyprice = jdbcTemplate.query("Select * from product",productrowmapper);
		//System.out.println(productsbyprice);
		if(productsbyprice==null) {
			return (List<Product>) ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<Product>());
		}
		
		return productsbyprice;
		
	}
	
	@RequestMapping(value="/products/brands", method=RequestMethod.GET)
	public List<Brands> getbrands(){
		RowMapper brand  = (ResultSet rs, int rowNum) ->{
			Brands brands = new Brands();
			
			brands.setBrandname(rs.getString("brand"));
			return brands;
		};
		return jdbcTemplate.query("select brand from product",brand);
	}

}
