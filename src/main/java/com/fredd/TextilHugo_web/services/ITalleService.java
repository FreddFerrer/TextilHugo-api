package com.fredd.TextilHugo_web.services;

import com.fredd.TextilHugo_web.model.entities.TalleIndumentaria;

import java.util.List;
import java.util.Optional;

public interface ITalleService {
    TalleIndumentaria addSize(TalleIndumentaria clothingSize);
    List<TalleIndumentaria> getAllSizes();
    Optional<TalleIndumentaria> getSizeById(Long sizeId);
    void deleteSizeById(Long sizeId);
}
