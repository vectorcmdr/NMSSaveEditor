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

public class dO : ComboBoxModel {
   public gH hZ;
   // $FF: synthetic field
   public dN ia;
   // $FF: synthetic field
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

   public void addListDataListener(ListDataListener var1) {
   }

   public void removeListDataListener(ListDataListener var1) {
   }

   public void setSelectedItem(Object var1) {
      this.hZ = (gH)var1;
      if (this.hZ == null) {
         dN.b(this.ia).SetText("");
         dN.c(this.ia).SetSelectedIndex(-1);
         dN.d(this.ia).SetSelectedIndex(-1);
         dN.e(this.ia).SetText("");
         dN.f(this.ia).setSelected(false);
         dN.f(this.ia).SetEnabled(false);
         dN.g(this.ia).SetEnabled(false);
         dN.h(this.ia).SetText("");
         dN.i(this.ia).SetText("");
         dN.j(this.ia).SetText("");
         dN.k(this.ia).SetText("");
         dN.l(this.ia).a(new List<object>());
      } else {
         dN.b(this.ia).SetText(this.hZ.Name);
         dN.c(this.ia).m(this.hZ.cT());
         dN.d(this.ia).m(this.hZ.cW());
         dN.e(this.ia).SetText(this.hZ.cK());
         eV var2 = this.bv.d("PlayerStateData.ShipUsesLegacyColours");
         dN.f(this.ia).setSelected(var2 != null && var2.ab(this.hZ.getIndex()));
         dN.f(this.ia).SetEnabled(true);
         dN.g(this.ia).SetEnabled(true);
         dN.h(this.ia).SetText(Double.toString(this.hZ.dF()));
         dN.i(this.ia).SetText(Double.toString(this.hZ.eb()));
         dN.j(this.ia).SetText(Double.toString(this.hZ.cX()));
         dN.k(this.ia).SetText(Double.toString(this.hZ.ec()));
         dN.l(this.ia).a(this.hZ.cC());
         dN.m(this.ia).SetEnabled(false);
         dN.n(this.ia).SetEnabled(false);
         if (dN.o(this.ia) != null) {
            for(int var3 = 0; var3 < dN.a(this.ia).Length; ++var3) {
               if (this.hZ == dN.a(this.ia)[var3] && var3 == dN.o(this.ia).dV()) {
                  dN.m(this.ia).SetEnabled(true);
                  dN.n(this.ia).SetEnabled(true);
               }
            }
         }
       }
   }

   public Object getSelectedItem() {
      return this.hZ;
   }

   // $FF: synthetic method
   public Object getElementAt(int var1) {
      return this.G(var1);
   }

}


}