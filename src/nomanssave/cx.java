package nomanssave;

import java.io.File;
import javax.swing.filechooser.FileFilter;

class cx extends FileFilter {
   // $FF: synthetic field
   final cv fR;

   cx(cv var1) {
      this.fR = var1;
   }

   public String getDescription() {
      return "Weapon Export File";
   }

   public boolean accept(File var1) {
      if (var1.isDirectory()) {
         return !var1.isHidden();
      } else {
         return var1.getName().endsWith(".wp0");
      }
   }
}
