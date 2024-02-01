package com.fredd.TextilHugo_web.services;

import com.fredd.TextilHugo_web.model.dtos.InventarioDto;
import com.fredd.TextilHugo_web.model.dtos.request.CreateInventarioDto;
import com.fredd.TextilHugo_web.model.dtos.request.EditInventarioDto;
import com.fredd.TextilHugo_web.model.entities.Inventario;

import java.util.List;
import java.util.Optional;

public interface IinventarioService {
    List<Inventario> getAllInventory();

    Optional<InventarioDto> getInventarioDtoById(Long inventarioId);
    Optional<Inventario> getInventarioById(Long inventarioId);

    Inventario addInventory(CreateInventarioDto newInventory);
    void deleteInventoryById(Long inventoryId);

    void saveInventory(Inventario existingInventory);


    void updateInventory(EditInventarioDto createInventoryDto, Long inventarioId);
}
