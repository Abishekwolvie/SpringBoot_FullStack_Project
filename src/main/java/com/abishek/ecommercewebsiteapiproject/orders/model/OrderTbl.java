package com.abishek.ecommercewebsiteapiproject.orders.model;

import com.abishek.ecommercewebsiteapiproject.products.model.Product;
import com.abishek.ecommercewebsiteapiproject.users.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderTbl {
    @Override
    public String toString() {
        return "OrderTbl{" +
                "orderprice=" + orderprice +
                ", userid=" + userid +
                ", dateTime=" + dateTime +
                '}';
    }

    public OrderTbl(LocalDateTime dateTime, User userid, long orderprice) {
        this.dateTime = dateTime;
        this.userid = userid;
        this.orderprice = orderprice;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String orderid;

    private long orderprice;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User userid;

    @OneToMany(mappedBy = "orderidval")
    private List<OrderItem> orderItem;

    private LocalDateTime dateTime;

}
