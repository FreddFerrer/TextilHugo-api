package com.fredd.TextilHugo_web.controllers;

import com.fredd.TextilHugo_web.exceptions.BadRequestException;
import com.fredd.TextilHugo_web.exceptions.ResourceNotFoundException;
import com.fredd.TextilHugo_web.model.dtos.ProductoDto;
import com.fredd.TextilHugo_web.model.dtos.ShoppingCartDto;
import com.fredd.TextilHugo_web.model.dtos.request.ShoppingCartRequestDto;
import com.fredd.TextilHugo_web.model.entities.Usuario;
import com.fredd.TextilHugo_web.model.mappers.ShoppingCartDTOMapper;
import com.fredd.TextilHugo_web.services.IShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/carrito")
@RequiredArgsConstructor
public class ShoppingCartController {

    private final IShoppingCartService shoppingCartService;
    private final ShoppingCartDTOMapper shoppingCartDTOMapper;

    @GetMapping
    public ResponseEntity<List<ShoppingCartDto>> getProductsInCart() {
        Usuario userDetails = (Usuario) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        List<ShoppingCartDto> productos = shoppingCartService.getProductsInCart(userDetails);

        return ResponseEntity.ok(productos);
    }

    @PostMapping("/agregar")
    public ResponseEntity<ShoppingCartDto> addProduct(@RequestBody ShoppingCartRequestDto requestDto) {
        try {
            ShoppingCartDto carrito = shoppingCartService.addProduct(requestDto);
            return ResponseEntity.ok(carrito);
        } catch (ResourceNotFoundException | BadRequestException e) {
            throw e;
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeProduct(@PathVariable Long id) {
        try {
            shoppingCartService.removeProductById(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException ex) {
            throw new ResourceNotFoundException("item", "id", id);
        } catch (DataAccessException exDt) {
            throw new BadRequestException(exDt.getMessage());
        }
    }
}
