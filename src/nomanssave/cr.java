package nomanssave;

import java.io.File;
import javax.swing.filechooser.FileFilter;

class cr extends FileFilter {
   // $FF: synthetic field
   final cp fM;

   cr(cp var1) {
      this.fM = var1;
   }

   public String getDescription() {
      return "Companion Export File";
   }

   public boolean accept(File var1) {
      if (var1.isDirectory()) {
         return !var1.isHidden();
      } else {
         return var1.getName().endsWith(".pet") || var1.getName().endsWith(".egg");
      }
   }
}
