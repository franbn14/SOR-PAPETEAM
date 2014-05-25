/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package monitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import logger.ClientLogger;
import org.omg.CORBA.ORB;
import logger.MonitorLogger;

/**
 *
 * @author fran
 */
public class ServiceMonitor {
    private static List<String> commands;
    private static String ip;
    private static String port;
    private static boolean running;
    
    public static void init() {
        boolean isWindows = System.getProperty("os.name").toLowerCase().contains("win");
        
        running=true;
        ip = "25.117.217.175";//"192.168.43.151";
        port="8080";
        commands = new ArrayList<String>();
        commands.add("ping");
        commands.add((isWindows?"-n":"-c"));
        commands.add("1");
        commands.add(ip);        
    }
    public static boolean doPing() throws IOException, InterruptedException {
        String s = null;
        //System.out.println("Ping to "+commands.get(commands.size()-1));
        ProcessBuilder pb = new ProcessBuilder(commands);
        Process process = pb.start();

        /*BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
        BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

        // read the output from the command
        System.out.println("Salida:\n");
        while ((s = stdInput.readLine()) != null)
        {
          System.out.println(s);
        }

        // read any errors from the attempted command
        System.out.println("Salida error:\n");
        while ((s = stdError.readLine()) != null)
        {
          System.out.println(s);
        }*/
        int result=process.waitFor();
        
        return result==0;
    }
    
    public static void start() throws InterruptedException, IOException {
        String newIp= "25.162.2.139";//"192.168.43.56";
        boolean changed=false;
        
        init();
        ServicePublisher.publish(ip,port);
        
        while(running) {                   
            if(!doPing()) {                                
                if(!changed) {
                    MonitorLogger.setLogMessage(-1,ip,"");
                    commands.set(commands.lastIndexOf(ip), newIp);    
                    
                    if(doPing()) { //Si la nueva ip está disponible
                        changed=true;
                        
                        ServicePublisher.publish(newIp, port);
                        MonitorLogger.setLogMessage(2,newIp,"");
                        commands.set(commands.lastIndexOf(newIp), ip);
                        System.out.println("cambiamos a :" + newIp);
                    }        
                    else 
                        MonitorLogger.setLogMessage(-2,newIp,"");
                }                                        
            }
            else if(changed) { //si hemos cambiado la ip
                
                ServicePublisher.publish(ip,port);
                changed=false;
                MonitorLogger.setLogMessage(2,ip,"");
                System.out.println("cambiamos a :" + ip);
            }                                            
            Thread.sleep(3000);
        }
    }
    
    /**
     * @param args the command line arguments
     */    
    public static void main(String[] args) throws IOException, InterruptedException {                           
        start();        
    }            
}
