using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Windows.Forms;
using System.Globalization;

namespace NMSSaveEditor
{

public class aB : DefaultTableCellRenderer {
   // $FF: synthetic field
   ap cu;

   private aB(ap var1) {
      this.cu = var1;
   }

   public Component getTableCellRendererComponent(DataGridView var1, Object var2, bool var3, bool var4, int var5, int var6) {
      Label var7 = (Label)base.getTableCellRendererComponent(var1, "", var3, var4, var5, var6);
      var7.setIcon((Image)var2);
      return var7;
   }

   // $FF: synthetic method
   aB(ap var1, aB var2) {
      // Constructor chain: base(var1)
   }
}

}
