using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

class aA : object {
   int f;

   public aA(DataGridView var1, int var2) {
      this.f = var2;
   }

   public Component getTableCellRendererComponent(DataGridView var1, object var2, bool var3, bool var4, int var5, int var6) {
      Label var7 = (Label)base.getTableCellRendererComponent(var1, var2, var3, var4, var5, var6);
      var7.setHorizontalAlignment(this.f);
      return var7;
   }
}

}
