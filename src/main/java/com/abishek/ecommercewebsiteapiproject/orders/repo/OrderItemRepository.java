package com.abishek.ecommercewebsiteapiproject.orders.repo;

import com.abishek.ecommercewebsiteapiproject.orders.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,String> {
}
