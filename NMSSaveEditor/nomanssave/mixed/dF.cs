using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{



public class dF : object {
   public gE hD;
   public dE hE;

   public dF(dE var1) {
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

   public void setSelectedItem(object var1) {
      this.hD = (gE)var1;
      int var2;
      if (this.hD == null) {
         dE.c(this.hE).Text = ("");
         dE.d(this.hE).Text = ("");

         for(var2 = 0; var2 < dE.e(this.hE).Length; ++var2) {
            dE.e(this.hE)[var2].Text = ("");
         }

         dE.f(this.hE).a(new gF[0]);
      } else {
         dE.c(this.hE).Text = (this.hD.Name);
         dE.d(this.hE).Text = (this.hD.cK());

         for(var2 = 0; var2 < dE.e(this.hE).Length; ++var2) {
            dE.e(this.hE)[var2].Text = ((this.hD.aq(var2).ToString()));
         }

         dE.f(this.hE).a(this.hD.dX());
      }

      dE.g(this.hE).PerformLayout();
   }

   public object getSelectedItem() {
      return this.hD;
   }
   public object getElementAt(int var1) {
      return this.E(var1);
   }
}



}
