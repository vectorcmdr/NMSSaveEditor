using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
public enum gr {
   pf("Combat", false),
   pg("Exploration", false),
   ph("Mining", false),
   pi("Diplomacy", false),
   pj("Support", false),
   pk("Normandy", true),
   pl("DeepSpace", true),
   pm("DeepSpaceCommon", true),
   pn("Pirate", false),
   po("GhostShip", true);

   private string name;
   private bool special;

   private gr(string var3, bool var4) {
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
      for(int var1 = 0; var1 < values().Length; ++var1) {
         if (var0.equalsIgnoreCase(values()[var1].name)) {
            return values()[var1];
         }
      }

      return null;
   }
}

}
