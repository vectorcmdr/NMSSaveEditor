using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class ab : object {
   public X bV;

   public ab(X var1) {
      this.bV = var1;
   }

   public int getSize() {
      return gl.Values.Length;
   }

   public gl r(int var1) {
      return gl.Values[var1];
   }

   public void addListDataListener(EventHandler var1) {
   }

   public void removeListDataListener(EventHandler var1) {
   }

   public void setSelectedItem(object var1) {
   }

   public object getSelectedItem() {
      return gl.oF;
   }
   public object getElementAt(int var1) {
      return this.r(var1);
   }
}


#else

public class ab
{
   public ab() { }
   public ab(params object[] args) { }
   public X bV = default;
   public int getSize() { return 0; }
   public gl r(int var1) { return default; }
   public void addListDataListener(EventHandler var1) { }
   public void removeListDataListener(EventHandler var1) { }
   public void setSelectedItem(object var1) { }
   public object getSelectedItem() { return default; }
   public object getElementAt(int var1) { return default; }
}

#endif

}
