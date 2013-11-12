/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor;

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
        ClientCEN cliente = new ClientCEN("usuario", "apellido", "pass", "11111111W", "calle falsa", new Date(1991, 11, 14));
        cliente.insert();
        cliente.delete();
    }
}
