package br.com.fornecedores.produto.model;
import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "produto")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(generator = "produto_seq_gen")
    @SequenceGenerator(name = "produto_seq_gen", sequenceName = "produto_seq", allocationSize = 1)
    private Long id;

    @Column(name = "gtin", unique = true, length = 256, nullable = false)
    private String gtin;

    @Column(name = "nome", nullable = false, length = 256)
    private String nome;
}