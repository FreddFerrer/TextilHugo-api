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

    @NotNull
    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    private Integer quantity;

    @NotNull
    @Min(value = 1, message = "El precio unitario debe ser mayor a 0")
    private Double unitPrice;

    @Temporal(TemporalType.DATE)
    private Date loadDate;

    @ManyToOne
    @JoinColumn(name = "indumentaria_id")
    @NotNull
    private Indumentaria clothing;

    @PrePersist
    public void prePersist() {
        loadDate = new Date();
    }
}
