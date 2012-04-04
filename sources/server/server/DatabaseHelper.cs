﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data.SqlClient;
using System.Text;
using System.Collections;

namespace server
{
    public class DatabaseHelper
    {
        private static DatabaseHelper instance;
        private SqlConnection con;

        private DatabaseHelper()
        {
            con =  new SqlConnection("Data Source=" + Environment.GetEnvironmentVariable("HOTEL_DB_ADDR")
                  + ";Initial Catalog=" + Environment.GetEnvironmentVariable("HOTEL_DB_NAME")
                  + ";User Id=" + Environment.GetEnvironmentVariable("HOTEL_DB_USER")
                  + ";Password=" + Environment.GetEnvironmentVariable("HOTEL_DB_PWD")
                  + ";MultipleActiveResultSets=True;");
            //con = new SqlConnection("Initial Catalog=hotel;Data Source=localhost;Integrated Security=SSPI;");
            
            con.Open();
        }

        public static DatabaseHelper GetInstance()
        {
            if (instance == null)
                instance = new DatabaseHelper();
            return instance;
        }

        /**
         * Return Codes:
         * 
         *  0 -> wrong password
         *  1 -> success -> user
         *  2 -> email not known
         *  3 -> success -> employee
         **/
        public int Authenticate(string Email, string Pwd)
        {
            SqlCommand command;
            SqlDataReader reader = null;

            if (reader != null) reader.Close();
            
            command = new SqlCommand("SELECT email FROM customer WHERE email = @email", con);
            command.Parameters.AddWithValue("@email", Email);
            reader = command.ExecuteReader();

            int return_code = 2;

            if (reader.Read())
            {
                command = new SqlCommand("SELECT email FROM customer WHERE email = @email and pwd = @pwd", con);
                command.Parameters.AddWithValue("@email", Email);
                command.Parameters.AddWithValue("@pwd", Pwd);
                reader.Close();
                reader = command.ExecuteReader();

                if (reader.Read())
                {
                    return_code = 1;
                }
                else
                {
                    return_code = 0;
                }
            }
            else if (Email.Equals(Pwd))
            {
                try
                {
                    command = new SqlCommand("SELECT hid FROM hotel WHERE hid = @hid", con);
                    command.Parameters.AddWithValue("@hid", Email.ToString());
                    reader.Close();
                    reader = command.ExecuteReader();

                    if (reader.Read())
                    {
                        return_code = 3;
                    }
                }
                catch (Exception ignore) { }
                // Faced this Exception when typing "test" and "test", these two are equal but not numeric
            }
            reader.Close();
            return return_code;
        }

        public bool Register(String Email, String Pwd, String Name, String Adr, String Tel)
        {
            // "dirty" hack to check if the email is already taken

            if (this.Authenticate(Email, Email) != 2)
                return false;

            try
            {
                SqlCommand command = new SqlCommand("INSERT INTO customer (email, pwd, name, adr, tel) values (@email, @pwd, @name, @adr, @tel)", con);
                command.Parameters.AddWithValue("@email", Email);
                command.Parameters.AddWithValue("@pwd", Pwd);
                command.Parameters.AddWithValue("@name", Name);
                command.Parameters.AddWithValue("@adr", Adr);
                command.Parameters.AddWithValue("@tel", Tel);
                command.ExecuteNonQuery();
                return true;
            }
            catch (Exception e)
            {
                return false;
            }
        }

        public List<Booking> ListBookings(String Email)
        {
            List<Booking> list = new List<Booking>();
            SqlCommand command = new SqlCommand("SELECT customer.email, customer.name, customer.adr, customer.tel,"
                           + " booking.at, booking.duration, booking.roomNr, booking.numAdults, booking.numChilds,"
                           + " hotel.hid, hotel.name, hotel.adr"
                           + " FROM booking JOIN hotel on(hotel.hid = booking.in_hotel_hid) JOIN customer on(customer.email = booking.by_customer_email)"
                           + " WHERE customer.email = @email", con);
            command.Parameters.AddWithValue("@email", Email);
            SqlDataReader reader = command.ExecuteReader();

            while (reader.Read())
            {
                Booking b = new Booking(
                        new Customer(
                            (String)reader["email"],
                            (String)reader["name"],
                            (String)reader["adr"],
                            (Decimal)reader["tel"]
                        ),
                        new Hotel(
                            (int)reader[9],
                            (String)reader[10],
                            (String)reader[11]
                        ),
                        (String)reader[4],
                        (int)reader[5],
                        (int)reader[6],
                        (int)reader[7],
                        (int)reader[8]
                    ); 

                list.Add(b);
            }

            reader.Close();
            return list;
        }

        public List<Hotel> ListHotels()
        {
            List<Hotel> list = new List<Hotel>();
            SqlCommand command = new SqlCommand("SELECT hid, name, adr FROM hotel", con);
            SqlDataReader reader = command.ExecuteReader();

            while (reader.Read())
            {
                list.Add(new Hotel(
                            (int)reader[0],
                            (String)reader[1],
                            (String)reader[2]
                        ));
            }

            reader.Close();
            return list;
        }


        public bool NewBooking(String email, int hid, String at, int duration, int roomNr, int numAdults, int numChilds)
        {
            // Verify if the email is valid, the dirty way
            if (this.Authenticate(email, email) == 2)
                return false;

            try
            {
                SqlCommand command = new SqlCommand("INSERT INTO booking (by_customer_email, in_hotel_hid, at, duration, roomNr, numAdults, numChilds)"
                    + " VALUES(@by_customer_email, @in_hotel_id, @at, @duration, @roomNr, @numAdults, @numChilds)", con);
                command.Parameters.AddWithValue("@by_customer_email", email);
                command.Parameters.AddWithValue("@in_hotel_id", hid);
                command.Parameters.AddWithValue("@at", at);
                command.Parameters.AddWithValue("@duration", duration);
                command.Parameters.AddWithValue("@roomNr", roomNr);
                command.Parameters.AddWithValue("@numAdults", numAdults);
                command.Parameters.AddWithValue("@numChilds", numChilds);
                command.ExecuteNonQuery();

                return true;
            }
            catch (Exception e)
            {
                return false;
            }
        }
    }
}