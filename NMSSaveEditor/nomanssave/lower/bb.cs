using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class bb : ActionListener {
   public ba dB;
   public G dC;

   public bb(ba var1, G var2) {
      this.dB = var1;
      this.dC = var2;
   }

   public void actionPerformed(EventArgs var1) {
      this.dC.f(hg.eo().ToString());
   }
}


#else

public class bb
{
   public bb() { }
   public bb(params object[] args) { }
   public ba dB = default;
   public G dC = default;
   public void actionPerformed(EventArgs var1) { }
}

#endif

}
