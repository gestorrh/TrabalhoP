package ufc.npi.proficiencia.service;


public interface NotificationService {

    void enviarMensagem(String assunto, String corpo, String destinatario);

}