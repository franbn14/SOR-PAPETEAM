using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using WsdlService;


namespace Desguace_Net
{
    public class Request
    {
        private int code;

        public int Code
        {
            get { return code; }
            set { code = value; }
        }
        private String deadline;

        public String Deadline
        {
            get { return deadline; }
            set { deadline = value; }
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
        private Double maxPrice;

        public Double MaxPrice
        {
            get { return maxPrice; }
            set { maxPrice = value; }
        }
        private Client client;

        public Client Client
        {
            get { return client; }
            set { client = value; }
        }
        private bool autoElect;

        public bool AutoElect
        {
            get { return autoElect; }
            set { autoElect = value; }
        }
        private bool finished;

        public bool Finished
        {
            get { return finished; }
            set { finished = value; }
        }
        private bool expired;

        public bool Expired
        {
            get { return expired; }
            set { expired = value; }
        }
        public override String ToString()
        {
            DarUnidades c = new DarUnidades();
            c.Url = Uddi.DarUrlWsdl("DarUnidades");

            return ((amount != null) ? amount + " " : "") + type + ((color != null) ? " | " + color : "") +
                ((size != null) ? " | " + size + c.DarUnidadId(sizeUnit) : "") + ((maxPrice != null) ? " | " + maxPrice+" €" : "");
        }


    }
}
