package com.abishek.ecommercewebsiteapiproject.orders.service;

import com.abishek.ecommercewebsiteapiproject.cart.CartRepository;
import com.abishek.ecommercewebsiteapiproject.orders.exceptions.OrderNotFoundException;
import com.abishek.ecommercewebsiteapiproject.orders.model.*;
import com.abishek.ecommercewebsiteapiproject.orders.repo.OrderItemRepository;
import com.abishek.ecommercewebsiteapiproject.orders.repo.OrderRepository;
import com.abishek.ecommercewebsiteapiproject.products.model.Product;
import com.abishek.ecommercewebsiteapiproject.products.service.ProductService;
import com.abishek.ecommercewebsiteapiproject.users.model.User;
import com.abishek.ecommercewebsiteapiproject.users.repository.UserRepository;
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
    private CartRepository cartRepository;

    public OrderService(OrderRepository orderRepository,UserRepository userRepository,ProductService productService,OrderItemRepository orderItemRepository,CartRepository cartRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productService = productService;
        this.orderItemRepository = orderItemRepository;
        this.cartRepository= cartRepository;
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

            List<String> cartids = orderDTOList.stream().map(OrderDTO::cartid).collect(Collectors.toList());

            cartRepository.deleteAllByCartitemidIn(cartids);



            return orderItems;


        }

        return  new ArrayList<>();


    }

    public List<OrderByUserIdDTO> findordersbyuserid(String userid){

        User user = userRepository.findById(userid).orElseThrow(()->new UsernameNotFoundException("Username not found"));

        List<OrderTbl> orderlistbyuserid = orderRepository.findAllByUserid(user);

        List<OrderByUserIdDTO> orderdetaillist =  orderlistbyuserid.stream().map((order)->new OrderByUserIdDTO(order.getOrderid(),order.getOrderprice(),order.getUserid().getId(),
                order.getDateTime())).collect(Collectors.toList());

        return orderdetaillist;
    }


    public List<OrderItemDto> findorderitembyorderid(String orderid){

        OrderTbl orderTbl= orderRepository.findById(orderid).orElseThrow(()->new OrderNotFoundException("Order with the id "+orderid+" not found"));

        List<OrderItem> orderitems = orderItemRepository.findAllByOrderidval(orderTbl);

        User usermobile = userRepository.findById(orderTbl.getUserid().getId()).orElseThrow(()->new UsernameNotFoundException("Username not found"));

        List<OrderItemDto> orderitemlist = orderitems.stream().map((orderitem) -> new OrderItemDto(orderitem.getOrderitemid(), orderitem.getProductdescription()
                , orderitem.getProductprice(), orderitem.getOrderidval().getOrderid(),orderTbl.getDateTime(),usermobile.getMobile())).collect(Collectors.toList());


        return orderitemlist;
    }
}
