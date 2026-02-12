using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Windows.Forms;
using System.Globalization;

namespace NMSSaveEditor
{

public class dy : MouseAdapter {
   // $FF: synthetic field
   public du hp;
   // $FF: synthetic field
   public gF hq;

   public dy(du var1, gF var2) {
      this.hp = var1;
      this.hq = var2;
   }

   public void mouseClicked(MouseEvent var1) {
      if (var1.getClickCount() == 2 && this.hq != null) {
         cg.a((Container)du.d(this.hp), (gQ)this.hq);
         du.c(this.hp);
      }
    }
}

}
