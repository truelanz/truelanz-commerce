package com.truelanz.truelanzcommerce.entities;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_order")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private OrderStatus status;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant moment;

    @ManyToOne // Muitos para um
    @JoinColumn(name = "client_id") // foreign key
    private User client;

    /*
     * Em CascadeType.ALL, todas as operações (inserção, atualização, exclusão e
     * leitura) na entidade "Order" serão propagadas para a entidade "Payment".
     * No caso específico do CascadeType.ALL, qualquer operação realizada na
     * entidade "Order" será propagada automaticamente para a entidade "Payment".
     * Por exemplo, se uma ordem for inserida no banco de dados, o pagamento
     * associado também será inserido automaticamente. Da mesma forma, se a ordem
     * for removida, o pagamento associado também será removido.
     */

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;

    @OneToMany(mappedBy = "id.order")
    @Setter(AccessLevel.NONE)
    private Set<OrderItem> items = new HashSet<>();

    // Retorna uma lista de produtos que estão associados aos itens de pedido (OrderItem) do objeto Order
    public List<Product> getProducts() {
        return items.stream().map(x -> x.getProduct()).toList();
    }
}
