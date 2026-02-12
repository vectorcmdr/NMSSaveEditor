using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{



public class dO : object {
   public gH hZ;
   public dN ia;
   public Application bv;

   public dO(dN var1, Application var2) {
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

   public void setSelectedItem(object var1) {
      this.hZ = (gH)var1;
      if (this.hZ == null) {
         dN.b(this.ia).Text = ("");
         dN.c(this.ia).SelectedIndex = (-1);
         dN.d(this.ia).SelectedIndex = (-1);
         dN.e(this.ia).Text = ("");
         dN.f(this.ia).Checked = (false);
         dN.f(this.ia).Enabled = (false);
         dN.g(this.ia).Enabled = (false);
         dN.h(this.ia).Text = ("");
         dN.i(this.ia).Text = ("");
         dN.j(this.ia).Text = ("");
         dN.k(this.ia).Text = ("");
         dN.l(this.ia).a(new List<object>());
      } else {
         dN.b(this.ia).Text = (this.hZ.Name);
         dN.c(this.ia).m(this.hZ.cT());
         dN.d(this.ia).m(this.hZ.cW());
         dN.e(this.ia).Text = (this.hZ.cK());
         eV var2 = this.bv.d("PlayerStateData.ShipUsesLegacyColours");
         dN.f(this.ia).Checked = (var2 != null && var2.ab(this.hZ.getIndex()));
         dN.f(this.ia).Enabled = (true);
         dN.g(this.ia).Enabled = (true);
         dN.h(this.ia).Text = ((this.hZ.dF().ToString()));
         dN.i(this.ia).Text = ((this.hZ.eb().ToString()));
         dN.j(this.ia).Text = ((this.hZ.cX().ToString()));
         dN.k(this.ia).Text = ((this.hZ.ec().ToString()));
         dN.l(this.ia).a(this.hZ.cC());
         dN.m(this.ia).Enabled = (false);
         dN.n(this.ia).Enabled = (false);
         if (dN.o(this.ia) != null) {
            for(int var3 = 0; var3 < dN.a(this.ia).Length; ++var3) {
               if (this.hZ == dN.a(this.ia)[var3] && var3 == dN.o(this.ia).dV()) {
                  dN.m(this.ia).Enabled = (true);
                  dN.n(this.ia).Enabled = (true);
               }
            }
         }

      }
   }

   public object getSelectedItem() {
      return this.hZ;
   }
   public object getElementAt(int var1) {
      return this.G(var1);
   }
}



}
