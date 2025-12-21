package com.abishek.ecommercewebsiteapiproject.orders.repo;

import com.abishek.ecommercewebsiteapiproject.orders.model.OrderTbl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderTbl,String> {
}
