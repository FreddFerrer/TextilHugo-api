package com.fredd.TextilHugo_web.model.mappers;

import com.fredd.TextilHugo_web.model.dtos.IndumentariaDto;
import com.fredd.TextilHugo_web.model.dtos.InventarioDto;
import com.fredd.TextilHugo_web.model.dtos.request.CreateInventarioDto;
import com.fredd.TextilHugo_web.model.entities.Indumentaria;
import com.fredd.TextilHugo_web.model.entities.Inventario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InventarioDTOMapper {

    @Mappings({
            @Mapping(target = "quantity", source = "createInventarioDto.cantidad"),
            @Mapping(target = "unitPrice", source = "createInventarioDto.precioUnitario"),
            @Mapping(target = "clothing", source = "createInventarioDto.indumentaria")
    })
    Inventario toEntity(CreateInventarioDto createInventarioDto);

    @Mappings({
            @Mapping(target = "id", source = "inventario.id"),
            @Mapping(target = "cantidad", source = "inventario.quantity"),
            @Mapping(target = "precioUnitario", source = "inventario.unitPrice"),
            @Mapping(target = "fechaDeLaCarga", source = "inventario.loadDate"),
            @Mapping(target = "indumentaria", source = "inventario.clothing")
    })
    InventarioDto toDto(Inventario inventario);

    List<IndumentariaDto> toDtoList(List<Indumentaria> indumentariaList);
}
