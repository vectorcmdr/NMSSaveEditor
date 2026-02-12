using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{


public sealed class gq {
   public static gq[] Values() { return new gq[] { oS, oT, oU, oV, oW, oX, oY, oZ, pa, pb, pc }; }
   public static gq valueOf(string name) { foreach (var v in Values()) if (v.ToString() == name) return v; return null; }

   public static readonly gq oS = new gq("Combat");
   public static readonly gq oT = new gq("Exploration");
   public static readonly gq oU = new gq("Industry");
   public static readonly gq oV = new gq("Trading");
   public static readonly gq oW = new gq("Cost Per Warp");
   public static readonly gq oX = new gq("Expedition Fuel Cost", -1);
   public static readonly gq oY = new gq("Expedition Duration", -1);
   public static readonly gq oZ = new gq("Loot");
   public static readonly gq pa = new gq("Repair");
   public static readonly gq pb = new gq("Damage Reduction");
   public static readonly gq pc = new gq("Stealth");


   public string name;
   public int pd;

   public gq(string var3) {
      // PORT_TODO: // PORT_TODO: this(var3, 1);
   }

   public gq(string var3, int var4) {
      this.name = var3;
      this.pd = var4;
   }

   public int di() {
      return this.pd;
   }

   public string toString() {
      return this.name;
   }
}



}
