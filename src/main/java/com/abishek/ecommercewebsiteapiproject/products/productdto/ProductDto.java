package com.abishek.ecommercewebsiteapiproject.products.productdto;

public record ProductDto(long price,boolean isavailable,int noofunits,int storage,String processor,String os,
                  String brand,String model, String graphicscard,String imageName,String imageType,byte[] image,int id){

}
