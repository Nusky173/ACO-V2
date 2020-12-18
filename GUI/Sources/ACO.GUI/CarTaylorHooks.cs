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
    /**
     * Allow interaction with CarTaylor
     * using the Command pattern.
     * 
     */
    public class CarTaylorHooks
    {
        /// <summary>
        /// JRE : full.win32.x86_64_14.0.2.v20200815-0932
        /// Not sure about that :/
        /// I dont have the time to write a clean implementation of this
        /// You may change it manually if its not workin' :/ sry.
        /// </summary>
        private static string JAVA_PATH = @"C:\Users\" + Environment.UserName + @"\.p2\pool\plugins\org.eclipse.justj.openjdk.hotspot.jre.full.win32.x86_64_14.0.2.v20200815-0932\jre\bin\javaw.exe";

        private const string JAR_PATH = "cartaylor.jar";
        /**
         * The process we create
         */
        private static Process Process
        {
            get;
            set;
        }
        public static void Run()
        {
            if (!File.Exists(JAVA_PATH))
            {
                MessageBox.Show("Unable to locate java path. ", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
                Environment.Exit(1);
            }

            if (!File.Exists(JAR_PATH))
            {
                MessageBox.Show("Unable to locate jar path.", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
                Environment.Exit(1);
            }

            StartJar(JAR_PATH);
        }
        /**
         * Start the Jar process
         */
        private static void StartJar(string filepath)
        {
            Process = new Process();
            Process.StartInfo.FileName = JAVA_PATH;
            Process.StartInfo.Arguments = "-jar " + filepath;
            Process.StartInfo.UseShellExecute = false;
            Process.StartInfo.RedirectStandardOutput = true;
            Process.StartInfo.RedirectStandardInput = true;
            Process.Start();

        }
        /**
         * Request a command and read the result. (T command())
         */
        public static string Get(string request)
        {
            Process.StandardInput.WriteLine(request);
            return Process.StandardOutput.ReadLine();
        }
        /**
         * Request a command without reading a result (void command())
         */
        public static void Put(string request)
        {
            Process.StandardInput.WriteLine(request);
        }
    }
}
