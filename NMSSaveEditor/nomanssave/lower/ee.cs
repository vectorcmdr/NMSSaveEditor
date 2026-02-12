using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

class ee : object {
   ec ik;
   private int il;

   ee(ec var1, int var2) {
      this.ik = var1;
      this.il = var2;
   }

   public int getSize() {
      return eb.aP().Length;
   }

   public gy H(int var1) {
      return eb.aP()[var1];
   }

   public void addListDataListener(EventHandler var1) {
   }

   public void removeListDataListener(EventHandler var1) {
   }

   public void setSelectedItem(Object var1) {
      gy var2 = (gy)var1;
      if (var2 != null && !var2.equals(eb.a(ec.h(this.ik))[this.il].ed())) {
         eb.a(ec.h(this.ik))[this.il].a(var2);
      }

   }

   public Object getSelectedItem() {
      return eb.a(ec.h(this.ik))[this.il].ed();
   }
   public Object getElementAt(int var1) {
      return this.H(var1);
   }
}

}
