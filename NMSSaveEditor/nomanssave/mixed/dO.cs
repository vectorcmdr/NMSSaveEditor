using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

class dO : object {
   private gH hZ;
   dN ia;
   private Application bv;

   dO(dN var1, Application var2) {
      this.ia = var1;
      this.bv = var2;
      this.hZ = null;
   }

   public int getSize() {
      return dN.a(this.ia) == null ? 0 : dN.a(this.ia).Length;
   }

   public gH G(int var1) {
      return dN.a(this.ia)[var1];
   }

   public void addListDataListener(EventHandler var1) {
   }

   public void removeListDataListener(EventHandler var1) {
   }

   public void setSelectedItem(Object var1) {
      this.hZ = (gH)var1;
      if (this.hZ == null) {
         dN.b(this.ia).setText("");
         dN.c(this.ia).setSelectedIndex(-1);
         dN.d(this.ia).setSelectedIndex(-1);
         dN.e(this.ia).setText("");
         dN.f(this.ia).setSelected(false);
         dN.f(this.ia).setEnabled(false);
         dN.g(this.ia).setEnabled(false);
         dN.h(this.ia).setText("");
         dN.i(this.ia).setText("");
         dN.j(this.ia).setText("");
         dN.k(this.ia).setText("");
         dN.l(this.ia).a(new List<object>());
      } else {
         dN.b(this.ia).setText(this.hZ.getName());
         dN.c(this.ia).m(this.hZ.cT());
         dN.d(this.ia).m(this.hZ.cW());
         dN.e(this.ia).setText(this.hZ.cK());
         eV var2 = this.bv.d("PlayerStateData.ShipUsesLegacyColours");
         dN.f(this.ia).setSelected(var2 != null && var2.ab(this.hZ.getIndex()));
         dN.f(this.ia).setEnabled(true);
         dN.g(this.ia).setEnabled(true);
         dN.h(this.ia).setText(Double.toString(this.hZ.dF()));
         dN.i(this.ia).setText(Double.toString(this.hZ.eb()));
         dN.j(this.ia).setText(Double.toString(this.hZ.cX()));
         dN.k(this.ia).setText(Double.toString(this.hZ.ec()));
         dN.l(this.ia).a(this.hZ.cC());
         dN.m(this.ia).setEnabled(false);
         dN.n(this.ia).setEnabled(false);
         if (dN.o(this.ia) != null) {
            for(int var3 = 0; var3 < dN.a(this.ia).Length; ++var3) {
               if (this.hZ == dN.a(this.ia)[var3] && var3 == dN.o(this.ia).dV()) {
                  dN.m(this.ia).setEnabled(true);
                  dN.n(this.ia).setEnabled(true);
               }
            }
         }

      }
   }

   public Object getSelectedItem() {
      return this.hZ;
   }
   public Object getElementAt(int var1) {
      return this.G(var1);
   }
}

}
