using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class b : ActionListener {
   public a _b;

   public b(a var1) {
      this._b = var1;
   }

   public void actionPerformed(EventArgs var1) {
      this._b.Hide();
   }
}


#else

public class b
{
   public b() { }
   public b(params object[] args) { }
   public a _b = default;
   public void actionPerformed(EventArgs var1) { }
}

#endif

}
