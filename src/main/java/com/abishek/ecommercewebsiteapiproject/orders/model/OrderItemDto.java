package com.abishek.ecommercewebsiteapiproject.orders.model;

import java.time.LocalDateTime;

public record OrderItemDto(String orderitemid, String productdescription, long productprice, String orderid,
                           LocalDateTime dateTime,String usermobile) {
}
