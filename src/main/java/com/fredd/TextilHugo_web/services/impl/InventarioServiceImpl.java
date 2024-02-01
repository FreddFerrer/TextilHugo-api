package com.fredd.TextilHugo_web.services.impl;

import com.fredd.TextilHugo_web.exceptions.ResourceNotFoundException;
import com.fredd.TextilHugo_web.model.dtos.InventarioDto;
import com.fredd.TextilHugo_web.model.dtos.request.CreateInventarioDto;
import com.fredd.TextilHugo_web.model.dtos.request.EditInventarioDto;
import com.fredd.TextilHugo_web.model.entities.Inventario;
import com.fredd.TextilHugo_web.model.mappers.InventarioDTOMapper;
import com.fredd.TextilHugo_web.model.mappers.ProductoDTOMapper;
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
    private final InventarioDTOMapper inventarioDTOMapper;
    private final ProductoDTOMapper productoDTOMapper;

    @Override
    public List<Inventario> getAllInventory() {
        return inventoryRepository.findAll();
    }

    @Override
    public Optional<InventarioDto> getInventarioDtoById(Long inventarioId) {
        Optional<Inventario> inventario = inventoryRepository.findById(inventarioId);
        return inventario.map(inventarioDTOMapper::toDto);
    }

    @Override
    public Optional<Inventario> getInventarioById(Long inventarioId) {
        return inventoryRepository.findById(inventarioId);
    }

    @Override
    public Inventario addInventory(CreateInventarioDto newInventoryDto) {
        Inventario newInventory = inventarioDTOMapper.toEntity(newInventoryDto);
        return inventoryRepository.save(newInventory);
    }

    @Override
    public void deleteInventoryById(Long inventoryId) {
        inventoryRepository.deleteById(inventoryId);
    }

    @Override
    public void saveInventory(Inventario existingInventory) {
        inventoryRepository.save(existingInventory);
    }

    @Override
    public void updateInventory(EditInventarioDto createInventoryDto, Long inventarioId) {
        Optional<InventarioDto> optionalInventory = getInventarioDtoById(inventarioId);

        if (optionalInventory.isPresent()) {
            Inventario existingInventory = inventarioDTOMapper.toEntity(optionalInventory.get());
            existingInventory.setCantidad(createInventoryDto.getCantidad());
            existingInventory.setPrecioUnitario(createInventoryDto.getPrecioUnitario());

            saveInventory(existingInventory);
        } else {
            throw new ResourceNotFoundException("inventario", "id", inventarioId);
        }
    }
}
