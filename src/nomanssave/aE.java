package nomanssave;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

class aE implements ComboBoxModel {
   aI cA;
   // $FF: synthetic field
   final aD cB;

   aE(aD var1) {
      this.cB = var1;
      this.cA = null;
   }

   public int getSize() {
      return aI.values().length;
   }

   public aI t(int var1) {
      return aI.values()[var1];
   }

   public void addListDataListener(ListDataListener var1) {
   }

   public void removeListDataListener(ListDataListener var1) {
   }

   public void setSelectedItem(Object var1) {
      this.cA = (aI)var1;
   }

   public Object getSelectedItem() {
      return this.cA;
   }

   // $FF: synthetic method
   public Object getElementAt(int var1) {
      return this.t(var1);
   }
}
