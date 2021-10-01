package com.watashi.bookstore.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "item")
public class Item extends EntidadeDominio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itm_id")
    private Integer id;

    @Column(name = "itm_quantidade")
    private Integer quantidade;
    
    @Column(name = "itm_quantidade_troca")
    private Integer quantidadeTroca;
    
    @Column(name = "itm_quantidade_devolucao")
    private Integer quantidadeDevolucao;

    @ManyToOne()
    @JoinColumn(name = "itm_livro_id")
    private Livro livro;
    
    @ManyToOne()
    @JoinColumn(name = "itm_status_id")
    private Status statusItem;

}