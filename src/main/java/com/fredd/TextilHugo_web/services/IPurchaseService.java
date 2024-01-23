package com.fredd.TextilHugo_web.services;

import com.fredd.TextilHugo_web.model.dtos.PurchaseRequest;
import com.fredd.TextilHugo_web.model.entities.Purchase;

public interface IPurchaseService {

    Purchase addPurchase(Purchase purchase);
    Purchase impactarCompra(PurchaseRequest purchaseRequest);
}
