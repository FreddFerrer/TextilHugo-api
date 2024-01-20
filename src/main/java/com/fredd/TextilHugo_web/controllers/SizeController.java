package com.fredd.TextilHugo_web.controllers;

import com.fredd.TextilHugo_web.exceptions.BadRequestException;
import com.fredd.TextilHugo_web.model.entities.ClothingSize;
import com.fredd.TextilHugo_web.services.ISizeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sizes")
@RequiredArgsConstructor
public class SizeController {

    private final ISizeService sizeService;

    @PostMapping
    public ResponseEntity<?> addSize(@RequestBody @Valid ClothingSize clothingSize) {

        ClothingSize newClothingSize;
        try {
            newClothingSize = sizeService.addSize(clothingSize);
        } catch (DataAccessException exDt) {
            throw new BadRequestException(exDt.getMessage());
        }
        return new ResponseEntity<>(newClothingSize, HttpStatus.CREATED);
    }
}
