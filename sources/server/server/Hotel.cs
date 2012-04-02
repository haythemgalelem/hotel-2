using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace server
{
    public class Hotel
    {
        public int Hid { get; set; }
        public String Name { get; set; }
        public String Adr { get; set; }

        public Hotel() { }

        public Hotel(int Hid, String Name, String Adr)
        {
            this.Hid = Hid;
            this.Name = Name;
            this.Adr = Adr;
        }
    }
}