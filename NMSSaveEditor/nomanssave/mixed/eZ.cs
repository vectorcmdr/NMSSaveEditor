using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
class eZ : fc {
   int index;
   eY kL;

   eZ(eY var1, int var2, fc var3) {
      base(var1, var3);
      this.kL = var1;
      this.index = var2;
   }

   protected override object a(Class var1, bool var2) {
      if (this.kN == null) {
         throw new Exception("Unexpected path");
      } else {
         eV var3 = (eV)this.kN.a(typeof(eV), var2);
         if (this.index >= 0 && this.index <= var3.Length) {
            if (this.index == var3.Length) {
               if (!var2) {
                  throw new fd((fd)null);
               } else {
                  object var4;
                  try {
                     var4 = var1.newInstance();
                  } catch (Exception var6) {
                     throw new Exception("Unexpected error", var6);
                  }

                  var3.Add(var4);
                  return var4;
               }
            } else if (var1.isInstance(var3.values[this.index])) {
               return var1.cast(var3.values[this.index]);
            } else {
               throw new Exception("Unexpected path");
            }
         } else {
            throw new Exception("Array index out of bounds");
         }
      }
   }

   protected override object getValue() {
      if (this.kN == null) {
         throw new Exception("Unexpected path");
      } else {
         eV var1 = (eV)this.kN.a(typeof(eV), false);
         return var1[this.index];
      }
   }

   protected override object a(object var1, bool var2) {
      if (this.kN == null) {
         throw new Exception("Unexpected path");
      } else {
         eV var3 = (eV)this.kN.a(typeof(eV), var2);
         if (this.index == var3.Length) {
            var3.Add(var1);
            return null;
         } else {
            return var3.set(this.index, var1);
         }
      }
   }

   protected override object bG() {
      if (this.kN == null) {
         throw new Exception("Unexpected path");
      } else {
         eV var1 = (eV)this.kN.a(typeof(eV), false);
         return var1.Remove(this.index);
      }
   }

   protected override eY e(eY var1) {
      if (this.kN == null) {
         throw new Exception("Unexpected path");
      } else {
         eV var2 = (eV)this.kN.a(typeof(eV), false);
         object var3 = var2[this.index];
         if (var3 == null) {
            var2.set(this.index, var1);
            return null;
         } else if (var3 is eY) {
            ((eY)var3).c(var1);
            return (eY)var3;
         } else {
            throw new Exception("Unsupported type: " + var3.GetType().getSimpleName());
         }
      }
   }
}

}
