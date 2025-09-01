package nomanssave;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class cl extends JFileChooser {
   private static cl fG = null;
   private static final String name = "Planetary Base Backup File";
   private static final ImageIcon fH = Application.a("UI-BASEICON.PNG", 16, 16);

   public static cl ar() {
      if (fG == null) {
         fG = new cl();
      }

      return fG;
   }

   private cl() {
      this.setFileSelectionMode(0);
      this.setAcceptAllFileFilterUsed(false);
      this.setFileView(new cm(this));
      this.setFileFilter(new cn(this));
      this.addChoosableFileFilter(new co(this));
      this.setDialogTitle("Choose Backup File");
      UIManager.addPropertyChangeListener((var1) -> {
         if ("lookAndFeel".equals(var1.getPropertyName())) {
            SwingUtilities.updateComponentTreeUI(this);
         }

      });
   }

   // $FF: synthetic method
   static ImageIcon as() {
      return fH;
   }
}
