package nomanssave;

import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class cs extends JFileChooser {
   private static cs fN = null;
   private static final String name = "Freighter Backup File";
   private static final ImageIcon fH = Application.a("UI-FREIGHTERICON.PNG", 16, 16);
   private JCheckBox fO;

   public static cs av() {
      if (fN == null) {
         fN = new cs();
      }

      return fN;
   }

   private cs() {
      this.setFileSelectionMode(0);
      this.setAcceptAllFileFilterUsed(false);
      this.setFileView(new ct(this));
      this.setFileFilter(new cu(this));
      this.setDialogTitle("Choose Backup File");
      JPanel var1 = new JPanel();
      var1.setLayout(new BoxLayout(var1, 1));
      var1.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 2));
      var1.add(new JLabel("Export Options:"));
      this.fO = new JCheckBox("Products/Substances");
      var1.add(this.fO);
      this.setAccessory(var1);
      UIManager.addPropertyChangeListener((var1x) -> {
         if ("lookAndFeel".equals(var1x.getPropertyName())) {
            SwingUtilities.updateComponentTreeUI(this);
         }

      });
   }

   public boolean aw() {
      return this.fO.isSelected();
   }

   public int showSaveDialog(Component var1) {
      this.getAccessory().setVisible(true);
      return super.showSaveDialog(var1);
   }

   public int showOpenDialog(Component var1) {
      this.getAccessory().setVisible(false);
      return super.showOpenDialog(var1);
   }

   // $FF: synthetic method
   static ImageIcon as() {
      return fH;
   }
}
