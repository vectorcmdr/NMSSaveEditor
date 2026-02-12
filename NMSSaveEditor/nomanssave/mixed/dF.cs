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

public class dF : ComboBoxModel {
   private gE hD;
   // $FF: synthetic field
   dE hE;

   dF(dE var1) {
      this.hE = var1;
      this.hD = null;
   }

   public int getSize() {
      return dE.b(this.hE) == null ? 0 : dE.b(this.hE).length;
   }

   public gE E(int var1) {
      return dE.b(this.hE)[var1];
   }

   public void addListDataListener(ListDataListener var1) {
   }

   public void removeListDataListener(ListDataListener var1) {
   }

   public void setSelectedItem(Object var1) {
      this.hD = (gE)var1;
      int var2;
      if (this.hD == null) {
         dE.c(this.hE).SetText("");
         dE.d(this.hE).SetText("");

         for(var2 = 0; var2 < dE.e(this.hE).length; ++var2) {
            dE.e(this.hE)[var2].SetText("");
         }

         dE.f(this.hE).a(new gF[0]);
      } else {
         dE.c(this.hE).SetText(this.hD.Name);
         dE.d(this.hE).SetText(this.hD.cK());

         for(var2 = 0; var2 < dE.e(this.hE).length; ++var2) {
            dE.e(this.hE)[var2].SetText(Convert.ToString(this.hD.aq(var2)));
         }

         dE.f(this.hE).a(this.hD.dX());
      }

      dE.g(this.hE).PerformLayout();
   }

   public Object getSelectedItem() {
      return this.hD;
   }

   // $FF: synthetic method
   public Object getElementAt(int var1) {
      return this.E(var1);
   }
}

}
