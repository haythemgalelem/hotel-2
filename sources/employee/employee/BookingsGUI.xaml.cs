using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;
using System.Data.Objects;

namespace employee
{
    /// <summary>
    /// Interaction logic for BookingsGUI.xaml
    /// </summary>
    public partial class BookingsGUI : Window
    {
        private hotelEntities entities;
        private int Hid { get; set; }
        
        public BookingsGUI(int Hid)
        {
            InitializeComponent();

            this.Hid = Hid;

            entities = new hotelEntities();
            ObjectQuery<booking> bookings = entities.booking;
            ObjectQuery<hotel> hotels = entities.hotel;
            ObjectQuery<customer> customers = entities.customer;

            var query = from
                  b in bookings
                        join c in customers on b.by_customer_email equals c.email
                        select new { b.at, b.duration, b.roomNr, b.numAdults, b.numChilds, c.email, c.adr, c.name, c.tel };

            dataGrid1.ItemsSource = query.ToList();

            var info = from h in hotels where h.hid.Equals(Hid) select h;

            List<hotel> hotel_info = info.ToList();

            info_hid.Content = hotel_info[0].hid;
            info_name.Content = hotel_info[0].name;
            info_adr.Content = hotel_info[0].adr;
        }

        private void logoutBtn_Click(object sender, RoutedEventArgs e)
        {
            (new MainWindow()).Show();
            Close();
        }

    }
}
