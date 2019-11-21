package ufc.npi.proficiencia.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ufc.npi.proficiencia.service.NotificationService;


@Service
public class EmailServiceImpl implements NotificationService {


    @Autowired
    private JavaMailSender sender;


    @Override
    public void enviarMensagem(String assunto, String corpo, String destinatario) {
        sender.send(constructMail(assunto, corpo, destinatario));
    }

    private SimpleMailMessage constructMail(String assunto, String corpo, String destinatario) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(assunto);
        email.setText(corpo);
        email.setTo(destinatario);
        email.setFrom("naoresponda@proficiencia.ufc.br");
        return email;
    }

}