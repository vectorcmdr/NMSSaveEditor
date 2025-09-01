package nomanssave;

import java.io.File;
import javax.swing.Icon;
import javax.swing.filechooser.FileView;

class cm extends FileView {
   // $FF: synthetic field
   final cl fI;

   cm(cl var1) {
      this.fI = var1;
   }

   public Icon getIcon(File var1) {
      String var2 = var1.getName();
      return (Icon)(!var2.endsWith(".pb3") && !var2.endsWith(".pb0") ? super.getIcon(var1) : cl.as());
   }

   public String getName(File var1) {
      String var2 = var1.getName();
      return !var2.endsWith(".pb3") && !var2.endsWith(".pb0") ? var2 : var2.substring(0, var2.length() - 4);
   }
}
