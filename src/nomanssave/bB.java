package nomanssave;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

class bB implements ComboBoxModel {
   er eu;
   // $FF: synthetic field
   final bl er;

   private bB(bl var1) {
      this.er = var1;
   }

   public int getSize() {
      return bl.a(this.er) == null ? 0 : bl.a(this.er).length;
   }

   public er v(int var1) {
      return bl.a(this.er) == null ? null : bl.a(this.er)[var1];
   }

   public void addListDataListener(ListDataListener var1) {
   }

   public void removeListDataListener(ListDataListener var1) {
   }

   public void setSelectedItem(Object var1) {
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
               bl.d(this.er)[var3].setText(Integer.toString(var4));
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
               bl.d(this.er)[var3].setText(Integer.toString(var4));
            }

            bl.e(this.er).updateUI();
         }
      }

   }

   public Object getSelectedItem() {
      return this.eu;
   }

   // $FF: synthetic method
   public Object getElementAt(int var1) {
      return this.v(var1);
   }

   // $FF: synthetic method
   bB(bl var1, bB var2) {
      this(var1);
   }
}
