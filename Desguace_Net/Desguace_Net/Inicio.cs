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
using Desguace_Net.FinishCom;
using System.Threading;

namespace Desguace_Net
{
    public partial class Inicio : Form
    {
        String userName = "";
        String nif = "";
        int idDes = 0;
        Services.TopicSubscriber r;
        Services.TopicSubscriber op;
        Services.TopicSubscriber of;
        List<Request> list;
        List<Offer> listOp;
        List<Offer> listOf;
        int IDAes;
        public Inicio(String user,int idAes)
        {
            IDAes = idAes;

            DarNombreClienteClient c = new DarNombreClienteClient();
            DarIdDesguacebyCifClient id = new DarIdDesguacebyCifClient();
            nif = user;
            Comunicacion com = Comunicacion.GetInstance();
            
            idDes = id.getIdDes(com.getID(),Comunicacion.Encrypt(user,com.getAes()));
            
            userName = c.DarNombreDesguace(com.getID(),Comunicacion.Encrypt(user,com.getAes()));

            userName = Comunicacion.Decrypt(userName, com.getAes());

            InitializeComponent();
            Text = "Bienvenido " + userName;

            r = new Services.TopicSubscriber("pendientes", "tcp://192.168.43.56:61616", "RecibidorRequest" + nif);
            op = new Services.TopicSubscriber(nif + "p", "tcp://192.168.43.56:61616", "RecibidorOfertasPen" + nif);
            of = new Services.TopicSubscriber(nif + "f", "tcp://192.168.43.56:61616", "RecibidorOfertasFin" + nif);
            r.OnMessageReceived += r_OnMessageReceived;
            op.OnMessageReceived += op_OnMessageReceived;
            of.OnMessageReceived += of_OnMessageReceived;

           
        }
        private void r_OnMessageReceived(string message)
        {
            

            //req = list.ElementAt(0);       
            //cargarRequest(message);
            Invoke(new Action(() => cargarRequest(message)));
            
     
        }
        private void op_OnMessageReceived(string message)
        {


            
            Invoke(new Action(() => cargarOfferPen(message)));
            


        }
        private void of_OnMessageReceived(string message)
        {


          
            Invoke(new Action(() => cargarOfferF(message)));



        }
       
        private void cargarRequest(String message)
        {

            dynamic json = Newtonsoft.Json.JsonConvert.DeserializeObject<List<Request>>(message);
            ListaRequest.Items.Clear();
            if (json != null)
            {
                list = new List<Request>(json);

                //ListaRequest = new ListBox();
               
                ListaRequest.Items.AddRange(list.ToArray());
            
            }
            
        }
        private void cargarOfferPen(String message)
        {

            dynamic json = Newtonsoft.Json.JsonConvert.DeserializeObject<List<Offer>>(message);
            OfferPList.Items.Clear();
            if (json != null)
            {
                listOp = new List<Offer>(json);


                //ListaRequest = new ListBox();
                
                OfferPList.Items.AddRange(listOp.ToArray());
                
            }
                
        }
        private void cargarOfferF(String message)
        {

            dynamic json = Newtonsoft.Json.JsonConvert.DeserializeObject<List<Offer>>(message);
            OfferFList.Items.Clear();
            if (json != null)
            {
                listOf = new List<Offer>(json);

                //ListaRequest = new ListBox();
                
                OfferFList.Items.AddRange(listOf.ToArray());
            }
            
        }
        private void Inicio_Load(object sender, EventArgs e)
        {
                    
           
            //list.ElementAt(0);
            //Console.WriteLine("Items: " + list.Count);
             
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
            FinishComClient f = new FinishComClient();
            f.Finish(IDAes);  
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
                Hacer_Oferta o = new Hacer_Oferta(selected, idDes);
                o.Show();
            }
            else
                Console.WriteLine("No hay seleccionadas");
        }

        
    }
}
