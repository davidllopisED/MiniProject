/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package email;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {
    private String usuario;
    private String pass;
    /**
    * Constructor para envio de email
    * @param usuario User del email
    * @param pass Password del email
    */
    public Mail(String usuario, String pass){
        this.usuario=usuario;
        this.pass=pass;
    }

    /**
     * 
     * @param from Correo desde el que se envia
     * @param to Correo al que se envia
     * @param subject CC
     * @param text Texto a enviar
     */
    public void enviaStartTLS(String from, String to, String subject, String text){
        final String username = usuario;
        final String password = pass;

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true"); //Usar autenticación mediante usuario y clave
        props.put("mail.smtp.starttls.enable", "true");//Para conectar de manera segura al servidor SMTP
        props.put("mail.smtp.host", "smtp.gmail.com"); //El servidor SMTP de Google
        props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);

            System.out.println("Enviado");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }


}