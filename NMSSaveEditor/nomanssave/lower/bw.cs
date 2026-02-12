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

public class bw : ComboBoxModel {
   public gr et;
   // $FF: synthetic field
   public bl er;

   public bw(bl var1) {
      this.er = var1;
   }

   public int getSize() {
      return gr.Values.Length;
   }

   public gr u(int var1) {
      return gr.Values[var1];
   }

   public void addListDataListener(ListDataListener var1) {
   }

   public void removeListDataListener(ListDataListener var1) {
   }

   public void setSelectedItem(Object var1) {
      this.et = (gr)var1;
      bl.a(this.er, bl.b(this.er) < 0 ? null : NMSSaveEditor.er.a(this.et));
      bl.b(this.er, bl.b(this.er) < 0 ? null : NMSSaveEditor.er.b(this.et));
      if (bl.b(this.er) >= 0 && this.et != null && !this.et.Equals(bl.c(this.er)[bl.b(this.er)].da())) {
         bl.c(this.er)[bl.b(this.er)].c(this.et);
         if (bl.a(this.er) != null && bl.a(this.er).Length > 0) {
            bl.c(this.er)[bl.b(this.er)].a(0, bl.a(this.er)[0]);
            bl.p(this.er).SetSelectedItem(bl.a(this.er)[0]);
         } else {
            bl.p(this.er).SetSelectedItem((Object)null);
         }

         bl.q(this.er).SetSelectedItem((Object)null);
         bl.r(this.er).SetSelectedItem((Object)null);
         bl.s(this.er).SetSelectedItem((Object)null);
         bl.t(this.er).SetSelectedItem((Object)null);
      }

      bl.e(this.er).updateUI();
      bl.p(this.er).updateUI();
      bl.q(this.er).updateUI();
      bl.r(this.er).updateUI();
      bl.s(this.er).updateUI();
      bl.t(this.er).updateUI();
   }

   public Object getSelectedItem() {
      return this.et;
   }

   // $FF: synthetic method
   public Object getElementAt(int var1) {
      return this.u(var1);
   }
}

}
