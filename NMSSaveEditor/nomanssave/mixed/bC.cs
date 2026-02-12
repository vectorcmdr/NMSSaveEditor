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

public class bC : ComboBoxModel {
   public int ev;
   public er eu;
   // $FF: synthetic field
   public bl er;

   public bC(bl var1, int var2) {
      this.er = var1;
      this.ev = var2;
   }

   public int getSize() {
      return 1 + (bl.f(this.er) == null ? 0 : bl.f(this.er).length);
   }

   public er v(int var1) {
      if (var1 == 0) {
         return null;
      } else {
         return bl.f(this.er) == null ? null : bl.f(this.er)[var1 - 1];
      }
   }

   public void addListDataListener(ListDataListener var1) {
   }

   public void removeListDataListener(ListDataListener var1) {
   }

   public void setSelectedItem(Object var1) {
      this.eu = (er)var1;
      if (bl.b(this.er) >= 0) {
         er var2 = bl.c(this.er)[bl.b(this.er)].ar(this.ev);
         if (this.eu != var2) {
            int var3;
            int var4;
            if (var2 != null) {
               var3 = var2.aU().ordinal();
               var4 = bl.c(this.er)[bl.b(this.er)].aq(var3) - var2.aV();
               if (var4 < 0) {
                  var4 = 0;
               }

               bl.c(this.er)[bl.b(this.er)].e(var3, var4);
               bl.d(this.er)[var3].SetText(Convert.ToString(var4));
            }

            if (this.eu == null) {
               bl.c(this.er)[bl.b(this.er)].a(this.ev, (er)null);
            } else {
               bl.c(this.er)[bl.b(this.er)].a(this.ev, this.eu);
               var3 = this.eu.aU().ordinal();
               var4 = bl.c(this.er)[bl.b(this.er)].aq(var3) + this.eu.aV();
               if (var4 < 0) {
                  var4 = 0;
               }

               bl.c(this.er)[bl.b(this.er)].e(var3, var4);
               bl.d(this.er)[var3].SetText(Convert.ToString(var4));
            }

            bl.e(this.er).updateUI();
         }
      }

   }

   public Object getSelectedItem() {
      return this.eu;
   }

   // $FF: synthetic method
   public Object getElementAt(int var1) {
      return this.v(var1);
   }
}

}
