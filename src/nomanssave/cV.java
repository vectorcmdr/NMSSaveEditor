package nomanssave;

import java.io.File;
import javax.swing.filechooser.FileFilter;

class cV extends FileFilter {
   // $FF: synthetic field
   final cT gw;

   cV(cT var1) {
      this.gw = var1;
   }

   public String getDescription() {
      return "Ship Export File";
   }

   public boolean accept(File var1) {
      if (var1.isDirectory()) {
         return !var1.isHidden();
      } else {
         return var1.getName().endsWith(".sh0");
      }
   }
}
