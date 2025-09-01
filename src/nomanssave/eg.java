package nomanssave;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

class eg implements ComboBoxModel {
   // $FF: synthetic field
   final ec ik;
   // $FF: synthetic field
   private final int il;

   eg(ec var1, int var2) {
      this.ik = var1;
      this.il = var2;
   }

   public int getSize() {
      return gL.values().length;
   }

   public gL I(int var1) {
      return gL.values()[var1];
   }

   public void addListDataListener(ListDataListener var1) {
   }

   public void removeListDataListener(ListDataListener var1) {
   }

   public void setSelectedItem(Object var1) {
      gL var2 = (gL)var1;
      if (var2 != null && !var2.equals(eb.a(ec.h(this.ik))[this.il].ef())) {
         eb.a(ec.h(this.ik))[this.il].a(var2);
      }

   }

   public Object getSelectedItem() {
      return eb.a(ec.h(this.ik))[this.il].ef();
   }

   // $FF: synthetic method
   public Object getElementAt(int var1) {
      return this.I(var1);
   }
}
