using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class i : ActionListener {
   public h z;

   public i(h var1) {
      this.z = var1;
   }

   public void actionPerformed(EventArgs var1) {
      string var2 = h.a(this.z).Text;
      h.a(this.z, ey.b(h.b(this.z), var2));
      h.c(this.z);
      if (h.d(this.z).Count == 0) {
         MessageBox.Show("Item not found.", "Warning");
      }

   }
}


#else

public class i
{
   public i() { }
   public i(params object[] args) { }
   public h z = default;
   public void actionPerformed(EventArgs var1) { }
}

#endif

}
