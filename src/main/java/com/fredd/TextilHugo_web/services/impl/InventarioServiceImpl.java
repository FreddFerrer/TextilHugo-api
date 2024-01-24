package com.fredd.TextilHugo_web.services.impl;

import com.fredd.TextilHugo_web.model.entities.Inventario;
import com.fredd.TextilHugo_web.model.repositories.IinventarioRepository;
import com.fredd.TextilHugo_web.services.IinventarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventarioServiceImpl implements IinventarioService {

    private final IinventarioRepository inventoryRepository;

    @Override
    public List<Inventario> getAllInventory() {
        return inventoryRepository.findAll();
    }

    @Override
    public Optional<Inventario> getInventoryById(Long inventoryId) {
        return inventoryRepository.findById(inventoryId);
    }

    @Override
    public Inventario addInventory(Inventario newInventory) {
        return inventoryRepository.save(newInventory);
    }

    @Override
    public void deleteInventoryById(Long inventoryId) {
        inventoryRepository.deleteById(inventoryId);
    }
}
