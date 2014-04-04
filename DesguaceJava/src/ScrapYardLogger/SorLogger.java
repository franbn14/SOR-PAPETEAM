/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ScrapYardLogger;

/**
 *
 * @author Gustavo
 */
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

/**
*
* @author alberto
*/
public abstract class SorLogger {

    protected static Logger logger;
    protected static FileHandler handler;
    private String type;
    private String fileName;
    
    public Logger getLogger() {
        return logger;
    }
    
    public SorLogger(String type, String fileName) {
        try {
            logger=Logger.getLogger(type,null);
            logger.setUseParentHandlers(false);
            handler=new FileHandler("log/"+fileName, true);
            
            SimpleFormatter formatter = new SimpleFormatter();
            handler.setFormatter(formatter);
            logger.addHandler(handler);
            System.out.println("Bien: "+fileName);
        } catch (IOException ex) {
            Logger.getLogger(SorLogger.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(SorLogger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void writeLog(String message, int type) {
        if(type>0)
            logger.info(message);
        else
            logger.severe(message);
    }
}