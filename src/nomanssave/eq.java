package nomanssave;

import java.util.Collections;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

class eq implements ComboBoxModel {
   private gO iz;
   // $FF: synthetic field
   final ep iA;

   eq(ep var1) {
      this.iA = var1;
      this.iz = null;
   }

   public int getSize() {
      return ep.a(this.iA) == null ? 0 : ep.a(this.iA).length;
   }

   public gO J(int var1) {
      return ep.a(this.iA)[var1];
   }

   public void addListDataListener(ListDataListener var1) {
   }

   public void removeListDataListener(ListDataListener var1) {
   }

   public void setSelectedItem(Object var1) {
      this.iz = (gO)var1;
      if (this.iz == null) {
         ep.b(this.iA).a(Collections.emptyList());
      } else {
         ep.b(this.iA).a(this.iz.cC());
      }
   }

   public Object getSelectedItem() {
      return this.iz;
   }

   // $FF: synthetic method
   public Object getElementAt(int var1) {
      return this.J(var1);
   }
}
