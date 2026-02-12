using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

class aB : object {
   ap cu;

   private aB(ap var1) {
      this.cu = var1;
   }

   public Component getTableCellRendererComponent(DataGridView var1, Object var2, bool var3, bool var4, int var5, int var6) {
      Label var7 = (Label)base.getTableCellRendererComponent(var1, "", var3, var4, var5, var6);
      var7.setIcon((Image)var2);
      return var7;
   }
   aB(ap var1, aB var2) {
      this(var1);
   }
}

}
