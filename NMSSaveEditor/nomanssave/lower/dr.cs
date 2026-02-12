using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class dr : ActionListener {
   public dj hl;
   public Application bv;

   public dr(dj var1, Application var2) {
      this.hl = var1;
      this.bv = var2;
   }

   public void actionPerformed(EventArgs var1) {
      int var2 = dj.j(this.hl).SelectedIndex;
      if (var2 >= 0 && var2 < dj.a(this.hl).Length) {
         this.bv.a(dj.a(this.hl)[var2]);
      }
   }
}


#else

public class dr
{
   public dr() { }
   public dr(params object[] args) { }
   public dj hl = default;
   public Application bv = default;
   public void actionPerformed(EventArgs var1) { }
}

#endif

}
