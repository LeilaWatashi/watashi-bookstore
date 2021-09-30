package com.watashi.bookstore.strategy.email.troca;

import com.watashi.bookstore.strategy.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.watashi.bookstore.domain.*;
import com.watashi.bookstore.repository.PessoaRepository;
import com.watashi.bookstore.util.EmailSender;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class EnviaEmailTrocaAprovadaComCupom implements IStrategy {
	
	@Autowired
	private EmailSender emailSender;
	
	@Autowired
	private PessoaRepository pessoaRepository;

    @Override
    public String processar(EntidadeDominio entidade) {
    	
    	if(entidade instanceof Transicao) {

			Transicao transicao = (Transicao) entidade;

    		Pedido pedido = transicao.getPedido();
    		
    		Usuario usuario = pedido.getCliente().getUsuario();
    		
    		Pessoa cliente = pessoaRepository.findPessoaByUsuario_Id(usuario.getId());
    		
    		Mensagem mensagem = new Mensagem();
    		
    		StringBuilder mensagemTexto = new StringBuilder();
    		
    		mensagem.setAssunto("Solicitação de troco do pedido " + pedido.getCodigoPedido() + " foi aprovado.");

    		mensagemTexto.append("Prezado " + cliente.getNome() + " " + cliente.getSobrenome() + ", ");
    		mensagemTexto.append("este email foi enviado para informar que a solicitação de troca foi aprovado para o pedido " + pedido.getCodigoPedido() + ".\n");
    		mensagemTexto.append("Seu código de cupom é " + transicao.getCupom().getCodigo() + " no valor de R$ " + transicao.getCupom().getValor() + ".\n");
    		mensagemTexto.append("Caso queira realizar outra compra, peço que realize o pedido em nosso site.\n\n");
    		mensagemTexto.append("Realm of Cards agradece sua preferência e te desejamos um ótimo dia.");
    		
    		mensagem.setMensagem(mensagemTexto);
    		
    		emailSender.enviaEmail(usuario, mensagem);   		
    		
    	}
    	
        return null;
    }
}
