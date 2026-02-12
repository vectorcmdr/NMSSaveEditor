using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class s : ActionListener {
   public p I;

   public s(p var1) {
      this.I = var1;
   }

   public void actionPerformed(EventArgs var1) {
      this.I.Hide();
   }
}


#else

public class s
{
   public s() { }
   public s(params object[] args) { }
   public p I = default;
   public void actionPerformed(EventArgs var1) { }
}

#endif

}
