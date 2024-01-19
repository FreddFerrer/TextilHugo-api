package com.fredd.TextilHugo_web.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "inventory")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String quantity;

    private Integer unitPrice;

    private Date loadDate;

    @ManyToOne
    @JoinColumn(name = "clothing_id")
    private Clothing clothing;
}
