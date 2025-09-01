package nomanssave;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

class bw implements ComboBoxModel {
   gr et;
   // $FF: synthetic field
   final bl er;

   bw(bl var1) {
      this.er = var1;
   }

   public int getSize() {
      return gr.values().length;
   }

   public gr u(int var1) {
      return gr.values()[var1];
   }

   public void addListDataListener(ListDataListener var1) {
   }

   public void removeListDataListener(ListDataListener var1) {
   }

   public void setSelectedItem(Object var1) {
      this.et = (gr)var1;
      bl.a(this.er, bl.b(this.er) < 0 ? null : nomanssave.er.a(this.et));
      bl.b(this.er, bl.b(this.er) < 0 ? null : nomanssave.er.b(this.et));
      if (bl.b(this.er) >= 0 && this.et != null && !this.et.equals(bl.c(this.er)[bl.b(this.er)].da())) {
         bl.c(this.er)[bl.b(this.er)].c(this.et);
         if (bl.a(this.er) != null && bl.a(this.er).length > 0) {
            bl.c(this.er)[bl.b(this.er)].a(0, bl.a(this.er)[0]);
            bl.p(this.er).setSelectedItem(bl.a(this.er)[0]);
         } else {
            bl.p(this.er).setSelectedItem((Object)null);
         }

         bl.q(this.er).setSelectedItem((Object)null);
         bl.r(this.er).setSelectedItem((Object)null);
         bl.s(this.er).setSelectedItem((Object)null);
         bl.t(this.er).setSelectedItem((Object)null);
      }

      bl.e(this.er).updateUI();
      bl.p(this.er).updateUI();
      bl.q(this.er).updateUI();
      bl.r(this.er).updateUI();
      bl.s(this.er).updateUI();
      bl.t(this.er).updateUI();
   }

   public Object getSelectedItem() {
      return this.et;
   }

   // $FF: synthetic method
   public Object getElementAt(int var1) {
      return this.u(var1);
   }
}
