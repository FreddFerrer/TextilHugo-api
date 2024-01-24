package com.fredd.TextilHugo_web.services.impl;

import com.fredd.TextilHugo_web.model.entities.Indumentaria;
import com.fredd.TextilHugo_web.model.repositories.IindumentariaRepository;
import com.fredd.TextilHugo_web.services.IindumentariaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IndumentariaServiceImpl implements IindumentariaService {

    private final IindumentariaRepository clothingRepository;

    @Override
    public List<Indumentaria> getAllClothings() {
        return clothingRepository.findAll();
    }

    @Override
    public Optional<Indumentaria> getClothingById(Long clothingId) {
        return clothingRepository.findById(clothingId);
    }

    @Override
    public Indumentaria addClothing(Indumentaria newClothing) {
        return clothingRepository.save(newClothing);
    }

    @Override
    public boolean existsById(Long id) {
        return clothingRepository.existsById(id);
    }

    @Override
    public void deleteClothingById(Long clothingId) {
        clothingRepository.deleteById(clothingId);
    }

}
