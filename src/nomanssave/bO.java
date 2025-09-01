package nomanssave;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Collections;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class bO extends JPanel implements eo {
   private static final Color eE;
   private static final Color eF;
   private static final Color eG;
   private static final Color eH;
   private static final Color eI;
   private static final Color eJ;
   public static final Color eK;
   private static final Color eL;
   private static final Color eM;
   private static final Color eN;
   public static final Color eO;
   public static final Border eP;
   private static final Border eQ;
   private final Application eR;
   private final JPanel eS;
   private final JComboBox eT;
   private final JButton eU;
   private List eV;
   private gt eW;

   static {
      eE = Color.GRAY;
      eF = new Color(255, 240, 240);
      eG = new Color(255, 255, 240);
      eH = new Color(240, 255, 250);
      eI = new Color(240, 250, 255);
      eJ = new Color(240, 255, 255);
      eK = Color.WHITE;
      eL = Color.BLACK;
      eM = Color.YELLOW;
      eN = Color.RED;
      eO = Color.BLACK;
      eP = BorderFactory.createLineBorder(eL, 1);
      eQ = BorderFactory.createCompoundBorder(eP, BorderFactory.createLineBorder(eM, 2));
   }

   bO(Application var1) {
      this.eR = var1;
      this.setLayout(new BorderLayout());
      JPanel var2 = new JPanel();
      var2.setLayout(new FlowLayout());
      this.eS = new JPanel();
      this.eS.setLayout(new GridBagLayout());
      int var3 = UIManager.getInt("Inventory.gridSize");
      this.setPreferredSize(new Dimension(var3 * 10 + 20, var3 * 8 + 50));
      this.add(var2, "North");
      JScrollPane var4 = new JScrollPane();
      var4.setViewportView(this.eS);
      var4.setBorder(new LineBorder(eL));
      this.add(var4, "Center");
      this.eV = Collections.emptyList();
      this.eT = new JComboBox();
      this.eT.setVisible(false);
      this.eT.setModel(new bP(this));
      this.eT.setRenderer(new bQ(this));
      var2.add(this.eT);
      this.eU = new JButton("Resize Inventory");
      this.eU.setVisible(false);
      this.eU.addActionListener(new bR(this));
      var2.add(this.eU);
      en.a(this);
      UIManager.addPropertyChangeListener((var1x) -> {
         if ("lookAndFeel".equals(var1x.getPropertyName())) {
            EventQueue.invokeLater(this::af);
         }

      });
   }

   public void a(boolean var1) {
      this.eU.setVisible(this.eW == null ? false : var1 || this.eW.dk());
      boolean var2 = this.eW == null ? false : var1 || this.eW.dp();
      synchronized(this.eS.getTreeLock()) {
         for(int var4 = 0; var4 < this.eS.getComponentCount(); ++var4) {
            Component var5 = this.eS.getComponent(var4);
            if (var5 instanceof bS) {
               bS var6 = (bS)var5;
               bS.b(var6).setEnabled(var2);
               bS.g(var6).setEnabled(var2);
            }
         }

      }
   }

   void a(gt var1) {
      if (this.eW == var1) {
         this.af();
      }

   }

   void w() {
      this.eV.stream().forEach((var1) -> {
         if (var1.dt() && this.eW == var1) {
            this.af();
         }

      });
   }

   void x() {
      this.eV.stream().forEach((var1) -> {
         if (var1.du() && this.eW == var1) {
            this.af();
         }

      });
   }

   void y() {
      this.eV.stream().forEach((var1) -> {
         if (var1.dp() && var1.dv() && this.eW == var1) {
            this.af();
         }

      });
   }

   void z() {
      this.eV.stream().forEach((var1) -> {
         if (var1.dq() && var1.ds() && this.eW == var1) {
            this.af();
         }

      });
   }

   void A() {
      this.eV.stream().forEach((var1) -> {
         if (var1.dk() && var1.dl() && this.eW == var1) {
            this.af();
         }

      });
   }

   void ae() {
      int var1 = this.eT.getSelectedIndex();
      if (var1 >= 0) {
         this.eW = (gt)this.eV.get(var1);
         this.af();
      }

   }

   private void af() {
      synchronized(this.eS.getTreeLock()) {
         this.eS.removeAll();
         if (this.eW != null) {
            int var2 = UIManager.getInt("Inventory.gridSize");
            Dimension var3 = new Dimension(var2, var2);

            for(int var4 = 0; var4 < this.eW.getHeight(); ++var4) {
               for(int var5 = 0; var5 < this.eW.getWidth(); ++var5) {
                  bS var6 = new bS(this, var5, var4, (bS)null);
                  var6.setMinimumSize(var3);
                  var6.setMaximumSize(var3);
                  var6.setPreferredSize(var3);
                  GridBagConstraints var7 = new GridBagConstraints();
                  var7.fill = 1;
                  var7.insets = new Insets(-1, -1, 0, 0);
                  var7.gridx = var5;
                  var7.gridy = var4;
                  this.eS.add(var6, var7);
               }
            }
         }
      }

      this.eS.revalidate();
      this.eS.updateUI();
   }

   void a(List var1) {
      this.eV = var1;
      this.eW = null;
      this.eT.updateUI();
      if (this.eV.size() == 0) {
         this.eT.setVisible(false);
         this.eU.setVisible(false);
         this.af();
      } else {
         this.eT.setVisible(this.eV.size() != 1);
         this.eU.setVisible(false);
         this.eT.setSelectedIndex(0);
      }

   }

   private bS a(int var1, int var2) {
      synchronized(this.eS.getTreeLock()) {
         for(int var4 = 0; var4 < this.eS.getComponentCount(); ++var4) {
            Component var5 = this.eS.getComponent(var4);
            if (var5 instanceof bS) {
               bS var6 = (bS)var5;
               if (bS.h(var6) == var1 && bS.i(var6) == var2) {
                  return var6;
               }
            }
         }

         return null;
      }
   }

   private void a(bS var1) {
      ey var2 = h.a(this, this.eW.dj());
      if (var2 != null) {
         this.eW.a(bS.h(var1), bS.i(var1), var2);
         bS.c(var1);
      }

   }

   private void a(gu var1, bS var2) {
      ey var3 = ey.d(var1.dz());
      int var4;
      if (var3 == null) {
         if ("Product".equals(var1.getType())) {
            var4 = 512;
         } else {
            if (!"Substance".equals(var1.getType())) {
               this.eR.c("Item details not found!");
               return;
            }

            var4 = 1024;
         }
      } else {
         var4 = gt.a(var3.bc());
      }

      List var5 = this.eR.g(var4);
      int var6 = var5.indexOf(this.eW);
      int var7 = dd.a(this, var5, var6);
      if (var7 != var6) {
         gt var8 = (gt)var5.get(var7);
         if (this.eW.a(bS.h(var2), bS.i(var2), var8)) {
            bS.c(var2);
            this.eR.a(var8);
         }
      }

   }

   private static String a(Object var0) {
      return var0 instanceof fg ? "Archived Tech" : var0.toString();
   }

   // $FF: synthetic method
   static gt a(bO var0) {
      return var0.eW;
   }

   // $FF: synthetic method
   static Application b(bO var0) {
      return var0.eR;
   }

   // $FF: synthetic method
   static void c(bO var0) {
      var0.af();
   }

   // $FF: synthetic method
   static void a(bO var0, bS var1) {
      var0.a(var1);
   }

   // $FF: synthetic method
   static void a(bO var0, gu var1, bS var2) {
      var0.a(var1, var2);
   }

   // $FF: synthetic method
   static bS a(bO var0, int var1, int var2) {
      return var0.a(var1, var2);
   }

   // $FF: synthetic method
   static Color ag() {
      return eE;
   }

   // $FF: synthetic method
   static Border ah() {
      return eQ;
   }

   // $FF: synthetic method
   static Color ai() {
      return eF;
   }

   // $FF: synthetic method
   static String b(Object var0) {
      return a(var0);
   }

   // $FF: synthetic method
   static Color aj() {
      return eN;
   }

   // $FF: synthetic method
   static Color ak() {
      return eG;
   }

   // $FF: synthetic method
   static Color al() {
      return eH;
   }

   // $FF: synthetic method
   static Color am() {
      return eI;
   }

   // $FF: synthetic method
   static Color an() {
      return eJ;
   }

   // $FF: synthetic method
   static List d(bO var0) {
      return var0.eV;
   }

   // $FF: synthetic method
   static void a(bO var0, gt var1) {
      var0.eW = var1;
   }

   // $FF: synthetic method
   static JButton e(bO var0) {
      return var0.eU;
   }
}
