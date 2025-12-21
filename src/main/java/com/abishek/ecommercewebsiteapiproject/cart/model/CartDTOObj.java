package com.abishek.ecommercewebsiteapiproject.cart.model;

import com.abishek.ecommercewebsiteapiproject.products.model.Product;

public record CartDTOObj(long price, String cartid,String productid) {
}
