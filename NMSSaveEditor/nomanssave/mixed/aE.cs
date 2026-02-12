using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


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


#else

public class aE
{
   public aE() { }
   public aE(params object[] args) { }
   public aI cA = default;
   public aD cB = default;
   public int getSize() { return 0; }
   public aI t(int var1) { return default; }
   public void addListDataListener(EventHandler var1) { }
   public void removeListDataListener(EventHandler var1) { }
   public void setSelectedItem(object var1) { }
   public object getSelectedItem() { return default; }
   public object getElementAt(int var1) { return default; }
}

#endif

}
