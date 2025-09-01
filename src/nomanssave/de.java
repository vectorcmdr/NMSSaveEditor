package nomanssave;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

class de implements ListModel {
   // $FF: synthetic field
   final dd gW;

   de(dd var1) {
      this.gW = var1;
   }

   public int getSize() {
      return dd.a(this.gW).size();
   }

   public gt w(int var1) {
      return (gt)dd.a(this.gW).get(var1);
   }

   public void addListDataListener(ListDataListener var1) {
   }

   public void removeListDataListener(ListDataListener var1) {
   }

   // $FF: synthetic method
   public Object getElementAt(int var1) {
      return this.w(var1);
   }
}
