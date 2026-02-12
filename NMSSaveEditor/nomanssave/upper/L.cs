using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

class L : object {
   gf bu;
   I bt;

   L(I var1) {
      this.bt = var1;
      this.bu = null;
   }

   public int getSize() {
      return I.a(this.bt) == null ? 0 : I.a(this.bt).cE().Count;
   }

   public gf p(int var1) {
      return I.a(this.bt) == null ? null : (gf)I.a(this.bt).cE()[var1];
   }

   public void addListDataListener(EventHandler var1) {
   }

   public void removeListDataListener(EventHandler var1) {
   }

   public void setSelectedItem(object var1) {
      this.bu = (gf)var1;
      if (this.bu == null) {
         I.e(this.bt).Text = ("");
         I.f(this.bt).Text = ("");
         I.f(this.bt).Enabled = (false);
         I.g(this.bt).Enabled = (false);
         I.h(this.bt).Enabled = (false);
         I.i(this.bt).Enabled = (false);
      } else {
         I.e(this.bt).Text = (Integer.toString(this.bu.cG()));
         I.f(this.bt).Text = (this.bu.Name);
         I.f(this.bt).Enabled = (true);
         I.g(this.bt).Enabled = (true);
         I.h(this.bt).Enabled = (true);
         I.i(this.bt).Enabled = (true);
      }

   }

   public object getSelectedItem() {
      return this.bu;
   }
   public object getElementAt(int var1) {
      return this.p(var1);
   }
}

}
