package com.fredd.TextilHugo_web.model.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "productos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name = "tipo", nullable = false)
    private String tipo;


    @Column(name = "descripcion", nullable = false)
    private String descripcion;


    @Column(name = "marca", nullable = false)
    private String marca;


    @Column(name = "genero")
    private String genero;


    @Column(name = "color", nullable = false)
    private String color;


    @Column(name = "material", nullable = false)
    private String material;


    @Column(name = "categoria", nullable = false)
    private String categoria;


    @Column(name = "temporada")
    private String temporada;

    @ManyToOne
    @JoinColumn(name = "talle_id")
    private Talle talle;
}
