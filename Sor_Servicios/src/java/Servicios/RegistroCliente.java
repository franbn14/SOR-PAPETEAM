/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import CEN.ClientCEN;
import Email.Email;
import Email.EmailFactoria;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKey;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.mail.MessagingException;
import logger.ClientLogger;
import security.AES;
import security.KeysManager;

/**
 *
 * @author fran
 */
@WebService(serviceName = "RegistroCliente")
public class RegistroCliente {

    KeysManager _keysManager = KeysManager.GetInstance();
    /**
     * Web service operation
     */
    @WebMethod(operationName = "Registro_Cli")
    public String Registro_Cli(@WebParam(name = "id") int id, @WebParam(name = "Nif") String Nif, @WebParam(name = "Nombre") String Nombre, @WebParam(name = "Direccion") String Direccion, @WebParam(name = "Password") String Password, @WebParam(name = "Apellidos") String Apellidos, @WebParam(name = "Fecha") String Fecha, @WebParam(name = "Email") String email) {
        SecretKey key = (SecretKey)_keysManager.getKey(id);
        String error = "";
        
        try {
            Nif = AES.decrypt(Nif, key);
            Nombre = AES.decrypt(Nombre, key);
            Direccion = AES.decrypt(Direccion, key);
            Password = AES.decrypt(Password, key);
            Apellidos = AES.decrypt(Apellidos, key);
            Fecha = AES.decrypt(Fecha, key);
            email = AES.decrypt(email, key);
        } catch (Exception ex) {
            error = "Error: No se ha podido descifrar el AES";
            ClientLogger.setLogMessage(-2,Nif,error);
            return error;
        }
        
        
        ClientCEN client = ClientCEN.getByNIF(Nif);
         int type=5;
         
        if (client != null) {
            error = "Error: NIF ya registrado";
            type=-2;
        } else {
            SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
            String strFecha = Fecha;
            Date fecha = null;
            try {
                fecha = formatoDelTexto.parse(strFecha);
                fecha.setMonth(fecha.getMonth() + 1);

            } catch (ParseException ex) {
                System.err.println(ex.getMessage());
            }
            client = new ClientCEN(Nombre, Apellidos, Password, Nif, Direccion, fecha, email);
            client.insert();
            Email mail = EmailFactoria.getEmail(EmailFactoria.tipoEmail.Registro, client);
            try {
                mail.send();
            } catch (MessagingException ex) {
                System.err.println(ex.getMessage());
            }

        }
        ClientLogger.setLogMessage(type,Nif,error);
        return error;
    }
}
