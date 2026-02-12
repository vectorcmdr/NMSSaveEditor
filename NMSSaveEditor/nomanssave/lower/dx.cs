using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class dx : ActionListener {
   public du hp;
   public gF hq;

   public dx(du var1, gF var2) {
      this.hp = var1;
      this.hq = var2;
   }

   public void actionPerformed(EventArgs var1) {
      if (this.hq != null) {
         dt.b(du.d(this.hp), this.hp);
      }

   }
}


#else

public class dx
{
   public dx() { }
   public dx(params object[] args) { }
   public du hp = default;
   public gF hq = default;
   public void actionPerformed(EventArgs var1) { }
}

#endif

}
