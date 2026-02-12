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

public class bY : ActionListener {
   // $FF: synthetic field
   bS fk;

   bY(bS var1) {
      this.fk = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      if (bO.a(bS.j(this.fk)).dp() || en.aS()) {
         if (bO.a(bS.j(this.fk)).dv()) {
            bO.c(bS.j(this.fk));
         }

      }
   }
}

}
