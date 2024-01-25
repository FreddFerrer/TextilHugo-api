package com.fredd.TextilHugo_web.services;

import com.fredd.TextilHugo_web.model.dtos.request.CompraRequestDto;
import com.fredd.TextilHugo_web.model.entities.Compra;

public interface ICompraService {

    Compra addPurchase(Compra purchase);
    Compra impactarCompra(CompraRequestDto purchaseRequest);
}
