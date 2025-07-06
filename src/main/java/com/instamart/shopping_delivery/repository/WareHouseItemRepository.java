package com.instamart.shopping_delivery.repository;

import com.instamart.shopping_delivery.models.WarehouseItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WareHouseItemRepository extends JpaRepository<WarehouseItem, UUID> {
}
