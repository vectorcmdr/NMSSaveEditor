package nomanssave;

import java.io.File;
import javax.swing.Icon;
import javax.swing.filechooser.FileView;

class cU extends FileView {
   // $FF: synthetic field
   final cT gw;

   cU(cT var1) {
      this.gw = var1;
   }

   public Icon getIcon(File var1) {
      String var2 = var1.getName();
      return (Icon)(var2.endsWith(".sh0") ? cT.as() : super.getIcon(var1));
   }

   public String getName(File var1) {
      String var2 = var1.getName();
      return var2.endsWith(".sh0") ? var2.substring(0, var2.length() - 4) : var2;
   }
}
