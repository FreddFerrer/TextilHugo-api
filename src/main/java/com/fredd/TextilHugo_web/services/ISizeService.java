package com.fredd.TextilHugo_web.services;

import com.fredd.TextilHugo_web.model.entities.ClothingSize;

import java.util.List;
import java.util.Optional;

public interface ISizeService {
    ClothingSize addSize(ClothingSize clothingSize);
    List<ClothingSize> getAllSizes();
    Optional<ClothingSize> getSizeById(Long sizeId);
    void deleteSizeById(Long sizeId);
}
