package nomanssave;

import java.io.File;
import javax.swing.filechooser.FileFilter;

class cn extends FileFilter {
   // $FF: synthetic field
   final cl fI;

   cn(cl var1) {
      this.fI = var1;
   }

   public String getDescription() {
      return "Planetary Base Backup File";
   }

   public boolean accept(File var1) {
      if (var1.isDirectory()) {
         return !var1.isHidden();
      } else {
         return var1.getName().endsWith(".pb3");
      }
   }
}
