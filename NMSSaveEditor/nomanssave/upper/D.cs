using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Windows.Forms;
using System.Globalization;

namespace NMSSaveEditor
{

public class D : ComboBoxModel {
   // $FF: synthetic field
   Application aZ;

   D(Application var1) {
      this.aZ = var1;
   }

   public int getSize() {
      return Application.d(this.aZ).length;
   }

   public ft m(int var1) {
      return Application.d(this.aZ)[var1];
   }

   public void addListDataListener(ListDataListener var1) {
   }

   public void removeListDataListener(ListDataListener var1) {
   }

   public void setSelectedItem(Object var1) {
      int var2;
      if (Application.i(this.aZ)) {
         Application.n(this.aZ).hidePopup();
         var2 = MessageBox.Show("Save data before switching slots?".ToString(), "Save".ToString(), MessageBoxButtons.YesNo);
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
      (Application.n(this.aZ)) {
         int var4 = 0;

         while(var4 < Application.d(this.aZ).length) {
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

   public Object getSelectedItem() {
      return Application.c(this.aZ) < 0 ? null : Application.d(this.aZ)[Application.c(this.aZ)];
   }

   // $FF: synthetic method
   public Object getElementAt(int var1) {
      return this.m(var1);
   }
}

}
