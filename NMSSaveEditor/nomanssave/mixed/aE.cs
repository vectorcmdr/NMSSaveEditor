using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

class aE : object {
   aI cA;
   aD cB;

   aE(aD var1) {
      this.cB = var1;
      this.cA = null;
   }

   public int getSize() {
      return aI.Values.Length;
   }

   public aI t(int var1) {
      return aI.Values[var1];
   }

   public void addListDataListener(EventHandler var1) {
   }

   public void removeListDataListener(EventHandler var1) {
   }

   public void setSelectedItem(Object var1) {
      this.cA = (aI)var1;
   }

   public Object getSelectedItem() {
      return this.cA;
   }
   public Object getElementAt(int var1) {
      return this.t(var1);
   }
}

}
