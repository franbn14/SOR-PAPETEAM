/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

/**
 *
 * @author esteve
 */
public class GenKeys {
    private PublicKey _public;
    private PrivateKey _private;
    
    public GenKeys() throws Exception{
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");  
        keyPairGenerator.initialize(1024, new SecureRandom()); 
        KeyPair keyPair = keyPairGenerator.generateKeyPair();  
        _public = keyPair.getPublic();  
        _private = keyPair.getPrivate();
    }

    public PublicKey getPublic() {
        return _public;
    }

    public void setPublic(PublicKey _public) {
        this._public = _public;
    }

    public PrivateKey getPrivate() {
        return _private;
    }

    public void setPrivate(PrivateKey _private) {
        this._private = _private;
    }
    
}
