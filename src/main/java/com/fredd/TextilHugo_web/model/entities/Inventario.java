package com.fredd.TextilHugo_web.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "inventario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "cantidad")
    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    private Integer cantidad;

    @Column(name = "precio_unitario")
    @Min(value = 1, message = "El precio unitario debe ser mayor a 0")
    private Double precioUnitario;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_incorporacion")
    private Date fechaIncorporacion;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    @NotNull
    private Producto producto;

    @PrePersist
    public void prePersist() {
        fechaIncorporacion = new Date();
    }

    public boolean hasSuficienteCantidad(Integer cantidad) {
        return this.cantidad >= cantidad;
    }
}
