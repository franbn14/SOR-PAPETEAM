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
using Desguace_Net.DarNombre;

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
            LoginDesguaceClient l1 = new LoginDesguaceClient();
         
           
            
            try
            {
                
                String error =l1.Login_Des(pass, user);
               
                if (error == "")
                {
                    DarNombreClienteClient c = new DarNombreClienteClient();
                    Inicio i= new Inicio(c.DarNombreDesguace(user));
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
            if (MessageBox.Show("¿Es cierto que desea salir?", "", MessageBoxButtons.YesNo,
        MessageBoxIcon.Question, MessageBoxDefaultButton.Button2) ==
        DialogResult.No)
                e.Cancel = true;
        }
    }
}
