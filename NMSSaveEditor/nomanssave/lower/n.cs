using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class n : ActionListener {
   public h z;

   public n(h var1) {
      this.z = var1;
   }

   public void actionPerformed(EventArgs var1) {
      this.z.Hide();
   }
}


#else

public class n
{
   public n() { }
   public n(params object[] args) { }
   public h z = default;
   public void actionPerformed(EventArgs var1) { }
}

#endif

}
