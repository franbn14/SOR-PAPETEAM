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
            this.lCif = new System.Windows.Forms.Label();
            this.Pieza_Text = new System.Windows.Forms.TextBox();
            this.lCantidad = new System.Windows.Forms.Label();
            this.Cant_Text = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.ColorText = new System.Windows.Forms.TextBox();
            this.label2 = new System.Windows.Forms.Label();
            this.Precio_Text = new System.Windows.Forms.TextBox();
            this.ErrorCantidad = new System.Windows.Forms.Label();
            this.ErrorPrecio = new System.Windows.Forms.Label();
            this.Concepto = new System.Windows.Forms.Label();
            this.LTam = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // EnviarOferta
            // 
            this.EnviarOferta.BackColor = System.Drawing.SystemColors.MenuHighlight;
            this.EnviarOferta.ForeColor = System.Drawing.SystemColors.HighlightText;
            this.EnviarOferta.Location = new System.Drawing.Point(357, 439);
            this.EnviarOferta.Name = "EnviarOferta";
            this.EnviarOferta.Size = new System.Drawing.Size(192, 36);
            this.EnviarOferta.TabIndex = 10;
            this.EnviarOferta.Text = "Hacer oferta";
            this.EnviarOferta.UseVisualStyleBackColor = false;
            this.EnviarOferta.Click += new System.EventHandler(this.EnviarOferta_Click);
            // 
            // lCif
            // 
            this.lCif.AutoSize = true;
            this.lCif.ForeColor = System.Drawing.SystemColors.HotTrack;
            this.lCif.Location = new System.Drawing.Point(9, 101);
            this.lCif.Name = "lCif";
            this.lCif.Size = new System.Drawing.Size(63, 13);
            this.lCif.TabIndex = 12;
            this.lCif.Text = "Descripción";
            // 
            // Pieza_Text
            // 
            this.Pieza_Text.Location = new System.Drawing.Point(12, 129);
            this.Pieza_Text.Name = "Pieza_Text";
            this.Pieza_Text.Size = new System.Drawing.Size(222, 20);
            this.Pieza_Text.TabIndex = 11;
            // 
            // lCantidad
            // 
            this.lCantidad.AutoSize = true;
            this.lCantidad.ForeColor = System.Drawing.SystemColors.HotTrack;
            this.lCantidad.Location = new System.Drawing.Point(9, 166);
            this.lCantidad.Name = "lCantidad";
            this.lCantidad.Size = new System.Drawing.Size(49, 13);
            this.lCantidad.TabIndex = 15;
            this.lCantidad.Text = "Cantidad";
            // 
            // Cant_Text
            // 
            this.Cant_Text.Location = new System.Drawing.Point(12, 194);
            this.Cant_Text.Name = "Cant_Text";
            this.Cant_Text.Size = new System.Drawing.Size(222, 20);
            this.Cant_Text.TabIndex = 14;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.ForeColor = System.Drawing.SystemColors.HotTrack;
            this.label1.Location = new System.Drawing.Point(9, 297);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(31, 13);
            this.label1.TabIndex = 19;
            this.label1.Text = "Color";
            // 
            // ColorText
            // 
            this.ColorText.Location = new System.Drawing.Point(12, 325);
            this.ColorText.Name = "ColorText";
            this.ColorText.Size = new System.Drawing.Size(222, 20);
            this.ColorText.TabIndex = 18;
            this.ColorText.TextChanged += new System.EventHandler(this.textBox3_TextChanged);
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.ForeColor = System.Drawing.SystemColors.HotTrack;
            this.label2.Location = new System.Drawing.Point(12, 366);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(37, 13);
            this.label2.TabIndex = 21;
            this.label2.Text = "Precio";
            // 
            // Precio_Text
            // 
            this.Precio_Text.Location = new System.Drawing.Point(12, 397);
            this.Precio_Text.Name = "Precio_Text";
            this.Precio_Text.Size = new System.Drawing.Size(222, 20);
            this.Precio_Text.TabIndex = 20;
            // 
            // ErrorCantidad
            // 
            this.ErrorCantidad.AutoSize = true;
            this.ErrorCantidad.ForeColor = System.Drawing.Color.Red;
            this.ErrorCantidad.Location = new System.Drawing.Point(242, 201);
            this.ErrorCantidad.Name = "ErrorCantidad";
            this.ErrorCantidad.Size = new System.Drawing.Size(29, 13);
            this.ErrorCantidad.TabIndex = 24;
            this.ErrorCantidad.Text = "Error";
            this.ErrorCantidad.Visible = false;
            // 
            // ErrorPrecio
            // 
            this.ErrorPrecio.AutoSize = true;
            this.ErrorPrecio.ForeColor = System.Drawing.Color.Red;
            this.ErrorPrecio.Location = new System.Drawing.Point(242, 404);
            this.ErrorPrecio.Name = "ErrorPrecio";
            this.ErrorPrecio.Size = new System.Drawing.Size(29, 13);
            this.ErrorPrecio.TabIndex = 26;
            this.ErrorPrecio.Text = "Error";
            this.ErrorPrecio.Visible = false;
            // 
            // Concepto
            // 
            this.Concepto.AutoSize = true;
            this.Concepto.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.Concepto.ForeColor = System.Drawing.SystemColors.HotTrack;
            this.Concepto.Location = new System.Drawing.Point(8, 44);
            this.Concepto.Name = "Concepto";
            this.Concepto.Size = new System.Drawing.Size(43, 20);
            this.Concepto.TabIndex = 27;
            this.Concepto.Text = "Tipo:";
            // 
            // LTam
            // 
            this.LTam.AutoSize = true;
            this.LTam.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.LTam.ForeColor = System.Drawing.SystemColors.HotTrack;
            this.LTam.Location = new System.Drawing.Point(8, 248);
            this.LTam.Name = "LTam";
            this.LTam.Size = new System.Drawing.Size(71, 20);
            this.LTam.TabIndex = 28;
            this.LTam.Text = "Tamaño:";
            // 
            // Hacer_Oferta
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.SystemColors.GradientActiveCaption;
            this.ClientSize = new System.Drawing.Size(616, 502);
            this.Controls.Add(this.LTam);
            this.Controls.Add(this.Concepto);
            this.Controls.Add(this.ErrorPrecio);
            this.Controls.Add(this.ErrorCantidad);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.Precio_Text);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.ColorText);
            this.Controls.Add(this.lCantidad);
            this.Controls.Add(this.Cant_Text);
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
        private System.Windows.Forms.Label lCif;
        private System.Windows.Forms.TextBox Pieza_Text;
        private System.Windows.Forms.Label lCantidad;
        private System.Windows.Forms.TextBox Cant_Text;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.TextBox ColorText;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.TextBox Precio_Text;
        private System.Windows.Forms.Label ErrorCantidad;
        private System.Windows.Forms.Label ErrorPrecio;
        private System.Windows.Forms.Label Concepto;
        private System.Windows.Forms.Label LTam;
    }
}