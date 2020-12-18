using Microsoft.Win32;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;

namespace ACO.GUI
{
    public class CarTaylorBinding
    {
        /// <summary>
        /// JRE : full.win32.x86_64_14.0.2.v20200815-0932
        /// </summary>
        private static string JavaPath = @"C:\Users\" + Environment.UserName + @"\.p2\pool\plugins\org.eclipse.justj.openjdk.hotspot.jre.full.win32.x86_64_14.0.2.v20200815-0932\jre\bin\javaw.exe";

        private const string JarPath = "cartaylor.jar";

        private static Process Process
        {
            get;
            set;
        }
        public static void Run()
        {
            if (!File.Exists(JavaPath))
            {
                MessageBox.Show("Unable to locate java path. ", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
                Environment.Exit(1);
            }

            if (!File.Exists(JarPath))
            {
                MessageBox.Show("Unable to locate jar path.", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
                Environment.Exit(1);
            }

            StartJar(JarPath);
        }
        private static void StartJar(string filepath)
        {
            Process = new Process();
            Process.StartInfo.FileName = JavaPath;
            Process.StartInfo.Arguments = "-jar " + filepath;
            Process.StartInfo.UseShellExecute = false;
            Process.StartInfo.RedirectStandardOutput = true;
            Process.StartInfo.RedirectStandardInput = true;
            Process.Start();

        }

        public static string Get(string request)
        {
            Process.StandardInput.WriteLine(request);
            return Process.StandardOutput.ReadLine();
        }
        public static void Put(string request)
        {
            Process.StandardInput.WriteLine(request);
        }
    }
}
