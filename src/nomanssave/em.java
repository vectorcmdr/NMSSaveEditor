package nomanssave;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class em extends JPanel {
   private final ba it;

   em() {
      GridBagLayout var1 = new GridBagLayout();
      var1.columnWidths = new int[]{aH.cI, 0, 0};
      var1.rowHeights = new int[1];
      var1.columnWeights = new double[]{0.0D, 1.0D, Double.MIN_VALUE};
      var1.rowWeights = new double[]{1.0D};
      this.setLayout(var1);
      this.it = new ba();
      GridBagConstraints var2 = new GridBagConstraints();
      var2.insets = new Insets(0, 0, 0, 0);
      var2.fill = 1;
      var2.gridx = 0;
      var2.gridy = 0;
      this.add(this.it, var2);
   }

   void b(JComponent var1) {
      GridBagConstraints var2 = new GridBagConstraints();
      var2.fill = 1;
      var2.gridx = 1;
      var2.gridy = 0;
      this.add(var1, var2);
   }

   void k(String var1) {
      this.it.k(var1);
   }

   void a(String var1, G var2) {
      this.it.a(var1, var2);
   }

   void a(String var1, JComponent var2) {
      this.it.a(var1, var2);
   }

   void a(String var1, boolean var2, JComponent var3) {
      this.it.a(var1, var2, var3);
   }

   void a(JComponent var1) {
      this.it.a(var1);
   }

   void Y() {
      this.it.Y();
   }
}
