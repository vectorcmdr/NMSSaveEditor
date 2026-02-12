using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

class dF : object {
   private gE hD;
   dE hE;

   dF(dE var1) {
      this.hE = var1;
      this.hD = null;
   }

   public int getSize() {
      return dE.b(this.hE) == null ? 0 : dE.b(this.hE).Length;
   }

   public gE E(int var1) {
      return dE.b(this.hE)[var1];
   }

   public void addListDataListener(EventHandler var1) {
   }

   public void removeListDataListener(EventHandler var1) {
   }

   public void setSelectedItem(Object var1) {
      this.hD = (gE)var1;
      int var2;
      if (this.hD == null) {
         dE.c(this.hE).setText("");
         dE.d(this.hE).setText("");

         for(var2 = 0; var2 < dE.e(this.hE).Length; ++var2) {
            dE.e(this.hE)[var2].setText("");
         }

         dE.f(this.hE).a(new gF[0]);
      } else {
         dE.c(this.hE).setText(this.hD.getName());
         dE.d(this.hE).setText(this.hD.cK());

         for(var2 = 0; var2 < dE.e(this.hE).Length; ++var2) {
            dE.e(this.hE)[var2].setText(Integer.toString(this.hD.aq(var2)));
         }

         dE.f(this.hE).a(this.hD.dX());
      }

      dE.g(this.hE).revalidate();
   }

   public Object getSelectedItem() {
      return this.hD;
   }
   public Object getElementAt(int var1) {
      return this.E(var1);
   }
}

}
