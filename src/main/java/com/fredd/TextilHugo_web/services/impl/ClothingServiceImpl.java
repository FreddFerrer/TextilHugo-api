package com.fredd.TextilHugo_web.services.impl;

import com.fredd.TextilHugo_web.model.entities.Clothing;
import com.fredd.TextilHugo_web.model.repositories.IClothingRepository;
import com.fredd.TextilHugo_web.services.IClothingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClothingServiceImpl implements IClothingService {

    private final IClothingRepository clothingRepository;

    @Override
    public List<Clothing> getAllClothings() {
        return clothingRepository.findAll();
    }

}
