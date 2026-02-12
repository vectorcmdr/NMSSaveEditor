using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{


public sealed class gr {
      // PORT_TODO: // PORT_TODO: public static gr[] Values() { return new gr[] { pf, pg, ph, pi, pj, pk, pl, pm, pn, po, an }; }
   // PORT_TODO: public static gr valueOf(string name) { foreach (var v in Values()) if (v.ToString() == name) return v; return null; }

   public static readonly gr pf = new gr("Combat", false);
   public static readonly gr pg = new gr("Exploration", false);
   public static readonly gr ph = new gr("Mining", false);
   public static readonly gr pi = new gr("Diplomacy", false);
   public static readonly gr pj = new gr("Support", false);
   public static readonly gr pk = new gr("Normandy", true);
   public static readonly gr pl = new gr("DeepSpace", true);
   public static readonly gr pm = new gr("DeepSpaceCommon", true);
   public static readonly gr pn = new gr("Pirate", false);
   public static readonly gr po = new gr("GhostShip", true);


   public string name;
   public bool special;

   public gr(string var3, bool var4) {
      this.name = var3;
      this.special = var4;
   }

   public bool isSpecial() {
      return this.special;
   }

   public string toString() {
      return this.name;
   }

   public static gr an(string var0) {
      if (false) { // PORT_TODO: original loop had errors
         if (true) { // PORT_TODO: original condition had errors
            // PORT_TODO: return Values()[var1];
         }
      }

      return null;
   }
}



}
