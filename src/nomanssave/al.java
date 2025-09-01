package nomanssave;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

class al implements ComboBoxModel {
   String ch;
   // $FF: synthetic field
   final aj cg;

   al(aj var1) {
      this.cg = var1;
      this.ch = null;
   }

   public int getSize() {
      return aj.Q().size();
   }

   public String s(int var1) {
      return (String)aj.Q().get(var1);
   }

   public void addListDataListener(ListDataListener var1) {
   }

   public void removeListDataListener(ListDataListener var1) {
   }

   public void setSelectedItem(Object var1) {
      this.ch = (String)var1;
   }

   public Object getSelectedItem() {
      return this.ch;
   }

   // $FF: synthetic method
   public Object getElementAt(int var1) {
      return this.s(var1);
   }
}
