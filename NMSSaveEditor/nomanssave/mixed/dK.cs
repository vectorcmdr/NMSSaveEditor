using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class dK : object {
   public eM hF;
   public dJ hG;

   public dK(dJ var1) {
      this.hG = var1;
   }

   public int getSize() {
      return 1 + eM.getCount();
   }

   public eM F(int var1) {
      return var1 == 0 ? null : eM.S(var1 - 1);
   }

   public void addListDataListener(EventHandler var1) {
   }

   public void removeListDataListener(EventHandler var1) {
   }

   public void setSelectedItem(object var1) {
      this.hF = (eM)var1;
   }

   public object getSelectedItem() {
      return this.hF;
   }
   public object getElementAt(int var1) {
      return this.F(var1);
   }
}


#else

public class dK
{
   public dK() { }
   public dK(params object[] args) { }
   public eM hF = default;
   public dJ hG = default;
   public int getSize() { return 0; }
   public eM F(int var1) { return default; }
   public void addListDataListener(EventHandler var1) { }
   public void removeListDataListener(EventHandler var1) { }
   public void setSelectedItem(object var1) { }
   public object getSelectedItem() { return default; }
   public object getElementAt(int var1) { return default; }
}

#endif

}
