package com.abishek.ecommercewebsiteapiproject.orders.service;

import com.abishek.ecommercewebsiteapiproject.orders.model.OrderDTO;
import com.abishek.ecommercewebsiteapiproject.orders.model.OrderItem;
import com.abishek.ecommercewebsiteapiproject.orders.model.OrderTbl;
import com.abishek.ecommercewebsiteapiproject.orders.repo.OrderItemRepository;
import com.abishek.ecommercewebsiteapiproject.orders.repo.OrderRepository;
import com.abishek.ecommercewebsiteapiproject.products.model.Product;
import com.abishek.ecommercewebsiteapiproject.products.service.ProductService;
import com.abishek.ecommercewebsiteapiproject.users.model.User;
import com.abishek.ecommercewebsiteapiproject.users.repository.UserRepository;
import org.hibernate.query.Order;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private UserRepository userRepository;
    private ProductService productService;
    private OrderItemRepository orderItemRepository;

    public OrderService(OrderRepository orderRepository,UserRepository userRepository,ProductService productService,OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productService = productService;
        this.orderItemRepository = orderItemRepository;
    }

    public List<OrderItem> addorder(List<OrderDTO> orderDTOList){

        long orderpice = orderDTOList.stream().map(OrderDTO::productprice).reduce(0l, (n1, n2) -> n1 + n2);

        String userid = orderDTOList.stream().map(OrderDTO::userid).findFirst().orElseThrow(()->new UsernameNotFoundException("No Userid"));

        User user = userRepository.findById(userid).orElseThrow(()->new UsernameNotFoundException("User not found"));

        OrderTbl orderTbl = new OrderTbl(LocalDateTime.now(),user,orderpice);

        OrderTbl saveorder = orderRepository.save(orderTbl);
        if(saveorder!=null){

            List<String> productids = orderDTOList.stream().map(OrderDTO::productid).collect(Collectors.toList());

            List<Product> products = productService.findproductbyids(productids);


            List<OrderItem> orderitemlist = products.stream().map((val) -> new OrderItem(saveorder, val.getPrice(),
                    val.getBrand() + " | " + val.getModel() + " | " + val.getOs() + " | " + val.getProcessor() + " | "
                            + val.getGraphicscard() + " | " + val.getStorage() + " | " + val.getRam() + " Ram")).collect(Collectors.toList());

            List<OrderItem> orderItems = orderItemRepository.saveAll(orderitemlist);

            return orderItems;


        }

        return  new ArrayList<>();


    }
}
