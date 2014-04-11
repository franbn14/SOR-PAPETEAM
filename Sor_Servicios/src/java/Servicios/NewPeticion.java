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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKey;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import logger.ClientLogger;
import security.AES;
import security.KeysManager;

/**
 *
 * @author fran
 */
@WebService(serviceName = "NewPeticion")
public class NewPeticion {

    @WebMethod(operationName = "InsertWeb")
    public int InsertWeb(@WebParam(name = "tipo") String tipo, @WebParam(name = "fechaTope") String fechaTope, @WebParam(name = "tamanyo") Double tamanyo, @WebParam(name = "tamUnidad") int tamUnidad, @WebParam(name = "color") String color, @WebParam(name = "cantidad") Integer cantidad, @WebParam(name = "precioMax") Double precioMax, @WebParam(name = "usuario") int usuario, @WebParam(name = "autoElect") boolean autoElect, @WebParam(name = "finalizado") boolean finalizado, @WebParam(name = "caducada") boolean caducada) {
        //TODO write your implementation code here:
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
        String strFecha = fechaTope;
        Date fecha = null;
        try {
            fecha = formatoDelTexto.parse(strFecha);
            fecha.setMonth(fecha.getMonth());


        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        ClientCEN cli = ClientCEN.getByID(usuario);

        RequestCEN r = new RequestCEN(fecha, tipo, tamanyo, tamUnidad, color, cantidad, precioMax, cli, autoElect, finalizado, caducada);
        ClientLogger.setLogMessage(2, cli.getNIF(), r.getCode() + "");

        return r.insert();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Insert")
    public String Insert(@WebParam(name = "id") int id, @WebParam(name = "tipo") String tipo, @WebParam(name = "fechaTope") String fechaTope, @WebParam(name = "tamanyo") String tamanyo, @WebParam(name = "tamUnidad") String tamUnidad, @WebParam(name = "color") String color, @WebParam(name = "cantidad") String cantidad, @WebParam(name = "precioMax") String precioMax, @WebParam(name = "usuario") String usuario, @WebParam(name = "autoElect") String autoElect, @WebParam(name = "finalizado") String finalizado, @WebParam(name = "caducada") String caducada) {

        SecretKey key = (SecretKey) KeysManager.GetInstance().getKey(id);
        
        Double tamanyoDouble = 0.0;
        int tamUnidadInt = 0;
        Integer cantidadInt = 0;
        Double precioMaxDouble = 0.0;
        int usuarioInt = -1;
        boolean autoElectBool = false;
        boolean finalizadoBool = false;
        boolean caducadaBool = false;
        try {
            tipo =  AES.decrypt(tipo, key);
            fechaTope = AES.decrypt(fechaTope, key);
            tamanyo = AES.decrypt(tamanyo, key);
            tamanyoDouble = Double.parseDouble(tamanyo);
            tamUnidad = AES.decrypt(tamUnidad, key);
            tamUnidadInt = Integer.parseInt(tamUnidad);
            color = AES.decrypt(color, key);
            cantidad = AES.decrypt(cantidad, key);
            cantidadInt = Integer.parseInt(cantidad);
            precioMax = AES.decrypt(precioMax, key);
            precioMaxDouble = Double.parseDouble(precioMax);
            usuario = AES.decrypt(usuario, key);
            usuarioInt = Integer.parseInt(usuario);
            autoElect = AES.decrypt(autoElect, key);
            autoElectBool = Boolean.parseBoolean(autoElect);
            finalizado = AES.decrypt(finalizado, key);
            finalizadoBool = Boolean.parseBoolean(finalizado);
            caducada = AES.decrypt(caducada, key);
            caducadaBool = Boolean.parseBoolean(caducada); 
        } catch (Exception ex) {
            System.err.println(ex);
        }
        

        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
        String strFecha = fechaTope;
        Date fecha = null;
        try {
            fecha = formatoDelTexto.parse(strFecha);
            fecha.setMonth(fecha.getMonth());


        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        ClientCEN cli = ClientCEN.getByID(usuarioInt);

        RequestCEN r = new RequestCEN(fecha, tipo, tamanyoDouble, tamUnidadInt, color, cantidadInt, precioMaxDouble, cli, autoElectBool, finalizadoBool, caducadaBool);
        ClientLogger.setLogMessage(2, cli.getNIF(), r.getCode() + "");

        Integer code = r.insert();
        String codeEnc = "";
        try {
            codeEnc = AES.encrypt(code.toString(), key);
        } catch (Exception ex) {
            System.err.println(ex);
        }
        
        
        return codeEnc;
    }
}
