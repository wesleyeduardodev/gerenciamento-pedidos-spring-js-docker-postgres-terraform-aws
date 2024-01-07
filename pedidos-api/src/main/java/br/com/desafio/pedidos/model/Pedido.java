package br.com.desafio.pedidos.model;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pedido")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(generator = "pedido_seq_gen")
    @SequenceGenerator(name = "pedido_seq_gen", sequenceName = "pedido_seq", allocationSize = 1)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_fornecedor", nullable = false)
    private Fornecedor fornecedor;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ItemPedido> itens;

    @Column(name = "data_pedido")
    private LocalDateTime dataPedido;

    @PrePersist
    void createdAt() {
        this.dataPedido = LocalDateTime.now();
    }
}
