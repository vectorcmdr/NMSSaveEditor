using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

public class fm : JavaThread {
   public WatchService lk = /* FileSystems */ null.newWatchService();
   public Dictionary<object, object> ll = new WeakHashMap();

   public fm() {
      this.IsBackground = (true);
      this.Start();
   }

   public void a(fq var1, FileInfo var2) {
      WatchKey var3 = var2.toPath().register(this.lk, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);
      this.ll.Put(var1, var3);
   }

   public void b(fq var1) {
      WatchKey var2 = (WatchKey)this.ll.Remove(var1);
      if (var2 != null) {
         var2.cancel();
      }

   }

   public void run() {
      try {
         try {
            Dictionary<object, object> var1 = new Dictionary<object, object>();

            while(true) {
               WatchKey var3 = this.lk.take();

               Kind var6;
               do {
                  object var2;
                  if (var1.ContainsKey(var3)) {
                     var2 = (List<object>)var1[var3];
                  } else {
                     var2 = new List<object>();
                     var1.Put(var3, var2);
                  }

                  IEnumerator var5 = var3.pollEvents().GetEnumerator();

                  while(var5.MoveNext()) {
                     WatchEvent var4 = (WatchEvent)var5.Current;
                     var6 = var4.kind();
                     if (var6 != StandardWatchEventKinds.OVERFLOW) {
                        string var7 = var4.context().ToString();
                        if (!((List<object>)var2).Contains(var7)) {
                           ((List<object>)var2).Add(var7);
                        }
                     }
                  }

                  if (!var3.reset()) {
                     break;
                  }

                  var3 = this.lk.poll(500L, /* TimeUnit */ 1);
               } while(var3 != null);

               lock(fl.bQ()) {
                  bool var23 = false;
                  IEnumerator var8 = var1.entrySet().GetEnumerator();

                  label156:
                  while(true) {
                     if (!var8.MoveNext()) {
                        var6 = null;
                        break;
                     }

                     Entry var25 = (Entry)var8.Current;
                     var3 = (WatchKey)var25.getKey();
                     List<object> var22 = (List<object>)var25.getValue();
                     var23 = false;
                     IEnumerator var10 = this.ll.entrySet().GetEnumerator();

                     while(true) {
                        Entry var9;
                        fq var24;
                        do {
                           do {
                              if (!var10.MoveNext()) {
                                 if (!var23) {
                                    var3.cancel();
                                 }
                                 goto label156;
                              }

                              var9 = (Entry)var10.Current;
                           } while(var9.getValue() != var3);
                        } while((var24 = (fq)var9.getKey()) == null);

                        var23 = true;
                        IEnumerator var12 = var22.GetEnumerator();

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
            this.lk.Close();
         }
      } catch (InterruptedException var20) {
      } catch (IOException var21) {
         hc.error("FileInfo watcher service error", var21);
      }

   }
}

}
