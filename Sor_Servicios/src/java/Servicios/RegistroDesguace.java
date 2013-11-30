/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servicios;

import CEN.ScrapYardCEN;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import java.util.Date;

/**
 *
 * @author fran
 */
@WebService(serviceName = "RegistroDesguace")
public class RegistroDesguace {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Registro")
    public String Registro(@WebParam(name = "Cif") String Cif, @WebParam(name = "Nombre") String Nombre, @WebParam(name = "Password") String Password, @WebParam(name = "Direccion") String Direccion) {
        //TODO write your implementation code here:
        
        
        String error="";
         ScrapYardCEN scry=ScrapYardCEN.getByCIF(Cif);
         
        if(scry!=null)
            error="Error: CIF ya registrado";
        else
        {
               

    
            ScrapYardCEN scry2=new ScrapYardCEN(Nombre, Password, Direccion, Cif);
            scry2.insert();
        
        }
        return error;
    }
}
