/*
 * This is a class that provides
 * to send an email.
 * It uses Gmail with SSL enryption protocol.
 * Connecting through port 465
 */
package EmailNotificator;


import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendNotification {
    
    /**
     * Constructor for single mail
     * @param messageTo - "whom?" (String).  Who will recieve a message
     * @param messageSubject - Subject (String). What is our subject (topic)
     * @param messageCode - What template should be sent
     */
    
    public SendNotification(String messageTo, String messageSubject, int messageCode){    
        primaryReader             = new PropertyReader();
        prop                      = new Properties();
         
    	email                     = primaryReader.getEmail();
        password                  = primaryReader.getPassword();
        smtp_server               = primaryReader.getSMTPServer();
        port                      = primaryReader.getPort();
        
        this.messageToUser        = messageTo;
        this.messageSubject       = messageSubject;
        this.messageCode          = messageCode;
                
        isSingleMessage           = true;
       
        tempLoader                = new MessageTemplateLoader(this.messageCode);
        
        this.messageBody = tempLoader.getTemplateMessage();
        
        try {
            singleInternetAddress = new InternetAddress(this.messageToUser);
        } catch (AddressException ex) {
            System.out.println("Someting wrong in an email address");
        }
        
        sendSSLEmail();
    }
    
    public SendNotification(String[] messageTo, String messageSubject, int messageCode){
        primaryReader           = new PropertyReader();
        prop                    = new Properties();
        
        
        email                   = primaryReader.getEmail();
        password                = primaryReader.getPassword();
        smtp_server             = primaryReader.getSMTPServer();
        port                    = primaryReader.getPort();
        
        this.messageToUsers     = messageTo;
        this.messageSubject     = messageSubject;
        this.messageCode        = messageCode;
        
        isSingleMessage         = false;
        
        tempLoader              = new MessageTemplateLoader(this.messageCode);
        
        this.messageBody = tempLoader.getTemplateMessage();
        
        multipleInternetAddress = new InternetAddress[messageToUsers.length];
        
        for(int i = 0;i< messageToUsers.length;i++){
            try {
                multipleInternetAddress[i] = new InternetAddress(this.messageToUsers[i]);
            } catch (AddressException ex) {
                System.out.println("Someting went wrong!!!");
            }
        }
        
        sendSSLEmail();
    }
    
    private void sendSSLEmail(){
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
            if(isSingleMessage == true){
                message.setRecipient(Message.RecipientType.TO, singleInternetAddress);
            } else if(isSingleMessage == false){
                message.setRecipients(Message.RecipientType.TO, multipleInternetAddress);
            }
            message.setSubject(messageSubject);
            message.setText(messageBody);
            Transport.send(message);
            System.out.println("All OK!");
        } catch(MessagingException e){
            System.out.println("Cant transport message");
        } finally{
            System.out.println("Complete");
        }
    }
    

    private String email                 = null;
    private String password              = null;
    private String smtp_server           = null;
    private String port                  = null;
    private String messageToUser         = null;
    private String messageSubject        = null;
    private String messageBody           = null;
    private String[] messageToUsers      = null;
    
    private int messageCode = 0;
    
    private boolean isSingleMessage      = true;
        
    private PropertyReader primaryReader = null;
    
    private InternetAddress singleInternetAddress     = null;
    private InternetAddress[] multipleInternetAddress = null;
    
    private Properties prop                  = null;
    private MessageTemplateLoader tempLoader = null;
    
}