using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

public class ee : object {
   public ec ik;
   public int il;

   public ee(ec var1, int var2) {
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

   public void setSelectedItem(object var1) {
      gy var2 = (gy)var1;
      if (var2 != null && !var2.Equals(eb.a(ec.h(this.ik))[this.il].ed())) {
         eb.a(ec.h(this.ik))[this.il].a(var2);
      }

   }

   public object getSelectedItem() {
      return eb.a(ec.h(this.ik))[this.il].ed();
   }
   public object getElementAt(int var1) {
      return this.H(var1);
   }
}

}
