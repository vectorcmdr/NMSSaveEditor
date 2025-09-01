package nomanssave;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

class dA implements ListModel {
   // $FF: synthetic field
   final dz hu;

   dA(dz var1) {
      this.hu = var1;
   }

   public int getSize() {
      return dz.a(this.hu).length;
   }

   public ft m(int var1) {
      return dz.a(this.hu)[var1];
   }

   public void addListDataListener(ListDataListener var1) {
   }

   public void removeListDataListener(ListDataListener var1) {
   }

   // $FF: synthetic method
   public Object getElementAt(int var1) {
      return this.m(var1);
   }
}
