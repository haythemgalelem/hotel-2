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
using System.Windows.Navigation;
using System.Windows.Shapes;
using employee.ServiceReference1;

namespace employee
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
        }

        private void loginBtn_Click(object sender, RoutedEventArgs e)
        {
            ServerSoapClient serv = new ServerSoapClient();
            
            int auth = serv.Authenticate(hid.Text, pwd.Password);

            if (auth == 3)
            {
                // success, next please
                (new BookingsGUI( int.Parse( hid.Text ) )).Show();
                Close();
            }
            else
            {
                MessageBox.Show("Hotel ID oder Kennung falsch.");
            }
        }
    }
}
