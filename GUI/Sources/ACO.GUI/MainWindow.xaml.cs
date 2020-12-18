using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace ACO.GUI
{
    /// <summary>
    /// Logique d'interaction pour MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();

            CarTaylorHooks.Run();

            DisplayCategories();
        }

        private void DisplayCategories()
        {
            categories.Items.Clear();
           
            string result = CarTaylorHooks.Get("CATEGORIES");

            foreach (var category in result.Split(','))
            {
                categories.Items.Add(category);
            }
        }
        private void Button_Click(object sender, RoutedEventArgs e)
        {
            CarTaylorHooks.Put("EXPORT");
            Process.Start("export.html");
        }

        private void CategoriesOnSelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            variants.Items.Clear();

            string result = CarTaylorHooks.Get("VARIANTS " + categories.SelectedItem.ToString());

            foreach (var partType in result.Split(','))
            {
                variants.Items.Add(partType);
            }
        }

        private void Button_Click_1(object sender, RoutedEventArgs e)
        {
            if (variants.SelectedItem != null)
            {
                CarTaylorHooks.Put("SELECT " + variants.SelectedItem);

                UpdateConfiguration();
            }
        }

        private void UpdateConfiguration()
        {
            config.Items.Clear();

            string result = CarTaylorHooks.Get("VIEW");

            foreach (var partType in result.Split(','))
            {
                config.Items.Add(partType);
            }
        }

        private void Button_Click_2(object sender, RoutedEventArgs e)
        {
            if (config.SelectedItem != null)
            {
                var category = config.SelectedItem.ToString().Split('(')[1].Split(')')[0];

                CarTaylorHooks.Put("UNSELECT " + category);

                UpdateConfiguration();
            }

        }

        private void Button_Click_3(object sender, RoutedEventArgs e)
        {
            string res = CarTaylorHooks.Get("VALID");

            if (res == "true")
            {
                MessageBox.Show("La configuration est valide !", "Infos", MessageBoxButton.OK, MessageBoxImage.Asterisk);
            }
            else
            {
                MessageBox.Show("La configuration est invalide !", "Infos", MessageBoxButton.OK, MessageBoxImage.Warning);
            }
        }

        private void Button_Click_4(object sender, RoutedEventArgs e)
        {
            string res = CarTaylorHooks.Get("COMPLETE");


            if (res == "true")
            {
                MessageBox.Show("La configuration est complète !", "Infos", MessageBoxButton.OK, MessageBoxImage.Asterisk);
            }
            else
            {
                MessageBox.Show("La configuration est incomplète !", "Infos", MessageBoxButton.OK, MessageBoxImage.Warning);
            }
        }
    }
}
