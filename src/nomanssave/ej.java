package nomanssave;

import java.awt.Component;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class ej extends JFileChooser {
   private static final ImageIcon im = Application.a("UI-FILEICON.PNG", 16, 16);
   private static final ImageIcon io = Application.a("UI-GAMEPASS.PNG", 16, 16);
   private static final ImageIcon ip = Application.a("UI-STEAMLOGO.PNG", 16, 16);
   private static final Pattern iq = Pattern.compile("st_(\\d*)");
   private static ej ir = null;

   private ej() {
      this.setFileSelectionMode(2);
      this.setAcceptAllFileFilterUsed(false);
      this.setFileFilter(new ek(this));
      this.setFileView(new el(this));
      this.setDialogTitle("Choose Save Path");
      UIManager.addPropertyChangeListener((var1) -> {
         if ("lookAndFeel".equals(var1.getPropertyName())) {
            SwingUtilities.updateComponentTreeUI(this);
         }

      });
   }

   private String a(File var1) {
      Matcher var2 = iq.matcher(var1.getName());
      if (var2.matches()) {
         long var3 = Long.parseLong(var2.group(1));
         return hi.h(var3);
      } else {
         return null;
      }
   }

   public static File b(File var0) {
      if (ir == null) {
         ir = new ej();
      }

      if (var0 != null && var0.exists()) {
         if (var0.isFile()) {
            var0 = var0.getParentFile();
         }

         ir.setCurrentDirectory(var0);
      } else {
         File var1 = new File(System.getProperty("user.home"));
         File var2 = new File(var1, "AppData\\Roaming\\HelloGames\\NMS");
         File var3 = new File(var1, "AppData\\Local\\Packages\\HelloGames.NoMansSky_bs190hzg1sesy\\SystemAppData");
         if (var2.isDirectory()) {
            ir.setCurrentDirectory(var2);
         } else if (var3.isDirectory()) {
            ir.setCurrentDirectory(var3);
         } else {
            ir.setCurrentDirectory(var1);
         }
      }

      return ir.showOpenDialog((Component)null) == 0 ? ir.getSelectedFile() : null;
   }

   // $FF: synthetic method
   static ImageIcon as() {
      return im;
   }

   // $FF: synthetic method
   static ImageIcon au() {
      return io;
   }

   // $FF: synthetic method
   static String a(ej var0, File var1) {
      return var0.a(var1);
   }

   // $FF: synthetic method
   static ImageIcon aR() {
      return ip;
   }
}
