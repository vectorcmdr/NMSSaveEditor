using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

public class J : object {
   gh bs;
   public I bt;

   public J(I var1) {
      this.bt = var1;
      this.bs = null;
   }

   public int getSize() {
      return I.a(this.bt) == null ? 0 : I.a(this.bt).cD().Count;
   }

   public gh o(int var1) {
      return I.a(this.bt) == null ? null : (gh)I.a(this.bt).cD()[var1];
   }

   public void addListDataListener(EventHandler var1) {
   }

   public void removeListDataListener(EventHandler var1) {
   }

   public void setSelectedItem(object var1) {
      this.bs = (gh)var1;
      if (this.bs == null) {
         I.b(this.bt).Text = ("");
         I.c(this.bt).Text = ("");
         I.c(this.bt).Enabled = (false);
      } else {
         gy var2 = this.bs.cJ();
         I.b(this.bt).Text = (var2 == null ? "" : var2.ToString());
         I.c(this.bt).Text = (this.bs.cK());
         I.c(this.bt).Enabled = (true);
      }

   }

   public object getSelectedItem() {
      return this.bs;
   }
   public object getElementAt(int var1) {
      return this.o(var1);
   }
}

}
