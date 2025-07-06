package com.instamart.shopping_delivery.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "warehouse")
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    String name;

    @OneToOne
    AppUser manager;

    @OneToOne
    Location location;

    @OneToMany
    List<WarehouseItem> warehouseItems;

    LocalDateTime createdAt;
    LocalDateTime updatedBy;
}
