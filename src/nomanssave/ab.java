package nomanssave;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

class ab implements ComboBoxModel {
   // $FF: synthetic field
   final X bV;

   ab(X var1) {
      this.bV = var1;
   }

   public int getSize() {
      return gl.values().length;
   }

   public gl r(int var1) {
      return gl.values()[var1];
   }

   public void addListDataListener(ListDataListener var1) {
   }

   public void removeListDataListener(ListDataListener var1) {
   }

   public void setSelectedItem(Object var1) {
   }

   public Object getSelectedItem() {
      return gl.oF;
   }

   // $FF: synthetic method
   public Object getElementAt(int var1) {
      return this.r(var1);
   }
}
