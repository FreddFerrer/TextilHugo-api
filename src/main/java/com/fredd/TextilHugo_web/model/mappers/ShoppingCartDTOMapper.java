package com.fredd.TextilHugo_web.model.mappers;

import com.fredd.TextilHugo_web.model.dtos.InventarioDto;
import com.fredd.TextilHugo_web.model.dtos.ShoppingCartDto;
import com.fredd.TextilHugo_web.model.dtos.UsuarioDto;
import com.fredd.TextilHugo_web.model.entities.Inventario;
import com.fredd.TextilHugo_web.model.entities.ShoppingCart;

import com.fredd.TextilHugo_web.model.entities.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


@Mapper(componentModel = "spring")
public interface ShoppingCartDTOMapper {

    @Mapping(target = "producto", source = "producto", qualifiedByName = "inventarioToInventarioDto")
    @Mapping(target = "usuario", source = "usuario", qualifiedByName = "usuarioToUsuarioDto")
    ShoppingCartDto toDto(ShoppingCart shoppingCart);

    @Named("inventarioToInventarioDto")
    InventarioDto inventarioToInventarioDto(Inventario inventario);

    @Named("usuarioToUsuarioDto")    // <-- Asegúrate de que la anotación @Named está en la misma interfaz
    UsuarioDto usuarioToUsuarioDto(Usuario usuario);
}
