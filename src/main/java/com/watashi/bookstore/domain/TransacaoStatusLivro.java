package com.watashi.bookstore.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Component
@Entity
@Table(name = "transacao_status_livro")
public class TransacaoStatusLivro extends EntidadeDominio{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tsc_id")
    private Integer id;

    @Column(name = "tsc_motivo")
    private String motivo;

    @ManyToOne()
    @JoinColumn(name = "tsc_livro_id")
    private Livro livro;

    @ManyToOne()
    @JoinColumn(name = "tsc_status_id")
    private Status status;
}
