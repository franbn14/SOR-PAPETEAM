/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor;

import CAD.UserCAD;
import CEN.*;
import java.util.Date;

/**
 *
 * @author esteve
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        System.out.println("Usuarios: ");
       ScrapYardCEN cen=ScrapYardCEN.getByCIF("66666666A");
            System.out.println(cen);
        }
    
    }

