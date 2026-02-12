using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

public class ar : ActionListener {
   public ap cu;

   public ar(ap var1) {
      this.cu = var1;
   }

   public void actionPerformed(EventArgs var1) {
      ap.k(this.cu);
   }
}

}
