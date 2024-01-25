package com.fredd.TextilHugo_web.model.mappers;

import com.fredd.TextilHugo_web.model.dtos.IndumentariaDto;
import com.fredd.TextilHugo_web.model.dtos.request.CreateIndumentariaDto;
import com.fredd.TextilHugo_web.model.entities.Indumentaria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IndumentariaDTOMapper {



    @Mappings({
            @Mapping(target = "tipo", source = "type"),
            @Mapping(target = "descripcion", source = "description"),
            @Mapping(target = "marca", source = "brand"),
            @Mapping(target = "genero", source = "gender"),
            @Mapping(target = "color", source = "color"),
            @Mapping(target = "material", source = "material"),
            @Mapping(target = "categoria", source = "category"),
            @Mapping(target = "temporada", source = "season"),
            @Mapping(target = "talleIndumentaria", source = "clothingSize")
    })
    IndumentariaDto toDto(Indumentaria indumentaria);


    @Mappings({
            @Mapping(target = "type", source = "tipo"),
            @Mapping(target = "description", source = "descripcion"),
            @Mapping(target = "brand", source = "marca"),
            @Mapping(target = "gender", source = "genero"),
            @Mapping(target = "color", source = "color"),
            @Mapping(target = "material", source = "material"),
            @Mapping(target = "category", source = "categoria"),
            @Mapping(target = "season", source = "temporada"),
            @Mapping(target = "clothingSize", source = "talleIndumentaria")
    })
    Indumentaria toEntity(CreateIndumentariaDto indumentariaDto);

    @Mappings({
            @Mapping(target = "tipo", source = "type"),
            @Mapping(target = "descripcion", source = "description"),
            @Mapping(target = "marca", source = "brand"),
            @Mapping(target = "genero", source = "gender"),
            @Mapping(target = "color", source = "color"),
            @Mapping(target = "material", source = "material"),
            @Mapping(target = "categoria", source = "category"),
            @Mapping(target = "temporada", source = "season"),
            @Mapping(target = "talleIndumentaria", source = "clothingSize")
    })
    List<IndumentariaDto> toDtoList(List<Indumentaria> listaIndumentariaDto);
}
