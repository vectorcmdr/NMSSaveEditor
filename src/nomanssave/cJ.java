package nomanssave;

class cJ {
   final cJ gi;
   final int gj;
   final String name;
   Object value;
   // $FF: synthetic field
   final cy gg;

   cJ(cy var1, cJ var2, int var3, String var4, Object var5) {
      this.gg = var1;
      this.gi = var2;
      this.value = var5;
      this.name = var4;
      this.gj = var3;
   }

   boolean isLeaf() {
      if (this.value == null) {
         return true;
      } else if (this.value instanceof eY) {
         return false;
      } else {
         return !(this.value instanceof eV);
      }
   }

   int getChildCount() {
      if (this.value == null) {
         return 0;
      } else if (this.value instanceof eY) {
         return ((eY)this.value).names().size();
      } else {
         return this.value instanceof eV ? ((eV)this.value).size() : 0;
      }
   }

   Object x(int var1) {
      if (this.value == null) {
         throw new RuntimeException("No children for null");
      } else if (this.value instanceof eY) {
         String var4 = (String)((eY)this.value).names().get(var1);
         Object var3 = ((eY)this.value).getValue(var4);
         return new cJ(this.gg, this, var1, var4, var3);
      } else if (this.value instanceof eV) {
         Object var2 = ((eV)this.value).getValue(var1);
         return new cJ(this.gg, this, var1, "[" + var1 + "]", var2);
      } else {
         throw new RuntimeException("No children for " + this.value.getClass().getName());
      }
   }

   int indexOf(Object var1) {
      return var1 instanceof cJ && ((cJ)var1).gi == this ? ((cJ)var1).gj : -1;
   }

   public String toString() {
      return this.name;
   }

   public String getText() {
      return fh.a(this.value, 1, (var0) -> {
         return var0 < 128;
      });
   }

   public void setText(String var1) {
      if (this.gi == null) {
         this.value = eY.E(var1);
         cy.b(this.gg).d((eY)this.value);
      } else {
         this.value = fh.P(var1);
         if (this.gi.value instanceof eY) {
            ((eY)this.gi.value).b(this.name, this.value);
         } else if (this.gi.value instanceof eV) {
            ((eV)this.gi.value).a(this.gj, this.value);
         }
      }

      cy.a(this.gg, false);
      cy.b(this.gg, true);
   }

   public void remove() {
      if (this.gi == null) {
         throw new RuntimeException("Cannot remove root node");
      } else {
         this.value = null;
         if (this.gi.value instanceof eY) {
            ((eY)this.gi.value).N(this.name);
         } else if (this.gi.value instanceof eV) {
            ((eV)this.gi.value).ac(this.gj);
         }

         cy.a(this.gg, false);
         cy.b(this.gg, true);
      }
   }
}
