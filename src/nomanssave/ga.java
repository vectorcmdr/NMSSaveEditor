package nomanssave;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;

class ga implements FileFilter {
   // $FF: synthetic field
   final fZ nb;
   // $FF: synthetic field
   private final ArrayList mg;

   ga(fZ var1, ArrayList var2) {
      this.nb = var1;
      this.mg = var2;
   }

   public boolean accept(File var1) {
      Matcher var2 = fT.cu().matcher(var1.getName());
      if (var2.matches()) {
         int var3 = var2.group(1).length() == 0 ? 0 : Integer.parseInt(var2.group(1)) - 1;
         if (var3 / 2 == this.nb.lT) {
            try {
               this.mg.add(new fV(fZ.a(this.nb), var1.getName(), var3));
            } catch (IOException var5) {
               hc.a("Cannot load " + var1.getName(), var5);
            }
         }
      }

      return false;
   }
}
