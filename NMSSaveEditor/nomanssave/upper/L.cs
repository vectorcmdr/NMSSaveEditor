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
      return I.a(this.bt) == null ? null : (gf)I.a(this.bt).cE().get(var1);
   }

   public void addListDataListener(EventHandler var1) {
   }

   public void removeListDataListener(EventHandler var1) {
   }

   public void setSelectedItem(Object var1) {
      this.bu = (gf)var1;
      if (this.bu == null) {
         I.e(this.bt).setText("");
         I.f(this.bt).setText("");
         I.f(this.bt).setEnabled(false);
         I.g(this.bt).setEnabled(false);
         I.h(this.bt).setEnabled(false);
         I.i(this.bt).setEnabled(false);
      } else {
         I.e(this.bt).setText(Integer.toString(this.bu.cG()));
         I.f(this.bt).setText(this.bu.getName());
         I.f(this.bt).setEnabled(true);
         I.g(this.bt).setEnabled(true);
         I.h(this.bt).setEnabled(true);
         I.i(this.bt).setEnabled(true);
      }

   }

   public Object getSelectedItem() {
      return this.bu;
   }
   public Object getElementAt(int var1) {
      return this.p(var1);
   }
}

}
