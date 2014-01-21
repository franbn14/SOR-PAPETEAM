using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.IO;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.ServiceModel;
using Desguace_Net.LoginServicio;
using Desguace_Net.DarNombre;
using Desguace_Net.DarID;
using Apache.NMS.ActiveMQ;
using Apache.NMS;
using Desguace_Net;
namespace Desguace_Net
{
    public partial class Inicio : Form
    {
        String userName = "";
        String nif = "";
        int idDes = 0;
        Services.TopicSubscriber request_subscriber;
        public Inicio(String user)
        {
          DarNombreClienteClient c = new DarNombreClienteClient();
            DarIdDesguacebyCifClient id = new DarIdDesguacebyCifClient();
            nif = user;
            idDes = id.getIdDes(user);
            
            userName = c.DarNombreDesguace(user);
            InitializeComponent();
            Text = "Bienvenido " + userName;

            Services.TopicSubscriber s = new Services.TopicSubscriber("pendientes", "tcp://localhost:61616", "recibidor");

            s.OnMessageReceived += s_OnMessageReceived;

            System.Threading.Thread.Sleep(10000);
           
            Console.WriteLine("despues");
                /*TopicSubscriber.MakeSubscriber(
                 "tcp://localhost:61616",
                 "pendientesSuscriber",
                "pendientes");*/
        }
         void s_OnMessageReceived(string message)
        {
            Console.WriteLine(message);
            textBox1.Text = message;
            Console.ReadLine();
        }

        private void Inicio_Load(object sender, EventArgs e)
        {

            //request_subscriber.Start("Request_Pendientes");
           // request_subscriber.OnMessageReceived += OnMessage;
            //request_subscriber.OnMessageReceived += OnMessage;
            //Console.WriteLine("de");
           
        }

       

        private void Inicio_FormClosing(object sender, FormClosingEventArgs e)
        {
            if (MessageBox.Show("¿Es cierto que desea salir?", "", MessageBoxButtons.YesNo, MessageBoxIcon.Question, MessageBoxDefaultButton.Button2) == DialogResult.No)
            {
                e.Cancel = true;
            }
          
        }

        private void button1_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void Inicio_FormClosed(object sender, FormClosedEventArgs e)
        {
            Application.Exit();
        }

        private void listView1_SelectedIndexChanged(object sender, EventArgs e)
        {

        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void dataGridView1_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }

        private void label2_Click(object sender, EventArgs e)
        {

        }

        private void dataGridView1_CellContentClick_1(object sender, DataGridViewCellEventArgs e)
        {

        }
      

        private void button2_Click(object sender, EventArgs e)
        {
            
        }
    }
}
