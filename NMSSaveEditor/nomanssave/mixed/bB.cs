using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

class bB : object {
   er eu;
   bl er;

   private bB(bl var1) {
      this.er = var1;
   }

   public int getSize() {
      return bl.a(this.er) == null ? 0 : bl.a(this.er).Length;
   }

   public er v(int var1) {
      return bl.a(this.er) == null ? null : bl.a(this.er)[var1];
   }

   public void addListDataListener(EventHandler var1) {
   }

   public void removeListDataListener(EventHandler var1) {
   }

   public void setSelectedItem(object var1) {
      this.eu = (er)var1;
      if (bl.b(this.er) >= 0) {
         er var2 = bl.c(this.er)[bl.b(this.er)].ar(0);
         if (this.eu != var2) {
            int var3;
            int var4;
            if (var2 != null) {
               var3 = var2.aU().ordinal();
               var4 = bl.c(this.er)[bl.b(this.er)].aq(var3) - var2.aV();
               if (var4 < 0) {
                  var4 = 0;
               }

               bl.c(this.er)[bl.b(this.er)].e(var3, var4);
               bl.d(this.er)[var3].Text = (Integer.toString(var4));
            }

            if (this.eu == null) {
               bl.c(this.er)[bl.b(this.er)].a(0, (er)null);
            } else {
               bl.c(this.er)[bl.b(this.er)].a(0, this.eu);
               var3 = this.eu.aU().ordinal();
               var4 = bl.c(this.er)[bl.b(this.er)].aq(var3) + this.eu.aV();
               if (var4 < 0) {
                  var4 = 0;
               }

               bl.c(this.er)[bl.b(this.er)].e(var3, var4);
               bl.d(this.er)[var3].Text = (Integer.toString(var4));
            }

            bl.e(this.er).Refresh();
         }
      }

   }

   public object getSelectedItem() {
      return this.eu;
   }
   public object getElementAt(int var1) {
      return this.v(var1);
   }
   bB(bl var1, bB var2) {
      this(var1);
   }
}

}
