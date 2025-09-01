package nomanssave;

import java.io.File;
import javax.swing.Icon;
import javax.swing.filechooser.FileView;

class el extends FileView {
   // $FF: synthetic field
   final ej is;

   el(ej var1) {
      this.is = var1;
   }

   public Icon getIcon(File var1) {
      String var2;
      if (var1.isFile()) {
         var2 = var1.getName();
         if (var2.endsWith(".hg") && !var2.startsWith("mf_")) {
            return ej.as();
         } else {
            return var2.equals("containers.index") ? ej.au() : null;
         }
      } else {
         var2 = ej.a(this.is, var1);
         return var2 == null ? null : ej.aR();
      }
   }

   public String getName(File var1) {
      if (var1.isFile()) {
         return var1.getName();
      } else {
         String var2 = ej.a(this.is, var1);
         return var2 == null ? var1.getName() : "[" + var2 + "] " + var1.getName();
      }
   }
}
