using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{


public class eZ : fc {
   public int index;
   public eY kL;

   public eZ(eY var1, int var2, fc var3) : base(var1, var3) {
      this.kL = var1;
      this.index = var2;
   }

   public override object a(Class var1, bool var2) {
      if (this.kN == null) {
         throw new Exception("Unexpected path");
      } else {
         eV var3 = (eV)this.kN.a(typeof(eV), var2);
         if (this.index >= 0 && this.index <= var3.length) {
            if (this.index == var3.length) {
               if (!var2) {
                  throw new fd((fd)null);
               } else {
                  object var4 = default;
                  try {
                     // PORT_TODO: var4 = Activator.CreateInstance(var1);
                  } catch (Exception var6) {
                     throw new Exception("Unexpected error", var6);
                  }

                  // PORT_TODO: var3.add(var4);
                  return var4;
               }
            } else if (var1.IsInstanceOfType(var3.values[this.index])) {
               return var3.values[this.index];
            } else {
               throw new Exception("Unexpected path");
            }
         } else {
            throw new Exception("Array index out of bounds");
         }
      }
   }

   public override object getValue() {
      if (this.kN == null) {
         throw new Exception("Unexpected path");
      } else {
         eV var1 = (eV)this.kN.a(typeof(eV), false);
         return var1.get(this.index);
      }
   }

   public override object a(object var1, bool var2) {
      if (this.kN == null) {
         throw new Exception("Unexpected path");
      } else {
         eV var3 = (eV)this.kN.a(typeof(eV), var2);
         if (this.index == var3.length) {
            var3.add(var1);
            return null;
         } else {
            return var3.set(this.index, var1);
         }
      }
   }

   public override object bG() {
      if (this.kN == null) {
         throw new Exception("Unexpected path");
      } else {
         eV var1 = (eV)this.kN.a(typeof(eV), false);
         return var1.remove(this.index);
      }
   }

   public override eY e(eY var1) {
      if (this.kN == null) {
         throw new Exception("Unexpected path");
      } else {
         eV var2 = (eV)this.kN.a(typeof(eV), false);
         object var3 = var2.get(this.index);
         if (var3 == null) {
            var2.set(this.index, var1);
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
