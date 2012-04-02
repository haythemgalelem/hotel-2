using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace server
{
    public class Booking
    {
        public Customer Customer { get; set; }
        public Hotel Hotel { get; set; }
        public String At { get; set; }
        public int Duration { get; set; }
        public int RoomNr { get; set; }
        public int NumAdults { get; set; }
        public int NumChilds { get; set; }

        public Booking() { }

        public Booking(Customer Customer, Hotel Hotel, String At, int Duration, int RoomNr, int NumAdults, int NumChilds)
        {
            this.Customer = Customer;
            this.Hotel = Hotel;
            this.At = At;
            this.Duration = Duration;
            this.RoomNr = RoomNr;
            this.NumAdults = NumAdults;
            this.NumChilds = NumChilds;
        }

    }
}