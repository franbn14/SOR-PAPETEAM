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
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.mail.MessagingException;

/**
 *
 * @author fran
 */
@WebService(serviceName = "RegistroCliente")
public class RegistroCliente {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Registro_Cli")
    public String Registro_Cli(@WebParam(name = "Nif") String Nif, @WebParam(name = "Nombre") String Nombre, @WebParam(name = "Direccion") String Direccion, @WebParam(name = "Password") String Password, @WebParam(name = "Apellidos") String Apellidos, @WebParam(name = "Fecha") String Fecha, @WebParam(name = "Email") String email) {
        String error = "";
        ClientCEN client = ClientCEN.getByNIF(Nif);
        if (client != null) {
            error = "Error: NIF ya registrado";
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
        return error;
    }
}
