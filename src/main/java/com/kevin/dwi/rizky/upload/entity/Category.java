package com.kevin.dwi.rizky.upload.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoryName;
    private String foto;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Food> foodList;
}
