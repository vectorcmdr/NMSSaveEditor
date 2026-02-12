using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{



public class hj : JavaThread {
   public long sK;
   public string sL;

   public hj(long var1) {
      this.sK = var1;
      this.sL = aH.getProperty("KnownPlayers." + var1);
      hi.ep().Put(var1, this);
      this.Start();
   }

   public void run() {
      string var1;
      try {
         var1 = hi.j(this.sK);
      } catch (IOException var7) {
         hc.a("Steam lookup error", var7);
         var1 = null;
      }

      lock(hi.ep()) {
         if (var1 != null) {
            Long[] var3 = (Long[])aH.a("SteamIDs", typeof(Long));
            bool var4 = new List<object>(new object[]{var3}).Any((var1x) => {
               return var1x.Equals(this.sK);
            });
            if (var4 == null) {
               Long[] var5 = new Long[var3.Length + 1];
               Array.Copy(var3, 0, var5, 0, var3.Length);
               var5[var3.Length] = this.sK;
               aH.a("SteamIDs", (object[])var5);
            }

            if (!var1.Equals(this.sL)) {
               aH.setProperty("KnownPlayers." + this.sK, var1);
               this.sL = var1;
            }
         }

      }
   }
}



}
