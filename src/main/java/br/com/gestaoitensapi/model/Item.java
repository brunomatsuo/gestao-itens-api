package br.com.gestaoitensapi.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "items")
@Entity(name = "items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String marca;
    private String descricao;
    private int quantidade;
    private BigDecimal preco;
}
