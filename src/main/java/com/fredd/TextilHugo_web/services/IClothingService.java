package com.fredd.TextilHugo_web.services;

import com.fredd.TextilHugo_web.model.entities.Clothing;

import java.util.List;
import java.util.Optional;

public interface IClothingService {

    List<Clothing> getAllClothings();
    Optional<Clothing> getClothingById(Long clothingId);
    Clothing addClothing(Clothing newClothing);
    void deleteClothingById(Long clothingId);
}
