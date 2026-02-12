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

public class ge {
   public List<object> gT;
   public List<object> nh;
   public List<object> ni;

   public static ge m(eY var0) {
      return new ge(var0);
   }

   public static Function ap(int var0) {
      return (var1) => {
         string var2 = var1.Name;
         if (var2 == null || var2.Length == 0 || "BLD_STORAGE_NAME".Equals(var2)) {
            var2 = "Chest " + var0;
         }

         return new string[]{var2};
      };
   }

   public static Function cB() {
      return (var0) => {
         return new string[]{"Ingredient Storage"};
      };
   }

   public ge(eY var1) {
      byte var2 = 8;
      byte var3 = 6;
      if (Application.e().D()) {
         var2 = 10;
         var3 = 8;
      }

      List<object> var4 = new List<object>();

      for(int var5 = 0; var5 < 10; ++var5) {
         var4.Add(new gt(ap(var5), var1.H("Chest" + (var5 + 1) + "Inventory"), 3584, var2, var3, false, false));
      }

      eY var14 = var1.H("CookingIngredientsInventory");
      if (var14 != null) {
         var4.Add(new gt(cB(), var14, 36352, var2, var3, false, false));
      }

      this.gT = UnmodifiableList(var4);
      List<object> var6 = new List<object>();
      eV var7 = var1.d("NPCWorkers");
      string var8 = "";

      for(int var10 = 0; var10 < var7.Count && var10 < 5; ++var10) {
         eY var9 = var7.V(var10);
         if (var9.M("HiredWorker")) {
            switch(var10) {
            case 0:
               var8 = "Armorer";
               break;
            case 1:
               var8 = "Farmer";
               break;
            case 2:
               var8 = "Overseer";
               break;
            case 3:
               var8 = "Technician";
               break;
            case 4:
               var8 = "Scientist";
            }

            var6.Add(new gh(this, var8, var9, (gh)null));
         }
      }

      this.nh = UnmodifiableList(var6);
      List<object> var15 = new List<object>();
      eV var11 = var1.d("PersistentPlayerBases");

      for(int var13 = 0; var13 < var11.Count; ++var13) {
         eY var12 = var11.V(var13);
         if ("HomePlanetBase".Equals(var12.getValueAsString("BaseType.PersistentBaseTypes")) && var12.J("BaseVersion") >= 3) {
            var15.Add(new gf(this, var12, (gf)null));
         }
      }

      this.ni = UnmodifiableList(var15);
   }

   public List<object> cC() {
      return this.gT;
   }

   public List<object> cD() {
      return this.nh;
   }

   public List<object> cE() {
      return this.ni;
   }
}

}
