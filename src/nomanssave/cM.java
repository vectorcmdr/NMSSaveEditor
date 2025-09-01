package nomanssave;

import java.io.File;
import javax.swing.filechooser.FileFilter;

class cM extends FileFilter {
   // $FF: synthetic field
   final cK gl;

   cM(cK var1) {
      this.gl = var1;
   }

   public String getDescription() {
      return "JSON File";
   }

   public boolean accept(File var1) {
      if (var1.isDirectory()) {
         return !var1.isHidden();
      } else {
         return var1.getName().endsWith(".json");
      }
   }
}
