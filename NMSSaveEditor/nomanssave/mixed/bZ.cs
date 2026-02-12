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

public class bZ : ActionListener {
   // $FF: synthetic field
   public bS fk;
   // $FF: synthetic field
   public int fl;
   // $FF: synthetic field
   public int fm;

   public bZ(bS var1, int var2, int var3) {
      this.fk = var1;
      this.fl = var2;
      this.fm = var3;
   }

   public void actionPerformed(ActionEvent var1) {
      if (bO.a(bS.j(this.fk)).dq()) {
         if (bO.a(bS.j(this.fk)).l(this.fl, this.fm)) {
            bO.a(bS.j(this.fk)).m(this.fl, this.fm);
            bS.c(this.fk);
         }
      }
   }
}

}
