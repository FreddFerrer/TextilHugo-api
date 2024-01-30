package com.fredd.TextilHugo_web.services.impl;

import com.fredd.TextilHugo_web.model.entities.Talle;
import com.fredd.TextilHugo_web.model.repositories.ITalleRepository;
import com.fredd.TextilHugo_web.services.ITalleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TalleServiceImpl implements ITalleService {
    private final ITalleRepository sizeRepository;

    @Override
    public Talle addSize(Talle clothingSize) {
        return sizeRepository.save(clothingSize);
    }

    @Override
    public List<Talle> getAllSizes() {
        return sizeRepository.findAll();
    }

    @Override
    public Optional<Talle> getSizeById(Long sizeId) {
        return sizeRepository.findById(sizeId);
    }

    @Override
    public void deleteSizeById(Long sizeId) {
        sizeRepository.deleteById(sizeId);
    }
}
