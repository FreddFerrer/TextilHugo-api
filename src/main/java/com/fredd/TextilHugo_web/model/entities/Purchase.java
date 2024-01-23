package com.fredd.TextilHugo_web.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Optional;

@Entity
@Table(name = "purchases")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Purchase {
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
    @JoinColumn(name = "inventory_id")
    @NotNull
    private Inventory inventory;

    public Purchase(Integer quantity, Double unitPrice, Inventory inventory) {
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.inventory = inventory;
    }

    @PrePersist
    public void prePersist() {
        purchaseDate = new Date();
    }
}
