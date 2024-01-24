package com.fredd.TextilHugo_web.services;

import com.fredd.TextilHugo_web.model.entities.Indumentaria;

import java.util.List;
import java.util.Optional;

public interface IindumentariaService {

    List<Indumentaria> getAllClothings();
    Optional<Indumentaria> getClothingById(Long clothingId);
    Indumentaria addClothing(Indumentaria newClothing);
    boolean existsById(Long id);
    void deleteClothingById(Long clothingId);
}
