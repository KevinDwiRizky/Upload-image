package com.kevin.dwi.rizky.upload.entity;

import jakarta.persistence.*;
import lombok.*;

import jakarta.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "food")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String foodName;
    private double price;

    @ManyToOne
    @JoinColumn(name = "kategori_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "restoran_id", nullable = false)
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;
}