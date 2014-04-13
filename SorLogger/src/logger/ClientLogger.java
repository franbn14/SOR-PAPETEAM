/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logger;

import java.util.logging.SimpleFormatter;

/**
 *
 * @author alberto
 */
public class ClientLogger extends SorLogger {
    private static String message;
              
    public ClientLogger() {
        super("Log Cliente","logCliente.log");
    }
                 
    public void setLogMessage(int type, String client, String extraInfo) {                
        message="";
        
        switch(type) {
            case 1: message="Sesión iniciada por "+client;
                break;
            
            case 2: message="Creada solicitud "+extraInfo+" por "+client;
                break;
               
            case 3: message="Eliminada solicitud "+extraInfo+" por "+client;
                break;
                
            case 4: message="Sesión finalizada por "+client;
                break;
                
            case 5: message="Registrado usuario "+client;
                break;
                
            case -1: message="Error al iniciar sesión: "+extraInfo+" "+client;
                break;
        }        
        writeLog(message,type);
    }
}