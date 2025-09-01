package nomanssave;

import java.io.File;
import javax.swing.Icon;
import javax.swing.filechooser.FileView;

class cq extends FileView {
   // $FF: synthetic field
   final cp fM;

   cq(cp var1) {
      this.fM = var1;
   }

   public Icon getIcon(File var1) {
      String var2 = var1.getName();
      if (var2.endsWith(".pet")) {
         return cp.as();
      } else {
         return (Icon)(var2.endsWith(".egg") ? cp.au() : super.getIcon(var1));
      }
   }

   public String getName(File var1) {
      String var2 = var1.getName();
      if (var2.endsWith(".pet")) {
         return var2.substring(0, var2.length() - 4);
      } else {
         return var2.endsWith(".egg") ? var2.substring(0, var2.length() - 4) : var2;
      }
   }
}
