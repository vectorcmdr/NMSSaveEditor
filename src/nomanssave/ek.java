package nomanssave;

import java.io.File;
import javax.swing.filechooser.FileFilter;

class ek extends FileFilter {
   // $FF: synthetic field
   final ej is;

   ek(ej var1) {
      this.is = var1;
   }

   public String getDescription() {
      return "Saved Game";
   }

   public boolean accept(File var1) {
      if (var1.isDirectory()) {
         return true;
      } else {
         String var2 = var1.getName();
         if (var2.endsWith(".hg") && !var2.startsWith("mf_")) {
            return true;
         } else {
            return var2.equals("containers.index");
         }
      }
   }
}
