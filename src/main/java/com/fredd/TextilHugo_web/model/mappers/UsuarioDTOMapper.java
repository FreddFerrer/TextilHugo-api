package com.fredd.TextilHugo_web.model.mappers;

import com.fredd.TextilHugo_web.model.dtos.UsuarioDto;
import com.fredd.TextilHugo_web.model.entities.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioDTOMapper {

    UsuarioDto toDto(Usuario usuario);
}
