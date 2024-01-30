package com.fredd.TextilHugo_web.services;

import com.fredd.TextilHugo_web.model.dtos.request.CreateInventarioDto;
import com.fredd.TextilHugo_web.model.entities.Inventario;

import java.util.List;
import java.util.Optional;

public interface IinventarioService {
    List<Inventario> getAllInventory();
    Optional<Inventario> getInventoryById(Long inventoryId);
    Inventario addInventory(CreateInventarioDto newInventory);
    void deleteInventoryById(Long inventoryId);

    void updateInventory(Inventario existingInventory);
}
