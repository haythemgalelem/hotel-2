﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;

namespace server
{
    /// <summary>
    /// Summary description for Server
    /// </summary>
    [WebService(Namespace = "http://myxcode.at/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // To allow this Web Service to be called from script, using ASP.NET AJAX, uncomment the following line. 
    // [System.Web.Script.Services.ScriptService]
    public class Server : System.Web.Services.WebService
    {
        private DatabaseHelper db = DatabaseHelper.GetInstance();

        public Server() { }

        [WebMethod]
        public int Authenticate(String email, String pwd)
        {
            db.CreateTablesIfNotExist();

            if (email == null &&  pwd == null)
            {
                throw new Exception("WHY?");
            }
            return db.Authenticate(email, pwd);
        }

        [WebMethod]
        public bool Register(String email, String pwd, String name, String adr, String tel)
        {
            return db.Register(email, pwd, name, adr, tel);
        }

        [WebMethod]
        public List<Booking> ListBookings(String email)
        {
            return db.ListBookings(email);
        }

        [WebMethod]
        public List<Booking> ListBookingsByHid(int hid)
        {
            return db.ListBookingsByHid(hid);
        }

        [WebMethod]
        public bool NewBooking(String email, int hid, String at, int duration, int roomNr, int numAdults, int numChilds)
        {
            return db.NewBooking(email, hid, at, duration, roomNr, numAdults, numChilds);
        }


        [WebMethod]
        public List<Hotel> ListHotels()
        {
            return db.ListHotels();
        }

        [WebMethod]
        public Hotel HotelInfo(int hid)
        {
            return db.HotelInfo(hid);
        }

        [WebMethod]
        public bool EditBooking(String email, int hid, String at, int duration, int roomNr, int numAdults, int numChilds)
        {
            return db.EditBooking(email, hid, at, duration, roomNr, numAdults, numChilds);
        }

        [WebMethod]
        public bool DeleteBooking(String email, int hid, String at)
        {
            return db.DeleteBooking(email, hid, at);
        }
    }
}
