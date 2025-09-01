package nomanssave;

import java.io.File;
import javax.swing.Icon;
import javax.swing.filechooser.FileView;

class ct extends FileView {
   // $FF: synthetic field
   final cs fP;

   ct(cs var1) {
      this.fP = var1;
   }

   public Icon getIcon(File var1) {
      String var2 = var1.getName();
      return (Icon)(var2.endsWith(".fb3") ? cs.as() : super.getIcon(var1));
   }

   public String getName(File var1) {
      String var2 = var1.getName();
      return var2.endsWith(".fb3") ? var2.substring(0, var2.length() - 4) : var2;
   }
}
