package com.fredd.TextilHugo_web.services.impl;

import com.fredd.TextilHugo_web.exceptions.ResourceNotFoundException;
import com.fredd.TextilHugo_web.model.dtos.ProductoDto;
import com.fredd.TextilHugo_web.model.dtos.request.CreateProductoDto;
import com.fredd.TextilHugo_web.model.entities.Producto;
import com.fredd.TextilHugo_web.model.mappers.ProductoDTOMapper;
import com.fredd.TextilHugo_web.model.repositories.IProductoRepository;
import com.fredd.TextilHugo_web.services.IProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements IProductoService {

    private final IProductoRepository clothingRepository;
    private final ProductoDTOMapper productoDTOMapper;

    @Override
    public List<ProductoDto> getAllClothings() {
        List<Producto> producto = clothingRepository.findAll();
        return productoDTOMapper.toDtoList(producto);
    }

    @Override
    public Optional<ProductoDto> getProductoById(Long clothingId) {
        Optional<Producto> indumentaria = clothingRepository.findById(clothingId);
        return indumentaria.map(productoDTOMapper::toDto);
    }

    @Override
    public ProductoDto saveIndumentaria(CreateProductoDto createIndumentaria) {
        Producto producto = productoDTOMapper.toEntity(createIndumentaria);
        producto = clothingRepository.save(producto);
        return productoDTOMapper.toDto(producto);
    }

    @Override
    public ProductoDto updateIndumentaria(Long id, ProductoDto updatedProductoDto) {
        // Verificar si la indumentaria existe por el ID
        Optional<Producto> optionalIndumentaria = clothingRepository.findById(id);

        if (optionalIndumentaria.isPresent()) {
            // Obtener la indumentaria existente
            Producto existingProducto = optionalIndumentaria.get();

            // Actualizar las propiedades de la indumentaria existente con las proporcionadas en updatedIndumentariaDto
            existingProducto.setTipo(updatedProductoDto.getTipo());
            existingProducto.setDescripcion(updatedProductoDto.getDescripcion());
            existingProducto.setMarca(updatedProductoDto.getMarca());
            existingProducto.setGenero(updatedProductoDto.getGenero());
            existingProducto.setColor(updatedProductoDto.getColor());
            existingProducto.setMaterial(updatedProductoDto.getMaterial());
            existingProducto.setCategoria(updatedProductoDto.getCategoria());
            existingProducto.setTemporada(updatedProductoDto.getTemporada());
            existingProducto.setTalle(updatedProductoDto.getTalle());

            // Guardar la indumentaria actualizada en la base de datos
            Producto updatedProducto = clothingRepository.save(existingProducto);

            // Mapear la indumentaria actualizada a DTO
            return productoDTOMapper.toDto(updatedProducto);
        } else {
            throw new ResourceNotFoundException("indumentaria", "id", id);
        }
    }

    @Override
    public boolean existsById(Long id) {
        return clothingRepository.existsById(id);
    }

    @Override
    public void deleteProductoById(Long idProducto) {
        Optional<ProductoDto> clothingDelete = getProductoById(idProducto);
        if (clothingDelete.isPresent()) {
            ProductoDto clothingId = clothingDelete.get();
            clothingRepository.deleteById(clothingId.getId());
        } else {
            throw new ResourceNotFoundException("indumentaria", "id", idProducto);
        }
    }

}
