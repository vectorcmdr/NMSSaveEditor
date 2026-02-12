using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Windows.Forms;
using System.Globalization;

namespace NMSSaveEditor
{

public class hj : Thread {
   long sK;
   string sL;

   hj(long var1) {
      this.sK = var1;
      this.sL = aH.getProperty("KnownPlayers." + var1);
      hi.ep().Put(var1, this);
      this.start();
   }

   public void run() {
      string var1;
      try {
         var1 = hi.j(this.sK);
      } catch (IOException var7) {
         hc.a("Steam lookup error", var7);
         var1 = null;
      }

      (hi.ep()) {
         if (var1 != null) {
            Long[] var3 = (Long[])aH.a("SteamIDs", typeof(Long));
            bool var4 = new List<object> {var3).anyMatch((var1x) => {
               return var1x.Equals(this.sK);
            });
            if (!var4) {
               Long[] var5 = new Long[var3.length + 1];
               Array.Copy(var3, 0, var5, 0, var3.length);
               var5[var3.length] = this.sK;
               aH.a("SteamIDs", (Object[])var5);
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
