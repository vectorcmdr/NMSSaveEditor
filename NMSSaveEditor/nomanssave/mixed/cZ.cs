using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

class cZ : object {
   private gg gQ;
   cY gR;

   cZ(cY var1) {
      this.gR = var1;
      this.gQ = null;
   }

   public int getSize() {
      return cY.a(this.gR).Count;
   }

   public gg C(int var1) {
      return (gg)cY.a(this.gR).get(var1);
   }

   public void addListDataListener(EventHandler var1) {
   }

   public void removeListDataListener(EventHandler var1) {
   }

   public void setSelectedItem(Object var1) {
      this.gQ = (gg)var1;
   }

   public Object getSelectedItem() {
      return this.gQ;
   }
   public Object getElementAt(int var1) {
      return this.C(var1);
   }
}

}
