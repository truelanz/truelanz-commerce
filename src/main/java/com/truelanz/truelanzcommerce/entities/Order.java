package com.truelanz.truelanzcommerce.entities;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_order")
@Getter
@Setter
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private OrderStatus status;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE") //Salvo como UTC por padrão.
    private Instant moment;

    //Constructor
    public Order(Long id, OrderStatus status, Instant moment) {
        this.id = id;
        this.status = status;
        this.moment = moment;
    }

    @ManyToOne // RELACIONAMENTO - Muitos para um
    @JoinColumn(name = "client_id") // criará uma foreign key na tabela "tb_order" com o id (primary key) da classe User - da tabela tb_user.
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

    // Retorna uma lista de produtos que estão associados aos itens de pedido (OrderItem) do objeto Product
    public List<Product> getProducts() {
        return items.stream().map(x -> x.getProduct()).toList();
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
        Order other = (Order) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
