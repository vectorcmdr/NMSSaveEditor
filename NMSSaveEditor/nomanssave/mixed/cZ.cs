using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class cZ : object {
   public gg gQ;
   public cY gR;

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


#else

public class cZ
{
   public cZ() { }
   public cZ(params object[] args) { }
   public gg gQ = default;
   public cY gR = default;
   public int getSize() { return 0; }
   public gg C(int var1) { return default; }
   public void addListDataListener(EventHandler var1) { }
   public void removeListDataListener(EventHandler var1) { }
   public void setSelectedItem(object var1) { }
   public object getSelectedItem() { return default; }
   public object getElementAt(int var1) { return default; }
}

#endif

}
