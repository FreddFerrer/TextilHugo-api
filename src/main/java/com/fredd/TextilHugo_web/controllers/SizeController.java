package com.fredd.TextilHugo_web.controllers;

import com.fredd.TextilHugo_web.model.entities.Size;
import com.fredd.TextilHugo_web.services.ISizeService;

import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<Size> addSize(@RequestBody Size size) {
        Size newSize = sizeService.addSize(size);
        return new ResponseEntity<>(newSize, HttpStatus.CREATED);
    }
}
