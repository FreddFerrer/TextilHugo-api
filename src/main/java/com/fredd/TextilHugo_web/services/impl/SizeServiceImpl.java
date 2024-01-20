package com.fredd.TextilHugo_web.services.impl;

import com.fredd.TextilHugo_web.model.entities.ClothingSize;
import com.fredd.TextilHugo_web.model.repositories.ISizeRepository;
import com.fredd.TextilHugo_web.services.ISizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SizeServiceImpl implements ISizeService {
    private final ISizeRepository sizeRepository;

    @Override
    public ClothingSize addSize(ClothingSize clothingSize) {
        return sizeRepository.save(clothingSize);
    }

    @Override
    public List<ClothingSize> getAllSizes() {
        return sizeRepository.findAll();
    }

    @Override
    public Optional<ClothingSize> getSizeById(Long sizeId) {
        return sizeRepository.findById(sizeId);
    }

    @Override
    public void deleteSizeById(Long sizeId) {
        sizeRepository.deleteById(sizeId);
    }
}
