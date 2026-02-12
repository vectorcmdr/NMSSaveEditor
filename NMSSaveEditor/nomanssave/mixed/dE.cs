using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

public class dE : Panel {
   public ba hv;
   public ba hw;
   public ComboBox hx;
   public G hy;
   public G hz;
   public G[] ea;
   public DataGridView hA;
   public dt hB;
   public gE[] hC;

   public dE(Application var1) {
      GridBagLayout var2 = new GridBagLayout();
      var2.columnWidths = new int[]{aH.cI, 0, 0};
      var2.rowHeights = new int[1];
      var2.columnWeights = new double[]{0.0D, 0.0D, 1.0D};
      var2.rowWeights = new double[]{1.0D};
      this.SuspendLayout(); // TODO: set layout var2);
      this.hv = new ba();
      GridBagConstraints var3 = new GridBagConstraints();
      var3.insets = new Padding(0, 0, 0, 0);
      var3.fill = 1;
      var3.gridx = 0;
      var3.gridy = 0;
      this.Add(this.hv, var3);
      this.hx = new ComboBox();
      this.hx.DataSource = (new dF(this));
      this.hv.a("Settlement", true, this.hx);
      this.hy = new dG(this);
      this.hv.a("Name", (Control)this.hy);
      this.hz = new dH(this);
      this.hv.a("Seed", this.hz);
      this.hv.Y();
      this.hv.k("Stats");
      this.ea = new G[gG.Values.Length];

      for(int var4 = 0; var4 < this.ea.Length; ++var4) {
         this.ea[var4] = new dM(this, gG.Values[var4], (dM)null);
         this.hv.a(gG.Values[var4].ToString(), (Control)this.ea[var4]);
      }

      this.hw = new ba();
      var3 = new GridBagConstraints();
      var3.insets = new Padding(0, 0, 0, 0);
      var3.fill = 1;
      var3.gridx = 2;
      var3.gridy = 0;
      this.Add(this.hw, var3);
      this.hw.k("Perks");
      Panel var5 = new Panel();
      this.hw.a(var5);
      this.hA = new DataGridView();
      this.hA.setCellSelectionEnabled(false);
      this.hA.DataSource = (new dI(this));
      this.hA.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(new dJ(this)));
      var5.setViewportView(this.hA);
      this.hB = new dt(var1);
      var3 = new GridBagConstraints();
      var3.insets = new Padding(0, 0, 0, 0);
      var3.fill = 1;
      var3.gridx = 1;
      var3.gridy = 0;
      this.Add(this.hB, var3);
   }

   public gE[] aN() {
      return this.hC;
   }

   public void a(gE[] var1) {
      if (var1.Length == 0) {
         this.hC = new gE[0];
         this.hx.SelectedIndex = (-1);
      } else {
         this.hC = var1;
         this.hx.SelectedIndex = (0);
      }

      this.hx.Refresh();
   }
   public static ComboBox a(dE var0) {
      return var0.hx;
   }
   public static gE[] b(dE var0) {
      return var0.hC;
   }
   public static G c(dE var0) {
      return var0.hy;
   }
   public static G d(dE var0) {
      return var0.hz;
   }
   public static G[] e(dE var0) {
      return var0.ea;
   }
   public static dt f(dE var0) {
      return var0.hB;
   }
   public static DataGridView g(dE var0) {
      return var0.hA;
   }
}

}
