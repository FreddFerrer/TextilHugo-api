package com.fredd.TextilHugo_web.services.impl;

import com.fredd.TextilHugo_web.model.entities.Size;
import com.fredd.TextilHugo_web.model.repositories.ISizeRepository;
import com.fredd.TextilHugo_web.services.ISizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SizeServiceImpl implements ISizeService {

    private final ISizeRepository sizeRepository;
    @Override
    public Size addSize(Size size) {
        return sizeRepository.save(size);
    }
}
