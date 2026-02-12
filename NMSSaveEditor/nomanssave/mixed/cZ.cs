using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

public class cZ : object {
   public gg gQ;
   cY gR;

   public cZ(cY var1) {
      this.gR = var1;
      this.gQ = null;
   }

   public int getSize() {
      return cY.a(this.gR).Count;
   }

   public gg C(int var1) {
      return (gg)cY.a(this.gR)[var1];
   }

   public void addListDataListener(EventHandler var1) {
   }

   public void removeListDataListener(EventHandler var1) {
   }

   public void setSelectedItem(object var1) {
      this.gQ = (gg)var1;
   }

   public object getSelectedItem() {
      return this.gQ;
   }
   public object getElementAt(int var1) {
      return this.C(var1);
   }
}

}
