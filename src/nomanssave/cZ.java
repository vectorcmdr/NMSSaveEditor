package nomanssave;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

class cZ implements ComboBoxModel {
   private gg gQ;
   // $FF: synthetic field
   final cY gR;

   cZ(cY var1) {
      this.gR = var1;
      this.gQ = null;
   }

   public int getSize() {
      return cY.a(this.gR).size();
   }

   public gg C(int var1) {
      return (gg)cY.a(this.gR).get(var1);
   }

   public void addListDataListener(ListDataListener var1) {
   }

   public void removeListDataListener(ListDataListener var1) {
   }

   public void setSelectedItem(Object var1) {
      this.gQ = (gg)var1;
   }

   public Object getSelectedItem() {
      return this.gQ;
   }

   // $FF: synthetic method
   public Object getElementAt(int var1) {
      return this.C(var1);
   }
}
