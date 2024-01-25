package com.fredd.TextilHugo_web.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "indumentarias")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Indumentaria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name = "tipo")
    private String type;


    @Column(name = "descripcion")
    private String description;


    @Column(name = "marca")
    private String brand;


    @Column(name = "genero")
    private String gender;


    @Column(name = "color")
    private String color;


    @Column(name = "material")
    private String material;


    @Column(name = "categoria")
    private String category;


    @Column(name = "temporada")
    private String season;

    @ManyToOne
    @JoinColumn(name = "talle_id")
    private TalleIndumentaria clothingSize;
}
