namespace Desguace_Net
{
    partial class Hacer_Oferta
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
            this.EnviarOferta = new System.Windows.Forms.Button();
            this.Error_Pieza = new System.Windows.Forms.Label();
            this.lCif = new System.Windows.Forms.Label();
            this.Pieza_Text = new System.Windows.Forms.TextBox();
            this.lCantidad = new System.Windows.Forms.Label();
            this.Cant_Text = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.ColorText = new System.Windows.Forms.TextBox();
            this.label2 = new System.Windows.Forms.Label();
            this.Precio_Text = new System.Windows.Forms.TextBox();
            this.Tamaño_Text = new System.Windows.Forms.TextBox();
            this.label3 = new System.Windows.Forms.Label();
            this.UnidadesCom = new System.Windows.Forms.ComboBox();
            this.lUnidad = new System.Windows.Forms.Label();
            this.ErrorCantidad = new System.Windows.Forms.Label();
            this.ErrorTamaño = new System.Windows.Forms.Label();
            this.ErrorPrecio = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // EnviarOferta
            // 
            this.EnviarOferta.BackColor = System.Drawing.SystemColors.MenuHighlight;
            this.EnviarOferta.ForeColor = System.Drawing.SystemColors.HighlightText;
            this.EnviarOferta.Location = new System.Drawing.Point(278, 429);
            this.EnviarOferta.Name = "EnviarOferta";
            this.EnviarOferta.Size = new System.Drawing.Size(192, 36);
            this.EnviarOferta.TabIndex = 10;
            this.EnviarOferta.Text = "Hacer oferta";
            this.EnviarOferta.UseVisualStyleBackColor = false;
            this.EnviarOferta.Click += new System.EventHandler(this.EnviarOferta_Click);
            // 
            // Error_Pieza
            // 
            this.Error_Pieza.AutoSize = true;
            this.Error_Pieza.ForeColor = System.Drawing.Color.Red;
            this.Error_Pieza.Location = new System.Drawing.Point(242, 54);
            this.Error_Pieza.Name = "Error_Pieza";
            this.Error_Pieza.Size = new System.Drawing.Size(29, 13);
            this.Error_Pieza.TabIndex = 13;
            this.Error_Pieza.Text = "Error";
            this.Error_Pieza.Visible = false;
            // 
            // lCif
            // 
            this.lCif.AutoSize = true;
            this.lCif.ForeColor = System.Drawing.SystemColors.HotTrack;
            this.lCif.Location = new System.Drawing.Point(9, 23);
            this.lCif.Name = "lCif";
            this.lCif.Size = new System.Drawing.Size(33, 13);
            this.lCif.TabIndex = 12;
            this.lCif.Text = "Pieza";
            // 
            // Pieza_Text
            // 
            this.Pieza_Text.Location = new System.Drawing.Point(12, 51);
            this.Pieza_Text.Name = "Pieza_Text";
            this.Pieza_Text.Size = new System.Drawing.Size(222, 20);
            this.Pieza_Text.TabIndex = 11;
            // 
            // lCantidad
            // 
            this.lCantidad.AutoSize = true;
            this.lCantidad.ForeColor = System.Drawing.SystemColors.HotTrack;
            this.lCantidad.Location = new System.Drawing.Point(9, 88);
            this.lCantidad.Name = "lCantidad";
            this.lCantidad.Size = new System.Drawing.Size(49, 13);
            this.lCantidad.TabIndex = 15;
            this.lCantidad.Text = "Cantidad";
            // 
            // Cant_Text
            // 
            this.Cant_Text.Location = new System.Drawing.Point(12, 116);
            this.Cant_Text.Name = "Cant_Text";
            this.Cant_Text.Size = new System.Drawing.Size(222, 20);
            this.Cant_Text.TabIndex = 14;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.ForeColor = System.Drawing.SystemColors.HotTrack;
            this.label1.Location = new System.Drawing.Point(9, 274);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(31, 13);
            this.label1.TabIndex = 19;
            this.label1.Text = "Color";
            // 
            // ColorText
            // 
            this.ColorText.Location = new System.Drawing.Point(12, 300);
            this.ColorText.Name = "ColorText";
            this.ColorText.Size = new System.Drawing.Size(222, 20);
            this.ColorText.TabIndex = 18;
            this.ColorText.TextChanged += new System.EventHandler(this.textBox3_TextChanged);
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.ForeColor = System.Drawing.SystemColors.HotTrack;
            this.label2.Location = new System.Drawing.Point(9, 336);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(37, 13);
            this.label2.TabIndex = 21;
            this.label2.Text = "Precio";
            // 
            // Precio_Text
            // 
            this.Precio_Text.Location = new System.Drawing.Point(12, 363);
            this.Precio_Text.Name = "Precio_Text";
            this.Precio_Text.Size = new System.Drawing.Size(222, 20);
            this.Precio_Text.TabIndex = 20;
            // 
            // Tamaño_Text
            // 
            this.Tamaño_Text.Location = new System.Drawing.Point(12, 176);
            this.Tamaño_Text.Name = "Tamaño_Text";
            this.Tamaño_Text.Size = new System.Drawing.Size(222, 20);
            this.Tamaño_Text.TabIndex = 16;
            this.Tamaño_Text.TextChanged += new System.EventHandler(this.textBox1_TextChanged);
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.ForeColor = System.Drawing.SystemColors.HotTrack;
            this.label3.Location = new System.Drawing.Point(9, 151);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(46, 13);
            this.label3.TabIndex = 17;
            this.label3.Text = "Tamaño";
            // 
            // UnidadesCom
            // 
            this.UnidadesCom.FormattingEnabled = true;
            this.UnidadesCom.Location = new System.Drawing.Point(12, 233);
            this.UnidadesCom.Name = "UnidadesCom";
            this.UnidadesCom.Size = new System.Drawing.Size(121, 21);
            this.UnidadesCom.TabIndex = 22;
            // 
            // lUnidad
            // 
            this.lUnidad.AutoSize = true;
            this.lUnidad.ForeColor = System.Drawing.SystemColors.HotTrack;
            this.lUnidad.Location = new System.Drawing.Point(9, 217);
            this.lUnidad.Name = "lUnidad";
            this.lUnidad.Size = new System.Drawing.Size(41, 13);
            this.lUnidad.TabIndex = 23;
            this.lUnidad.Text = "Unidad";
            // 
            // ErrorCantidad
            // 
            this.ErrorCantidad.AutoSize = true;
            this.ErrorCantidad.ForeColor = System.Drawing.Color.Red;
            this.ErrorCantidad.Location = new System.Drawing.Point(242, 123);
            this.ErrorCantidad.Name = "ErrorCantidad";
            this.ErrorCantidad.Size = new System.Drawing.Size(29, 13);
            this.ErrorCantidad.TabIndex = 24;
            this.ErrorCantidad.Text = "Error";
            this.ErrorCantidad.Visible = false;
            // 
            // ErrorTamaño
            // 
            this.ErrorTamaño.AutoSize = true;
            this.ErrorTamaño.ForeColor = System.Drawing.Color.Red;
            this.ErrorTamaño.Location = new System.Drawing.Point(242, 183);
            this.ErrorTamaño.Name = "ErrorTamaño";
            this.ErrorTamaño.Size = new System.Drawing.Size(29, 13);
            this.ErrorTamaño.TabIndex = 25;
            this.ErrorTamaño.Text = "Error";
            this.ErrorTamaño.Visible = false;
            // 
            // ErrorPrecio
            // 
            this.ErrorPrecio.AutoSize = true;
            this.ErrorPrecio.ForeColor = System.Drawing.Color.Red;
            this.ErrorPrecio.Location = new System.Drawing.Point(242, 370);
            this.ErrorPrecio.Name = "ErrorPrecio";
            this.ErrorPrecio.Size = new System.Drawing.Size(29, 13);
            this.ErrorPrecio.TabIndex = 26;
            this.ErrorPrecio.Text = "Error";
            this.ErrorPrecio.Visible = false;
            // 
            // Hacer_Oferta
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.SystemColors.GradientActiveCaption;
            this.ClientSize = new System.Drawing.Size(482, 493);
            this.Controls.Add(this.ErrorPrecio);
            this.Controls.Add(this.ErrorTamaño);
            this.Controls.Add(this.ErrorCantidad);
            this.Controls.Add(this.lUnidad);
            this.Controls.Add(this.UnidadesCom);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.Precio_Text);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.ColorText);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.Tamaño_Text);
            this.Controls.Add(this.lCantidad);
            this.Controls.Add(this.Cant_Text);
            this.Controls.Add(this.Error_Pieza);
            this.Controls.Add(this.lCif);
            this.Controls.Add(this.Pieza_Text);
            this.Controls.Add(this.EnviarOferta);
            this.ForeColor = System.Drawing.SystemColors.HotTrack;
            this.Name = "Hacer_Oferta";
            this.Text = "Hacer_Oferta";
            this.Load += new System.EventHandler(this.Hacer_Oferta_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button EnviarOferta;
        private System.Windows.Forms.Label Error_Pieza;
        private System.Windows.Forms.Label lCif;
        private System.Windows.Forms.TextBox Pieza_Text;
        private System.Windows.Forms.Label lCantidad;
        private System.Windows.Forms.TextBox Cant_Text;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.TextBox ColorText;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.TextBox Precio_Text;
        private System.Windows.Forms.TextBox Tamaño_Text;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.ComboBox UnidadesCom;
        private System.Windows.Forms.Label lUnidad;
        private System.Windows.Forms.Label ErrorCantidad;
        private System.Windows.Forms.Label ErrorTamaño;
        private System.Windows.Forms.Label ErrorPrecio;
    }
}