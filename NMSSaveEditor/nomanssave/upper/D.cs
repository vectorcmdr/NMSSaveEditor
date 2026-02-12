using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

public class D : object {
   public Application aZ;

   public D(Application var1) {
      this.aZ = var1;
   }

   public int getSize() {
      return Application.d(this.aZ).Length;
   }

   public ft m(int var1) {
      return Application.d(this.aZ)[var1];
   }

   public void addListDataListener(EventHandler var1) {
   }

   public void removeListDataListener(EventHandler var1) {
   }

   public void setSelectedItem(object var1) {
      int var2;
      if (Application.i(this.aZ)) {
         Application.n(this.aZ).hidePopup();
         var2 = MessageBox.Show(Application.h(this.aZ), "Save data before switching slots?", "Save", 1);
         if (var2 == 0) {
            Application.k(this.aZ);
         } else {
            if (var2 == 2) {
               return;
            }

            Application.f(this.aZ, false);
         }
      }

      var2 = -1;
      lock(Application.n(this.aZ)) {
         int var4 = 0;

         while(var4 < Application.d(this.aZ).Length) {
            if (Application.d(this.aZ)[var4] != var1) {
               ++var4;
            } else {
               var2 = var4;
               break;
            }
         }
      }

      Application.a(this.aZ, var2);
   }

   public object getSelectedItem() {
      return Application.c(this.aZ) < 0 ? null : Application.d(this.aZ)[Application.c(this.aZ)];
   }
   public object getElementAt(int var1) {
      return this.m(var1);
   }
}

}
