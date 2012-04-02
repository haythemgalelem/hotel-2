using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace server
{
    public class Customer
    {
        public String Email { get; set; }
        public String Name { get; set; }
        public String Adr { get; set; }
        public int Tel { get; set; }

        public Customer() { }

        public Customer(String Email, String Name, String Adr, int Tel)
        {
            this.Email = Email;
            this.Name = Name;
            this.Adr = Adr;
            this.Tel = Tel;
        }
    }
}