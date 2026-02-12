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

public class dx : ActionListener {
   // $FF: synthetic field
   du hp;
   // $FF: synthetic field
   private gF hq;

   dx(du var1, gF var2) {
      this.hp = var1;
      this.hq = var2;
   }

   public void actionPerformed(ActionEvent var1) {
      if (this.hq != null) {
         dt.b(du.d(this.hp), this.hp);
      }

   }
}

}
