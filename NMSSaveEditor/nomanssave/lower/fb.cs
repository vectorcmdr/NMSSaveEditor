using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
class fb : fc {
   string name;
   eY kL;

   fb(eY var1, string var2, fc var3) {
      base(var1, var3);
      this.kL = var1;
      this.name = var2;
   }

   Object a(Class var1, bool var2) {
      eY var3;
      if (this.kN == null) {
         var3 = this.kL;
      } else {
         var3 = (eY)this.kN.a(eY.class, var2);
      }

      int var4 = var3.IndexOf(this.name);
      if (var4 < 0) {
         if (!var2) {
            throw new fd((fd)null);
         } else {
            Object var5;
            try {
               var5 = var1.newInstance();
            } catch (IllegalAccessException | InstantiationException var7) {
               throw new Exception("Unexpected error", var7);
            }

            var3.put(this.name, var5);
            return var5;
         }
      } else if (var1.isInstance(var3.values[var4])) {
         return var1.cast(var3.values[var4]);
      } else {
         throw new Exception("Unexpected path");
      }
   }

   Object getValue() {
      eY var1;
      if (this.kN == null) {
         var1 = this.kL;
      } else {
         var1 = (eY)this.kN.a(eY.class, false);
      }

      return var1.get(this.name);
   }

   Object a(Object var1, bool var2) {
      eY var3;
      if (this.kN == null) {
         var3 = this.kL;
      } else {
         var3 = (eY)this.kN.a(eY.class, var2);
      }

      return var3.put(this.name, var1);
   }

   Object bG() {
      eY var1;
      if (this.kN == null) {
         var1 = this.kL;
      } else {
         var1 = (eY)this.kN.a(eY.class, false);
      }

      return var1.F(this.name);
   }

   eY e(eY var1) {
      eY var2;
      if (this.kN == null) {
         var2 = this.kL;
      } else {
         var2 = (eY)this.kN.a(eY.class, false);
      }

      Object var3 = var2.get(this.name);
      if (var3 == null) {
         var2.put(this.name, var1);
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
