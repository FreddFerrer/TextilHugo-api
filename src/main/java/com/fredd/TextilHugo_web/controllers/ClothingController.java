package com.fredd.TextilHugo_web.controllers;

import com.fredd.TextilHugo_web.exceptions.BadRequestException;
import com.fredd.TextilHugo_web.exceptions.ResourceNotFoundException;
import com.fredd.TextilHugo_web.model.entities.Clothing;
import com.fredd.TextilHugo_web.services.IClothingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/clothes")
@RequiredArgsConstructor
public class ClothingController {

    private final IClothingService clothingService;


    @GetMapping()
    public ResponseEntity<?> getAllClothings() {
        List<Clothing> clothing = clothingService.getAllClothings();
        if (clothing == null || clothing.isEmpty()) {
            throw new ResourceNotFoundException("indumentarias");
        }
        return new ResponseEntity<>(clothing, HttpStatus.OK);
    }

    @GetMapping("/{clothingId}")
    public ResponseEntity<?> getClothingById(@PathVariable Long clothingId) {

        Optional<Clothing> clothing = clothingService.getClothingById(clothingId);

        if (clothing.isEmpty()) {
            throw new ResourceNotFoundException("indumentaria", "id", clothingId);
        }

        return new ResponseEntity<>(clothing, HttpStatus.OK);

    }

    @PostMapping()
    public ResponseEntity<?> addClothing(@RequestBody @Valid Clothing clothing) {
        Clothing newClothing;
        try {
            newClothing = clothingService.addClothing(clothing);
        } catch (DataAccessException exDt) {
            throw new BadRequestException(exDt.getMessage());
        }
        return new ResponseEntity<>(newClothing, HttpStatus.CREATED);
    }

    @PutMapping("{idClothing}")
    public ResponseEntity<?> updateClothing(@RequestBody @Valid Clothing clothing, @PathVariable Long idClothing){

        try {
            Optional<Clothing> optionalClothing = clothingService.getClothingById(idClothing);

            if (optionalClothing.isPresent()){
                Clothing existingClothing = optionalClothing.get();
                existingClothing.setType(clothing.getType());
                existingClothing.setDescription(clothing.getDescription());
                existingClothing.setBrand(clothing.getBrand());
                existingClothing.setGender(clothing.getGender());
                existingClothing.setColor(clothing.getColor());
                existingClothing.setMaterial(clothing.getMaterial());
                existingClothing.setCategory(clothing.getCategory());
                existingClothing.setSeason(clothing.getSeason());
                existingClothing.setClothingSize(clothing.getClothingSize());

                clothingService.addClothing(existingClothing);

                return ResponseEntity.ok(existingClothing);

            } else {
                throw new ResourceNotFoundException("indumentaria", "id", idClothing);
            }
        } catch (DataAccessException e){
            throw new BadRequestException(e.getMessage());
        }
    }

    @DeleteMapping("{idClothing}")
    public ResponseEntity<?> delete(@PathVariable Long idClothing) {
        try {
            Optional<Clothing> clothingDelete = clothingService.getClothingById(idClothing);
            if (clothingDelete.isPresent()){
                Clothing clothingId = clothingDelete.get();
                clothingService.deleteClothingById(clothingId.getId());

                return new ResponseEntity<>(clothingDelete, HttpStatus.NO_CONTENT);
            } else {
                throw new ResourceNotFoundException("indumentaria", "id", idClothing);
            }
        } catch (DataAccessException exDt) {
            throw  new BadRequestException(exDt.getMessage());
        }
    }

}
