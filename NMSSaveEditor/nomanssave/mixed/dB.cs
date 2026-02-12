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

public class dB : ActionListener {
   // $FF: synthetic field
   public dz hu;

   public dB(dz var1) {
      this.hu = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      int var2 = dz.b(this.hu).SelectedIndex;
      if (var2 < 0 || dz.a(this.hu)[var2].Length == 0 || JOptionPane.showConfirmDialog(this.hu, "You are about to overwrite this save slot, are you sure you want to do this?", "Warning", 2) == 0) {
         dz.a(this.hu, var2);
         this.hu.SetVisible(false);
      }
   }
}

}
