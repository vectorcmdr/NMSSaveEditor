package nomanssave;

import javax.swing.ComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListDataListener;

class E implements ComboBoxModel {
   // $FF: synthetic field
   final Application aZ;

   E(Application var1) {
      this.aZ = var1;
   }

   public int getSize() {
      return Application.f(this.aZ).length;
   }

   public fs n(int var1) {
      return Application.f(this.aZ)[var1];
   }

   public void addListDataListener(ListDataListener var1) {
   }

   public void removeListDataListener(ListDataListener var1) {
   }

   public void setSelectedItem(Object var1) {
      int var2;
      if (Application.i(this.aZ)) {
         Application.o(this.aZ).hidePopup();
         var2 = JOptionPane.showConfirmDialog(Application.h(this.aZ), "Are you sure you want to load a different file and lose current changes?", "Save", 2);
         if (var2 != 0) {
            return;
         }

         Application.f(this.aZ, false);
      }

      var2 = -1;
      synchronized(Application.n(this.aZ)) {
         int var4 = 0;

         for(int var5 = 0; var5 < Application.f(this.aZ).length; ++var5) {
            if (Application.f(this.aZ)[var5] == var1) {
               var2 = var4;
               Application.f(this.aZ)[var4++] = Application.f(this.aZ)[var5];
            } else if (!(Application.f(this.aZ)[var5] instanceof F)) {
               Application.f(this.aZ)[var4++] = Application.f(this.aZ)[var5];
            }
         }

         if (var4 < Application.f(this.aZ).length) {
            fs[] var7 = new fs[var4];
            System.arraycopy(Application.f(this.aZ), 0, var7, 0, var4);
            Application.a(this.aZ, var7);
         }
      }

      Application.b(this.aZ, var2);
   }

   public Object getSelectedItem() {
      return Application.e(this.aZ) < 0 ? null : Application.f(this.aZ)[Application.e(this.aZ)];
   }

   // $FF: synthetic method
   public Object getElementAt(int var1) {
      return this.n(var1);
   }
}
