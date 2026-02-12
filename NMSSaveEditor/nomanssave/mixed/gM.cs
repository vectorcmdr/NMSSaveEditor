using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{


public class gM {
   public bool Enabled => isEnabled();
   public eV rH;
   public eY rI;
   public int index;

   public static gM[] D(eY var0) {
      // PORT_TODO: eV var1 = var0.d("SquadronUnlockedPilotSlots");
      // PORT_TODO: eV var2 = var0.d("SquadronPilots");
      // PORT_TODO: if (var1 != null && var2 != null) {
         // PORT_TODO: gM[] var3 = new gM[Math.Min(var1.Count, var2.Count)];

         // PORT_TODO: for(int var4 = 0; var4 < var3.Length; ++var4) {
            // PORT_TODO: var3[var4] = new gM(var1, var2.V(var4), var4);
         // PORT_TODO: }

         // PORT_TODO: return var3;
      // PORT_TODO: } else {
         // PORT_TODO: return new gM[0];
      // PORT_TODO: }
      return null;
   }

   public gM(eV var1, eY var2, int var3) {
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
      // PORT_TODO: return this.rI.d("NPCResource.Seed").ab(0) && this.rI.d("ShipResource.Seed").ab(0);
      return false;
   }

   public gy ed() {
      // PORT_TODO: return gy.@as(this.rI.getValueAsString("NPCResource.Filename"));
      return default;
   }

   public void a(gy var1) {
      // PORT_TODO: this.rI.b("NPCResource.Filename", (object)var1.K());
   }

   public string ee() {
      // PORT_TODO: eV var1 = this.rI.d("NPCResource.Seed");
      // PORT_TODO: return var1.ab(0) ? var1.X(1) : "";
      return default;
   }

   public void ax(string var1) {
      // PORT_TODO: eV var2 = this.rI.d("NPCResource.Seed");
      if (var1 != null && var1.Length != 0) {
         // PORT_TODO: var2.a(0, true);
         // PORT_TODO: var2.a(1, var1);
      } else {
         // PORT_TODO: var2.a(0, false);
         // PORT_TODO: var2.a(1, "0x0");
      }

   }

   public gL ef() {
      // PORT_TODO: return gL.aw(this.rI.getValueAsString("ShipResource.Filename"));
      return default;
   }

   public void a(gL var1) {
      // PORT_TODO: this.rI.b("ShipResource.Filename", (object)var1.K());
   }

   public string eg() {
      // PORT_TODO: eV var1 = this.rI.d("ShipResource.Seed");
      // PORT_TODO: return var1.ab(0) ? var1.X(1) : "";
      return default;
   }

   public void ay(string var1) {
      // PORT_TODO: eV var2 = this.rI.d("ShipResource.Seed");
      if (var1 != null && var1.Length != 0) {
         // PORT_TODO: var2.a(0, true);
         // PORT_TODO: var2.a(1, var1);
      } else {
         // PORT_TODO: var2.a(0, false);
         // PORT_TODO: var2.a(1, "0x0");
      }

   }

   public int eh() {
      // PORT_TODO: return this.rI.J("PilotRank");
      return 0;
   }

   public void aI(int var1) {
      // PORT_TODO: this.rI.b("PilotRank", (object)var1);
   }

   public string toString() {
      return this.Enabled ? (this.isValid() ? "Wingman " + this.index : "EMPTY") : "LOCKED";
   }
}



}
