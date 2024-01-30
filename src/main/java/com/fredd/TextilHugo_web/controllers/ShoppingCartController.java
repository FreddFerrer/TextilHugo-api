package com.fredd.TextilHugo_web.controllers;

import com.fredd.TextilHugo_web.exceptions.ResourceNotFoundException;
import com.fredd.TextilHugo_web.model.entities.ShoppingCart;
import com.fredd.TextilHugo_web.services.IShoppingCartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/carrito")
@RequiredArgsConstructor
public class ShoppingCartController {

    private final IShoppingCartService shoppingCartService;

    @GetMapping()
    public ResponseEntity<List<ShoppingCart>> getListByClient(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String userName = userDetails.getUsername();
        return new ResponseEntity<>(shoppingCartService.getListByClient(userName), HttpStatus.OK);
    }

    @GetMapping("/count/{client_id}")
    public ResponseEntity<Long> countByClient(@PathVariable("client_id")Long id){
        return new ResponseEntity<>(shoppingCartService.getCountByClient(id),HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> addProduct(@Valid @RequestBody ShoppingCart shoppingCart,
                                              BindingResult bindingResult){
        if (bindingResult.hasErrors())
            throw new ResourceNotFoundException("carrito");
        shoppingCartService.addProduct(shoppingCart);
        return new ResponseEntity<>(shoppingCart, HttpStatus.CREATED);
    }

    @DeleteMapping("/clean/{item_id}")
    public ResponseEntity<?> removeProduct(@PathVariable Long item_id){
        shoppingCartService.removeProduct(item_id);
        return new ResponseEntity<>(item_id, HttpStatus.NO_CONTENT);
    }
}
