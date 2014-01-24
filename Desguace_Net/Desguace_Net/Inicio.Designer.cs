namespace Desguace_Net
{
    partial class Inicio
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
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.ListaRequest = new System.Windows.Forms.ListBox();
            this.hacerOferta = new System.Windows.Forms.Button();
            this.OfferPList = new System.Windows.Forms.ListBox();
            this.OfferFList = new System.Windows.Forms.ListBox();
            this.SuspendLayout();
            // 
            // button1
            // 
            this.button1.BackColor = System.Drawing.SystemColors.MenuHighlight;
            this.button1.ForeColor = System.Drawing.SystemColors.HighlightText;
            this.button1.Location = new System.Drawing.Point(1075, 587);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(192, 36);
            this.button1.TabIndex = 1;
            this.button1.Text = "Salir";
            this.button1.UseVisualStyleBackColor = false;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Microsoft Sans Serif", 15F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label1.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(0)))), ((int)(((byte)(192)))));
            this.label1.Location = new System.Drawing.Point(41, 60);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(133, 25);
            this.label1.TabIndex = 2;
            this.label1.Text = "PETICIONES";
            this.label1.Click += new System.EventHandler(this.label1_Click);
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Font = new System.Drawing.Font("Microsoft Sans Serif", 15F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label2.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(0)))), ((int)(((byte)(192)))));
            this.label2.Location = new System.Drawing.Point(471, 60);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(210, 25);
            this.label2.TabIndex = 4;
            this.label2.Text = "Mis ofertas Pendientes";
            this.label2.Click += new System.EventHandler(this.label2_Click);
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Font = new System.Drawing.Font("Microsoft Sans Serif", 15F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label3.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(0)))), ((int)(((byte)(192)))));
            this.label3.Location = new System.Drawing.Point(876, 60);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(206, 25);
            this.label3.TabIndex = 7;
            this.label3.Text = "Mis ofertas Aceptadas";
            // 
            // ListaRequest
            // 
            this.ListaRequest.BackColor = System.Drawing.SystemColors.MenuHighlight;
            this.ListaRequest.Font = new System.Drawing.Font("Microsoft Sans Serif", 10.25F);
            this.ListaRequest.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(0)))), ((int)(((byte)(64)))));
            this.ListaRequest.FormattingEnabled = true;
            this.ListaRequest.HorizontalScrollbar = true;
            this.ListaRequest.ItemHeight = 17;
            this.ListaRequest.Location = new System.Drawing.Point(37, 88);
            this.ListaRequest.Name = "ListaRequest";
            this.ListaRequest.Size = new System.Drawing.Size(375, 259);
            this.ListaRequest.TabIndex = 8;
            this.ListaRequest.SelectedIndexChanged += new System.EventHandler(this.ListaRequest_SelectedIndexChanged);
            // 
            // hacerOferta
            // 
            this.hacerOferta.BackColor = System.Drawing.SystemColors.MenuHighlight;
            this.hacerOferta.ForeColor = System.Drawing.SystemColors.HighlightText;
            this.hacerOferta.Location = new System.Drawing.Point(37, 356);
            this.hacerOferta.Name = "hacerOferta";
            this.hacerOferta.Size = new System.Drawing.Size(192, 36);
            this.hacerOferta.TabIndex = 9;
            this.hacerOferta.Text = "Hacer oferta";
            this.hacerOferta.UseVisualStyleBackColor = false;
            this.hacerOferta.Click += new System.EventHandler(this.hacerOferta_Click);
            // 
            // OfferPList
            // 
            this.OfferPList.BackColor = System.Drawing.SystemColors.MenuHighlight;
            this.OfferPList.Font = new System.Drawing.Font("Microsoft Sans Serif", 10.25F);
            this.OfferPList.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(0)))), ((int)(((byte)(64)))));
            this.OfferPList.FormattingEnabled = true;
            this.OfferPList.HorizontalScrollbar = true;
            this.OfferPList.ItemHeight = 17;
            this.OfferPList.Location = new System.Drawing.Point(445, 88);
            this.OfferPList.Name = "OfferPList";
            this.OfferPList.Size = new System.Drawing.Size(375, 259);
            this.OfferPList.TabIndex = 10;
            // 
            // OfferFList
            // 
            this.OfferFList.BackColor = System.Drawing.SystemColors.MenuHighlight;
            this.OfferFList.Font = new System.Drawing.Font("Microsoft Sans Serif", 10.25F);
            this.OfferFList.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(0)))), ((int)(((byte)(64)))));
            this.OfferFList.FormattingEnabled = true;
            this.OfferFList.HorizontalScrollbar = true;
            this.OfferFList.ItemHeight = 17;
            this.OfferFList.Location = new System.Drawing.Point(871, 88);
            this.OfferFList.Name = "OfferFList";
            this.OfferFList.Size = new System.Drawing.Size(375, 259);
            this.OfferFList.TabIndex = 11;
            // 
            // Inicio
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.SystemColors.GradientActiveCaption;
            this.ClientSize = new System.Drawing.Size(1304, 635);
            this.Controls.Add(this.OfferFList);
            this.Controls.Add(this.OfferPList);
            this.Controls.Add(this.hacerOferta);
            this.Controls.Add(this.ListaRequest);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.button1);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.Fixed3D;
            this.MaximizeBox = false;
            this.Name = "Inicio";
            this.Text = "Desguace Solution";
            this.FormClosed += new System.Windows.Forms.FormClosedEventHandler(this.Inicio_FormClosed);
            this.Load += new System.EventHandler(this.Inicio_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.ListBox ListaRequest;
        private System.Windows.Forms.Button hacerOferta;
        private System.Windows.Forms.ListBox OfferPList;
        private System.Windows.Forms.ListBox OfferFList;
    }
}