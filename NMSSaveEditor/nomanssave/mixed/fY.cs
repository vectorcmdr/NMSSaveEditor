using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;

namespace NMSSaveEditor
{



public class fY : fX, fs {
   public DateTime LastWriteTimeUtc => DateTimeOffset.FromUnixTimeMilliseconds(lastModified()).UtcDateTime;
   public string Name => getName();
   public int lO;
   public fn me;
   public string na;
   public fT mN;

public fY(fT var1, int var2) : base(var1, "Slot" + (var2 / 2 + 1) + (var2 % 2 == 0 ? "Auto" : "Manual")) {
      this.mN = var1;
      this.lO = var2;

      try {
         // PORT_TODO: string var3 = new string(this.ah(1048576));
         // PORT_TODO: Matcher var4 = fT.cl().matcher(var3);
         // PORT_TODO: if (var4.find()) {
      // PORT_TODO: Matcher var4 = null; // PORT_TODO: stub declaration
            // PORT_TODO: this.na = var4.group(3);
         // PORT_TODO: }

         // PORT_TODO: this.me = fn.T(var3);
      } catch (IOException var5) {
         hc.a("Could not read game mode from " + this.mO.name, var5);
      }

   }

public fY(fT var1, fV var2, eY var3) : base(var1, var2) {
      this.mN = var1;
      this.lO = var2.mb;
      this.mZ.a(var2.mQ);
      // PORT_TODO: int var4 = fT.ao(var3.J("Version"));
      // PORT_TODO: if (var4 != 0) {
         // PORT_TODO: this.mZ.setVersion(var4);
      // PORT_TODO: }

      // PORT_TODO: this.na = var3.getValueAsString("CommonStateData.SaveName");
      if (this.na != null) {
         this.mZ.Y(this.na);
      }

      this.me = fn.i(var3);
      // PORT_TODO: long var5 = var3.K("PlayerStateData.TotalPlayTime");
      // PORT_TODO: if (var5 != 0L) {
         // PORT_TODO: this.mZ.d(var5);
      // PORT_TODO: }

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
      // PORT_TODO: int var2 = fT.ao(var1.J("Version"));
      // PORT_TODO: if (var2 != 0) {
         // PORT_TODO: this.mZ.setVersion(var2);
      // PORT_TODO: }

      // PORT_TODO: this.na = var1.getValueAsString("CommonStateData.SaveName");
      if (this.na != null) {
         this.mZ.Y(this.na);
      }

      this.me = fn.i(var1);
      // PORT_TODO: long var3 = var1.K("PlayerStateData.TotalPlayTime");
      // PORT_TODO: if (var3 != 0L) {
         // PORT_TODO: this.mZ.d(var3);
      // PORT_TODO: }

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
