package com.fredd.TextilHugo_web.controllers;

import com.fredd.TextilHugo_web.exceptions.BadRequestException;
import com.fredd.TextilHugo_web.exceptions.ResourceNotFoundException;
import com.fredd.TextilHugo_web.model.dtos.PurchaseRequest;
import com.fredd.TextilHugo_web.model.entities.Inventory;
import com.fredd.TextilHugo_web.model.entities.Purchase;
import com.fredd.TextilHugo_web.services.IPurchaseService;
import com.fredd.TextilHugo_web.services.IinventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final IinventoryService inventoryService;
    private final IPurchaseService purchaseService;

    @GetMapping()
    public ResponseEntity<?> getAllInventories() {
        List<Inventory> inventories = inventoryService.getAllInventory();
        if (inventories == null || inventories.isEmpty()) {
            throw new ResourceNotFoundException("inventario");
        }
        return new ResponseEntity<>(inventories, HttpStatus.OK);
    }

    @GetMapping("/{inventoryId}")
    public ResponseEntity<?> getInventoryById(@PathVariable Long inventoryId) {

        Optional<Inventory> inventory = inventoryService.getInventoryById(inventoryId);

        if (inventory.isEmpty()) {
            throw new ResourceNotFoundException("inventario", "id", inventoryId);
        }

        return new ResponseEntity<>(inventory, HttpStatus.OK);

    }

    @PostMapping()
    public ResponseEntity<?> addInventory(@RequestBody @Valid Inventory inventory) {
        Inventory newInventory;
        try {
            newInventory = inventoryService.addInventory(inventory);
        } catch (DataAccessException exDt) {
            throw new BadRequestException(exDt.getMessage());
        }
        return new ResponseEntity<>(newInventory, HttpStatus.CREATED);
    }

    @PutMapping("/{inventoryId}")
    public void updateInventory(@RequestBody @Valid Inventory inventory, @PathVariable Long inventoryId) {

        try {
            Optional<Inventory> optionalInventory = inventoryService.getInventoryById(inventoryId);

            if (optionalInventory.isPresent()) {
                Inventory existingInventory = optionalInventory.get();
                existingInventory.setQuantity(inventory.getQuantity());
                existingInventory.setUnitPrice(inventory.getUnitPrice());
                existingInventory.setClothing(inventory.getClothing());

                inventoryService.addInventory(existingInventory);

                ResponseEntity.ok(existingInventory);

            } else {
                throw new ResourceNotFoundException("inventario", "id", inventoryId);
            }
        } catch (DataAccessException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @DeleteMapping("/{inventoryId}")
    public ResponseEntity<?> deleteInventory(@PathVariable Long inventoryId) {
        try {
            Optional<Inventory> inventoryDelete = inventoryService.getInventoryById(inventoryId);
            if (inventoryDelete.isPresent()) {
                Inventory inventory = inventoryDelete.get();
                inventoryService.deleteInventoryById(inventory.getId());

                return new ResponseEntity<>(inventoryDelete, HttpStatus.NO_CONTENT);
            } else {
                throw new ResourceNotFoundException("inventario", "id", inventoryId);
            }
        } catch (DataAccessException exDt) {
            throw new BadRequestException(exDt.getMessage());
        }
    }

    @PostMapping("/buy")
    public ResponseEntity<?> impactarCompra(@RequestBody @Valid PurchaseRequest purchaseRequest) {

        try {
            Purchase effectedPurchase = purchaseService.impactarCompra(purchaseRequest);
            return ResponseEntity.ok(effectedPurchase);
        } catch (ResourceNotFoundException | BadRequestException e) {
            throw e; // Reenvía las excepciones específicas
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage()); // Envuelve otras excepciones en BadRequestException
        }
    }
}
