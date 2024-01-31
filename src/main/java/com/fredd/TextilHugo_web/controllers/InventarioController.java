package com.fredd.TextilHugo_web.controllers;

import com.fredd.TextilHugo_web.exceptions.BadRequestException;
import com.fredd.TextilHugo_web.exceptions.ResourceNotFoundException;
import com.fredd.TextilHugo_web.model.dtos.request.CompraRequestDto;
import com.fredd.TextilHugo_web.model.dtos.request.CreateInventarioDto;
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
@RequestMapping("/api/v1/inventario")
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

    @GetMapping("/{inventarioId}")
    public ResponseEntity<?> getInventoryById(@PathVariable Long inventarioId) {

        Optional<Inventario> inventory = inventoryService.getInventoryById(inventarioId);

        if (inventory.isEmpty()) {
            throw new ResourceNotFoundException("inventario", "id", inventarioId);
        }

        return new ResponseEntity<>(inventory, HttpStatus.OK);

    }

    @PostMapping()
    public ResponseEntity<?> addInventory(@RequestBody @Valid CreateInventarioDto inventory) {
        Inventario newInventory;
        try {
            newInventory = inventoryService.addInventory(inventory);
        } catch (DataAccessException exDt) {
            throw new BadRequestException(exDt.getMessage());
        }
        return new ResponseEntity<>(newInventory, HttpStatus.CREATED);
    }

    @PutMapping("/{inventarioId}")
    public void updateInventory(@RequestBody @Valid Inventario inventory, @PathVariable Long inventarioId) {

        try {
            Optional<Inventario> optionalInventory = inventoryService.getInventoryById(inventarioId);

            if (optionalInventory.isPresent()) {
                Inventario existingInventory = optionalInventory.get();
                existingInventory.setCantidad(inventory.getCantidad());
                existingInventory.setPrecioUnitario(inventory.getPrecioUnitario());
                existingInventory.setProducto(inventory.getProducto());

                inventoryService.updateInventory(existingInventory);

                ResponseEntity.ok(existingInventory);

            } else {
                throw new ResourceNotFoundException("inventario", "id", inventarioId);
            }
        } catch (DataAccessException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @DeleteMapping("/{inventarioId}")
    public ResponseEntity<?> deleteInventory(@PathVariable Long inventarioId) {
        try {
            Optional<Inventario> inventoryDelete = inventoryService.getInventoryById(inventarioId);
            if (inventoryDelete.isPresent()) {
                Inventario inventory = inventoryDelete.get();
                inventoryService.deleteInventoryById(inventory.getId());

                return new ResponseEntity<>(inventoryDelete, HttpStatus.NO_CONTENT);
            } else {
                throw new ResourceNotFoundException("inventario", "id", inventarioId);
            }
        } catch (DataAccessException exDt) {
            throw new BadRequestException(exDt.getMessage());
        }
    }

    @PostMapping("/compra")
    public ResponseEntity<?> impactarCompra(@RequestBody @Valid CompraRequestDto compraRequest) {

        try {
            Compra effectedPurchase = purchaseService.impactarCompra(compraRequest);
            return ResponseEntity.ok(effectedPurchase);
        } catch (ResourceNotFoundException | BadRequestException e) {
            throw e; // Reenvía las excepciones específicas
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage()); // Envuelve otras excepciones en BadRequestException
        }
    }
}
