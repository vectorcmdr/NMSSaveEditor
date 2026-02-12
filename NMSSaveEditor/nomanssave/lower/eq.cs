using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

public class eq : object {
   public gO iz;
   ep iA;

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

}
