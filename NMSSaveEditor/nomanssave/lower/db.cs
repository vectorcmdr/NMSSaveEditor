using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class db : ActionListener {
   public cY gR;

   public db(cY var1) {
      this.gR = var1;
   }

   public void actionPerformed(EventArgs var1) {
      this.gR.Hide();
   }
}


#else

public class db
{
   public db() { }
   public db(params object[] args) { }
   public cY gR = default;
   public void actionPerformed(EventArgs var1) { }
}

#endif

}
