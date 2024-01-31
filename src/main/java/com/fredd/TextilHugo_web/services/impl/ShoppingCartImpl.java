package com.fredd.TextilHugo_web.services.impl;

import com.fredd.TextilHugo_web.exceptions.BadRequestException;
import com.fredd.TextilHugo_web.model.dtos.ShoppingCartDto;
import com.fredd.TextilHugo_web.model.dtos.request.ShoppingCartRequestDto;
import com.fredd.TextilHugo_web.model.entities.Inventario;
import com.fredd.TextilHugo_web.model.entities.ShoppingCart;
import com.fredd.TextilHugo_web.model.entities.Usuario;
import com.fredd.TextilHugo_web.model.mappers.ShoppingCartDTOMapper;
import com.fredd.TextilHugo_web.model.repositories.IShoppingCartRepository;
import com.fredd.TextilHugo_web.services.IShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShoppingCartImpl implements IShoppingCartService {

    private final IShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartDTOMapper shoppingCartDTOMapper;


    @Override
    public ShoppingCartDto addProduct(ShoppingCartRequestDto shoppingCartRequestDto) {
        //Usuario userDetails = (Usuario) SecurityContextHolder.getContext().getAuthentication()
        //        .getPrincipal();

        Inventario inventario = shoppingCartRequestDto.getInventario();
        Integer cantidad = shoppingCartRequestDto.getCantidad();

        // Validar existencia y cantidad disponible en Inventario
        if (!inventario.hasSuficienteCantidad(cantidad)) {
            throw new BadRequestException("Cantidad insuficiente en inventario");
        }

        //Optional<ShoppingCart> existingCart = shoppingCartRepository.findByUsuarioAndProducto(userDetails, inventario);

        /*if (existingCart.isPresent()) {
            // Producto ya existe en el carrito, actualizar cantidad
            ShoppingCart cart = existingCart.get();
            cart.setCantidad(cart.getCantidad() + cantidad);
            shoppingCartRepository.save(cart);
            return shoppingCartDTOMapper.toDto(cart);
        } else {
            // Crear nuevo carrito
            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.setUsuario(userDetails);
            shoppingCart.setProducto(inventario);
            shoppingCart.setCantidad(cantidad);
            shoppingCartRepository.save(shoppingCart);
            return shoppingCartDTOMapper.toDto(shoppingCart);
        }*/
        ShoppingCart shoppingCart = new ShoppingCart();
        return shoppingCartDTOMapper.toDto(shoppingCart);
    }


}
