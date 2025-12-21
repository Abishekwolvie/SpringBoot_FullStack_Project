package com.abishek.ecommercewebsiteapiproject.orders.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String orderitemid;

    private String productdescription;

    private long productprice;

    public OrderItem(OrderTbl orderidval, long productprice, String productdescription) {
        this.orderidval = orderidval;
        this.productprice = productprice;
        this.productdescription = productdescription;
    }

    @ManyToOne
    @JoinColumn(name="order_id")
    private OrderTbl orderidval;

    @Override
    public String toString() {
        return "OrderItem{" +
                "orderitemid='" + orderitemid + '\'' +
                ", productdescription='" + productdescription + '\'' +
                ", productprice=" + productprice +
                ", orderidval=" + orderidval +
                '}';
    }
}
