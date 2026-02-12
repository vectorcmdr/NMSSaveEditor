using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;

namespace NMSSaveEditor
{

class fY : fX, fs {
   int lO;
   fn me;
   string na;
   fT mN;

   fY(fT var1, int var2) {
      base(var1, "Slot" + (var2 / 2 + 1) + (var2 % 2 == 0 ? "Auto" : "Manual"));
      this.mN = var1;
      this.lO = var2;

      try {
         string var3 = new string(this.ah(1048576));
         Matcher var4 = fT.cl().matcher(var3);
         if (var4.find()) {
            this.na = var4.group(3);
         }

         this.me = fn.T(var3);
      } catch (IOException var5) {
         hc.a("Could not read game mode from " + this.mO.name, var5);
      }

   }

   fY(fT var1, fV var2, eY var3) {
      base(var1, var2);
      this.mN = var1;
      this.lO = var2.mb;
      this.mZ.a(var2.mQ);
      int var4 = fT.ao(var3.J("Version"));
      if (var4 != 0) {
         this.mZ.setVersion(var4);
      }

      this.na = var3.getValueAsString("CommonStateData.SaveName");
      if (this.na != null) {
         this.mZ.Y(this.na);
      }

      this.me = fn.i(var3);
      long var5 = var3.K("PlayerStateData.TotalPlayTime");
      if (var5 != 0L) {
         this.mZ.d(var5);
      }

      this.h(var3);
   }

   public fn L() {
      return this.me;
   }

   public long lastModified() {
      return this.mO.timestamp;
   }

   public eY M() {
      return this.a(eG.jV);
   }

   public string b(eY var1) {
      this.a(this.lO == 0 ? "wgsbackup" : "wgsbackup" + (this.lO + 1), this.me);
      int var2 = fT.ao(var1.J("Version"));
      if (var2 != 0) {
         this.mZ.setVersion(var2);
      }

      this.na = var1.getValueAsString("CommonStateData.SaveName");
      if (this.na != null) {
         this.mZ.Y(this.na);
      }

      this.me = fn.i(var1);
      long var3 = var1.K("PlayerStateData.TotalPlayTime");
      if (var3 != 0L) {
         this.mZ.d(var3);
      }

      this.h(var1);
      return this.mO.filename;
   }

   public string toString() {
      return this.mO.name;
   }

   public string getName() {
      return this.na;
   }

   public string getDescription() {
      return this.mZ.getDescription();
   }
}

}
