package com.fredd.TextilHugo_web.services;

import com.fredd.TextilHugo_web.model.dtos.ProductoDto;
import com.fredd.TextilHugo_web.model.dtos.ShoppingCartDto;
import com.fredd.TextilHugo_web.model.dtos.request.ShoppingCartRequestDto;
import com.fredd.TextilHugo_web.model.entities.ShoppingCart;
import com.fredd.TextilHugo_web.model.entities.Usuario;

import java.util.List;
import java.util.Optional;


public interface IShoppingCartService {


    ShoppingCartDto addProduct(ShoppingCartRequestDto shoppingCartRequestDto);
    Optional<ShoppingCartDto> getShoppingCartItemById(Long itemId);
    List<ShoppingCartDto> getProductsInCart();
    void removeProductById(Long id);
}
