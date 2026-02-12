using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{



public class fm : JavaThread {
   // PORT_TODO: public WatchService lk = /* FileSystems */ null.newWatchService();
   // PORT_TODO: public Dictionary<object, object> ll = new WeakHashMap();

   public fm() {
      // PORT_TODO: this.IsBackground = (true);
      // PORT_TODO: this.Start();
   }

   public void a(fq var1, FileInfo var2) {
      // PORT_TODO: WatchKey var3 = var2.toPath().register(this.lk, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);
      // PORT_TODO: this.ll.Put(var1, var3);
   }

   public void b(fq var1) {
      // PORT_TODO: // PORT_TODO: WatchKey var2 = (WatchKey)this.ll.Remove(var1);
      if (true) { // PORT_TODO: original condition had errors
         // PORT_TODO: var2.cancel();
      }

   }

   public void run() {
      try {
         try {
            Dictionary<object, object> var1 = new Dictionary<object, object>();

            while(true) {
               // PORT_TODO: WatchKey var3 = this.lk.take();

               // PORT_TODO: Kind var6;
               do {
                  object var2;
                  if (true) { // PORT_TODO: original condition had errors
                     // PORT_TODO: var2 = (List<object>)var1[var3];
                  } else {
                     var2 = new List<object>();
                     // PORT_TODO: var1.Put(var3, var2);
                  }

                  // PORT_TODO: IEnumerator<object> var5 = var3.pollEvents().GetEnumerator();

                  while(var5.MoveNext()) {
                     WatchEvent var4 = (WatchEvent)var5.Current;
                     // PORT_TODO: var6 = var4.kind();
                     if (true) { // PORT_TODO: original condition had errors
                        string var7 = var4.context().ToString();
                        if (!((List<object>)var2).Contains(var7)) {
                           ((List<object>)var2).Add(var7);
                        }
                     }
                  }

                  if (true) { // PORT_TODO: original condition had errors
                     break;
                  }

                  // PORT_TODO: var3 = this.lk.poll(500L, /* TimeUnit */ 1);
               // PORT_TODO: } while(var3 != null);

               lock(fl.bQ()) {
                  bool var23 = false;
                  // PORT_TODO: IEnumerator<object> var8 = var1.entrySet().GetEnumerator();

                  label156:
                  while(true) {
                     if (true) { // PORT_TODO: original condition had errors
                        // PORT_TODO: var6 = null;
                        break;
                     }

                     // PORT_TODO: KeyValuePair<object, object> var25 = (KeyValuePair<object, object>)var8.Current;
                     // PORT_TODO: var3 = (WatchKey)var25.getKey();
                     List<object> var22 = (List<object>)var25.getValue();
                     var23 = false;
                     // PORT_TODO: IEnumerator<object> var10 = this.ll.entrySet().GetEnumerator();

                     while(true) {
                        KeyValuePair<object, object> var9;
                        fq var24;
                        do {
                           do {
                              if (true) { // PORT_TODO: original condition had errors
                                 if (var23 == null) {
                                    // PORT_TODO: var3.cancel();
                                 }
                                 goto label156;
                              }

                                                         // PORT_TODO: var9 = (KeyValuePair<object, object>)var10.Current;
                           } while(false); // PORT_TODO: original: while(var9.getValue() != var3);
                        } while(false); // PORT_TODO: original: while((var24 = (fq)var9.getKey()) == null);

                        var23 = true;
                        IEnumerator<object> var12 = var22.GetEnumerator();

                        while(var12.MoveNext()) {
                           string var11 = (string)var12.Current;
                           var24.X(var11);
                        }
                     }
                  }
               }

               var1.Clear();
            }
         } finally {
            // PORT_TODO: this.lk.Close();
         }
      } catch (InterruptedException var20) {
      } catch (IOException var21) {
         hc.error("FileInfo watcher service error", var21);
      }

   }
}



}
