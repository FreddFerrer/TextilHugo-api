package com.fredd.TextilHugo_web.services.impl;

import com.fredd.TextilHugo_web.exceptions.BadRequestException;
import com.fredd.TextilHugo_web.exceptions.ResourceNotFoundException;
import com.fredd.TextilHugo_web.model.dtos.InventarioDto;
import com.fredd.TextilHugo_web.model.dtos.request.CompraRequestDto;
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
    public Compra impactarCompra(CompraRequestDto purchaseRequest) {
        Optional<Inventario> inventory = inventoryService.getInventarioById(purchaseRequest.getInventarioId());
        if (inventory.isEmpty()) {
            throw new ResourceNotFoundException("inventario", "id", purchaseRequest.getInventarioId());
        }

        Inventario inventoryExist = inventory.get();
        if (inventoryExist.getCantidad() < purchaseRequest.getCantidad()) {
            throw new BadRequestException("Stock insuficiente para la prenda");
        }

        // Actualizar inventario
        inventoryExist.setCantidad(inventoryExist.getCantidad() - purchaseRequest.getCantidad());
        inventoryService.saveInventory(inventoryExist);

        // Crear y guardar compra
        Compra purchase = new Compra(purchaseRequest.getCantidad(), inventoryExist.getPrecioUnitario(), inventoryExist);
        return addPurchase(purchase);
    }
}
