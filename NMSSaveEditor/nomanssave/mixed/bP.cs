using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class bP : object {
   public bO eX;

   public bP(bO var1) {
      this.eX = var1;
   }

   public int getSize() {
      return bO.d(this.eX).Count;
   }

   public gt w(int var1) {
      return (gt)bO.d(this.eX)[var1];
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


#else

public class bP
{
   public bP() { }
   public bP(params object[] args) { }
   public bO eX = default;
   public int getSize() { return 0; }
   public gt w(int var1) { return default; }
   public void addListDataListener(EventHandler var1) { }
   public void removeListDataListener(EventHandler var1) { }
   public void setSelectedItem(object var1) { }
   public object getSelectedItem() { return default; }
   public object getElementAt(int var1) { return default; }
}

#endif

}
