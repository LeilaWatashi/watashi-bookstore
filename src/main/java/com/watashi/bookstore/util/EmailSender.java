package com.watashi.bookstore.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.watashi.bookstore.domain.Mensagem;
import com.watashi.bookstore.domain.Usuario;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class EmailSender {
    
    @Autowired
    private JavaMailSender mailSender;

    public Boolean enviaEmail(Usuario usuario, Mensagem mensagem) {

    	SimpleMailMessage message = new SimpleMailMessage();
        
        message.setFrom("watashibookstore@outlook.com");
        message.setTo(usuario.getEmail());
        message.setSubject(mensagem.getAssunto());
        message.setText(mensagem.getMensagem().toString());

        try {
            mailSender.send(message);
            log.info("Email enviado com sucesso para ["+ usuario.getEmail() +"], assunto: ["+ mensagem.getAssunto() +"].");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Erro ao enviar email para ["+ usuario.getEmail() +"], assunto: ["+ mensagem.getAssunto() +"].");
            return false;
        }
    }
}
