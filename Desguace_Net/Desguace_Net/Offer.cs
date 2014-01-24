using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Desguace_Net.DarUnidadTipo;

namespace Desguace_Net
{
    class Offer
    {
        private int code;

        public int Code
        {
            get { return code; }
            set { code = value; }
        }
        private String type;

        public String Type
        {
            get { return type; }
            set { type = value; }
        }
        private Double size;

        public Double Size
        {
            get { return size; }
            set { size = value; }
        }
        private Int32 sizeUnit;

        public Int32 SizeUnit
        {
            get { return sizeUnit; }
            set { sizeUnit = value; }
        }
        private String color;

        public String Color
        {
            get { return color; }
            set { color = value; }
        }
        private Int32 amount;

        public Int32 Amount
        {
            get { return amount; }
            set { amount = value; }
        }
        private Double price;

        public Double Price
        {
            get { return price; }
            set { price = value; }
        }
        private Request request;

        internal Request Request
        {
            get { return request; }
            set { request = value; }
        }
        private Desguace scrapyard;

        internal Desguace Scrapyard
        {
            get { return scrapyard; }
            set { scrapyard = value; }
        }
        private bool accepted;

        public bool Accepted
        {
            get { return accepted; }
            set { accepted = value; }
        }
        public override String ToString()
        {
            DarUnidadesClient c = new DarUnidadesClient();

            return ((amount != null) ? amount + " " : "") + type + ((color != null) ? " | " + color : "") +
                ((size != null) ? " | " + size + c.DarUnidadId(sizeUnit) : "") + ((Price != null) ? " | " + Price+" €" : "");
        }
    }
}
