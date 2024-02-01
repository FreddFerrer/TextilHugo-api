package com.fredd.TextilHugo_web.services;

import com.fredd.TextilHugo_web.model.dtos.ShoppingCartDto;
import com.fredd.TextilHugo_web.model.dtos.request.ShoppingCartRequestDto;
import com.fredd.TextilHugo_web.model.entities.Usuario;

import java.util.List;


public interface IShoppingCartService {


    ShoppingCartDto addProduct(ShoppingCartRequestDto shoppingCartRequestDto);

    List<ShoppingCartDto> getProductsInCart(Usuario usuario);
}
