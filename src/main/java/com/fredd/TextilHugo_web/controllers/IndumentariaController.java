package com.fredd.TextilHugo_web.controllers;

import com.fredd.TextilHugo_web.exceptions.BadRequestException;
import com.fredd.TextilHugo_web.exceptions.ResourceNotFoundException;
import com.fredd.TextilHugo_web.model.dtos.IndumentariaDto;
import com.fredd.TextilHugo_web.model.dtos.request.CreateIndumentariaDto;
import com.fredd.TextilHugo_web.model.entities.Indumentaria;
import com.fredd.TextilHugo_web.model.mappers.IndumentariaDTOMapper;
import com.fredd.TextilHugo_web.services.IindumentariaService;
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
public class IndumentariaController {

    private final IindumentariaService clothingService;
    private final IndumentariaDTOMapper indumentariaDTOMapper;


    @GetMapping()
    public ResponseEntity<?> getAllClothings() {
        List<IndumentariaDto> clothing = clothingService.getAllClothings();
        if (clothing == null || clothing.isEmpty()) {
            throw new ResourceNotFoundException("indumentarias");
        }
        return new ResponseEntity<>(clothing, HttpStatus.OK);
    }

    @GetMapping("/{clothingId}")
    public ResponseEntity<?> getClothingById(@PathVariable Long clothingId) {

        Optional<IndumentariaDto> clothing = clothingService.getClothingById(clothingId);

        if (clothing.isEmpty()) {
            throw new ResourceNotFoundException("indumentaria", "id", clothingId);
        }

        return new ResponseEntity<>(clothing, HttpStatus.OK);

    }

    @PostMapping()
    public ResponseEntity<?> saveIndumentaria(@RequestBody @Valid CreateIndumentariaDto clothing) {
        IndumentariaDto newClothing;
        try {
            newClothing = clothingService.saveIndumentaria(clothing);
        } catch (DataAccessException exDt) {
            throw new BadRequestException(exDt.getMessage());
        }
        return new ResponseEntity<>(newClothing, HttpStatus.CREATED);
    }

    @PutMapping("{idClothing}")
    public ResponseEntity<?> updateClothing(@RequestBody @Valid IndumentariaDto clothing, @PathVariable Long idClothing){

        try {
            IndumentariaDto updatedClothing = clothingService.updateIndumentaria(idClothing, clothing);
            return ResponseEntity.ok(updatedClothing);
        } catch (DataAccessException e){
            throw new BadRequestException(e.getMessage());
        }
    }

    @DeleteMapping("{idClothing}")
    public ResponseEntity<?> delete(@PathVariable Long idClothing) {
        try {
            Optional<IndumentariaDto> clothingDelete = clothingService.getClothingById(idClothing);
            if (clothingDelete.isPresent()){
                IndumentariaDto clothingId = clothingDelete.get();
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
