package com.instamart.shopping_delivery.models;


import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "cartItems")
public class CartItems {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        UUID id;
        UUID cartId;
        UUID productId;
        int quantity;
        int price;
        int discount;

}
