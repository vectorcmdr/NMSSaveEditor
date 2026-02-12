using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class e : DefaultTableCellRenderer {
   public int f;

   public e(int var1) {
      this.f = var1;
   }

   public Component getTableCellRendererComponent(DataGridView var1, object var2, bool var3, bool var4, int var5, int var6) {
      Label var7 = (Label)this.getTableCellRendererComponent(/*base*/var1, var2, var3, var4, var5, var6);
      var7.setHorizontalAlignment(this.f);
      return var7;
   }
}


#else

public class e
{
   public e() { }
   public e(params object[] args) { }
   public int f = 0;
   public Component getTableCellRendererComponent(DataGridView var1, object var2, bool var3, bool var4, int var5, int var6) { return default; }
}

#endif

}
