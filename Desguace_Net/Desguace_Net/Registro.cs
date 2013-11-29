using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

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

                Close();
            }
        }

  
    }
}
