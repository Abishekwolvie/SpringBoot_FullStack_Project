package com.abishek.ecommercewebsiteapiproject.products.service;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.abishek.ecommercewebsiteapiproject.products.productdto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.abishek.ecommercewebsiteapiproject.products.model.Brands;
import com.abishek.ecommercewebsiteapiproject.products.model.Product;
import com.abishek.ecommercewebsiteapiproject.products.repository.ProductRepository;

@Service
public class ProductService {
	

	ProductRepository productrepository;
	JdbcTemplate jdbctemplate;

	public ProductService(ProductRepository productrepository,JdbcTemplate jdbctemplate) {
		super();
		this.productrepository = productrepository;
		this.jdbctemplate = jdbctemplate;
	}
	
	public List<ProductDto> getAllProducts(){

        List<Product> products = productrepository.findAll();

        List<ProductDto> productdto = products.stream().map((product -> new ProductDto(
                product.getPrice(), product.isIsavailable(), product.getNoofunits(), product.getStorage(),
                product.getProcessor(), product.getOs(), product.getBrand(), product.getModel(), product.getGraphicscard(),
                product.getImageName(), product.getImageType(), product.getImage(),product.getId()
        ))).collect(Collectors.toList());

        return productdto;
	}
	
	public Product addProducts(Product product) {
		
		return productrepository.save(product);
		
	}
	
	public Optional<ProductDto> findProductById(int id) {
		
		 Optional<Product> product = productrepository.findById(id);


        Optional<ProductDto> productdto = product.map(productmap -> new ProductDto(productmap.getPrice(), productmap.isIsavailable(), productmap.getNoofunits(), productmap.getStorage(),
                 productmap.getProcessor(), productmap.getOs(), productmap.getBrand(), productmap.getModel(), productmap.getGraphicscard(),
                 productmap.getImageName(), productmap.getImageType(), productmap.getImage(), productmap.getId()));



		 
		 return productdto;
	}
	
	public List<Product> findProductByBrandName(String brandname){
		
		return productrepository.findAllByBrand(brandname);
	}
	
	public List<Product> findProductByPriceRange(int minrange,int maxrange){
		
//		RowMapper productrowmapper  = (ResultSet rs, int rowNum) ->{
//			Product product = new Product();
//			product.setId(rs.getInt("id"));
//			product.setBrand(rs.getString("brand"));
//			product.setIsavailable(rs.getBoolean("isavailable"));
//			product.setModel(rs.getString("model"));
//			product.setNoofunits(rs.getInt("noofunits"));
//			product.setOs(rs.getString("os"));
//			product.setPrice(rs.getLong("price"));
//			
//			return product;
//		};
//		
//		return jdbctemplate.query("Select * from product where price between ? and ?",productrowmapper,minrange,maxrange);
		
		return productrepository.findByPriceBetween(minrange, maxrange);
	}
	
	public List<Brands> getbrands(){
		RowMapper brand  = (ResultSet rs, int rowNum) ->{
			Brands brands = new Brands();
			
			brands.setBrandname(rs.getString("brand"));
			return brands;
		};
		return jdbctemplate.query("select brand from product",brand);
	}
	
	public Product updateProduct(Product product) {
		
		return productrepository.save(product);
	}
	
	public void deleteproduct(int id) {
		
		 productrepository.deleteById(id);
		
		
	}
	
	public List<Product> searchbykeyword(String keyword){
		return productrepository.searchforproduct(keyword);
	}
	

}
