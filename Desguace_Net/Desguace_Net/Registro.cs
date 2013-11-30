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
using Desguace_Net.RegistroDesguace;

namespace Desguace_Net
{
    public partial class Registro : Form
    {
        public Registro()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            Erro_Cif.Visible = false;
            error_Pass.Visible = false;

            if (Cif_Text.Text == "")
            {
                Erro_Cif.Visible = true;
                Erro_Cif.Text = "Este campo no puede estar vacío";
            }
            if (Pass_Text.Text == "")
            {
                error_Pass.Visible = true;
                error_Pass.Text = "Este campo no puede estar vacío";
            }
            if (Cif_Text.Text != "" && Pass_Text.Text != "")
            {
                Erro_Cif.Visible = false;
                error_Pass.Visible = false;

                RegistroDesguaceClient l1 = new RegistroDesguaceClient();


                try
                {
                    String error = "";
                    error = l1.Registro(Cif_Text.Text, Nombre_Text.Text, Pass_Text.Text, Dire_Text.Text);


                    if (error == "")
                    {
                        if (MessageBox.Show("Registro Correcto. Por Favor para acceder Loguese.", "", MessageBoxButtons.OK,
       MessageBoxIcon.Information) == DialogResult.OK)
                        {
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
    }
}