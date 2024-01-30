package com.fredd.TextilHugo_web.services.impl;

import com.fredd.TextilHugo_web.model.entities.ShoppingCart;
import com.fredd.TextilHugo_web.model.repositories.IShoppingCartRepository;
import com.fredd.TextilHugo_web.services.IShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShoppingCartImpl implements IShoppingCartService {

    private final IShoppingCartRepository shoppingCartRepository;

    @Override
    public List<ShoppingCart> getListByClient(String username) {
        return this.shoppingCartRepository.findByClienteUsername(username);
    }

    @Override
    public void cleanShoppingCart(Long clienteId) {
        this.shoppingCartRepository.deleteByClienteId(clienteId);
    }

    @Override
    public void removeProduct(Long id) {
        this.shoppingCartRepository.deleteById(id);
    }

    @Override
    public void addProduct(ShoppingCart shoppingCart) {
        this.shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public Long getCountByClient(Long clientId) {
        return this.shoppingCartRepository.countByClienteId(clientId);
    }
}
