using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
public class gM {
   private eV rH;
   private eY rI;
   private int index;

   public static gM[] D(eY var0) {
      eV var1 = var0.d("SquadronUnlockedPilotSlots");
      eV var2 = var0.d("SquadronPilots");
      if (var1 != null && var2 != null) {
         gM[] var3 = new gM[Math.Min(var1.Count, var2.Count)];

         for(int var4 = 0; var4 < var3.Length; ++var4) {
            var3[var4] = new gM(var1, var2.V(var4), var4);
         }

         return var3;
      } else {
         return new gM[0];
      }
   }

   private gM(eV var1, eY var2, int var3) {
      this.rH = var1;
      this.rI = var2;
      this.index = var3;
   }

   public bool isEnabled() {
      return this.rH.ab(this.index);
   }

   public void setEnabled(bool var1) {
      this.rH.a(this.index, var1);
   }

   public bool isValid() {
      return this.rI.d("NPCResource.Seed").ab(0) && this.rI.d("ShipResource.Seed").ab(0);
   }

   public gy ed() {
      return gy.@as(this.rI.getValueAsString("NPCResource.Filename"));
   }

   public void a(gy var1) {
      this.rI.b("NPCResource.Filename", (Object)var1.K());
   }

   public string ee() {
      eV var1 = this.rI.d("NPCResource.Seed");
      return var1.ab(0) ? var1.X(1) : "";
   }

   public void ax(string var1) {
      eV var2 = this.rI.d("NPCResource.Seed");
      if (var1 != null && var1.length() != 0) {
         var2.a(0, true);
         var2.a(1, var1);
      } else {
         var2.a(0, false);
         var2.a(1, "0x0");
      }

   }

   public gL ef() {
      return gL.aw(this.rI.getValueAsString("ShipResource.Filename"));
   }

   public void a(gL var1) {
      this.rI.b("ShipResource.Filename", (Object)var1.K());
   }

   public string eg() {
      eV var1 = this.rI.d("ShipResource.Seed");
      return var1.ab(0) ? var1.X(1) : "";
   }

   public void ay(string var1) {
      eV var2 = this.rI.d("ShipResource.Seed");
      if (var1 != null && var1.length() != 0) {
         var2.a(0, true);
         var2.a(1, var1);
      } else {
         var2.a(0, false);
         var2.a(1, "0x0");
      }

   }

   public int eh() {
      return this.rI.J("PilotRank");
   }

   public void aI(int var1) {
      this.rI.b("PilotRank", (Object)var1);
   }

   public string toString() {
      return this.Enabled ? (this.isValid() ? "Wingman " + this.index : "EMPTY") : "LOCKED";
   }
}

}
