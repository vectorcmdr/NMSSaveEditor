using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Windows.Forms;

namespace NMSSaveEditor
{

public class ej : JFileChooser {
   public static Image im = Application.a("UI-FILEICON.PNG", 16, 16);
   public static Image io = Application.a("UI-GAMEPASS.PNG", 16, 16);
   public static Image ip = Application.a("UI-STEAMLOGO.PNG", 16, 16);
   public static Pattern iq = Pattern.compile("st_(\\d*)");
   public static ej ir = null;

   public ej() {
      this.setFileSelectionMode(2);
      this.setAcceptAllFileFilterUsed(false);
      this.setFileFilter(new ek(this));
      this.setFileView(new el(this));
      this.setDialogTitle("Choose Save Path");
      /* TODO: port from Java - addPropertyChangeListener for lookAndFeel */
   }

   public string a(FileInfo var1) {
      Matcher var2 = iq.matcher(var1.Name);
      if (var2.Matches()) {
         long var3 = long.Parse(var2.group(1));
         return hi.h(var3);
      } else {
         return null;
      }
   }

   public static FileInfo b(FileInfo var0) {
      if (ir == null) {
         ir = new ej();
      }

      if (var0 != null && var0.Exists) {
         if (var0.Exists) {
            var0 = var0.Directory;
         }

         ir.setCurrentDirectory(var0);
      } else {
         FileInfo var1 = new FileInfo(Environment.GetEnvironmentVariable("user.home"));
         FileInfo var2 = new FileInfo(var1, "AppData\\Roaming\\HelloGames\\NMS");
         FileInfo var3 = new FileInfo(var1, "AppData\\Local\\Packages\\HelloGames.NoMansSky_bs190hzg1sesy\\SystemAppData");
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
   public static Image @as() {
      return im;
   }
   public static Image au() {
      return io;
   }
   public static string a(ej var0, FileInfo var1) {
      return var0.a(var1);
   }
   public static Image aR() {
      return ip;
   }
}

}
