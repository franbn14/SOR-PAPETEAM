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
using Desguace_Net.LoginServicio;
using System.Security.Cryptography;
using Desguace_Net.DarNombre;
using Desguace_Net.FinishCom;

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
            LoginDesguaceClient l1 = new LoginDesguaceClient();
            
            try
            {
                SHA512 shaM = new SHA512Managed();
                byte[] passCi = shaM.ComputeHash(strToByteArray(pass));
                
                StringBuilder hex = new StringBuilder(passCi.Length * 2);
                foreach (byte b in passCi)
                    hex.AppendFormat("{0:x2}", b);

               
               
                Comunicacion com = Comunicacion.GetInstance();

                String error =l1.Login_Des(com.getID(),Comunicacion.Encrypt(hex.ToString(),com.getAes()),Comunicacion.Encrypt( user,com.getAes()));
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
            catch (EndpointNotFoundException ex)
            {
                Error_Pass.Visible = true;
                Error_Pass.Text = "Se ha caido el servidor";
            }
          
        }

        private void Login_FormClosing(object sender, FormClosingEventArgs e)
        {
            
        }
    }
}
