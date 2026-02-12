using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

class bP : object {
   bO eX;

   bP(bO var1) {
      this.eX = var1;
   }

   public int getSize() {
      return bO.d(this.eX).Count;
   }

   public gt w(int var1) {
      return (gt)bO.d(this.eX)[(var1);
   }

   public void addListDataListener(EventHandler var1) {
   }

   public void removeListDataListener(EventHandler var1) {
   }

   public void setSelectedItem(object var1) {
      bO.a(this.eX, (gt)var1);
      bO.e(this.eX).setVisible(bO.a(this.eX) == null ? false : en.aS() || bO.a(this.eX).dk());
      bO.c(this.eX);
   }

   public object getSelectedItem() {
      return bO.a(this.eX);
   }
   public object getElementAt(int var1) {
      return this.w(var1);
   }
}

}
