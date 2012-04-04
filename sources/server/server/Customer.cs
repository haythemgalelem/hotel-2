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
        public Decimal Tel { get; set; }

        public Customer() { }

        public Customer(String Email, String Name, String Adr, Decimal Tel)
        {
            this.Email = Email;
            this.Name = Name;
            this.Adr = Adr;
            this.Tel = Tel;
        }
    }
}