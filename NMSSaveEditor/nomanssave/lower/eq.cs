using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class eq : object {
   public gO iz;
   public ep iA;

   public eq(ep var1) {
      this.iA = var1;
      this.iz = null;
   }

   public int getSize() {
      return ep.a(this.iA) == null ? 0 : ep.a(this.iA).Length;
   }

   public gO J(int var1) {
      return ep.a(this.iA)[var1];
   }

   public void addListDataListener(EventHandler var1) {
   }

   public void removeListDataListener(EventHandler var1) {
   }

   public void setSelectedItem(object var1) {
      this.iz = (gO)var1;
      if (this.iz == null) {
         ep.b(this.iA).a(new List<object>());
      } else {
         ep.b(this.iA).a(this.iz.cC());
      }
   }

   public object getSelectedItem() {
      return this.iz;
   }
   public object getElementAt(int var1) {
      return this.J(var1);
   }
}


#else

public class eq
{
   public eq() { }
   public eq(params object[] args) { }
   public gO iz = default;
   public ep iA = default;
   public int getSize() { return 0; }
   public gO J(int var1) { return default; }
   public void addListDataListener(EventHandler var1) { }
   public void removeListDataListener(EventHandler var1) { }
   public void setSelectedItem(object var1) { }
   public object getSelectedItem() { return default; }
   public object getElementAt(int var1) { return default; }
}

#endif

}
