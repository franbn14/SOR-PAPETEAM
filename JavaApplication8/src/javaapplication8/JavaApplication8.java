/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication8;

import CEN.RequestCEN;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


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
       
    
         String peti;
         peti=darPeticiones("11111111A");
         System.out.println(peti);
         ArrayList<RequestCEN> listarequest= new ArrayList<RequestCEN>();
        Gson gson = new Gson();
     
        java.lang.reflect.Type collectionType = new TypeToken<ArrayList<RequestCEN>>(){}.getType();
        String requeststring= darPeticiones("11111111A");
      if(!requeststring.equals("") && requeststring!=null){
        listarequest = gson.fromJson(requeststring, collectionType);
        System.out.println(listarequest.get(0).getColor());
      }
         
       
          
     
        
    }

    private static int getIdDes(java.lang.String nif) {
        servicios.DarIdDesguacebyCif_Service service = new servicios.DarIdDesguacebyCif_Service();
        servicios.DarIdDesguacebyCif port = service.getDarIdDesguacebyCifPort();
        return port.getIdDes(nif);
    }

    private static int insert(java.lang.String tipo, java.lang.String fechaTope, double tamanyo, int tamUnidad, java.lang.String color, int cantidad, double precioMax, int usuario, boolean autoElect, boolean finalizado) {
        servicios.NewPeticion_Service service = new servicios.NewPeticion_Service();
        servicios.NewPeticion port = service.getNewPeticionPort();
        return port.insert(tipo, fechaTope, tamanyo, tamUnidad, color, cantidad, precioMax, usuario, autoElect, finalizado);
    }

    private static String darPeticiones(java.lang.String nif) {
        servicios.DarPeticionesNifP_Service service = new servicios.DarPeticionesNifP_Service();
        servicios.DarPeticionesNifP port = service.getDarPeticionesNifPPort();
        return port.darPeticiones(nif);
    }

 
 

   
    }

   



