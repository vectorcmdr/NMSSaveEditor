package nomanssave;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

class J implements ComboBoxModel {
   gh bs;
   // $FF: synthetic field
   final I bt;

   J(I var1) {
      this.bt = var1;
      this.bs = null;
   }

   public int getSize() {
      return I.a(this.bt) == null ? 0 : I.a(this.bt).cD().size();
   }

   public gh o(int var1) {
      return I.a(this.bt) == null ? null : (gh)I.a(this.bt).cD().get(var1);
   }

   public void addListDataListener(ListDataListener var1) {
   }

   public void removeListDataListener(ListDataListener var1) {
   }

   public void setSelectedItem(Object var1) {
      this.bs = (gh)var1;
      if (this.bs == null) {
         I.b(this.bt).setText("");
         I.c(this.bt).setText("");
         I.c(this.bt).setEnabled(false);
      } else {
         gy var2 = this.bs.cJ();
         I.b(this.bt).setText(var2 == null ? "" : var2.toString());
         I.c(this.bt).setText(this.bs.cK());
         I.c(this.bt).setEnabled(true);
      }

   }

   public Object getSelectedItem() {
      return this.bs;
   }

   // $FF: synthetic method
   public Object getElementAt(int var1) {
      return this.o(var1);
   }
}
