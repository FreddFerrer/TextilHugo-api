package com.fredd.TextilHugo_web.services.impl;

import com.fredd.TextilHugo_web.exceptions.BadRequestException;
import com.fredd.TextilHugo_web.exceptions.ResourceNotFoundException;
import com.fredd.TextilHugo_web.model.dtos.CompraRequest;
import com.fredd.TextilHugo_web.model.entities.Inventario;
import com.fredd.TextilHugo_web.model.entities.Compra;
import com.fredd.TextilHugo_web.model.repositories.ICompraRepository;
import com.fredd.TextilHugo_web.services.ICompraService;
import com.fredd.TextilHugo_web.services.IinventarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompraServiceImpl implements ICompraService {

    private final ICompraRepository purchaseRepository;
    private final IinventarioService inventoryService;

    @Override
    public Compra addPurchase(Compra purchase) {
        return purchaseRepository.save(purchase);
    }

    @Override
    public Compra impactarCompra(CompraRequest purchaseRequest) {
        Optional<Inventario> inventory = inventoryService.getInventoryById(purchaseRequest.getInventoryId());
        if (inventory.isEmpty()) {
            throw new ResourceNotFoundException("inventario", "id", purchaseRequest.getInventoryId());
        }

        Inventario inventoryExist = inventory.get();
        if (inventoryExist.getQuantity() < purchaseRequest.getQuantity()) {
            throw new BadRequestException("Stock insuficiente para la prenda");
        }

        // Actualizar inventario
        inventoryExist.setQuantity(inventoryExist.getQuantity() - purchaseRequest.getQuantity());
        inventoryService.addInventory(inventoryExist);

        // Crear y guardar compra
        Compra purchase = new Compra(purchaseRequest.getQuantity(), inventoryExist.getUnitPrice(), inventoryExist);
        return addPurchase(purchase);
    }
}
