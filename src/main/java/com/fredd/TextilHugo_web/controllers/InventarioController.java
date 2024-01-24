package com.fredd.TextilHugo_web.controllers;

import com.fredd.TextilHugo_web.exceptions.BadRequestException;
import com.fredd.TextilHugo_web.exceptions.ResourceNotFoundException;
import com.fredd.TextilHugo_web.model.dtos.CompraRequest;
import com.fredd.TextilHugo_web.model.entities.Inventario;
import com.fredd.TextilHugo_web.model.entities.Compra;
import com.fredd.TextilHugo_web.services.ICompraService;
import com.fredd.TextilHugo_web.services.IinventarioService;
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
public class InventarioController {

    private final IinventarioService inventoryService;
    private final ICompraService purchaseService;

    @GetMapping()
    public ResponseEntity<?> getAllInventories() {
        List<Inventario> inventories = inventoryService.getAllInventory();
        if (inventories == null || inventories.isEmpty()) {
            throw new ResourceNotFoundException("inventario");
        }
        return new ResponseEntity<>(inventories, HttpStatus.OK);
    }

    @GetMapping("/{inventoryId}")
    public ResponseEntity<?> getInventoryById(@PathVariable Long inventoryId) {

        Optional<Inventario> inventory = inventoryService.getInventoryById(inventoryId);

        if (inventory.isEmpty()) {
            throw new ResourceNotFoundException("inventario", "id", inventoryId);
        }

        return new ResponseEntity<>(inventory, HttpStatus.OK);

    }

    @PostMapping()
    public ResponseEntity<?> addInventory(@RequestBody @Valid Inventario inventory) {
        Inventario newInventory;
        try {
            newInventory = inventoryService.addInventory(inventory);
        } catch (DataAccessException exDt) {
            throw new BadRequestException(exDt.getMessage());
        }
        return new ResponseEntity<>(newInventory, HttpStatus.CREATED);
    }

    @PutMapping("/{inventoryId}")
    public void updateInventory(@RequestBody @Valid Inventario inventory, @PathVariable Long inventoryId) {

        try {
            Optional<Inventario> optionalInventory = inventoryService.getInventoryById(inventoryId);

            if (optionalInventory.isPresent()) {
                Inventario existingInventory = optionalInventory.get();
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
            Optional<Inventario> inventoryDelete = inventoryService.getInventoryById(inventoryId);
            if (inventoryDelete.isPresent()) {
                Inventario inventory = inventoryDelete.get();
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
    public ResponseEntity<?> impactarCompra(@RequestBody @Valid CompraRequest purchaseRequest) {

        try {
            Compra effectedPurchase = purchaseService.impactarCompra(purchaseRequest);
            return ResponseEntity.ok(effectedPurchase);
        } catch (ResourceNotFoundException | BadRequestException e) {
            throw e; // Reenvía las excepciones específicas
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage()); // Envuelve otras excepciones en BadRequestException
        }
    }
}
