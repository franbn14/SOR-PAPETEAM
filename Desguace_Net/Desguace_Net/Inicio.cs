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
using Desguace_Net.DarID;
using Apache.NMS.ActiveMQ;

namespace Desguace_Net
{
    public partial class Inicio : Form
    {
        String userName = "";
        String nif = "";
        int idDes = 0;
        public Inicio(String user)
        {
          DarNombreClienteClient c = new DarNombreClienteClient();
            DarIdDesguacebyCifClient id = new DarIdDesguacebyCifClient();
            nif = user;
            idDes = id.getIdDes(user);
            userName = c.DarNombreDesguace(user);
            InitializeComponent();
        }

        private void Inicio_Load(object sender, EventArgs e)
        {
            Text = "Bienvenido "+userName;
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
    }
}
