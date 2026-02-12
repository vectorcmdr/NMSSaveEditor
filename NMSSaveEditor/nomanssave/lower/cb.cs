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

public class cb : ActionListener {
   // $FF: synthetic field
   public bS fk;
   // $FF: synthetic field
   public int fl;
   // $FF: synthetic field
   public int fm;

   public cb(bS var1, int var2, int var3) {
      this.fk = var1;
      this.fl = var2;
      this.fm = var3;
   }

   public void actionPerformed(ActionEvent var1) {
      bool var2 = bS.d(this.fk).getState();
      bO.a(bS.j(this.fk)).a(this.fl, this.fm, var2);
      bS.c(this.fk);
   }
}

}
