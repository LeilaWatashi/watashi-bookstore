package com.watashi.bookstore.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "livro")
public class Livro extends EntidadeDominio implements Comparable<Livro>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "liv_id")
    private Integer id;

    @Column(name = "liv_codigo")
    private String codigo;

    @Column(name = "liv_nome")
    private String nome;

    @Column(name = "liv_descricao")
    private String descricao;

    @Column(name = "liv_data_cad")
    private LocalDate dataCadastro;

    @Column(name = "liv_valor_compra")
    private Double valorCompra;

    @Column(name = "liv_quantidade_disponivel")
    private Integer quantidadeDisponivel;

    @Column(name = "liv_quantidade_estoque")
    private Integer quantidadeEstoque;

    @Column(name = "liv_imagem_path")
    private String imagemPath;

    @ManyToOne()
    @JoinColumn(name = "liv_categoria_id")
    private CategoriaLivro categoriaLivro;

    @ManyToOne
    @JoinColumn(name = "liv_status_id")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "liv_grupo_precificacao_id")
    private GrupoPrecificacao grupoPrecificacao;

    @Override
    public int compareTo(@NotNull Livro o) {
        Livro outroLivro = o;
        return this.getId().compareTo(outroLivro.getId());
    }
}
