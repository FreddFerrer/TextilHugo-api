package com.fredd.TextilHugo_web.model.entities;

import jakarta.persistence.*;
import lombok.*;

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

    private String type;

    private String description;

    private String brand;

    private String gender;

    private String color;

    private String material;

    private String category;

    private String season;

    @OneToOne
    @JoinColumn(name = "size_id")
    private Size size;
}
