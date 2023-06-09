package com.truelanz.truelanzcommerce.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;
    
    private Double price;
    private String imgUrl;

    @ManyToMany
    @JoinTable(name = "tb_product_category", // classe intermediária que será criada
            joinColumns = @JoinColumn(name = "product_id"), // foreign key da classe onde estou mapeando
            inverseJoinColumns = @JoinColumn(name = "category_id")) // foreign key da classe que estou relacionando.
    @Setter(AccessLevel.NONE)
    private Set<Category> categories = new HashSet<>(); // Em @ManyToMany usa-se Set e HashSet, e não List!

    /*
     * @ManyToMany(mappedBy = "products")
     * 
     * @Setter(AccessLevel.NONE)
     * private List<Order> orders = new ArrayList<>();
     */
}
