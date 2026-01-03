package com.abishek.ecommercewebsiteapiproject.orders.repo;

import com.abishek.ecommercewebsiteapiproject.orders.model.OrderItem;
import com.abishek.ecommercewebsiteapiproject.orders.model.OrderTbl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem,String> {

    List<OrderItem> findAllByOrderidval(OrderTbl orderitemid);
}
