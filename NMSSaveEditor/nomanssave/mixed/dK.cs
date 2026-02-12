using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{



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



}
