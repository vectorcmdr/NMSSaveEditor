package nomanssave;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.UIManager;
import javax.swing.border.Border;

class bS extends JPanel {
   private final int x;
   private final int y;
   private JCheckBoxMenuItem eY;
   private JMenuItem eZ;
   private JMenuItem fa;
   private JMenuItem fb;
   private JCheckBoxMenuItem fc;
   private JMenuItem fd;
   private JMenuItem fe;
   private JMenuItem ff;
   private JMenuItem fg;
   private JMenuItem fh;
   private JMenuItem fi;
   private JMenuItem fj;
   // $FF: synthetic field
   final bO eX;

   private bS(bO var1, int var2, int var3) {
      this.eX = var1;
      this.x = var2;
      this.y = var3;
      this.setLayout(new GridBagLayout());
      JPopupMenu var4 = new JPopupMenu();
      this.eY = new JCheckBoxMenuItem("Enabled");
      this.eY.addActionListener(new bT(this, var2, var3));
      this.eY.setEnabled(bO.a(var1).dp() || en.aS());
      var4.add(this.eY);
      this.eZ = new JMenuItem("Enable All Slots");
      this.eZ.addActionListener(new bY(this));
      this.eZ.setEnabled(bO.a(var1).dp() || en.aS());
      var4.add(this.eZ);
      this.fa = new JMenuItem("Repair Slot");
      this.fa.addActionListener(new bZ(this, var2, var3));
      this.fa.setVisible(bO.a(var1).dq());
      var4.add(this.fa);
      this.fb = new JMenuItem("Repair All Slots");
      this.fb.addActionListener(new ca(this));
      this.fb.setVisible(bO.a(var1).dq());
      var4.add(this.fb);
      this.fc = new JCheckBoxMenuItem("Supercharged");
      this.fc.addActionListener(new cb(this, var2, var3));
      this.fc.setVisible(bO.a(var1).do());
      var4.add(this.fc);
      this.fd = new JMenuItem("Supercharge All Slots");
      this.fd.addActionListener(new cc(this));
      this.fd.setVisible(bO.a(var1).do());
      var4.add(this.fd);
      var4.addSeparator();
      this.fe = new JMenuItem("Item Details");
      this.fe.addActionListener(new cd(this, var2, var3));
      var4.add(this.fe);
      this.ff = new JMenuItem("Add Item");
      this.ff.addActionListener(new ce(this, var2, var3));
      var4.add(this.ff);
      this.fg = new JMenuItem("Repair Item");
      this.fg.addActionListener(new cf(this, var2, var3));
      var4.add(this.fg);
      this.fh = new JMenuItem("Move Item");
      this.fh.addActionListener(new bU(this, var2, var3));
      var4.add(this.fh);
      this.fi = new JMenuItem("Fill Stack");
      this.fi.addActionListener(new bV(this, var2, var3));
      var4.add(this.fi);
      this.fj = new JMenuItem("Delete Item");
      this.fj.addActionListener(new bW(this, var2, var3));
      var4.add(this.fj);
      this.setComponentPopupMenu(var4);
      this.setBorder(bO.eP);
      this.addMouseListener(new bX(this, var2, var3));
      this.aq();
   }

   private boolean ao() {
      return bO.a(this.eX).h(this.x, this.y);
   }

   private boolean ap() {
      return bO.a(this.eX).l(this.x, this.y);
   }

   private void aq() {
      this.removeAll();
      this.eY.setEnabled(bO.a(this.eX).dp() || en.aS());
      this.eZ.setEnabled(bO.a(this.eX).dp() || en.aS());
      this.fa.setVisible(bO.a(this.eX).dq());
      this.fb.setVisible(bO.a(this.eX).dq());
      if (!bO.a(this.eX).h(this.x, this.y)) {
         this.eY.setSelected(false);
         this.fa.setEnabled(false);
         this.fe.setVisible(false);
         this.fg.setVisible(false);
         this.ff.setVisible(true);
         this.ff.setEnabled(false);
         this.fh.setVisible(false);
         this.fi.setVisible(false);
         this.fj.setVisible(false);
         this.fc.setVisible(false);
         this.setBorder(bO.eP);
         this.setBackground(bO.ag());
         this.setToolTipText((String)null);
      } else {
         gu var1;
         ey var2;
         boolean var3;
         String var4;
         if (bO.a(this.eX).l(this.x, this.y)) {
            this.eY.setSelected(true);
            this.fa.setEnabled(true);
            this.fe.setVisible(false);
            this.fg.setVisible(false);
            this.ff.setVisible(true);
            this.ff.setEnabled(false);
            this.fh.setVisible(false);
            this.fi.setVisible(false);
            this.fj.setVisible(false);
            this.fc.setVisible(bO.a(this.eX).do());
            if (bO.a(this.eX).k(this.x, this.y)) {
               this.setBorder(bO.ah());
               this.fc.setState(true);
            } else {
               this.setBorder(bO.eP);
               this.fc.setState(false);
            }

            this.setBackground(bO.ai());
            var1 = bO.a(this.eX).f(this.x, this.y);
            if (var1 == null) {
               this.setToolTipText((String)null);
            } else {
               var2 = ey.d(var1.dz());
               var3 = var2 instanceof eQ && var1.dC() != 0.0D;
               var4 = var2 == null ? bO.b(var1.dz()) : var2.getName();
               int var5 = UIManager.getInt("Inventory.iconSize");
               ImageIcon var6 = var2 == null ? null : var2.c(var5, var5);
               int var7 = 0;
               if (var6 != null) {
                  this.a(var6, var5, var7++);
               }

               Color var8 = var3 ? bO.aj() : bO.eO;
               this.a(var4, var7++, var8);
               this.a(var1.dA() < 0 ? "" : var1.dA() + "/" + var1.dB(), var7++, var8);
               this.setToolTipText(var4);
            }
         } else {
            this.eY.setSelected(true);
            this.fa.setEnabled(false);
            this.fc.setVisible(bO.a(this.eX).do());
            if (bO.a(this.eX).k(this.x, this.y)) {
               this.setBorder(bO.ah());
               this.fc.setState(true);
            } else {
               this.setBorder(bO.eP);
               this.fc.setState(false);
            }

            var1 = bO.a(this.eX).f(this.x, this.y);
            if (var1 == null) {
               this.fe.setVisible(false);
               this.fg.setVisible(false);
               this.ff.setVisible(true);
               this.ff.setEnabled(true);
               this.fh.setVisible(false);
               this.fi.setVisible(false);
               this.fj.setVisible(false);
               this.setBackground(bO.eK);
               this.setToolTipText((String)null);
            } else {
               var2 = ey.d(var1.dz());
               var3 = var2 instanceof eQ && var1.dC() != 0.0D;
               this.fe.setVisible(true);
               this.fg.setVisible(var3);
               this.ff.setVisible(false);
               this.ff.setEnabled(false);
               this.fh.setVisible(true);
               this.fi.setVisible(false);
               this.fj.setVisible(true);
               var4 = var1.getType();
               if (var4.equals("Technology")) {
                  this.setBackground(bO.ak());
                  if (var1.dA() >= 0 && var1.dA() < var1.dB()) {
                     this.fi.setText("Recharge");
                     this.fi.setVisible(true);
                  }
               } else if (var4.equals("Product")) {
                  this.setBackground(bO.al());
                  if (var1.dB() > 1) {
                     this.fi.setText("Fill Stack");
                     this.fi.setVisible(true);
                  }
               } else if (var4.equals("Substance")) {
                  this.setBackground(bO.am());
                  if (var1.dB() > 1) {
                     this.fi.setText("Fill Stack");
                     this.fi.setVisible(true);
                  }
               } else {
                  this.setBackground(bO.an());
               }

               this.fe.setEnabled(var2 != null);
               String var10 = var2 == null ? bO.b(var1.dz()) : var2.getName();
               int var11 = UIManager.getInt("Inventory.iconSize");
               ImageIcon var13 = var2 == null ? null : var2.c(var11, var11);
               int var12 = 0;
               if (var13 != null) {
                  this.a(var13, var11, var12++);
               }

               Color var9 = var3 ? bO.aj() : bO.eO;
               this.a(var10, var12++, var9);
               this.a(var1.dA() < 0 ? " " : var1.dA() + "/" + var1.dB(), var12++, var9);
               this.setToolTipText(var10);
            }
         }
      }

      this.revalidate();
      this.updateUI();
   }

   private void a(ImageIcon var1, int var2, int var3) {
      JLabel var4 = new JLabel(var1);
      var4.setPreferredSize(new Dimension(var2, var2));
      GridBagConstraints var5 = new GridBagConstraints();
      var5.anchor = 10;
      var5.fill = 0;
      var5.insets = new Insets(5, 0, 5, 0);
      var5.gridx = 0;
      var5.gridy = var3;
      this.add(var4, var5);
   }

   private void a(String var1, int var2, Color var3) {
      JLabel var4 = new JLabel();
      var4.setFont(UIManager.getFont("Inventory.font"));
      var4.setBackground((Color)null);
      var4.setBorder((Border)null);
      var4.setText(var1);
      var4.setForeground(var3);
      GridBagConstraints var5 = new GridBagConstraints();
      var5.anchor = 10;
      var5.fill = 0;
      int var6 = UIManager.getInt("Inventory.iconSize");
      var5.insets = new Insets(var2 == 0 ? var6 + 10 : 0, 0, 0, 0);
      var5.gridx = 0;
      var5.gridy = var2;
      this.add(var4, var5);
   }

   // $FF: synthetic method
   static JCheckBoxMenuItem b(bS var0) {
      return var0.eY;
   }

   // $FF: synthetic method
   static void c(bS var0) {
      var0.aq();
   }

   // $FF: synthetic method
   static JCheckBoxMenuItem d(bS var0) {
      return var0.fc;
   }

   // $FF: synthetic method
   static boolean e(bS var0) {
      return var0.ao();
   }

   // $FF: synthetic method
   static boolean f(bS var0) {
      return var0.ap();
   }

   // $FF: synthetic method
   static JMenuItem g(bS var0) {
      return var0.eZ;
   }

   // $FF: synthetic method
   bS(bO var1, int var2, int var3, bS var4) {
      this(var1, var2, var3);
   }

   // $FF: synthetic method
   static int h(bS var0) {
      return var0.x;
   }

   // $FF: synthetic method
   static int i(bS var0) {
      return var0.y;
   }

   // $FF: synthetic method
   static bO j(bS var0) {
      return var0.eX;
   }
}
