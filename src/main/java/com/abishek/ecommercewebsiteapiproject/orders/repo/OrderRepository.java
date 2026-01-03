package com.abishek.ecommercewebsiteapiproject.orders.repo;

import com.abishek.ecommercewebsiteapiproject.orders.model.OrderTbl;
import com.abishek.ecommercewebsiteapiproject.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderTbl,String> {

    List<OrderTbl> findAllByUserid(User user);
}
