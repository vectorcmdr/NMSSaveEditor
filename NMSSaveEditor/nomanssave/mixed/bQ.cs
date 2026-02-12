using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class bQ : object {
   public bO eX;

   public bQ(bO var1) {
      this.eX = var1;
   }

   public Component getListCellRendererComponent(ListBox var1, object var2, int var3, bool var4, bool var5) {
      if (var2 is gt) {
         var2 = ((gt)var2).getSimpleName();
      }

      return base.getListCellRendererComponent(var1, var2, var3, var4, var5);
   }
}


#else

public class bQ
{
   public bQ() { }
   public bQ(params object[] args) { }
   public bO eX = default;
   public Component getListCellRendererComponent(ListBox var1, object var2, int var3, bool var4, bool var5) { return default; }
}

#endif

}
