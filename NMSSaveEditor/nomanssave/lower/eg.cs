using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{



public class eg : object {
   public ec ik;
   public int il;

   public eg(ec var1, int var2) {
      this.ik = var1;
      this.il = var2;
   }

   public int getSize() {
      // PORT_TODO: return gL.Values.Length;
      return default;
   }

   public gL I(int var1) {
      // PORT_TODO: return gL.Values[var1];
      return default;
   }

   public void addListDataListener(EventHandler var1) {
   }

   public void removeListDataListener(EventHandler var1) {
   }

   public void setSelectedItem(object var1) {
      gL var2 = (gL)var1;
      if (var2 != null && !var2.Equals(eb.a(ec.h(this.ik))[this.il].ef())) {
         eb.a(ec.h(this.ik))[this.il].a(var2);
      }

   }

   public object getSelectedItem() {
      return eb.a(ec.h(this.ik))[this.il].ef();
   }
   public object getElementAt(int var1) {
      return this.I(var1);
   }
}



}
