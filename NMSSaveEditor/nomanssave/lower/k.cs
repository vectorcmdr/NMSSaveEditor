using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

class k : object {
   private ex B;
   h z;

   k(h var1) {
      this.z = var1;
      this.B = null;
   }

   public int getSize() {
      return h.g(this.z).Count;
   }

   public ex c(int var1) {
      return (ex)h.g(this.z)[var1];
   }

   public void addListDataListener(EventHandler var1) {
   }

   public void removeListDataListener(EventHandler var1) {
   }

   public void setSelectedItem(object var1) {
      this.B = (ex)var1;
      h.h(this.z);
   }

   public object getSelectedItem() {
      return this.B;
   }
   public object getElementAt(int var1) {
      return this.c(var1);
   }
}

}
