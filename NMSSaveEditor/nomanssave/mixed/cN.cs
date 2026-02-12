using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

public class cN : ComboBox {
   public bool gm;
   private readonly Enum[] gn;
   public List<object> go;
   public cR gp;
   public object gq;
   public static Color gr;
   public static Color gs;

   static cN() {
      gr = Color.Red;
      gs = new Color(255, 100, 100);
   }

   public cN(Class var1) {
      this.gm = typeof(gD).isAssignableFrom(var1);
      this.gn = (Enum[])var1.getEnumConstants();
      this.go = new List<object>();
      this.DataSource = (new cO(this, var1));
      this.setRenderer(new cP(this));
   }

   public void m(string var1) {
      object var2 = null;
      if (var1 != null) {
         Enum[] var6;
         int var5 = (var6 = this.gn).Length;

         for(int var4 = 0; var4 < var5; ++var4) {
            Enum var3 = var6[var4];
            if (this.gm) {
               if (((gD)var3).K().Equals(var1)) {
                  var2 = var3;
                  break;
               }
            } else if (var3.name().Equals(var1)) {
               var2 = var3;
               break;
            }
         }

         if (var2 == null) {
            int var7 = this.go.IndexOf(new cQ(this, var1));
            if (var7 >= 0) {
               var2 = this.go[var7];
            } else {
               var2 = this.gm ? new cS(this, var1) : var1;
               this.go.Add(var2);
            }
         }
      }

      this.gq = var2;
      this.selectedItemChanged();
      this.Refresh();
   }

   public void a(cR var1) {
      this.gp = var1;
   }
   public static List<object> a(cN var0) {
      return var0.go;
   }
   public static object b(cN var0) {
      return var0.gq;
   }
   public static void a(cN var0, object var1) {
      var0.gq = var1;
   }
   public static cR c(cN var0) {
      return var0.gp;
   }
   public static bool d(cN var0) {
      return var0.gm;
   }
   public static Enum[] e(cN var0) {
      return var0.gn;
   }
   public static Color ag() {
      return gs;
   }
   public static Color aB() {
      return gr;
   }
}

}
