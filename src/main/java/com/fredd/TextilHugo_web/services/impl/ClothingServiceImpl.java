package com.fredd.TextilHugo_web.services.impl;

import com.fredd.TextilHugo_web.model.entities.Clothing;
import com.fredd.TextilHugo_web.model.repositories.IClothingRepository;
import com.fredd.TextilHugo_web.services.IClothingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClothingServiceImpl implements IClothingService {

    private final IClothingRepository clothingRepository;

    @Override
    public List<Clothing> getAllClothings() {
        return clothingRepository.findAll();
    }

    @Override
    public Optional<Clothing> getClothingById(Long clothingId) {
        return clothingRepository.findById(clothingId);
    }

    @Override
    public Clothing addClothing(Clothing newClothing) {
        return clothingRepository.save(newClothing);
    }

    @Override
    public void deleteClothingById(Long clothingId) {
        clothingRepository.deleteById(clothingId);
    }

}
