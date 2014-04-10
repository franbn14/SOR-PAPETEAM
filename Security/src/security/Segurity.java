/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package security;


import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import sun.misc.BASE64Decoder;
import security.RSA;
import sun.misc.BASE64Encoder;


/**
 *
 * @author esteve
 */
public class Segurity {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        /*String msg = "Hola mundo";
        
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(1024);
        KeyPair keyPair = keyGen.genKeyPair();
        
        Signature sig = Signature.getInstance("MD5WithRSA");
        sig.initSign(keyPair.getPrivate());
        sig.update(msg.getBytes());
        
        byte[] signature = sig.sign();
        
        
        Signature sig2 = Signature.getInstance("MD5WithRSA");
        sig2.initVerify(keyPair.getPublic());
        sig2.update(msg.getBytes());
        
         if (sig2.verify(signature)) { 
             System.out.println( "Verificación positiva" ); 
         }
         else System.out.println( "Fallo la verificacion" );*/
        
        /**** Simulación comunicación servidor - cliente ****/
        
        /*String msgServer = "Hola mundo";
         
         GenKeys ServerKeys = new GenKeys();
         GenKeys ClientKeys = new GenKeys();
         
         //Simulo que la clave publica del cliente será un string
         BASE64Encoder b64e = new BASE64Encoder();
         String stringClientKey = b64e.encode(ClientKeys.getPublic().getEncoded());
            
         BASE64Decoder b64d = new BASE64Decoder();
         byte[] byteClientKey = b64d.decodeBuffer(stringClientKey);
                 
         X509EncodedKeySpec clientKeySpec = new X509EncodedKeySpec(byteClientKey);
         PublicKey clientKey =  KeyFactory.getInstance("RSA").generatePublic(clientKeySpec);
         
         //Encripto en el servidor
         String json = RSA.encrypt(clientKey, ServerKeys.getPrivate(), msgServer);
         
         //Desencripto en el cliente
         Package p = new Package(json);
         String msg = RSA.decrypt(ServerKeys.getPublic(), ClientKeys.getPrivate(), p.msg, p.sign);
         
         System.out.println(msg);*/
         
    }
}
