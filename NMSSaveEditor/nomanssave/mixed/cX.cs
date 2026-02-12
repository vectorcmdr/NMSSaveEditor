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

public class cX : Runnable {
   // $FF: synthetic field
   public cW gL;

   public cX(cW var1) {
      this.gL = var1;
   }

   public void run() {
      try {
         int var1 = cW.a(this.gL).getDocument().Count;
         Rectangle var2 = cW.a(this.gL).modelToView(var1);
         if (var2 != null && var2.y != cW.b(this.gL)) {
            cW.c(this.gL);
            this.gL.Invalidate();
            cW.a(this.gL, var2.y);
         }
      } catch (BadLocationException var3) {
      }

   }
}

}
