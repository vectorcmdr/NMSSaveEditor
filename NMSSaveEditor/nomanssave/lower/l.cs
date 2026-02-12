using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

class l : object {
   private ey C;
   h z;

   l(h var1) {
      this.z = var1;
      this.C = null;
   }

   public int getSize() {
      return h.i(this.z).Count;
   }

   public ey d(int var1) {
      return (ey)h.i(this.z)[var1];
   }

   public void addListDataListener(EventHandler var1) {
   }

   public void removeListDataListener(EventHandler var1) {
   }

   public void setSelectedItem(object var1) {
      this.C = (ey)var1;
   }

   public object getSelectedItem() {
      return this.C;
   }
   public object getElementAt(int var1) {
      return this.d(var1);
   }
}

}
