package com.watashi.bookstore.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.watashi.bookstore.domain.*;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Component
public class LivroDTO extends EntidadeDominio implements IDTO {

    private Integer id;

    private String nome;

    private String descricao;

    private LocalDate dataCadastro;

    private Double valorVenda;

    private GrupoPrecificacao grupoPrecificacao;

    private Integer quantidadeDisponivel;

    private Integer quantidadeEstoque;

    private String imagemPath;

    private CategoriaLivro categoriaLivro;

    private Status status;

    private MultipartFile imagemArquivo;

    @Value("${imagem-path}")
    private String caminhoDaImagem;

    @Override
    public EntidadeDominio parseEntityToDTO(EntidadeDominio dominio) {

        if(dominio instanceof Livro){

            Livro livro = (Livro) dominio;
            LivroDTO livroDTO = new LivroDTO();

            livroDTO.setId(livro.getId());
            livroDTO.setNome(livro.getNome());
            livroDTO.setDescricao(livro.getDescricao());
            livroDTO.setDataCadastro(livro.getDataCadastro());
            livroDTO.setValorVenda(livro.getValorCompra() * livro.getGrupoPrecificacao().getValor());
            livroDTO.setQuantidadeDisponivel(livro.getQuantidadeDisponivel());
            livroDTO.setQuantidadeEstoque(livro.getQuantidadeEstoque());
            livroDTO.setCategoriaLivro(livro.getCategoriaLivro());

            return livroDTO;
        }
        return null;
    }

    @Override
    public EntidadeDominio parseDTOToEntity(IDTO dto) {

        if(dto instanceof LivroDTO){

            LivroDTO livroDTO = (LivroDTO) dto;
            Livro livro = new Livro();

            livro.setId(livroDTO.id);
            livro.setNome(livroDTO.nome);
            livro.setDescricao(livroDTO.descricao);
            livro.setStatus(livroDTO.status);
            livro.setQuantidadeDisponivel(livroDTO.quantidadeDisponivel);
            livro.setCategoriaLivro(livroDTO.categoriaLivro);
            livro.setImagemPath(caminhoDaImagem + livroDTO.imagemArquivo.getOriginalFilename());

            return livro;
        }
        return null;
    }
}

