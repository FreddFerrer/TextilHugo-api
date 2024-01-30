package com.fredd.TextilHugo_web.services;

import com.fredd.TextilHugo_web.model.entities.Talle;

import java.util.List;
import java.util.Optional;

public interface ITalleService {
    Talle addSize(Talle clothingSize);
    List<Talle> getAllSizes();
    Optional<Talle> getSizeById(Long sizeId);
    void deleteSizeById(Long sizeId);
}
