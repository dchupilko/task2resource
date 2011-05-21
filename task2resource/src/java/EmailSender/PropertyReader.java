/*
 * This is a class that parses ini-file cinfiguration.
 * Using Ini4j.
 * http://ini4j.sourceforge.net/
 */
package EmailSender;

/*
 * Если не видит Ini4J, его можно найти в
 * \trunk\task2resource\build\web\WEB-INF\lib\
 */
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import org.ini4j.Ini;
import org.ini4j.IniPreferences;

/**
 * @author Igor Petrov
 */

public class PropertyReader {

    //private static final String FILEPATH = ".\\PropertyFiles\\EmailSenderConfig.ini";
    /*
     * if somebody has Linux
     */
    private static final String FILEPATH =  "." + File.separator 
                                          + "PropertyFiles" + File.separator 
                                          + "EmailSenderConfig.ini";

    public PropertyReader() {
        try {
            pref = new IniPreferences(new Ini(new File(FILEPATH)));
        } catch (IOException ex) {
            Logger.getLogger(PropertyReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        getNewConfig();
    }

    /*
     * Trying to read [PROPERTIES] node
     */
    private void getNewConfig() {
        PROPERTIES  = pref.node("PROPERTIES");
        email       = PROPERTIES.get("email", null);
        password    = PROPERTIES.get("password", null);
        smtp_server = PROPERTIES.get("smtp_server", null);
        port        = PROPERTIES.get("port", null);
        encryption  = PROPERTIES.get("encryption", null);
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
    
    /*
     * Variables to read info from *.ini file
     */
    private String email           = null;
    private String password        = null;
    private String smtp_server     = null;
    private String port            = null;
    private String encryption      = null;
    
    private Preferences pref       = null;
    
    /*
     * ini file has [PROPERTIES] node
     */
    private Preferences PROPERTIES = null;
}
