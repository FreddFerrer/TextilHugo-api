package com.fredd.TextilHugo_web.services;

import com.fredd.TextilHugo_web.model.entities.Inventory;

import java.util.List;
import java.util.Optional;

public interface IinventoryService {
    List<Inventory> getAllInventory();
    Optional<Inventory> getInventoryById(Long inventoryId);
    Inventory addInventory(Inventory newInventory);
    void deleteInventoryById(Long inventoryId);
}
