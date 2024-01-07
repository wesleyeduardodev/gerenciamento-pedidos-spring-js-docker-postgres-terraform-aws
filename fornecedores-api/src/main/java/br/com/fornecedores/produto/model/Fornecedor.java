package br.com.fornecedores.produto.model;
import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "fornecedor")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Fornecedor {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "cnpj", unique = true, nullable = false, length = 14)
    private String cnpj;

    @Column(name = "nome", nullable = false, length = 256)
    private String nome;
}
