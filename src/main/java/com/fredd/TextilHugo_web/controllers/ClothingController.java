package com.fredd.TextilHugo_web.controllers;

import com.fredd.TextilHugo_web.model.entities.Clothing;
import com.fredd.TextilHugo_web.model.entities.Size;
import com.fredd.TextilHugo_web.services.IClothingService;
import com.fredd.TextilHugo_web.services.impl.ClothingServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clothes")
@RequiredArgsConstructor
public class ClothingController {

    private final IClothingService clothingService;


    @GetMapping
    public ResponseEntity<List<Clothing>> getAllSizes() {
        List<Clothing> clothing = clothingService.getAllClothings();
        return new ResponseEntity<>(clothing, HttpStatus.OK);
    }
}
