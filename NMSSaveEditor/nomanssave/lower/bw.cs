using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

public class bw : object {
   gr et;
   bl er;

   public bw(bl var1) {
      this.er = var1;
   }

   public int getSize() {
      return gr.Values.Length;
   }

   public gr u(int var1) {
      return gr.Values[var1];
   }

   public void addListDataListener(EventHandler var1) {
   }

   public void removeListDataListener(EventHandler var1) {
   }

   public void setSelectedItem(object var1) {
      this.et = (gr)var1;
      bl.a(this.er, bl.b(this.er) < 0 ? null : nomanssave.er.a(this.et));
      bl.b(this.er, bl.b(this.er) < 0 ? null : nomanssave.er.b(this.et));
      if (bl.b(this.er) >= 0 && this.et != null && !this.et.Equals(bl.c(this.er)[bl.b(this.er)].da())) {
         bl.c(this.er)[bl.b(this.er)].c(this.et);
         if (bl.a(this.er) != null && bl.a(this.er).Length > 0) {
            bl.c(this.er)[bl.b(this.er)].a(0, bl.a(this.er)[0]);
            bl.p(this.er).SelectedItem = (bl.a(this.er)[0]);
         } else {
            bl.p(this.er).SelectedItem = ((object)null);
         }

         bl.q(this.er).SelectedItem = ((object)null);
         bl.r(this.er).SelectedItem = ((object)null);
         bl.s(this.er).SelectedItem = ((object)null);
         bl.t(this.er).SelectedItem = ((object)null);
      }

      bl.e(this.er).Refresh();
      bl.p(this.er).Refresh();
      bl.q(this.er).Refresh();
      bl.r(this.er).Refresh();
      bl.s(this.er).Refresh();
      bl.t(this.er).Refresh();
   }

   public object getSelectedItem() {
      return this.et;
   }
   public object getElementAt(int var1) {
      return this.u(var1);
   }
}

}
