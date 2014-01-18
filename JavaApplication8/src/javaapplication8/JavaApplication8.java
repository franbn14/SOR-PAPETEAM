/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication8;

import CEN.ClientCEN;
import CEN.RequestCEN;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;


/**
 *
 * @author fran
 */
public class JavaApplication8 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
      
      /*   String peti;
         peti=darPeticiones("11111111A");
         System.out.println(peti);
         ArrayList<String> listarequest= new ArrayList<String>();
         Gson gson = new Gson();
        java.lang.reflect.Type collectionType = new TypeToken<ArrayList<String>>(){}.getType();*/
       // String requeststring= darOfertasSelection(4);
       // System.out.println(requeststring);
                /*
     
      if(!requeststring.equals("") && requeststring!=null){
        listarequest = gson.fromJson(requeststring, collectionType);
        System.out.println(listarequest.get(0));
      }*/
        
        System.out.println(insert("ruedas anchas", "05-10-2014", 45.0, 12, "verde", 12,45.0,2, false, false));
        
       
       
    }

    private static int getIdDes(java.lang.String nif) {
        servicios.DarIdDesguacebyCif_Service service = new servicios.DarIdDesguacebyCif_Service();
        servicios.DarIdDesguacebyCif port = service.getDarIdDesguacebyCifPort();
        return port.getIdDes(nif);
    }

  
    private static String darPeticiones(java.lang.String nif) {
        servicios.DarPeticionesNifP_Service service = new servicios.DarPeticionesNifP_Service();
        servicios.DarPeticionesNifP port = service.getDarPeticionesNifPPort();
        return port.darPeticiones(nif);
    }

    private static String darUnidadId(int id) {
        servicios.DarUnidades_Service service = new servicios.DarUnidades_Service();
        servicios.DarUnidades port = service.getDarUnidadesPort();
        return port.darUnidadId(id);
    }

    private static String darUnidadId_1(int id) {
        servicios.DarUnidades_Service service = new servicios.DarUnidades_Service();
        servicios.DarUnidades port = service.getDarUnidadesPort();
        return port.darUnidadId(id);
    }

    private static String darTodasUnidades() {
        servicios.DarUnidades_Service service = new servicios.DarUnidades_Service();
        servicios.DarUnidades port = service.getDarUnidadesPort();
        return port.darTodasUnidades();
    }

    private static String darOfertasByR(int idR) {
        servicios.DarOfertasRequest_Service service = new servicios.DarOfertasRequest_Service();
        servicios.DarOfertasRequest port = service.getDarOfertasRequestPort();
        return port.darOfertasByR(idR);
    }

    private static String darOfertasByRequestOk(int idR) {
        servicios.DarOfertasRequestOk_Service service = new servicios.DarOfertasRequestOk_Service();
        servicios.DarOfertasRequestOk port = service.getDarOfertasRequestOkPort();
        return port.darOfertasByRequestOk(idR);
    }

    private static String darOfertasSelection(int idR) {
        servicios.DarOfertasSeleccionadas_Service service = new servicios.DarOfertasSeleccionadas_Service();
        servicios.DarOfertasSeleccionadas port = service.getDarOfertasSeleccionadasPort();
        return port.darOfertasSelection(idR);
    }

    private static void borrar(int id) {
        servicios.BorrarPeticion_Service service = new servicios.BorrarPeticion_Service();
        servicios.BorrarPeticion port = service.getBorrarPeticionPort();
        port.borrar(id);
    }

    private static String aceptarOfertasDe(java.lang.String idS) {
        servicios.AceptarOfertas_Service service = new servicios.AceptarOfertas_Service();
        servicios.AceptarOfertas port = service.getAceptarOfertasPort();
        return port.aceptarOfertasDe(idS);
    }

    private static int insert(java.lang.String tipo, java.lang.String fechaTope, double tamanyo, int tamUnidad, java.lang.String color, int cantidad, double precioMax, int usuario, boolean autoElect, boolean finalizado) {
        servicios.NewPeticion_Service service = new servicios.NewPeticion_Service();
        servicios.NewPeticion port = service.getNewPeticionPort();
        return port.insert(tipo, fechaTope, tamanyo, tamUnidad, color, cantidad, precioMax, usuario, autoElect, finalizado);
    }

 
 

   
    }

   



