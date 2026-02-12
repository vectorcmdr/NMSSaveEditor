using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Windows.Forms;

namespace NMSSaveEditor
{

public class ej : OpenFileDialog {
   private static Image im = Application.a("UI-FILEICON.PNG", 16, 16);
   private static Image io = Application.a("UI-GAMEPASS.PNG", 16, 16);
   private static Image ip = Application.a("UI-STEAMLOGO.PNG", 16, 16);
   private static Pattern iq = Pattern.compile("st_(\\d*)");
   private static ej ir = null;

   private ej() {
      this.setFileSelectionMode(2);
      this.setAcceptAllFileFilterUsed(false);
      this.setFileFilter(new ek(this));
      this.setFileView(new el(this));
      this.setDialogTitle("Choose Save Path");
      SystemInformation.addPropertyChangeListener((var1) => {
         if ("lookAndFeel".Equals(var1.getPropertyName())) {
            Control.updateComponentTreeUI(this);
         }

      });
   }

   private string a(File var1) {
      Matcher var2 = iq.matcher(var1.Name);
      if (var2.Matches()) {
         long var3 = long.Parse(var2.group(1));
         return hi.h(var3);
      } else {
         return null;
      }
   }

   public static File b(File var0) {
      if (ir == null) {
         ir = new ej();
      }

      if (var0 != null && var0.Exists) {
         if (var0.Exists) {
            var0 = var0.Directory;
         }

         ir.setCurrentDirectory(var0);
      } else {
         File var1 = new File(Environment.GetEnvironmentVariable("user.home"));
         File var2 = new File(var1, "AppData\\Roaming\\HelloGames\\NMS");
         File var3 = new File(var1, "AppData\\Local\\Packages\\HelloGames.NoMansSky_bs190hzg1sesy\\SystemAppData");
         if (var2.Attributes.HasFlag(FileAttributes.Directory)) {
            ir.setCurrentDirectory(var2);
         } else if (var3.Attributes.HasFlag(FileAttributes.Directory)) {
            ir.setCurrentDirectory(var3);
         } else {
            ir.setCurrentDirectory(var1);
         }
      }

      return ir.showOpenDialog((Component)null) == 0 ? ir.getSelectedFile() : null;
   }
   static Image as() {
      return im;
   }
   static Image au() {
      return io;
   }
   static string a(ej var0, File var1) {
      return var0.a(var1);
   }
   static Image aR() {
      return ip;
   }
}

}
