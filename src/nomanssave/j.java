package nomanssave;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

class j implements ComboBoxModel {
   private eB A;
   // $FF: synthetic field
   final h z;

   j(h var1) {
      this.z = var1;
      this.A = null;
   }

   public int getSize() {
      return h.e(this.z).size();
   }

   public eB b(int var1) {
      return (eB)h.e(this.z).get(var1);
   }

   public void addListDataListener(ListDataListener var1) {
   }

   public void removeListDataListener(ListDataListener var1) {
   }

   public void setSelectedItem(Object var1) {
      this.A = (eB)var1;
      h.f(this.z);
   }

   public Object getSelectedItem() {
      return this.A;
   }

   // $FF: synthetic method
   public Object getElementAt(int var1) {
      return this.b(var1);
   }
}
