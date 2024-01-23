package com.fredd.TextilHugo_web.services.impl;

import com.fredd.TextilHugo_web.exceptions.BadRequestException;
import com.fredd.TextilHugo_web.exceptions.ResourceNotFoundException;
import com.fredd.TextilHugo_web.model.dtos.PurchaseRequest;
import com.fredd.TextilHugo_web.model.entities.Inventory;
import com.fredd.TextilHugo_web.model.entities.Purchase;
import com.fredd.TextilHugo_web.model.repositories.IPurchaseRepository;
import com.fredd.TextilHugo_web.services.IPurchaseService;
import com.fredd.TextilHugo_web.services.IinventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements IPurchaseService {

    private final IPurchaseRepository purchaseRepository;
    private final IinventoryService inventoryService;

    @Override
    public Purchase addPurchase(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    @Override
    public Purchase impactarCompra(PurchaseRequest purchaseRequest) {
        Optional<Inventory> inventory = inventoryService.getInventoryById(purchaseRequest.getInventoryId());
        if (inventory.isEmpty()) {
            throw new ResourceNotFoundException("inventario", "id", purchaseRequest.getInventoryId());
        }

        Inventory inventoryExist = inventory.get();
        if (inventoryExist.getQuantity() < purchaseRequest.getQuantity()) {
            throw new BadRequestException("Stock insuficiente para la prenda");
        }

        // Actualizar inventario
        inventoryExist.setQuantity(inventoryExist.getQuantity() - purchaseRequest.getQuantity());
        inventoryService.addInventory(inventoryExist);

        // Crear y guardar compra
        Purchase purchase = new Purchase(purchaseRequest.getQuantity(), inventoryExist.getUnitPrice(), inventoryExist);
        return addPurchase(purchase);
    }
}
