using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{


public class cJ {
   public string Text => getText();
   public cJ gi;
   public int gj;
   public string name;
   public object value;
   public cy gg;

   public cJ(cy var1, cJ var2, int var3, string var4, object var5) {
      this.gg = var1;
      this.gi = var2;
      this.value = var5;
      this.name = var4;
      this.gj = var3;
   }

   public bool isLeaf() {
      if (this.value == null) {
         return true;
      } else if (this.value is eY) {
         return false;
      } else {
         return !(this.value is eV);
      }
   }

   public int getChildCount() {
      if (this.value == null) {
         return 0;
      } else if (this.value is eY) {
         // PORT_TODO: return ((eY)this.value).names().Count;
      } else {
         return this.value is eV ? ((eV)this.value).Count : 0;
         return default;
      }
      return 0; // PORT_TODO: auto-added
      return 0; // PORT_TODO: auto-added
   }

   public object x(int var1) {
      if (this.value == null) {
         throw new Exception("No children for null");
      } else if (this.value is eY) {
      object var3 = null; // PORT_TODO: stub declaration
      string var4 = null; // PORT_TODO: stub declaration
         // PORT_TODO: string var4 = (string)((eY)this.value).names()[var1];
         // PORT_TODO: object var3 = ((eY)this.value).getValue(var4);
         return new cJ(this.gg, this, var1, var4, var3);
      } else if (this.value is eV) {
         object var2 = ((eV)this.value).getValue(var1);
         return new cJ(this.gg, this, var1, "[" + var1 + "]", var2);
      } else {
         throw new Exception("No children for " + this.value.GetType().Name);
      }
   }

   public int indexOf(object var1) {
      return var1 is cJ && ((cJ)var1).gi == this ? ((cJ)var1).gj : -1;
   }

   public string toString() {
      return this.name;
   }

   public string getText() {
      // PORT_TODO: return fh.a(this.value, 1, (var0) => {
         // PORT_TODO: return var0 < 128;
      // PORT_TODO: });
       return null; // PORT_TODO: stub return
    }

   public void setText(string var1) {
      if (this.gi == null) {
         this.value = eY.E(var1);
         // PORT_TODO: cy.b(this.gg).d((eY)this.value);
      } else {
         this.value = fh.P(var1);
         if (this.gi.value is eY) {
            // PORT_TODO: ((eY)this.gi.value).b(this.name, this.value);
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
            // PORT_TODO: ((eY)this.gi.value).N(this.name);
         } else if (this.gi.value is eV) {
            ((eV)this.gi.value).ac(this.gj);
         }

         cy.a(this.gg, false);
         cy.b(this.gg, true);
      }
   }
}



}
