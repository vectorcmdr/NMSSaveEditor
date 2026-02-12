using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Xml;

namespace NMSSaveEditor
{

public class eR : ey {
   string kc;
   eA kn;
   eQ ko;

   eR(eQ var1, Element var2) {
      base(var2.getAttribute("id"));
      this.ko = var1;
      this.kc = var2.hasAttribute("icon") ? var2.getAttribute("icon") : null;
      this.kn = ey.p(var2.getAttribute("template"));
   }

   public object aZ() {
      return this.M(this.ko.jY ? (int)Math.Floor(new Random().NextDouble() * 100000.0D) : 0);
   }

   public object M(int var1) {
      if (this.id.length() == 13 && this.id[0] == '^') {
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

   public override eB ba() {
      return eB.jO;
   }

   public override bool bb() {
      return this.ko.jY;
   }

   private string y(string var1) {
      if ("NAME".Equals(var1)) {
         return this.ko.name;
      } else {
         return "TECH_DESC".Equals(var1) ? this.ko.description : var1;
      }
   }

   public override string getName() {
      return this.kn.a(this.y);
   }

   public override ex bc() {
      return ex.jd;
   }

   public override bool bd() {
      return false;
   }

   public override bool be() {
      return false;
   }

   public override Integer bf() {
      return null;
   }

   public override string bg() {
      return this.kn.b(this.y);
   }

   public override bool bh() {
      return false;
   }

   public override string bi() {
      return this.kc;
   }

   public override int bj() {
      return 0;
   }

   public override string getDescription() {
      return this.kn.c(this.y);
   }

   public override List<object> bk() {
      return this.ko.ke;
   }

   public string toString() {
      return this.ko.name.length() == 0 ? this.id : this.ko.name;
   }
}

}