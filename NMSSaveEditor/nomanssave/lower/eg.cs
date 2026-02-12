using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class eg : object {
   public ec ik;
   public int il;

   public eg(ec var1, int var2) {
      this.ik = var1;
      this.il = var2;
   }

   public int getSize() {
      return gL.Values.Length;
   }

   public gL I(int var1) {
      return gL.Values[var1];
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


#else

public class eg
{
   public eg() { }
   public eg(params object[] args) { }
   public ec ik = default;
   public int il = 0;
   public int getSize() { return 0; }
   public gL I(int var1) { return default; }
   public void addListDataListener(EventHandler var1) { }
   public void removeListDataListener(EventHandler var1) { }
   public void setSelectedItem(object var1) { }
   public object getSelectedItem() { return default; }
   public object getElementAt(int var1) { return default; }
}

#endif

}
