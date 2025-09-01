package nomanssave;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class cv extends JFileChooser {
   private static cv fQ = null;
   private static final String name = "Weapon Export File";
   private static final ImageIcon fH = Application.a("UI-WEAPONICON.PNG", 16, 16);

   public static cv ax() {
      if (fQ == null) {
         fQ = new cv();
      }

      return fQ;
   }

   private cv() {
      this.setFileSelectionMode(0);
      this.setAcceptAllFileFilterUsed(false);
      this.setFileView(new cw(this));
      this.setFileFilter(new cx(this));
      this.setDialogTitle("Choose Weapon Export File");
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
