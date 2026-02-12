using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class dA : ListModel {
   public dz hu;

   public dA(dz var1) {
      this.hu = var1;
   }

   public int getSize() {
      return dz.a(this.hu).Length;
   }

   public ft m(int var1) {
      return dz.a(this.hu)[var1];
   }

   public void addListDataListener(EventHandler var1) {
   }

   public void removeListDataListener(EventHandler var1) {
   }
   public object getElementAt(int var1) {
      return this.m(var1);
   }
}


#else

public class dA
{
   public dA() { }
   public dA(params object[] args) { }
   public dz hu = default;
   public int getSize() { return 0; }
   public ft m(int var1) { return default; }
   public void addListDataListener(EventHandler var1) { }
   public void removeListDataListener(EventHandler var1) { }
   public object getElementAt(int var1) { return default; }
}

#endif

}
