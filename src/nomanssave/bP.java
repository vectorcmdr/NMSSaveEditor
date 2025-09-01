package nomanssave;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

class bP implements ComboBoxModel {
   // $FF: synthetic field
   final bO eX;

   bP(bO var1) {
      this.eX = var1;
   }

   public int getSize() {
      return bO.d(this.eX).size();
   }

   public gt w(int var1) {
      return (gt)bO.d(this.eX).get(var1);
   }

   public void addListDataListener(ListDataListener var1) {
   }

   public void removeListDataListener(ListDataListener var1) {
   }

   public void setSelectedItem(Object var1) {
      bO.a(this.eX, (gt)var1);
      bO.e(this.eX).setVisible(bO.a(this.eX) == null ? false : en.aS() || bO.a(this.eX).dk());
      bO.c(this.eX);
   }

   public Object getSelectedItem() {
      return bO.a(this.eX);
   }

   // $FF: synthetic method
   public Object getElementAt(int var1) {
      return this.w(var1);
   }
}
