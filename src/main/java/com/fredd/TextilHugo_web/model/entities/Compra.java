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
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    private Integer quantity;

    @NotNull
    @Min(value = 1, message = "El precio unitario debe ser mayor a 0")
    private Double unitPrice;

    @Temporal(TemporalType.DATE)
    private Date purchaseDate;

    @ManyToOne
    @JoinColumn(name = "inventario_id")
    @NotNull
    private Inventario inventory;

    public Compra(Integer quantity, Double unitPrice, Inventario inventory) {
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.inventory = inventory;
    }

    @PrePersist
    public void prePersist() {
        purchaseDate = new Date();
    }
}
