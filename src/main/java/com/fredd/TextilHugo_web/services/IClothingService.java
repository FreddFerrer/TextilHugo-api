package com.fredd.TextilHugo_web.services;

import com.fredd.TextilHugo_web.model.entities.Clothing;
import com.fredd.TextilHugo_web.model.entities.Size;

import java.util.List;

public interface IClothingService {

    List<Clothing> getAllClothings();

}
