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
    public partial class Inicio : Form
    {
        String userName = "";
        public Inicio(String user)
        {

            userName = user;
           
            
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
    }
}
