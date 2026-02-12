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

public class cu : FileFilter {
   // $FF: synthetic field
   cs fP;

   cu(cs var1) {
      this.fP = var1;
   }

   public string getDescription() {
      return "Freighter Backup File";
   }

   public bool accept(FileInfo var1) {
      if (var1.IsDirectory()) {
         return !var1.isHidden();
      } else {
         return var1.Name.EndsWith(".fb3");
      }
   }
}

}
