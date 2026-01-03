package com.abishek.ecommercewebsiteapiproject.orders.controller;

import com.abishek.ecommercewebsiteapiproject.orders.model.OrderByUserIdDTO;
import com.abishek.ecommercewebsiteapiproject.orders.model.OrderDTO;
import com.abishek.ecommercewebsiteapiproject.orders.model.OrderItem;
import com.abishek.ecommercewebsiteapiproject.orders.model.OrderItemDto;
import com.abishek.ecommercewebsiteapiproject.orders.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/laptopstore/api/v1")
@CrossOrigin
public class OrderController {


    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/orderitem")
    public ResponseEntity<?> addtoorder(@RequestBody List<OrderDTO> orderDTOList){

        List<OrderItem> addorder = orderService.addorder(orderDTOList);

        if(addorder.isEmpty()){
            return new ResponseEntity<>("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/getordersbyuserid/{userid}")
    public ResponseEntity<?>  getOrdersbyUserid(@PathVariable String userid){

        List<OrderByUserIdDTO> orderitems = orderService.findordersbyuserid(userid);

        return new ResponseEntity<>(orderitems,HttpStatus.OK);

    }
    @GetMapping("/orderitem/{orderid}")
    public ResponseEntity<?> getOrderItemsByOrderid(@PathVariable String orderid){

        List<OrderItemDto> orderitems = orderService.findorderitembyorderid(orderid);

        return new ResponseEntity<>(orderitems,HttpStatus.OK);


    }
}
