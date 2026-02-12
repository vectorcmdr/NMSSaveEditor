using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
class cJ {
   cJ gi;
   int gj;
   string name;
   object value;
   cy gg;

   cJ(cy var1, cJ var2, int var3, string var4, object var5) {
      this.gg = var1;
      this.gi = var2;
      this.value = var5;
      this.name = var4;
      this.gj = var3;
   }

   bool isLeaf() {
      if (this.value == null) {
         return true;
      } else if (this.value is eY) {
         return false;
      } else {
         return !(this.value is eV);
      }
   }

   int getChildCount() {
      if (this.value == null) {
         return 0;
      } else if (this.value is eY) {
         return ((eY)this.value).names().Count;
      } else {
         return this.value is eV ? ((eV)this.value).Count : 0;
      }
   }

   object x(int var1) {
      if (this.value == null) {
         throw new Exception("No children for null");
      } else if (this.value is eY) {
         string var4 = (string)((eY)this.value).names()[(var1);
         object var3 = ((eY)this.value).getValue(var4);
         return new cJ(this.gg, this, var1, var4, var3);
      } else if (this.value is eV) {
         object var2 = ((eV)this.value).getValue(var1);
         return new cJ(this.gg, this, var1, "[" + var1 + "]", var2);
      } else {
         throw new Exception("No children for " + this.value.GetType().Name);
      }
   }

   int indexOf(object var1) {
      return var1 is cJ && ((cJ)var1).gi == this ? ((cJ)var1).gj : -1;
   }

   public string toString() {
      return this.name;
   }

   public string getText() {
      return fh.a(this.value, 1, (var0) => {
         return var0 < 128;
      });
   }

   public void setText(string var1) {
      if (this.gi == null) {
         this.value = eY.E(var1);
         cy.b(this.gg).d((eY)this.value);
      } else {
         this.value = fh.P(var1);
         if (this.gi.value is eY) {
            ((eY)this.gi.value).b(this.name, this.value);
         } else if (this.gi.value is eV) {
            ((eV)this.gi.value).a(this.gj, this.value);
         }
      }

      cy.a(this.gg, false);
      cy.b(this.gg, true);
   }

   public void remove() {
      if (this.gi == null) {
         throw new Exception("Cannot remove root node");
      } else {
         this.value = null;
         if (this.gi.value is eY) {
            ((eY)this.gi.value).N(this.name);
         } else if (this.gi.value is eV) {
            ((eV)this.gi.value).ac(this.gj);
         }

         cy.a(this.gg, false);
         cy.b(this.gg, true);
      }
   }
}

}
