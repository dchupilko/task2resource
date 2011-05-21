/*
 * This is a class that provides
 * to send an email.
 * It uses Gmail with SSL enryption protocol.
 * Connecting through port 465
 */
package EmailSender;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Igor Petrov
 */
public class SendMailSSL {
    
    /**
     * Constructor for single mail
     * @param messageTo - "whom?" (String).  Кому отправляем письмо?
     * @param messageSubject - Subject (String). Тема письма
     * @param messageBody  - text in letter. Собственно, само сообщение
     */
    
    public SendMailSSL(String messageTo, String messageSubject, String messageBody){
        primaryReader = new PropertyReader();
        email = primaryReader.getEmail();
        password = primaryReader.getPassword();
        smtp_server = primaryReader.getSMTPServer();
        port = primaryReader.getPort();
        this.messageTo = messageTo;
        this.messageSubject = messageSubject;
        this.messageBody = messageBody;
        prop = new Properties();
    }
    
    public void sendSSLEmail(){
        /*
         * Initilizing connection properties
         */
        prop.put("mail.smtp.host", smtp_server);
        prop.put("mail.port.socketFactory.port", port);
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.port", port);
        
        /*
         * Trying to make new autorised email session 
         */
        Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator(){
            @Override
           protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(email, password);
           }
        });
        
        /*
         * Creating new letter
         */
        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(messageTo));
            message.setSubject(messageSubject);
            message.setText(messageBody);
            Transport.send(message);
            System.out.println("All OK!");
        } catch(MessagingException e){
            System.out.println("Something went wrong :(");
        } finally{
            System.out.println("Complete");
        }
    }
    
    private String email = null;
    private String password = null;
    private String smtp_server = null;
    private String port = null;
    private String messageTo = null;
    private String messageSubject = null;
    private String messageBody = null;
    private PropertyReader primaryReader = null;;
    private Properties prop = null;
}
