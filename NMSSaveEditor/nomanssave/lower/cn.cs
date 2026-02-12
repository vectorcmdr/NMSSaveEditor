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

public class cn : FileFilter {
   // $FF: synthetic field
   public cl fI;

   public cn(cl var1) {
      this.fI = var1;
   }

   public string getDescription() {
      return "Planetary Base Backup File";
   }

   public bool accept(FileInfo var1) {
      if (var1.IsDirectory()) {
         return !var1.isHidden();
      } else {
         return var1.Name.EndsWith(".pb3");
      }
   }
}

}
