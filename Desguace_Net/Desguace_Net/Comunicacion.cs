using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Security.Cryptography;
using System.Text;
using System.Threading.Tasks;
using System.ServiceModel;
using System.Security.Cryptography.X509Certificates;
using WsdlService;

namespace Desguace_Net
{
    class Package
    {

        public String msg;
        public String sign;
    }
    class RSAKey
    {
        public String modulus;
        public String exponent;
        public int id;

    }

    public class Comunicacion
    {
        private static Comunicacion instance = null;
        private int ID;
        private byte[] claveAES;
    
   private Comunicacion() 
   {
       RSACryptoServiceProvider rsaKey = new RSACryptoServiceProvider(1024);
       InitCom initc = new InitCom();
       initc.Url = Uddi.DarUrlWsdl("InitCom");
       byte[] modulus = rsaKey.ExportParameters(false).Modulus;
       byte[] exponent = rsaKey.ExportParameters(false).Exponent;

       String json = initc.exchangeKeys(Convert.ToBase64String(modulus), Convert.ToBase64String(exponent));

       dynamic json1 = Newtonsoft.Json.JsonConvert.DeserializeObject<RSAKey>(json);

       RSACryptoServiceProvider rsaKeyS = new RSACryptoServiceProvider(1024);

       RSAKey k;
       if (json1 != null)
       {
           k = json1;
           ID = k.id;
            json=initc.getAESKey(ID);
            json1 = Newtonsoft.Json.JsonConvert.DeserializeObject<Package>(json);
            Package p;
            if (json1 != null)
            {
                p = json1;
               byte[] aes = rsaKey.Decrypt(Convert.FromBase64String(p.msg), false);
                string res = Encoding.UTF8.GetString(aes);
                claveAES = Convert.FromBase64String(res);
            }
           

       }
   }
 
   public static Comunicacion GetInstance()
   {
     if (instance == null)
        instance = new Comunicacion();
 
     return instance;
   }
   public int getID()
   {
       return ID;
   }
   public byte[] getAes()
   {
       return claveAES;
   }

   public void setInstanceNull()
   {
       instance = null;
   }
    public static string Encrypt(string raw,byte[] aes)
    {
            using (var csp = new AesCryptoServiceProvider())
            {
                ICryptoTransform e = GetCryptoTransform(csp, true,aes);
                byte[] inputBuffer = Encoding.UTF8.GetBytes(raw);
                byte[] output = e.TransformFinalBlock(inputBuffer, 0, inputBuffer.Length);

                string encrypted = Convert.ToBase64String(output);

                return encrypted;
            }
     }

    public static string Decrypt(string encrypted,byte[] aes)
    {
            using (var csp = new AesCryptoServiceProvider())
            {
                var d = GetCryptoTransform(csp, false,aes);
                byte[] output = Convert.FromBase64String(encrypted);
                byte[] decryptedOutput = d.TransformFinalBlock(output, 0, output.Length);

                string decypted = Encoding.UTF8.GetString(decryptedOutput);
                return decypted;
            }
        }

        public static ICryptoTransform GetCryptoTransform(AesCryptoServiceProvider csp, bool encrypting, byte[] aes)
        {
            csp.Mode = CipherMode.CBC;
            csp.Padding = PaddingMode.PKCS7;
            
            String iv = "e675f725e675f725";


            byte[] key = aes;


          csp.IV = Encoding.UTF8.GetBytes(iv);
         
            csp.Key = key;
            if (encrypting)
            {
                return csp.CreateEncryptor();
            }
            return csp.CreateDecryptor();
        }
        
    
    }
    
}
