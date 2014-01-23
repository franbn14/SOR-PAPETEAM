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
        Services.TopicSubscriber s;
        List<Request> list;

        public Inicio(String user)
        {
          DarNombreClienteClient c = new DarNombreClienteClient();
            DarIdDesguacebyCifClient id = new DarIdDesguacebyCifClient();
            nif = user;
            idDes = id.getIdDes(user);
            
            userName = c.DarNombreDesguace(user);
            InitializeComponent();
            Text = "Bienvenido " + userName;

             s = new Services.TopicSubscriber("pendientes", "tcp://localhost:61616", "recibidor");

            s.OnMessageReceived += s_OnMessageReceived;

           
        }
        private void s_OnMessageReceived(string message)
        {
            

            //req = list.ElementAt(0);       
            cargarRequest(message);
            Console.WriteLine(message);     

           
                        
        }
        private void cargarRequest(String message)
        {

            dynamic json = Newtonsoft.Json.JsonConvert.DeserializeObject<List<Request>>(message);
            list = new List<Request>(json);
           // ListaRequest.Items.Clear();
            RequestP.Rows.Clear();
            RequestP.Rows.Add(list.ElementAt(0).Type);  
        
        }
        private void Inicio_Load(object sender, EventArgs e)
        {
                    
            Request req = new Request();// list.ElementAt(0);
            req.Type = "Juanito";
            Console.WriteLine("Items: " + list.Count);
             
           // ListaRequest.Items.Clear();            
                 
        }

        public void checkList()
        {
           
        }
       

        private void Inicio_FormClosing(object sender, FormClosingEventArgs e)
        {
            
          
        }

        private void button1_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void Inicio_FormClosed(object sender, FormClosedEventArgs e)
        {
            Application.Exit();
        }
        
        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void label2_Click(object sender, EventArgs e)
        {

        }

        private void ListaRequest_SelectedIndexChanged(object sender, EventArgs e)
        {
            
        }

        private void hacerOferta_Click(object sender, EventArgs e)
        {
            Request selected = (Request)ListaRequest.SelectedItem;

            if (selected != null)
            {
                Console.WriteLine(selected.Code + " " + selected.Type);
            }
            else
                Console.WriteLine("No hay seleccionadas");
        }

        
    }
}
