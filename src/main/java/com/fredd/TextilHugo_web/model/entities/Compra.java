package com.fredd.TextilHugo_web.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "compras")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_compra", nullable = false)
    private Long id;

    @NotNull
    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    @Column(name = "cantidad")
    private Integer cantidad;

    @NotNull
    @Min(value = 1, message = "El precio unitario debe ser mayor a 0")
    @Column(name = "precio_unitario")
    private Double precioUnitario;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_compra")
    private Date fechaCompra;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    @NotNull
    private Inventario inventario;

    public Compra(Integer cantidad, Double precioUnitario, Inventario inventario) {
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.inventario = inventario;
    }

    @PrePersist
    public void prePersist() {
        fechaCompra = new Date();
    }
}
