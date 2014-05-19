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
using System.Security.Cryptography;
using WsdlService;




namespace Desguace_Net
{
    public partial class Login : Form
    {
        public Login()
        {
            InitializeComponent();
        }
        public static byte[] strToByteArray(string str)
        {
           System.Text.ASCIIEncoding encoding = new System.Text.ASCIIEncoding();


           return encoding.GetBytes(str);

        }
        private void Form1_Load(object sender, EventArgs e)
        {


        }

        private void linkLabel1_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)
        {
            Registro re=new Registro();
            re.ShowDialog();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            String user = textBox1.Text;
            String pass = textBox2.Text;
         
            
            
            try
            {
                LoginDesguace l = new LoginDesguace();
                l.Url = Uddi.DarUrlWsdl("LoginDesguace");
                SHA512 shaM = new SHA512Managed();
                byte[] passCi = shaM.ComputeHash(strToByteArray(pass));
                
                StringBuilder hex = new StringBuilder(passCi.Length * 2);
                foreach (byte b in passCi)
                    hex.AppendFormat("{0:x2}", b);

               
               
                Comunicacion com = Comunicacion.GetInstance();

                String error =l.Login_Des(com.getID(),Comunicacion.Encrypt(hex.ToString(),com.getAes()),Comunicacion.Encrypt( user,com.getAes()));
                error = Comunicacion.Decrypt(error, com.getAes());
               
                if (error == "")
                {
                    
                    Inicio i= new Inicio(user,com.getID());
                    i.Show();
                    this.Hide();
                }
                else
                {
                    Error_Pass.Visible = true;
                    Error_Pass.Text = error;
                }

            }
            catch (Exception ex)
            {
                Error_Pass.Visible = true;
                Error_Pass.Text = "Fallo en el servidor. Intentelo de nuevo";
            }
          
        }

        private void Login_FormClosing(object sender, FormClosingEventArgs e)
        {
            
        }

        private void ContrasenaEn_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)
        {
            if (textBox1.Text == "")
            {
                Error_Pass.Visible = true;
                Error_Pass.Text = "Debes poner un CIF";
            }
            else
            {
                try
                {
                    PassManager ps = new PassManager();
                    ps.Url = Uddi.DarUrlWsdl("PassManager");
                    string error = ps.forgetPass(textBox1.Text);
                    if (error == "")
                    {
                        Error_Pass.Visible = true;
                        Error_Pass.Text = "Se ha enviado un email";
                    }
                    else
                    {
                        Error_Pass.Visible = true;
                        Error_Pass.Text = error;
                    }
                }
                catch (EndpointNotFoundException ex)
                {
                    Error_Pass.Visible = true;
                    Error_Pass.Text = "Fallo en el servidor. Intentelo de nuevo";
                }
                catch (Exception ex)
                {
                    Error_Pass.Visible = true;
                    Error_Pass.Text = "No se encuentra el servicio";
                }
            }
        }
    }
}
