package com.fredd.TextilHugo_web.services;

import com.fredd.TextilHugo_web.model.dtos.IndumentariaDto;
import com.fredd.TextilHugo_web.model.dtos.request.CreateIndumentariaDto;

import java.util.List;
import java.util.Optional;

public interface IindumentariaService {

    List<IndumentariaDto> getAllClothings();
    Optional<IndumentariaDto> getClothingById(Long indumentariaId);
    IndumentariaDto saveIndumentaria(CreateIndumentariaDto createIndumentariaDto);

    IndumentariaDto updateIndumentaria(Long id, IndumentariaDto updatedIndumentariaDto);

    boolean existsById(Long id);
    void deleteClothingById(Long clothingId);
}
