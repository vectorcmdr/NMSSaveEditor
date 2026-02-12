using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Windows.Forms;
using System.Globalization;

namespace NMSSaveEditor
{

public class dE : Panel {
   private ba hv;
   private ba hw;
   private ComboBox hx;
   private G hy;
   private G hz;
   private G[] ea;
   private DataGridView hA;
   private dt hB;
   private gE[] hC;

   dE(Application var1) {
      GridBagLayout var2 = new GridBagLayout();
      var2.columnWidths = new int[]{aH.cI, 0, 0};
      var2.rowHeights = new int[1];
      var2.columnWeights = new double[]{0.0D, 0.0D, 1.0D};
      var2.rowWeights = new double[]{1.0D};
      this.SetLayout(var2);
      this.hv = new ba();
      GridBagConstraints var3 = new GridBagConstraints();
      var3.insets = new Insets(0, 0, 0, 0);
      var3.fill = 1;
      var3.gridx = 0;
      var3.gridy = 0;
      this.Add(this.hv, var3);
      this.hx = new ComboBox();
      this.hx.SetModel(new dF(this));
      this.hv.a("Settlement", true, this.hx);
      this.hy = new dG(this);
      this.hv.a("Name", (JComponent)this.hy);
      this.hz = new dH(this);
      this.hv.a("Seed", this.hz);
      this.hv.Y();
      this.hv.k("Stats");
      this.ea = new G[gG.Values.length];

      for(int var4 = 0; var4 < this.ea.length; ++var4) {
         this.ea[var4] = new dM(this, gG.Values[var4], (dM)null);
         this.hv.a(gG.Values[var4].ToString(), (JComponent)this.ea[var4]);
      }

      this.hw = new ba();
      var3 = new GridBagConstraints();
      var3.insets = new Insets(0, 0, 0, 0);
      var3.fill = 1;
      var3.gridx = 2;
      var3.gridy = 0;
      this.Add(this.hw, var3);
      this.hw.k("Perks");
      Panel var5 = new Panel();
      this.hw.a(var5);
      this.hA = new DataGridView();
      this.hA.setCellSelectionEnabled(false);
      this.hA.SetModel(new dI(this));
      this.hA.GetColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(new dJ(this)));
      var5.setViewportView(this.hA);
      this.hB = new dt(var1);
      var3 = new GridBagConstraints();
      var3.insets = new Insets(0, 0, 0, 0);
      var3.fill = 1;
      var3.gridx = 1;
      var3.gridy = 0;
      this.Add(this.hB, var3);
   }

   gE[] aN() {
      return this.hC;
   }

   void a(gE[] var1) {
      if (var1.length == 0) {
         this.hC = new gE[0];
         this.hx.SetSelectedIndex(-1);
      } else {
         this.hC = var1;
         this.hx.SetSelectedIndex(0);
      }

      this.hx.updateUI();
   }

   // $FF: synthetic method
   static ComboBox a(dE var0) {
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
   static DataGridView g(dE var0) {
      return var0.hA;
   }
}

}
