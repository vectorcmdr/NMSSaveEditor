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

public class eZ : fc {
   public int index;
   // $FF: synthetic field
   public eY kL;

   public eZ(eY var1, int var2, fc var3) {
      // base(var1, var3);
      this.kL = var1;
      this.index = var2;
   }

   public Object a(Class var1, bool var2) {
      if (this.kN == null) {
         throw new Exception("Unexpected path");
      } else {
         eV var3 = (eV)this.kN.a(typeof(eV), var2);
         if (this.index >= 0 && this.index <= var3.Length) {
            if (this.index == var3.Length) {
               if (!var2) {
                  throw new fd((fd)null);
               } else {
                  Object var4;
                  try {
                     var4 = var1.GetType().Assembly.CreateInstance("");
                  } catch (Exception var6) {
                     throw new Exception("Unexpected error", var6);
                  }
                   var3.Add(var4);
                  return var4;
               }
            } else if (var1.IsInstanceOfType(var3.values[this.index])) {
               return var1.cast(var3.values[this.index]);
            } else {
               throw new Exception("Unexpected path");
            }
         } else {
            throw new Exception("Array index out of bounds");
         }
      }
   }

   public Object getValue() {
      if (this.kN == null) {
         throw new Exception("Unexpected path");
      } else {
         eV var1 = (eV)this.kN.a(typeof(eV), false);
         return var1.Get(this.index);
      }
   }

   public Object a(Object var1, bool var2) {
      if (this.kN == null) {
         throw new Exception("Unexpected path");
      } else {
         eV var3 = (eV)this.kN.a(typeof(eV), var2);
         if (this.index == var3.Length) {
            var3.Add(var1);
            return null;
         } else {
            return var3.Set(this.index, var1);
         }
      }
   }

   public Object bG() {
      if (this.kN == null) {
         throw new Exception("Unexpected path");
      } else {
         eV var1 = (eV)this.kN.a(typeof(eV), false);
         return var1.Remove(this.index);
      }
   }

   public eY e(eY var1) {
      if (this.kN == null) {
         throw new Exception("Unexpected path");
      } else {
         eV var2 = (eV)this.kN.a(typeof(eV), false);
         Object var3 = var2.Get(this.index);
         if (var3 == null) {
            var2.Set(this.index, var1);
            return null;
         } else if (var3 is eY) {
            ((eY)var3).c(var1);
            return (eY)var3;
         } else {
            throw new Exception("Unsupported type: " + var3.GetType().Name);
         }
      }
   }
}

}
