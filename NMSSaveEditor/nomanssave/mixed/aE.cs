using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{



public class aE : object {
   public aI cA;
   public aD cB;

   public aE(aD var1) {
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

   public void setSelectedItem(object var1) {
      this.cA = (aI)var1;
   }

   public object getSelectedItem() {
      return this.cA;
   }
   public object getElementAt(int var1) {
      return this.t(var1);
   }
}



}
