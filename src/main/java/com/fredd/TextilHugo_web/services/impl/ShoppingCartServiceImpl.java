package com.fredd.TextilHugo_web.services.impl;

import com.fredd.TextilHugo_web.exceptions.BadRequestException;
import com.fredd.TextilHugo_web.exceptions.ResourceNotFoundException;
import com.fredd.TextilHugo_web.model.dtos.ShoppingCartDto;
import com.fredd.TextilHugo_web.model.dtos.request.ShoppingCartRequestDto;
import com.fredd.TextilHugo_web.model.entities.Inventario;
import com.fredd.TextilHugo_web.model.entities.ShoppingCart;
import com.fredd.TextilHugo_web.model.entities.Usuario;
import com.fredd.TextilHugo_web.model.mappers.ShoppingCartDTOMapper;
import com.fredd.TextilHugo_web.model.repositories.IShoppingCartRepository;
import com.fredd.TextilHugo_web.model.repositories.IinventarioRepository;
import com.fredd.TextilHugo_web.services.IShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements IShoppingCartService {

    private final IinventarioRepository inventarioRepository;
    private final IShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartDTOMapper shoppingCartDTOMapper;

    @Override
    public ShoppingCartDto addProduct(ShoppingCartRequestDto shoppingCartRequestDto) {
        Usuario userDetails = (Usuario) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        Inventario inventario = inventarioRepository.findById(shoppingCartRequestDto.getInventario().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Inventario no encontrado"));

        Integer cantidad = shoppingCartRequestDto.getCantidad();

        // Validar existencia y cantidad disponible en Inventario
        if (!inventario.hasSuficienteCantidad(cantidad)) {
            throw new BadRequestException("Cantidad insuficiente en inventario");
        }

        Optional<ShoppingCart> existingCart = shoppingCartRepository.findByUsuarioAndProducto(userDetails, inventario);

        if (existingCart.isPresent()) {
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
        }
    }

    @Override
    public List<ShoppingCartDto> getProductsInCart(Usuario usuario) {
        List<ShoppingCart> carts = shoppingCartRepository.findByUsuario(usuario);
        List<ShoppingCartDto> dtos = new ArrayList<>();

        for (ShoppingCart cart : carts) {
            dtos.add(shoppingCartDTOMapper.toDto(cart));
        }

        return dtos;
    }

}
