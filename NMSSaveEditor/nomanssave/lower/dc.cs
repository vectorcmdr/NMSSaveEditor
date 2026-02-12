using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class dc : ActionListener {
   public cY gR;

   public dc(cY var1) {
      this.gR = var1;
   }

   public void actionPerformed(EventArgs var1) {
      this.gR.Hide();
   }
}


#else

public class dc
{
   public dc() { }
   public dc(params object[] args) { }
   public cY gR = default;
   public void actionPerformed(EventArgs var1) { }
}

#endif

}
