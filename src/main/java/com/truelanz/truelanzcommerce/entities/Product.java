package com.truelanz.truelanzcommerce.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_product")
@Getter
@Setter
@NoArgsConstructor
public class Product {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
        @Column(columnDefinition = "TEXT")
        private String description;
        private Double price;
        private String imgUrl;

        //Constructor
        public Product(Long id, String name, String description, Double price, String imgUrl) {
                this.id = id;
                this.name = name;
                this.description = description;
                this.price = price;
                this.imgUrl = imgUrl;
        }

        @ManyToMany
        @JoinTable(name = "tb_product_category", // classe intermediária que será criada
                joinColumns = @JoinColumn(name = "product_id"), // foreign key da classe onde estou mapeando
                inverseJoinColumns = @JoinColumn(name = "category_id")) // foreign key da classe que estou relacionando
                                                  
        @Setter(AccessLevel.NONE)
        //@NonNull // Não incluir no constructor
        private Set<Category> categories = new HashSet<>(); // Em @ManyToMany usa-se Set e HashSet, e não List!

        @OneToMany(mappedBy = "id.product")
        @Setter(AccessLevel.NONE)
        private Set<OrderItem> items = new HashSet<>();

        // Retorna uma lista de ordens que estão associados aos itens de pedido (OrderItem) do objeto Order
        public List<Order> getOrders() {
                return items.stream().map(x -> x.getOrder()).toList();
        }
        
        // ---equals and hashcode--- \\
        @Override
        public int hashCode() {
                final int prime = 31;
                int result = 1;
                result = prime * result + ((id == null) ? 0 : id.hashCode());
                return result;
        }

        @Override
        public boolean equals(Object obj) {
                if (this == obj)
                        return true;
                if (obj == null)
                        return false;
                if (getClass() != obj.getClass())
                        return false;
                Product other = (Product) obj;
                if (id == null) {
                        if (other.id != null)
                                return false;
                } else if (!id.equals(other.id))
                        return false;
                return true;
        } 

}
