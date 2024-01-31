package com.fredd.TextilHugo_web.model.mappers;

import com.fredd.TextilHugo_web.model.dtos.ShoppingCartDto;
import com.fredd.TextilHugo_web.model.entities.ShoppingCart;

import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ShoppingCartDTOMapper {


    ShoppingCartDto toDto(ShoppingCart shoppingCart);
}
