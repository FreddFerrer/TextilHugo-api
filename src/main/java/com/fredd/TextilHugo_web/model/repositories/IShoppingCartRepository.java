package com.fredd.TextilHugo_web.model.repositories;

import com.fredd.TextilHugo_web.model.entities.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    List<ShoppingCart> findByClienteId(Long clienteId);
    List<ShoppingCart> findByClienteUsername(String clienteEmail);
    void deleteByClienteId(Long clienteId);
    Long countByClienteId(Long id);
}
