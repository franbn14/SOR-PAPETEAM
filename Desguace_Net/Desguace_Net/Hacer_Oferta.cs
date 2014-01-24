using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using Desguace_Net.DarUnidadTipo;
using System.Text.RegularExpressions;

namespace Desguace_Net
{
    public partial class Hacer_Oferta : Form
    {
        int IdDes = 0;
        int IdRe = 0;
        public Hacer_Oferta(int idR,int idDes)
        {
            InitializeComponent();
            IdDes = idDes;
            IdRe = idR;


        }

        private void textBox1_TextChanged(object sender, EventArgs e)
        {

        }

        private void textBox3_TextChanged(object sender, EventArgs e)
        {

        }

        private void Hacer_Oferta_Load(object sender, EventArgs e)
        {
            DarUnidadesClient c =new DarUnidadesClient();
            dynamic json = Newtonsoft.Json.JsonConvert.DeserializeObject<List<String>>(c.DarTodasUnidades());
            UnidadesCom.Items.AddRange(json.ToArray());
            UnidadesCom.SelectedIndex = 0;
        }

        private void EnviarOferta_Click(object sender, EventArgs e)
        {
            ErrorCantidad.Visible = false;
            ErrorPrecio.Visible = false;
            Error_Pieza.Visible = false;
            ErrorTamaño.Visible = false;
            bool correcto = true;

            if (Pieza_Text.Text == "" || Pieza_Text.Text == null)
            {
                Error_Pieza.Visible = true;
                Error_Pieza.Text = "Error este campo no puede estar vacio";
                correcto = false;
            }
            if (!checkNumber(Cant_Text.Text))
            {
                ErrorCantidad.Visible = true;
                ErrorCantidad.Text = "No es un número";
                correcto = false;
            }
            if (!checkNumber(Tamaño_Text.Text))
            {
                ErrorTamaño.Visible = true;
                ErrorTamaño.Text = "No es un número";
                correcto = false;
            }


            if (!checkNumber(Precio_Text.Text))
            {
                ErrorPrecio.Visible = true;
                ErrorPrecio.Text = "Error no es un número";
                correcto = false;
            }

            if (correcto)
            {
                String oferta = Pieza_Text.Text + "," + Tamaño_Text.Text + "," + UnidadesCom.SelectedIndex + "," + ColorText.Text + "," + Cant_Text.Text + "," + Precio_Text.Text + "," + IdDes + "," + IdRe;
                Services.TopicPublisher p = new Services.TopicPublisher("OfferDelivery", "tcp://192.168.43.56:61616");
                p.SendMessage(oferta);
                if (MessageBox.Show("Oferta Enviada", "", MessageBoxButtons.OK,
      MessageBoxIcon.Information) == DialogResult.OK)
                {
                    Close();
                }
            }

        }
        private bool checkNumber(string number)
        {
            if(number !=null && number!="")
                return Regex.IsMatch(number, @"^[0-9]+([.,][0-9]+)?$");
            
            return true;
        }

    }
}
