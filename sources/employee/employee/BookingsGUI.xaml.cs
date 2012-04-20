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
using System.Configuration;
using employee.ServiceReference1;

namespace employee
{
    /// <summary>
    /// Interaction logic for BookingsGUI.xaml
    /// </summary>
    public partial class BookingsGUI : Window
    {
        private int Hid { get; set; }
        public Booking[] bookings;
        private Booking[] original_bookings;
        public Hotel hotel;

        private ServerSoapClient serv = new ServerSoapClient();
        
        public BookingsGUI(int Hid)
        {
            InitializeComponent();
            this.Hid = Hid;

            hotel = this.serv.HotelInfo(this.Hid);
          
            info_hid.Content = hotel.Hid;
            info_name.Content = hotel.Name;
            info_adr.Content = hotel.Adr;

            this.reloadBtn_Click(null, null);
        }

        private void logoutBtn_Click(object sender, RoutedEventArgs e)
        {
            (new MainWindow()).Show();
            Close();
        }

        private void reloadBtn_Click(object sender, RoutedEventArgs e)
        {

            bookings = this.serv.ListBookingsByHid(this.Hid);
            original_bookings = this.serv.ListBookingsByHid(this.Hid);

            info_rows.Content = bookings.Length;

            dataGrid1.ItemsSource = bookings.ToList();

        }

        private void button1_Click(object sender, RoutedEventArgs e)
        {
            bool something_changed = false;
            for (int i = 0; i < original_bookings.Length; i++)
            {
                Booking original = original_bookings[i];
                Booking changed = bookings[i];
                Console.WriteLine("[Originial] Duration = " + original.Duration);
                Console.WriteLine("[Changed] Duration = " + changed.Duration);
                if (
                    original.Duration != changed.Duration ||
                    original.RoomNr != changed.RoomNr ||
                    original.NumAdults != changed.NumAdults ||
                    original.NumChilds != changed.NumChilds)
                {
                    something_changed = true;
                    this.serv.EditBooking(changed.Customer.Email, this.Hid, changed.At, changed.Duration, changed.RoomNr, changed.NumAdults, changed.NumChilds);
                }
            }

            if (something_changed)
            {
                reloadBtn_Click(null, null);
                MessageBox.Show("Something changed, saved.");
            }
            else
            {
                MessageBox.Show("Nothing changed sorry.");
            }

        }

        private void button2_Click(object sender, RoutedEventArgs e)
        {
            if (dataGrid1.SelectedIndex < 0)
            {
                MessageBox.Show("No row selected");
            }
            else
            {
                Booking delete = this.bookings[dataGrid1.SelectedIndex];
                this.serv.DeleteBooking(delete.Customer.Email, this.Hid, delete.At);
                reloadBtn_Click(null, null);
                MessageBox.Show("Deleted!");

            }
        }

        private void button3_Click(object sender, RoutedEventArgs e)
        {
            PrintDialog printDlg = new PrintDialog();

            if ((bool)printDlg.ShowDialog().GetValueOrDefault())
            {
                Size pageSize = new Size(printDlg.PrintableAreaWidth, printDlg.PrintableAreaHeight);
                // sizing of the element.
                dataGrid1.Measure(pageSize);
                dataGrid1.Arrange(new Rect(5, 5, pageSize.Width, pageSize.Height));
                printDlg.PrintVisual(dataGrid1, Title);
            }

        }
    }
}
