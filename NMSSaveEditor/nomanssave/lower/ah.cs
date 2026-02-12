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

public class ah : ActionListener {
   // $FF: synthetic field
   X bV;

   ah(X var1) {
      this.bV = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      gj var2 = (gj)X.k(this.bV).SelectedItem;
      if (var2 != null) {
         if (X.h(this.bV).isSelected() ^ var2.cQ()) {
            var2.d(X.h(this.bV).isSelected());
         }

      }
   }
}

}
