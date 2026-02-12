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
      // PORT_TODO: return dN.a(this.ia) == null ? 0 : dN.a(this.ia).Length;
      return 0;
   }

   public gH G(int var1) {
      // PORT_TODO: return dN.a(this.ia)[var1];
      return default;
   }

   public void addListDataListener(EventHandler var1) {
   }

   public void removeListDataListener(EventHandler var1) {
   }

   public void setSelectedItem(object var1) {
      this.hZ = (gH)var1;
      if (this.hZ == null) {
         // PORT_TODO: dN.b(this.ia).Text = ("");
         // PORT_TODO: dN.c(this.ia).SelectedIndex = (-1);
         // PORT_TODO: dN.d(this.ia).SelectedIndex = (-1);
         // PORT_TODO: dN.e(this.ia).Text = ("");
         // PORT_TODO: dN.f(this.ia).Checked = (false);
         // PORT_TODO: dN.f(this.ia).Enabled = (false);
         // PORT_TODO: dN.g(this.ia).Enabled = (false);
         // PORT_TODO: dN.h(this.ia).Text = ("");
         // PORT_TODO: dN.i(this.ia).Text = ("");
         // PORT_TODO: dN.j(this.ia).Text = ("");
         // PORT_TODO: dN.k(this.ia).Text = ("");
         // PORT_TODO: dN.l(this.ia).a(new List<object>());
      } else {
         // PORT_TODO: dN.b(this.ia).Text = (this.hZ.Name);
         // PORT_TODO: dN.c(this.ia).m(this.hZ.cT());
         // PORT_TODO: dN.d(this.ia).m(this.hZ.cW());
         // PORT_TODO: dN.e(this.ia).Text = (this.hZ.cK());
         eV var2 = this.bv.d("PlayerStateData.ShipUsesLegacyColours");
         // PORT_TODO: dN.f(this.ia).Checked = (var2 != null && var2.ab(this.hZ.getIndex()));
         // PORT_TODO: dN.f(this.ia).Enabled = (true);
         // PORT_TODO: dN.g(this.ia).Enabled = (true);
         // PORT_TODO: dN.h(this.ia).Text = ((this.hZ.dF().ToString()));
         // PORT_TODO: dN.i(this.ia).Text = ((this.hZ.eb().ToString()));
         // PORT_TODO: dN.j(this.ia).Text = ((this.hZ.cX().ToString()));
         // PORT_TODO: dN.k(this.ia).Text = ((this.hZ.ec().ToString()));
         // PORT_TODO: dN.l(this.ia).a(this.hZ.cC());
         // PORT_TODO: dN.m(this.ia).Enabled = (false);
         // PORT_TODO: dN.n(this.ia).Enabled = (false);
         // PORT_TODO: if (dN.o(this.ia) != null) {
            // PORT_TODO: for(int var3 = 0; var3 < dN.a(this.ia).Length; ++var3) {
               // PORT_TODO: if (this.hZ == dN.a(this.ia)[var3] && var3 == dN.o(this.ia).dV()) {
                  // PORT_TODO: dN.m(this.ia).Enabled = (true);
                  // PORT_TODO: dN.n(this.ia).Enabled = (true);
               // PORT_TODO: }
            // PORT_TODO: }
         // PORT_TODO: }

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
