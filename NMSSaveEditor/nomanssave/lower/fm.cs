using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

public class fm : JavaThread {
   // PORT_TODO: File watcher service - needs FileSystemWatcher implementation
   // public WatchService lk;
   // public Dictionary<object, object> ll = new Dictionary<object, object>();

   public fm() {
      // PORT_TODO: this.IsBackground = true;
      // PORT_TODO: this.Start();
   }

   public void a(fq var1, FileInfo var2) {
      // PORT_TODO: Register file/directory for watching
   }

   public void b(fq var1) {
      // PORT_TODO: Unregister watcher
   }

   public void run() {
      try {
         try {
            Dictionary<object, object> var1 = new Dictionary<object, object>();

            while(true) {
               // PORT_TODO: File watcher event loop
               System.Threading.Thread.Sleep(500);
               var1.Clear();
            }
         } finally {
            // PORT_TODO: this.lk.Close();
         }
      } catch (Exception) {
         // InterruptedException or IOException
      }
   }
}

}
