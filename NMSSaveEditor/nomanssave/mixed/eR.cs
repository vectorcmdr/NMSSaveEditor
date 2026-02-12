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

public class eR : ey {
   public string kc;
   public eA kn;
   // $FF: synthetic field
   public eQ ko;

   public eR(eQ var1, XmlElement var2) {
      // base(var2.GetAttribute("id"));
      this.ko = var1;
      this.kc = var2.HasAttribute("icon") ? var2.GetAttribute("icon") : null;
      this.kn = ey.p(var2.GetAttribute("template"));
   }

   public Object aZ() {
      return this.M(this.ko.jY ? (int)Math.Floor(new Random().NextDouble() * 100000.0D) : 0);
   }

   public Object M(int var1) {
      if (this.id.Length == 13 && this.id[0] == '^') {
         if (var1 >= 0 && var1 < 100000) {
            MemoryStream var2 = new MemoryStream();
            var2.Write(94);

            int var3;
            int var4;
            for(var3 = 0; var3 < 6; ++var3) {
               var4 = "0123456789ABCDEFabcdef".IndexOf(this.id[var3 * 2 + 1]);
               int var5 = "0123456789ABCDEFabcdef".IndexOf(this.id[var3 * 2 + 2]);
               if (var4 < 0 || var5 < 0) {
                  throw new Exception("Cannot create ID: invalid hex");
               }

               if (var4 >= 16) {
                  var4 -= 6;
               }

               if (var5 >= 16) {
                  var5 -= 6;
               }

               var2.Write(var4 << 4 | var5);
            }

            var2.Write(35);

            for(var3 = 100000; var3 > 1; var3 /= 10) {
               var4 = var1 * 10 / var3 % 10;
               var2.Write("0123456789ABCDEFabcdef"[var4]);
            }

            return new fg(var2.toByteArray());
         } else {
            throw new Exception("Cannot create ID: invalid proc");
         }
      } else {
         throw new Exception("Cannot create ID: invalid string");
      }
   }

   public eB ba() {
      return eB.jO;
   }

   public bool bb() {
      return this.ko.jY;
   }

   public string y(string var1) {
      if ("NAME".Equals(var1)) {
         return this.ko.name;
      } else {
         return "TECH_DESC".Equals(var1) ? this.ko.description : var1;
      }
   }

   public string getName() {
      return this.kn.a(this.y);
   }

   public ex bc() {
      return ex.jd;
   }

   public bool bd() {
      return false;
   }

   public bool be() {
      return false;
   }

   public Integer bf() {
      return null;
   }

   public string bg() {
      return this.kn.b(this.y);
   }

   public bool bh() {
      return false;
   }

   public string bi() {
      return this.kc;
   }

   public int bj() {
      return 0;
   }

   public string getDescription() {
      return this.kn.c(this.y);
   }

   public List<object> bk() {
      return this.ko.ke;
   }

   public string toString() {
      return this.ko.name.Length == 0 ? this.id : this.ko.name;
   }
}

}
