using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

class dA : ListModel {
   dz hu;

   dA(dz var1) {
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
   public Object getElementAt(int var1) {
      return this.m(var1);
   }
}

}
