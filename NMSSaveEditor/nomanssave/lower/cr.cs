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

public class cr : FileFilter {
   // $FF: synthetic field
   public cp fM;

   public cr(cp var1) {
      this.fM = var1;
   }

   public string getDescription() {
      return "Companion Export File";
   }

   public bool accept(FileInfo var1) {
      if (var1.isDirectory()) {
         return !var1.isHidden();
      } else {
         return var1.Name.EndsWith(".pet") || var1.Name.EndsWith(".egg");
      }
   }
}

}
