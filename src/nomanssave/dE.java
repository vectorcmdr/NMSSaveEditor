package nomanssave;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class dE extends JPanel {
   private ba hv;
   private ba hw;
   private JComboBox hx;
   private G hy;
   private G hz;
   private G[] ea;
   private JTable hA;
   private dt hB;
   private gE[] hC;

   dE(Application var1) {
      GridBagLayout var2 = new GridBagLayout();
      var2.columnWidths = new int[]{aH.cI, 0, 0};
      var2.rowHeights = new int[1];
      var2.columnWeights = new double[]{0.0D, 0.0D, 1.0D};
      var2.rowWeights = new double[]{1.0D};
      this.setLayout(var2);
      this.hv = new ba();
      GridBagConstraints var3 = new GridBagConstraints();
      var3.insets = new Insets(0, 0, 0, 0);
      var3.fill = 1;
      var3.gridx = 0;
      var3.gridy = 0;
      this.add(this.hv, var3);
      this.hx = new JComboBox();
      this.hx.setModel(new dF(this));
      this.hv.a("Settlement", true, this.hx);
      this.hy = new dG(this);
      this.hv.a("Name", (JComponent)this.hy);
      this.hz = new dH(this);
      this.hv.a("Seed", this.hz);
      this.hv.Y();
      this.hv.k("Stats");
      this.ea = new G[gG.values().length];

      for(int var4 = 0; var4 < this.ea.length; ++var4) {
         this.ea[var4] = new dM(this, gG.values()[var4], (dM)null);
         this.hv.a(gG.values()[var4].toString(), (JComponent)this.ea[var4]);
      }

      this.hw = new ba();
      var3 = new GridBagConstraints();
      var3.insets = new Insets(0, 0, 0, 0);
      var3.fill = 1;
      var3.gridx = 2;
      var3.gridy = 0;
      this.add(this.hw, var3);
      this.hw.k("Perks");
      JScrollPane var5 = new JScrollPane();
      this.hw.a(var5);
      this.hA = new JTable();
      this.hA.setCellSelectionEnabled(false);
      this.hA.setModel(new dI(this));
      this.hA.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(new dJ(this)));
      var5.setViewportView(this.hA);
      this.hB = new dt(var1);
      var3 = new GridBagConstraints();
      var3.insets = new Insets(0, 0, 0, 0);
      var3.fill = 1;
      var3.gridx = 1;
      var3.gridy = 0;
      this.add(this.hB, var3);
   }

   gE[] aN() {
      return this.hC;
   }

   void a(gE[] var1) {
      if (var1.length == 0) {
         this.hC = new gE[0];
         this.hx.setSelectedIndex(-1);
      } else {
         this.hC = var1;
         this.hx.setSelectedIndex(0);
      }

      this.hx.updateUI();
   }

   // $FF: synthetic method
   static JComboBox a(dE var0) {
      return var0.hx;
   }

   // $FF: synthetic method
   static gE[] b(dE var0) {
      return var0.hC;
   }

   // $FF: synthetic method
   static G c(dE var0) {
      return var0.hy;
   }

   // $FF: synthetic method
   static G d(dE var0) {
      return var0.hz;
   }

   // $FF: synthetic method
   static G[] e(dE var0) {
      return var0.ea;
   }

   // $FF: synthetic method
   static dt f(dE var0) {
      return var0.hB;
   }

   // $FF: synthetic method
   static JTable g(dE var0) {
      return var0.hA;
   }
}
