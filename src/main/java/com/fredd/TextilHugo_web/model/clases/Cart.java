package com.fredd.TextilHugo_web.model.clases;

import com.fredd.TextilHugo_web.model.entities.Purchase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {
    private Map<Long, Purchase> purchases;
    private Double total;

    public Cart() {
        purchases = new HashMap<>();
        total = 0.0;
    }

    public void addProduct(Purchase compra) {
        purchases.put(compra.getId(), compra);
        total += compra.getQuantity() * compra.getUnitPrice();
    }

    public void deleteProduct(Long idCompra) {
        Purchase compra = purchases.remove(idCompra);
        if (compra != null) {
            total -= compra.getQuantity() * compra.getUnitPrice();
        }
    }

    public List<Purchase> getCompras() {
        return new ArrayList<>(purchases.values());
    }

    public Double getTotal() {
        return total;
    }
}
