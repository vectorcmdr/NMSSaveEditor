using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class r : ActionListener {
   public p I;

   public r(p var1) {
      this.I = var1;
   }

   public void actionPerformed(EventArgs var1) {
      int[] var2 = p.b(this.I).getSelectedRows();
      p.a(this.I, new List<object>());

      for(int var4 = 0; var4 < var2.Length; ++var4) {
         int var3 = p.b(this.I).convertRowIndexToModel(var2[var4]);
         p.c(this.I).Add((string)p.b(this.I).DataSource.getValueAt(var3, 3));
      }

      this.I.Hide();
   }
}


#else

public class r
{
   public r() { }
   public r(params object[] args) { }
   public p I = default;
   public void actionPerformed(EventArgs var1) { }
}

#endif

}
