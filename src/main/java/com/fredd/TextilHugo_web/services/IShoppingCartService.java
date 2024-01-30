package com.fredd.TextilHugo_web.services;

import com.fredd.TextilHugo_web.model.entities.ShoppingCart;

import java.util.List;

public interface IShoppingCartService {

    List<ShoppingCart> getListByClient(String username);

    void cleanShoppingCart(Long clienteId);

    void removeProduct(Long id);

    void addProduct(ShoppingCart shoppingCart);

    Long getCountByClient(Long clientId);
}
