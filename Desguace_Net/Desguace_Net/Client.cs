using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Desguace_Net
{
    class Client
    {
        private String surname;

        public String Surname
        {
            get { return surname; }
            set { surname = value; }
        }
        private String nif;

        public String Nif
        {
            get { return nif; }
            set { nif = value; }
        }
        private String DOB;

        public String Dob
        {
            get { return DOB; }
            set { DOB = value; }
        }
        private int id;

        public int Id
        {
            get { return id; }
            set { id = value; }
        }
        private String name;

        public String Name
        {
            get { return name; }
            set { name = value; }
        }
        private String password;

        public String Password
        {
            get { return password; }
            set { password = value; }
        }
        private String address;

        public String Address
        {
            get { return address; }
            set { address = value; }
        }
    }
}
