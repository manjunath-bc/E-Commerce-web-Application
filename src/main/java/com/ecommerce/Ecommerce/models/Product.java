package com.ecommerce.Ecommerce.models;

import lombok.*;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    private String name;

    private String image;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private int quantity;

    private int price;

    private int weight;

    private String description;

}