package com.fredd.TextilHugo_web.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "clothes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Clothing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotEmpty(message = "El tipo no debe estar vacio ni ser menor a 10 caracteres")
    @Size(min = 1, max = 100, message = "La longitud del campo debe estar entre 10 y 100 caracteres")
    private String type;

    @NotEmpty(message = "La descripcion no debe estar vacia")
    @Size(min = 1, max = 100, message = "La longitud del campo debe estar entre 10 y 100 caracteres")
    private String description;

    @NotEmpty(message = "Campo 'marca' requerido")
    private String brand;

    @NotEmpty(message = "Campo 'genero' requerido")
    private String gender;

    @NotEmpty(message = "Campo 'color' requerido")
    private String color;

    @NotEmpty(message = "Campo 'material' requerido")
    private String material;

    @NotEmpty(message = "Campo 'categoria' requerido")
    private String category;

    @NotEmpty(message = "Campo 'temporada' requerido")
    private String season;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "size_id")
    private ClothingSize clothingSize;
}
