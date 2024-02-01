package com.fredd.TextilHugo_web.model.mappers;

import com.fredd.TextilHugo_web.model.dtos.ProductoDto;
import com.fredd.TextilHugo_web.model.dtos.InventarioDto;
import com.fredd.TextilHugo_web.model.dtos.request.CreateInventarioDto;
import com.fredd.TextilHugo_web.model.entities.Producto;
import com.fredd.TextilHugo_web.model.entities.Inventario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InventarioDTOMapper {

    Inventario toEntity(CreateInventarioDto createInventarioDto);
    Inventario toEntity(InventarioDto createInventarioDto);

    @Mappings({
            @Mapping(target = "fechaDeLaCarga", source = "fechaIncorporacion"),
    })
    InventarioDto toDto(Inventario inventario);

    List<ProductoDto> toDtoList(List<Producto> productoList);
}
