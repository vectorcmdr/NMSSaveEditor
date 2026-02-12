using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class o : ActionListener {
   public h z;

   public o(h var1) {
      this.z = var1;
   }

   public void actionPerformed(EventArgs var1) {
      this.z.Hide();
   }
}


#else

public class o
{
   public o() { }
   public o(params object[] args) { }
   public h z = default;
   public void actionPerformed(EventArgs var1) { }
}

#endif

}
