package com.watashi.bookstore.strategy.email.devolucao;

import com.watashi.bookstore.domain.*;
import com.watashi.bookstore.repository.PessoaRepository;
import com.watashi.bookstore.strategy.IStrategy;
import com.watashi.bookstore.util.EmailSender;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class EnviaEmailDevolucaoRecusada implements IStrategy {
	
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
    		
    		mensagem.setAssunto("Solicitação de devolução foi recusada referente ao pedido " + pedido.getCodigoPedido() + ".");

    		mensagemTexto.append("Prezado " + cliente.getNome() + " " + cliente.getSobrenome() + ", ");
    		mensagemTexto.append("este email foi enviado para comunicar que sua solicitação de devolução do pedido " + pedido.getCodigoPedido() + " foi recusada.\n");
    		mensagemTexto.append("Caso queira realizar outra compra, peço que realize o pedido em nosso site.\n\n");
    		mensagemTexto.append("Realm of Cards agradece sua preferência e te desejamos um ótimo dia.");
    		
    		mensagem.setMensagem(mensagemTexto);
    		
    		emailSender.enviaEmail(usuario, mensagem);   		
    		
    	}
    	
        return null;
    }
}
