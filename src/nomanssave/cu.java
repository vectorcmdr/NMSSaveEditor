package nomanssave;

import java.io.File;
import javax.swing.filechooser.FileFilter;

class cu extends FileFilter {
   // $FF: synthetic field
   final cs fP;

   cu(cs var1) {
      this.fP = var1;
   }

   public String getDescription() {
      return "Freighter Backup File";
   }

   public boolean accept(File var1) {
      if (var1.isDirectory()) {
         return !var1.isHidden();
      } else {
         return var1.getName().endsWith(".fb3");
      }
   }
}
