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

public class bt : ListSelectionListener {
   // $FF: synthetic field
   bl er;
   // $FF: synthetic field
   private Application bv;

   bt(bl var1, Application var2) {
      this.er = var1;
      this.bv = var2;
   }

   public void valueChanged(ListSelectionEvent var1) {
      JavaCompat.InvokeLater(new bu(this, this.bv));
   }

   // $FF: synthetic method
   static bl a(bt var0) {
      return var0.er;
   }
}

}
