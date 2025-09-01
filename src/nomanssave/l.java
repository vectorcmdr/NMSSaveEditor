package nomanssave;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

class l implements ComboBoxModel {
   private ey C;
   // $FF: synthetic field
   final h z;

   l(h var1) {
      this.z = var1;
      this.C = null;
   }

   public int getSize() {
      return h.i(this.z).size();
   }

   public ey d(int var1) {
      return (ey)h.i(this.z).get(var1);
   }

   public void addListDataListener(ListDataListener var1) {
   }

   public void removeListDataListener(ListDataListener var1) {
   }

   public void setSelectedItem(Object var1) {
      this.C = (ey)var1;
   }

   public Object getSelectedItem() {
      return this.C;
   }

   // $FF: synthetic method
   public Object getElementAt(int var1) {
      return this.d(var1);
   }
}
