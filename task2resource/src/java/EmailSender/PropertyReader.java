/*
 * This is a class that parses ini-file cinfiguration.
 * Using Ini4j.
 */
package EmailSender;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import org.ini4j.Ini;
import org.ini4j.IniPreferences;

/**
 *
 * @author Igor Petrov
 */
public class PropertyReader {

    private static final String FILEPATH = "D:\\Uni\\NC\\EmailSenderConfig.ini";

    public PropertyReader() {
        try {
            pref = new IniPreferences(new Ini(new File(FILEPATH)));
        } catch (IOException ex) {
            Logger.getLogger(PropertyReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        getNewConfig();
    }

    private void getNewConfig() {
        PROPERTIES = pref.node("PROPERTIES");
        email = PROPERTIES.get("mail", null);
        password = PROPERTIES.get("password", null);
        smtp_server = PROPERTIES.get("smtp_server", null);
        port = PROPERTIES.get("port", null);
        encryption = PROPERTIES.get("encryption", null);
    }
    
    public String getEmail(){
        return email;
    }
    
    public String getPassword(){
        return password;
    }
    
    public String getSMTPServer(){
        return smtp_server;
    }
    
    public String getPort(){
        return port;
    }
    
    public String getEncryption(){
        return encryption;
    }
    
    private String email = null;
    private String password = null;
    private String smtp_server = null;
    private String port = null;
    private String encryption = null;
    private Preferences pref = null;
    private Preferences PROPERTIES = null;
}
