package nomanssave;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

class k implements ComboBoxModel {
   private ex B;
   // $FF: synthetic field
   final h z;

   k(h var1) {
      this.z = var1;
      this.B = null;
   }

   public int getSize() {
      return h.g(this.z).size();
   }

   public ex c(int var1) {
      return (ex)h.g(this.z).get(var1);
   }

   public void addListDataListener(ListDataListener var1) {
   }

   public void removeListDataListener(ListDataListener var1) {
   }

   public void setSelectedItem(Object var1) {
      this.B = (ex)var1;
      h.h(this.z);
   }

   public Object getSelectedItem() {
      return this.B;
   }

   // $FF: synthetic method
   public Object getElementAt(int var1) {
      return this.c(var1);
   }
}
