using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.ServiceModel;
using System.Text.RegularExpressions;

using System.Security.Cryptography;
using Desguace_Net.RegistroDesguace;
using Desguace_Net.FinishCom;


namespace Desguace_Net
{
    public partial class Registro : Form
    {
        public Registro()
        {
            InitializeComponent();
        }
        public static byte[] strToByteArray(string str)
        {
            System.Text.ASCIIEncoding encoding = new System.Text.ASCIIEncoding();
            return encoding.GetBytes(str);

        }

        public static bool IsValidEmail(string strMailAddress)
        {
            // Return true if strIn is in valid e-mail format.
            return Regex.IsMatch(strMailAddress, @"^(?("")("".+?""@)|(([0-9a-zA-Z]((\.(?!\.))|[-!#\$%&'\*\+/=\?\^`\{\}\|~\w])*)(?<=[0-9a-zA-Z])@))" + @"(?(\[)(\[(\d{1,3}\.){3}\d{1,3}\])|(([0-9a-zA-Z][-\w]*[0-9a-zA-Z]\.)+[a-zA-Z]{2,6}))$");
        }

        public static bool IsValidPass(string pass)
        {
            // Return true if strIn is in valid e-mail format.
            return Regex.IsMatch(pass, @"^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,}$");
        }
        public bool validateCif(string cif)
        {
            if (string.IsNullOrEmpty(cif)) return false;
            cif = cif.Trim().ToUpper();

            //Debe tener una longitud igual a 9 caracteres;
            if (cif.Length != 9) return false;
            // ... y debe comenzar por una letra, la cual pasamos a mayúscula, ... 
            // 
            string firstChar = cif.Substring(0, 1);
            // ...que necesariamente deberá de estar comprendida en 
            // el siguiente intervalo: ABCDEFGHJNPQRSUVW 
            // 
            string cadena = "ABCDEFGHKLMNPQS";
            if (cadena.IndexOf(firstChar) == -1) return false;
            string digits = cif.Substring(1, 8);
            return Regex.IsMatch(digits, @"^[0-9]{8}$");
         
        }

        private void button1_Click(object sender, EventArgs e)
        {
            Erro_Cif.Visible = false;
            error_Pass.Visible = false;
            ErrorEmail.Visible = false;

            if (Cif_Text.Text == "")
            {
                Erro_Cif.Visible = true;
                Erro_Cif.Text = "Este campo no puede estar vacío";
            }
            if (!validateCif(Cif_Text.Text ))
            {
                Erro_Cif.Visible = true;
                Erro_Cif.Text = "CIF en formato incorrecto";
            }
            if (!IsValidPass(Pass_Text.Text))
            {
                error_Pass.Visible = true;
                error_Pass.Text = "Formato Incorrecto.Longitud mayor o igual a 8.\n Además debe contener al menos 1 miniscula,mayuscula y 1 numero";
            }
            if (Pass_Text.Text != Pass2.Text && IsValidPass(Pass_Text.Text))
            {
                error_Pass.Visible = true;
                error_Pass.Text = "Los password no coinciden";
            }
            if(!IsValidEmail(Email_text.Text))
            {
                    ErrorEmail.Visible = true;
                    ErrorEmail.Text="Email debe seguir el formato ejemplo@loquesea.com";
                
            }
            if (Cif_Text.Text != "" && Pass_Text.Text != "" && Pass_Text.Text == Pass2.Text && IsValidEmail(Email_text.Text) && validateCif(Cif_Text.Text))
            {
                
                Erro_Cif.Visible = false;
                error_Pass.Visible = false;
                ErrorEmail.Visible = false;
                RegistroDesguaceClient l1 = new RegistroDesguaceClient();

                Comunicacion com = Comunicacion.GetInstance();
                try
                {
                    String error = "";

                    SHA512 shaM = new SHA512Managed();
                   byte [] passCi = shaM.ComputeHash(strToByteArray(Pass_Text.Text));
                   StringBuilder hex = new StringBuilder(passCi.Length * 2);
                   foreach (byte b in passCi)
                       hex.AppendFormat("{0:x2}", b);

                  error = l1.Registro(com.getID(), Comunicacion.Encrypt(Cif_Text.Text, com.getAes()), Comunicacion.Encrypt(Nombre_Text.Text, com.getAes()), Comunicacion.Encrypt(hex.ToString(), com.getAes()), Comunicacion.Encrypt(Dire_Text.Text, com.getAes()), Comunicacion.Encrypt(Email_text.Text, com.getAes()));


                    if (error == "")
                    {

                        if (MessageBox.Show("Registro Correcto. Por Favor para acceder Loguese.", "", MessageBoxButtons.OK,
       MessageBoxIcon.Information) == DialogResult.OK)
                        {
                            FinishComClient client = new FinishComClient();
                            client.Finish(com.getID());
                            com.setInstanceNull();
                            Close();

                        }
                    }
                    else
                    {
                        Erro_Cif.Visible = true;
                        Erro_Cif.Text = error;
                    }

                }
                catch (EndpointNotFoundException ex)
                {

                    Erro_Cif.Visible = true;
                    Erro_Cif.Text="Se ha caido el servidor";
                    
                }
            }


        }

        private void label7_Click(object sender, EventArgs e)
        {

        }
    }
}