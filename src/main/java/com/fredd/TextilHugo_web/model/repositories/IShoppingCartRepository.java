package com.fredd.TextilHugo_web.model.repositories;

import com.fredd.TextilHugo_web.model.entities.Inventario;
import com.fredd.TextilHugo_web.model.entities.ShoppingCart;
import com.fredd.TextilHugo_web.model.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    Optional<ShoppingCart> findByUsuarioAndProducto(Usuario userDetails, Inventario inventario);

    List<ShoppingCart> findByUsuario(Usuario usuario);
}
