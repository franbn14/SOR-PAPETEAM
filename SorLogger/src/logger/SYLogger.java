/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logger;
import java.security.Timestamp;
import java.util.Date;
import java.util.logging.SimpleFormatter;
/**
 *
 * @author Gustavo
 */
public class SYLogger extends  SorLogger {
    private static String message;
              
    public SYLogger() {
        super("Log Cliente","sy-log.log");
    }
                 
    public void setLogMessage(int type, String client, String extraInfo) {
        message="";
        switch(type) {
            case 1: message="Sesión iniciada por "+client;
                break;
            
            case 2: message="Creada peticion "+extraInfo+" por "+client;
                break;
               
            case 3: message="Registro completado por "+client;
                break;
                
            case 4: message="Sesión finalizada por "+client;
                break;
                
            case -1: message="Error al iniciar sesión: "+extraInfo+" "+client;
                break;
            
            case -2: message="Error. El usuario "+ client +" ya existe";
                break;
                
            case -3: message="Error. La peticion no se ha podido realizar: "+extraInfo+" "+client;
                break;
        }
        writeLog(message,type);
    }
}