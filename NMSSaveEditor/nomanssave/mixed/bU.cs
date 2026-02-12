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

public class bU : ActionListener {
   // $FF: synthetic field
   bS fk;
   // $FF: synthetic field
   private int fl;
   // $FF: synthetic field
   private int fm;

   bU(bS var1, int var2, int var3) {
      this.fk = var1;
      this.fl = var2;
      this.fm = var3;
   }

   public void actionPerformed(ActionEvent var1) {
      gu var2 = bO.a(bS.j(this.fk)).f(this.fl, this.fm);
      if (var2 != null) {
         bO.a(bS.j(this.fk), var2, this.fk);
      }

   }
}

}
