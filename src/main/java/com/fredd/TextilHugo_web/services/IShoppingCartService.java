package com.fredd.TextilHugo_web.services;

import com.fredd.TextilHugo_web.model.dtos.ShoppingCartDto;
import com.fredd.TextilHugo_web.model.dtos.request.ShoppingCartRequestDto;


public interface IShoppingCartService {

    ShoppingCartDto addProduct(ShoppingCartRequestDto shoppingCartRequestDto);
}
