package com.fredd.TextilHugo_web.controllers;

import com.fredd.TextilHugo_web.exceptions.BadRequestException;
import com.fredd.TextilHugo_web.model.entities.TalleIndumentaria;
import com.fredd.TextilHugo_web.services.ITalleService;

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
@RequestMapping("/api/v1/sizes")
@RequiredArgsConstructor
public class TalleController {

    private final ITalleService sizeService;

    @PostMapping
    public ResponseEntity<?> addSize(@RequestBody @Valid TalleIndumentaria clothingSize) {

        TalleIndumentaria newClothingSize;
        try {
            newClothingSize = sizeService.addSize(clothingSize);
        } catch (DataAccessException exDt) {
            throw new BadRequestException(exDt.getMessage());
        }
        return new ResponseEntity<>(newClothingSize, HttpStatus.CREATED);
    }
}
