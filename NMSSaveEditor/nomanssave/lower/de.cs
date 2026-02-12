using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class de : ListModel {
   public dd gW;

   public de(dd var1) {
      this.gW = var1;
   }

   public int getSize() {
      return dd.a(this.gW).Count;
   }

   public gt w(int var1) {
      return (gt)dd.a(this.gW)[var1];
   }

   public void addListDataListener(EventHandler var1) {
   }

   public void removeListDataListener(EventHandler var1) {
   }
   public object getElementAt(int var1) {
      return this.w(var1);
   }
}


#else

public class de
{
   public de() { }
   public de(params object[] args) { }
   public dd gW = default;
   public int getSize() { return 0; }
   public gt w(int var1) { return default; }
   public void addListDataListener(EventHandler var1) { }
   public void removeListDataListener(EventHandler var1) { }
   public object getElementAt(int var1) { return default; }
}

#endif

}
