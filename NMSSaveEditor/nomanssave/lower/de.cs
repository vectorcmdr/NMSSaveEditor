using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

class de : ListModel {
   dd gW;

   de(dd var1) {
      this.gW = var1;
   }

   public int getSize() {
      return dd.a(this.gW).Count;
   }

   public gt w(int var1) {
      return (gt)dd.a(this.gW)[(var1);
   }

   public void addListDataListener(EventHandler var1) {
   }

   public void removeListDataListener(EventHandler var1) {
   }
   public Object getElementAt(int var1) {
      return this.w(var1);
   }
}

}
