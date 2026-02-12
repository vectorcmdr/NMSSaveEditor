using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class k : object {
   public ex B;
   public h z;

   public k(h var1) {
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


#else

public class k
{
   public k() { }
   public k(params object[] args) { }
   public ex B = default;
   public h z = default;
   public int getSize() { return 0; }
   public ex c(int var1) { return default; }
   public void addListDataListener(EventHandler var1) { }
   public void removeListDataListener(EventHandler var1) { }
   public void setSelectedItem(object var1) { }
   public object getSelectedItem() { return default; }
   public object getElementAt(int var1) { return default; }
}

#endif

}
