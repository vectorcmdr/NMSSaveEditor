package nomanssave;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.WatchEvent.Kind;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

class fm extends Thread {
   final WatchService lk = FileSystems.getDefault().newWatchService();
   final Map ll = new WeakHashMap();

   fm() {
      this.setDaemon(true);
      this.start();
   }

   void a(fq var1, File var2) {
      WatchKey var3 = var2.toPath().register(this.lk, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);
      this.ll.put(var1, var3);
   }

   void b(fq var1) {
      WatchKey var2 = (WatchKey)this.ll.remove(var1);
      if (var2 != null) {
         var2.cancel();
      }

   }

   public void run() {
      try {
         try {
            HashMap var1 = new HashMap();

            while(true) {
               WatchKey var3 = this.lk.take();

               Kind var6;
               do {
                  Object var2;
                  if (var1.containsKey(var3)) {
                     var2 = (List)var1.get(var3);
                  } else {
                     var2 = new ArrayList();
                     var1.put(var3, var2);
                  }

                  Iterator var5 = var3.pollEvents().iterator();

                  while(var5.hasNext()) {
                     WatchEvent var4 = (WatchEvent)var5.next();
                     var6 = var4.kind();
                     if (var6 != StandardWatchEventKinds.OVERFLOW) {
                        String var7 = var4.context().toString();
                        if (!((List)var2).contains(var7)) {
                           ((List)var2).add(var7);
                        }
                     }
                  }

                  if (!var3.reset()) {
                     break;
                  }

                  var3 = this.lk.poll(500L, TimeUnit.MILLISECONDS);
               } while(var3 != null);

               synchronized(fl.bQ()) {
                  boolean var23 = false;
                  Iterator var8 = var1.entrySet().iterator();

                  label156:
                  while(true) {
                     if (!var8.hasNext()) {
                        var6 = null;
                        break;
                     }

                     Entry var25 = (Entry)var8.next();
                     var3 = (WatchKey)var25.getKey();
                     List var22 = (List)var25.getValue();
                     var23 = false;
                     Iterator var10 = this.ll.entrySet().iterator();

                     while(true) {
                        Entry var9;
                        fq var24;
                        do {
                           do {
                              if (!var10.hasNext()) {
                                 if (!var23) {
                                    var3.cancel();
                                 }
                                 continue label156;
                              }

                              var9 = (Entry)var10.next();
                           } while(var9.getValue() != var3);
                        } while((var24 = (fq)var9.getKey()) == null);

                        var23 = true;
                        Iterator var12 = var22.iterator();

                        while(var12.hasNext()) {
                           String var11 = (String)var12.next();
                           var24.X(var11);
                        }
                     }
                  }
               }

               var1.clear();
            }
         } finally {
            this.lk.close();
         }
      } catch (InterruptedException var20) {
      } catch (IOException var21) {
         hc.error("File watcher service error", var21);
      }

   }
}
