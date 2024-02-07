package com.fredd.TextilHugo_web.services;

import com.fredd.TextilHugo_web.model.dtos.ProductoDto;
import com.fredd.TextilHugo_web.model.dtos.request.CreateProductoDto;

import java.util.List;
import java.util.Optional;

public interface IProductoService {

    List<ProductoDto> getAllClothings();
    Optional<ProductoDto> getClothingById(Long indumentariaId);
    ProductoDto saveIndumentaria(CreateProductoDto createProductoDto);

    ProductoDto updateIndumentaria(Long id, ProductoDto updatedProductoDto);

    boolean existsById(Long id);

    void deleteProductoById(Long idClothing);
}
