package com.fredd.TextilHugo_web.model.clases;

import com.fredd.TextilHugo_web.model.entities.Compra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetalleCompra {
    private Map<Long, Compra> carritoCompras;
    private Double total;

    public DetalleCompra() {
        carritoCompras = new HashMap<>();
        total = 0.0;
    }

    public void addProduct(Compra compra) {
        carritoCompras.put(compra.getId(), compra);
        total += compra.getQuantity() * compra.getUnitPrice();
    }

    public void deleteProduct(Long idCompra) {
        Compra compra = carritoCompras.remove(idCompra);
        if (compra != null) {
            total -= compra.getQuantity() * compra.getUnitPrice();
        }
    }

    public List<Compra> getCompras() {
        return new ArrayList<>(carritoCompras.values());
    }

    public Double getTotal() {
        return total;
    }
}
