using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class dw : ActionListener {
   public du hp;
   public gF hq;

   public dw(du var1, gF var2) {
      this.hp = var1;
      this.hq = var2;
   }

   public void actionPerformed(EventArgs var1) {
      if (this.hq != null) {
         dt.a(du.d(this.hp), this.hp);
      }

   }
}


#else

public class dw
{
   public dw() { }
   public dw(params object[] args) { }
   public du hp = default;
   public gF hq = default;
   public void actionPerformed(EventArgs var1) { }
}

#endif

}
