package com.instamart.shopping_delivery.models;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "ordersItems")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    UUID orderId;
    UUID productId;
    int quantity;
    int totalAmount;

    LocalDateTime createdAt;
    LocalDateTime updatedBy;
}
