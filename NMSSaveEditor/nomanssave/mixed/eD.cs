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

public class eD : eE {
   public string version;

   public eD(Stream var1, string var2) {
      // base((eE)null);
      this.version = var2;
      List<object> var3 = new List<object>();
      StreamReader var4 = new StreamReader(new StreamReader(var1));
       string var5;
      try {
         while((var5 = var4.ReadLine()) != null) {
            try {
               if (var5.Length != 0) {
                  int var9 = var5.IndexOf("\t");
                  if (var9 < 0) {
                     hc.debug("Mapping not available: " + var5);
                     var3.Add(var5);
                  } else {
                     string var6 = var5.Substring(0, var9);
                     string var7 = var5.Substring(var9 + 1, var5.Length);
                     eF var8;
                     if ((var8 = this.t(var6)) != null) {
                        if (!var7.Equals(var8.name)) {
                           throw new IOException("Mapping error: " + var6);
                        }
                         hc.debug("Mapping duplicated: " + var6);
                     } else if ((var8 = this.u(var7)) != null) {
                        if (!var6.Equals(var8.key)) {
                           throw new IOException("Reverse error: " + var7);
                        }
                         hc.debug("Reverse duplicated: " + var7);
                     } else {
                        this.Add(var6, var7);
                     }
                  }
               }
            } catch (Exception var13) {
               hc.a("Ignoring: " + var5, var13);
            }
         }
      } finally {
         var4.Close();
      }
       IEnumerator<object> var15 = var3.GetEnumerator();
       while(var15.MoveNext()) {
         var5 = (string)var15.Current;
         if (this.t(var5) != null) {
            throw new IOException("Mapping error: " + var5);
         }
          if (this.u(var5) != null) {
            throw new IOException("Reverse error: " + var5);
         }
          this.Add(var5, var5);
      }
    }

   public string toString() {
      return this.version;
   }

   // $FF: synthetic method
   public eD(Stream var1, string var2, eD var3) {
      // Constructor chain: base(var1, var2)
   }
}

}
