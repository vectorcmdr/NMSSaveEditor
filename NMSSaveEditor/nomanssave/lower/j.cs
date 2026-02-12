using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

class j : object {
   private eB A;
   h z;

   j(h var1) {
      this.z = var1;
      this.A = null;
   }

   public int getSize() {
      return h.e(this.z).Count;
   }

   public eB b(int var1) {
      return (eB)h.e(this.z)[(var1);
   }

   public void addListDataListener(EventHandler var1) {
   }

   public void removeListDataListener(EventHandler var1) {
   }

   public void setSelectedItem(object var1) {
      this.A = (eB)var1;
      h.f(this.z);
   }

   public object getSelectedItem() {
      return this.A;
   }
   public object getElementAt(int var1) {
      return this.b(var1);
   }
}

}
