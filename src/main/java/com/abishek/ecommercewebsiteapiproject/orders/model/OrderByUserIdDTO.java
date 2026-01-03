package com.abishek.ecommercewebsiteapiproject.orders.model;

import java.time.LocalDateTime;

public record OrderByUserIdDTO(String orderid, long orderprice, String userid, LocalDateTime datetime) {
}
