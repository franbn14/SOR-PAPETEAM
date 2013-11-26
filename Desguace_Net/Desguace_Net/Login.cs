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

namespace Desguace_Net
{
    public partial class Login : Form
    {
        public Login()
        {
            InitializeComponent();
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
            LoginDesguace l1 = new LoginDesguaceClient();
         
            Login_DesRequest r=new Login_DesRequest();
            Login_DesRequestBody l=new Login_DesRequestBody ();
            
            l.cif = user;
            l.Password = pass;
            r.Body = l;
            Login_DesResponse res = new Login_DesResponse();
            try
            {
                res = l1.Login_Des(r);

                String error = res.Body.@return;
               
                if (error == "")
                {
                    Inicio i= new Inicio();
                    i.ShowDialog();
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
            if (MessageBox.Show("¿Es cierto que desea salir?", "", MessageBoxButtons.YesNo,
        MessageBoxIcon.Question, MessageBoxDefaultButton.Button2) ==
        DialogResult.No)
                e.Cancel = true;
        }
    }
}
