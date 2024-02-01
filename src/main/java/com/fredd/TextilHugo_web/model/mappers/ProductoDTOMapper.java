package com.fredd.TextilHugo_web.model.mappers;

import com.fredd.TextilHugo_web.model.dtos.ProductoDto;
import com.fredd.TextilHugo_web.model.dtos.request.CreateProductoDto;
import com.fredd.TextilHugo_web.model.entities.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductoDTOMapper {

    ProductoDto toDto(Producto producto);

    Producto toEntity(CreateProductoDto indumentariaDto);
    Producto toEntity(ProductoDto indumentariaDto);

    List<ProductoDto> toDtoList(List<Producto> listaProductoDto);
}
