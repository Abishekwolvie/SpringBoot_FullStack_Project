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
                product.getId(),product.getRam()
        ))).collect(Collectors.toList());

        return productdto;
	}
	
	public ProductDto addProducts(ProductDto productdto) {
        Product product = new Product(productdto.price(),productdto.isavailable(),productdto.noofunits(),
                productdto.storage(),productdto.processor(),productdto.os(),productdto.brand()
                ,productdto.model(),productdto.graphicscard(),productdto.ram());

        Product savedproduct = productrepository.save(product);

        ProductDto productDtoresponse = new ProductDto(savedproduct.getPrice(), savedproduct.isIsavailable(), savedproduct.getNoofunits(), savedproduct.getStorage(),
                savedproduct.getProcessor(), savedproduct.getOs(), savedproduct.getBrand(), savedproduct.getModel(), savedproduct.getGraphicscard(),
                savedproduct.getId(),savedproduct.getRam());

		return productDtoresponse;
		
	}
	
	public Optional<ProductDto> findProductById(String id) {
		
		 Optional<Product> product = productrepository.findById(id);


        Optional<ProductDto> productdto = product.map(productmap -> new ProductDto(productmap.getPrice(), productmap.isIsavailable(), productmap.getNoofunits(), productmap.getStorage(),
                 productmap.getProcessor(), productmap.getOs(), productmap.getBrand(), productmap.getModel(), productmap.getGraphicscard(),
                productmap.getId(),productmap.getRam()));

		 return productdto;
	}


    public Optional<Product> findProductByIdcartadd(String id) {
        Optional<Product> product = productrepository.findById(id);
        return product;
    }
	
	public List<Product> findProductByBrandName(String brandname){
		
		return productrepository.findAllByBrand(brandname);
	}
	
	public List<Product> findProductByPriceRange(int minrange,int maxrange){

		
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
	
	public void deleteproduct(String id) {
		
		 productrepository.deleteById(id);
		
		
	}
	
	public List<Product> searchbykeyword(String keyword){
		return productrepository.searchforproduct(keyword);
	}

    public List<Product> findproductbyids(List<String> productids){
        return productrepository.findByIdIn(productids);
    }
	

}
