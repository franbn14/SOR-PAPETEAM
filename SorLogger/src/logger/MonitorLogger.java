/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logger;

import java.util.Date;
import static logger.SorLogger.setLogger;
import static logger.SorLogger.writeLog;

/**
 *
 * @author alberto
 */
public class MonitorLogger extends SorLogger{
    private static String message;    
    
     public static void setLogMessage(int type, String ip, String extraInfo) {                                
        Date today = new Date();
        String date=today.getDate()+"-"+(today.getMonth()+1)+"-"+(today.getYear()+1900);
        setLogger("Monitor Log","logMonitor"+date+".log");
        
        message="";
        
        switch(type) {
            case 1: message="Servidor secundario detectado "+ip;
                break;
            
            case 2: message="Servidor principal recuperado "+ip;
                break;       
                
            case 3: message="Servicios web publicados en "+ip;
                break;                                                           
                                                            
            case -1: message="Servidor principal perdido: "+ip;
                break;
                
            case -2: message="Servidor secundario perdido: "+ip;
                break;
                
            case -3: message="Error al conectar con servicios web en "+ip+": "+extraInfo;
                break;
        }        
        writeLog(message,type);
    }
}
