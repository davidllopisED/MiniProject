/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package email;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {
    /**
    * Constructor para envio de email
    */
    public Mail(){}

    /**
     * Envia un correo al destinatario. 
     * @param to Correo al que se envia
     * @param subject CC
     * @param text Texto a enviar
     */
    public void enviaCorreo(String to, String subject, String text){
        try {
            Properties properties = new Properties();
            properties.setProperty("mail.smtp.host", "smtp.gmail.com");
            properties.setProperty("mail.smtp.starttls.enable", "true");
            properties.setProperty("mail.smtp.port", "587");
            properties.setProperty("mail.smtp.auth", "true");
        
            Session session = Session.getDefaultInstance(properties);
            Properties properties1 = new Properties();
            properties1.load(Mail.class.getClassLoader().getResourceAsStream("Mail.properties"));
            
            MimeMessage mimeMessage = new MimeMessage(session);
            
            mimeMessage.setFrom(new InternetAddress(properties1.getProperty("from")));
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(text);
            
            Transport transport = session.getTransport("smtp");
            
            transport.connect(properties1.getProperty("from"),properties1.getProperty("password"));
            
            transport.sendMessage(mimeMessage, mimeMessage.getRecipients(Message.RecipientType.TO));
            transport.close();
        } catch (IOException ex) {
            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AddressException ex) {
            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}