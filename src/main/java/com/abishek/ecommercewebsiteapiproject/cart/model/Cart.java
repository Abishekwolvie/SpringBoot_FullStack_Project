package com.abishek.ecommercewebsiteapiproject.cart.model;

import com.abishek.ecommercewebsiteapiproject.products.model.Product;

import com.abishek.ecommercewebsiteapiproject.users.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String cartitemid;

    public Cart(long price, User userid, Product productid, LocalDateTime dateTime) {
        this.price = price;
        this.userid = userid;
        this.productid = productid;
        this.dateTime = dateTime;
    }

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product productid;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User userid;
    private LocalDateTime dateTime;
    private long price;

    @Override
    public String toString() {
        return "Cart{" +
                "cartitemid='" + cartitemid + '\'' +
                ", productid=" + productid +
                ", userid=" + userid +
                ", dateTime=" + dateTime +
                ", price=" + price +
                '}';
    }
}
