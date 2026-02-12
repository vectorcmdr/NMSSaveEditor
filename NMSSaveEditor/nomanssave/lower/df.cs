using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

class df : object {
   dd gW;

   df(dd var1) {
      this.gW = var1;
   }

   public void mouseClicked(MouseEventArgs var1) {
      if (var1.getClickCount() == 2) {
         dd.a(this.gW, dd.b(this.gW).getSelectedIndex());
         this.gW.setVisible(false);
      }

   }
}

}
