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

public class ca : ActionListener {
   // $FF: synthetic field
   public bS fk;

   public ca(bS var1) {
      this.fk = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      if (bO.a(bS.j(this.fk)).dq()) {
         if (bO.a(bS.j(this.fk)).ds()) {
            bO.c(bS.j(this.fk));
         }

      }
   }
}

}
