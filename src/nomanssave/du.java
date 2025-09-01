package nomanssave;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.UIManager;
import javax.swing.border.Border;

class du extends JPanel {
   final gF hm;
   private JMenuItem fh;
   private JMenuItem hn;
   private JMenuItem fe;
   // $FF: synthetic field
   final dt ho;

   private du(dt var1, gF var2) {
      this.ho = var1;
      this.hm = var2;
      int var3 = UIManager.getInt("Inventory.gridSize");
      Dimension var4 = new Dimension(var3, var3);
      this.setBackground(bO.eK);
      this.setMinimumSize(var4);
      this.setMaximumSize(var4);
      this.setPreferredSize(var4);
      this.setLayout(new GridBagLayout());
      this.setBorder(bO.eP);
      JPopupMenu var5 = new JPopupMenu();
      this.fe = new JMenuItem("Item Details");
      this.fe.addActionListener(new dv(this, var2));
      var5.add(this.fe);
      this.hn = new JMenuItem("Change Item");
      this.hn.addActionListener(new dw(this, var2));
      var5.add(this.hn);
      this.fh = new JMenuItem("Move Item");
      this.fh.addActionListener(new dx(this, var2));
      var5.add(this.fh);
      this.setComponentPopupMenu(var5);
      this.addMouseListener(new dy(this, var2));
      this.aM();
   }

   private void aM() {
      this.removeAll();
      int var1 = UIManager.getInt("Inventory.gridSize");
      Dimension var2 = new Dimension(var1, var1);
      this.setBackground(bO.eK);
      this.setMinimumSize(var2);
      this.setMaximumSize(var2);
      this.setPreferredSize(var2);
      if (this.hm != null && this.hm.isValid()) {
         this.fe.setEnabled(true);
         this.hn.setEnabled(true);
         this.fh.setEnabled(this.hm.dA() > 0);
         ey var3 = ey.d(this.hm.dz());
         String var4 = var3 == null ? this.hm.ei() : var3.getName();
         int var5 = UIManager.getInt("Inventory.iconSize");
         Font var6 = UIManager.getFont("Inventory.font");
         ImageIcon var7 = var3 == null ? null : var3.c(var5, var5);
         int var8 = 0;
         JLabel var9;
         GridBagConstraints var10;
         if (var7 != null) {
            var9 = new JLabel(var7);
            var9.setPreferredSize(new Dimension(var5, var5));
            var10 = new GridBagConstraints();
            var10.anchor = 10;
            var10.fill = 0;
            var10.insets = new Insets(5, 0, 5, 0);
            var10.gridx = 0;
            var10.gridy = var8++;
            this.add(var9, var10);
         }

         var9 = new JLabel();
         var9.setFont(var6);
         var9.setBackground((Color)null);
         var9.setBorder((Border)null);
         var9.setText(var4);
         var9.setForeground(bO.eO);
         var10 = new GridBagConstraints();
         var10.anchor = 10;
         var10.fill = 0;
         var10.insets = new Insets(var8 == 0 ? var5 + 10 : 0, 0, 0, 0);
         var10.gridx = 0;
         var10.gridy = var8++;
         this.add(var9, var10);
         var9 = new JLabel();
         var9.setFont(var6);
         var9.setBackground((Color)null);
         var9.setBorder((Border)null);
         var9.setText(Integer.toString(this.hm.dA()) + "/" + Integer.toString(this.hm.dB()));
         var9.setForeground(bO.eO);
         var10 = new GridBagConstraints();
         var10.anchor = 10;
         var10.fill = 0;
         var10.insets = new Insets(0, 0, 0, 0);
         var10.gridx = 0;
         var10.gridy = var8++;
         this.add(var9, var10);
      } else {
         this.fe.setEnabled(false);
         this.hn.setEnabled(false);
         this.fh.setEnabled(false);
      }

      this.revalidate();
      this.updateUI();
   }

   // $FF: synthetic method
   static void c(du var0) {
      var0.aM();
   }

   // $FF: synthetic method
   du(dt var1, gF var2, du var3) {
      this(var1, var2);
   }

   // $FF: synthetic method
   static dt d(du var0) {
      return var0.ho;
   }
}
