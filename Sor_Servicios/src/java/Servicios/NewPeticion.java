/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servicios;
import CEN.RequestCEN;
import CEN.ClientCEN;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import logger.ClientLogger;

/**
 *
 * @author fran
 */
@WebService(serviceName = "NewPeticion")
public class NewPeticion {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Insert")
    public int Insert(@WebParam(name = "tipo") String tipo, @WebParam(name = "fechaTope") String fechaTope, @WebParam(name = "tamanyo") Double tamanyo, @WebParam(name = "tamUnidad") int tamUnidad, @WebParam(name = "color") String color, @WebParam(name = "cantidad") Integer cantidad, @WebParam(name = "precioMax") Double precioMax, @WebParam(name = "usuario") int usuario, @WebParam(name = "autoElect") boolean autoElect, @WebParam(name = "finalizado") boolean finalizado, @WebParam(name = "caducada") boolean caducada) {
        //TODO write your implementation code here:
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
              String strFecha = fechaTope;
              Date fecha = null;
   try{
        fecha = formatoDelTexto.parse(strFecha);
        fecha.setMonth(fecha.getMonth());
        

    } 
   
    catch (ParseException ex) {
        ex.printStackTrace();
    }
        ClientCEN cli=ClientCEN.getByID(usuario);
    
        RequestCEN r=new RequestCEN(fecha,tipo,tamanyo,tamUnidad,color,cantidad,precioMax,cli, autoElect, finalizado, caducada);
        ClientLogger.setLogMessage(2,cli.getNIF(),r.getCode()+"");
        
        return r.insert();
    }
}
