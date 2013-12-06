namespace Desguace_Net
{
    partial class Registro
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.button1 = new System.Windows.Forms.Button();
            this.Cif_Text = new System.Windows.Forms.TextBox();
            this.Nombre_Text = new System.Windows.Forms.TextBox();
            this.Pass_Text = new System.Windows.Forms.TextBox();
            this.Dire_Text = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            this.Erro_Cif = new System.Windows.Forms.Label();
            this.error_Pass = new System.Windows.Forms.Label();
            this.label6 = new System.Windows.Forms.Label();
            this.Pass2 = new System.Windows.Forms.TextBox();
            this.SuspendLayout();
            // 
            // button1
            // 
            this.button1.AutoSize = true;
            this.button1.BackColor = System.Drawing.SystemColors.MenuHighlight;
            this.button1.ForeColor = System.Drawing.SystemColors.HighlightText;
            this.button1.Location = new System.Drawing.Point(355, 384);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(142, 35);
            this.button1.TabIndex = 0;
            this.button1.Text = "Enviar Datos";
            this.button1.UseVisualStyleBackColor = false;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // Cif_Text
            // 
            this.Cif_Text.Location = new System.Drawing.Point(59, 102);
            this.Cif_Text.Name = "Cif_Text";
            this.Cif_Text.Size = new System.Drawing.Size(222, 20);
            this.Cif_Text.TabIndex = 1;
            // 
            // Nombre_Text
            // 
            this.Nombre_Text.Location = new System.Drawing.Point(59, 165);
            this.Nombre_Text.Name = "Nombre_Text";
            this.Nombre_Text.Size = new System.Drawing.Size(222, 20);
            this.Nombre_Text.TabIndex = 2;
            // 
            // Pass_Text
            // 
            this.Pass_Text.Location = new System.Drawing.Point(59, 237);
            this.Pass_Text.Name = "Pass_Text";
            this.Pass_Text.Size = new System.Drawing.Size(222, 20);
            this.Pass_Text.TabIndex = 3;
            this.Pass_Text.UseSystemPasswordChar = true;
            // 
            // Dire_Text
            // 
            this.Dire_Text.Location = new System.Drawing.Point(59, 348);
            this.Dire_Text.Name = "Dire_Text";
            this.Dire_Text.Size = new System.Drawing.Size(222, 20);
            this.Dire_Text.TabIndex = 4;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.ForeColor = System.Drawing.SystemColors.HotTrack;
            this.label1.Location = new System.Drawing.Point(56, 74);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(23, 13);
            this.label1.TabIndex = 5;
            this.label1.Text = "CIF";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.ForeColor = System.Drawing.SystemColors.HotTrack;
            this.label2.Location = new System.Drawing.Point(56, 136);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(44, 13);
            this.label2.TabIndex = 6;
            this.label2.Text = "Nombre";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.ForeColor = System.Drawing.SystemColors.HotTrack;
            this.label3.Location = new System.Drawing.Point(56, 207);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(53, 13);
            this.label3.TabIndex = 7;
            this.label3.Text = "Password";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.ForeColor = System.Drawing.SystemColors.HotTrack;
            this.label4.Location = new System.Drawing.Point(56, 322);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(52, 13);
            this.label4.TabIndex = 8;
            this.label4.Text = "Direccion";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label5.ForeColor = System.Drawing.SystemColors.HotTrack;
            this.label5.Location = new System.Drawing.Point(113, 9);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(350, 25);
            this.label5.TabIndex = 9;
            this.label5.Text = "Rellena el formulario de registro";
            this.label5.TextAlign = System.Drawing.ContentAlignment.TopCenter;
            // 
            // Erro_Cif
            // 
            this.Erro_Cif.AutoSize = true;
            this.Erro_Cif.ForeColor = System.Drawing.Color.Red;
            this.Erro_Cif.Location = new System.Drawing.Point(289, 105);
            this.Erro_Cif.Name = "Erro_Cif";
            this.Erro_Cif.Size = new System.Drawing.Size(29, 13);
            this.Erro_Cif.TabIndex = 10;
            this.Erro_Cif.Text = "Error";
            this.Erro_Cif.Visible = false;
            // 
            // error_Pass
            // 
            this.error_Pass.AutoSize = true;
            this.error_Pass.ForeColor = System.Drawing.Color.Red;
            this.error_Pass.Location = new System.Drawing.Point(289, 244);
            this.error_Pass.Name = "error_Pass";
            this.error_Pass.Size = new System.Drawing.Size(29, 13);
            this.error_Pass.TabIndex = 11;
            this.error_Pass.Text = "Error";
            this.error_Pass.Visible = false;
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.ForeColor = System.Drawing.SystemColors.HotTrack;
            this.label6.Location = new System.Drawing.Point(56, 260);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(97, 13);
            this.label6.TabIndex = 13;
            this.label6.Text = "Confirma Password";
            // 
            // Pass2
            // 
            this.Pass2.Location = new System.Drawing.Point(59, 290);
            this.Pass2.Name = "Pass2";
            this.Pass2.Size = new System.Drawing.Size(222, 20);
            this.Pass2.TabIndex = 12;
            this.Pass2.UseSystemPasswordChar = true;
            // 
            // Registro
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.SystemColors.GradientActiveCaption;
            this.ClientSize = new System.Drawing.Size(571, 443);
            this.Controls.Add(this.label6);
            this.Controls.Add(this.Pass2);
            this.Controls.Add(this.error_Pass);
            this.Controls.Add(this.Erro_Cif);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.Dire_Text);
            this.Controls.Add(this.Pass_Text);
            this.Controls.Add(this.Nombre_Text);
            this.Controls.Add(this.Cif_Text);
            this.Controls.Add(this.button1);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.Fixed3D;
            this.Name = "Registro";
            this.Text = "Registro Desguace";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.TextBox Cif_Text;
        private System.Windows.Forms.TextBox Nombre_Text;
        private System.Windows.Forms.TextBox Pass_Text;
        private System.Windows.Forms.TextBox Dire_Text;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Label Erro_Cif;
        private System.Windows.Forms.Label error_Pass;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.TextBox Pass2;
    }
}