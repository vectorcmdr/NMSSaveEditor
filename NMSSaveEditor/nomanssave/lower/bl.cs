using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

public class bl : Panel, eo {
   private static int dQ = 50;
   private DataGridView dR;
   private Button bQ;
   private Button dS;
   private ba dT;
   private G dU;
   private ComboBox dV;
   private TextBox dW;
   private cN dX;
   private G dY;
   private G dZ;
   private G[] ea;
   private ba eb;
   private ComboBox ec;
   private ComboBox ed;
   private ComboBox ee;
   private ComboBox ef;
   private ComboBox eg;
   private G eh;
   private G ei;
   private G ej;
   private G ek;
   private Label el;
   private Button em;
   private er[] en;
   private er[] eo;
   private gp[] ep;
   private int eq;

   bl(Application var1) {
      TableLayoutPanel var2 = new TableLayoutPanel(1, 3);
      this.LayoutEnginevar2);
      Panel var3 = new Panel();
      var3.setMinimumSize(new Size(300, 0));
      var3.setMaximumSize(new Size(300, int.MaxValue));
      var3.Size = (new Size(300, 0));
      Panel var4 = new Panel();
      var4.LayoutEnginenew TableLayoutPanel());
      var4.Add(var3, "Center");
      Panel var5 = new Panel();
      this.bQ = new Button("Delete");
      this.bQ.Enabled = (false);
      this.bQ.Click += (new bm(this, var1));
      var5.Add(this.bQ);
      this.dS = new Button("Copy");
      this.dS.Enabled = (false);
      this.dS.Click += (new br(this, var1));
      var5.Add(this.dS);
      var4.Add(var5, "South");
      this.Add(var4);
      this.dR = new DataGridView();
      this.dR.setSelectionMode(0);
      this.dR.DataSource = (new bs(this));
      this.dR.getColumnModel().getColumn(2).setMaxWidth(60);
      this.dR.getSelectionModel().addListSelectionListener(new bt(this, var1));
      var3.setViewportView(this.dR);
      this.dT = new ba(new int[]{aH.cJ, 0});
      this.dT.Hide();
      this.Add(this.dT);
      this.dT.k("Frigate Info");
      this.dU = new bv(this);
      this.dT.a("Name", (Control)this.dU);
      this.dV = new ComboBox();
      this.dV.DataSource = (new bw(this));
      this.dT.a("Type", (Control)this.dV);
      this.dW = new TextBox();
      this.dW.setEditable(false);
      this.dT.a("Class", (Control)this.dW);
      this.dX = new cN(typeof(gd));
      this.dX.a((var1x) => {
         if (this.eq >= 0) {
            this.ep[this.eq].am(var1x);
         }

      });
      this.dT.a("NPC Race", (Control)this.dX);
      this.dY = new bx(this);
      this.dT.a("Home Seed", (Control)this.dY);
      this.dZ = new by(this);
      this.dT.a("Model Seed", this.dZ);
      this.dT.k("Traits");
      bD var6 = new bD(this, (bD)null);
      this.ec = new ComboBox();
      this.ec.DataSource = (new bB(this, (bB)null));
      this.ec.setRenderer(var6);
      this.dT.a(this.ec);
      this.ed = new ComboBox();
      this.ed.DataSource = (new bC(this, 1));
      this.ed.setRenderer(var6);
      this.dT.a(this.ed);
      this.ee = new ComboBox();
      this.ee.DataSource = (new bC(this, 2));
      this.ee.setRenderer(var6);
      this.dT.a(this.ee);
      this.ef = new ComboBox();
      this.ef.DataSource = (new bC(this, 3));
      this.ef.setRenderer(var6);
      this.dT.a(this.ef);
      this.eg = new ComboBox();
      this.eg.DataSource = (new bC(this, 4));
      this.eg.setRenderer(var6);
      this.dT.a(this.eg);
      this.dT.Y();
      Panel var7 = new Panel();
      var7.LayoutEnginenew FormLayout(new ColumnSpec[]{FormFactory.GLUE_COLSPEC, FormFactory.DEFAULT_COLSPEC, FormFactory.GLUE_COLSPEC}, new RowSpec[]{FormFactory.DEFAULT_ROWSPEC, FormFactory.DEFAULT_ROWSPEC}));
      this.el = new Label("");
      var7.Add(this.el, "2,1");
      this.em = new Button("Repair");
      this.em.Click += (new bz(this));
      Panel var8 = new Panel();
      var8.Add(this.em);
      var7.Add(var8, "2,2");
      this.dT.a(var7);
      this.eb = new ba(new int[]{aH.cJ, 0});
      this.eb.Hide();
      this.Add(this.eb);
      this.eb.k("Stats");
      this.ea = new G[gq.Values.Length];

      for(int var9 = 0; var9 < this.ea.Length; ++var9) {
         this.ea[var9] = new bA(this, var9, (bA)null);
         this.eb.a(gq.Values[var9].ToString(), (Control)this.ea[var9]);
      }

      this.eb.Y();
      this.eb.k("Totals");
      this.eh = new bn(this);
      this.eb.a("Expeditions", (Control)this.eh);
      this.ei = new bo(this);
      this.eb.a("Successful", (Control)this.ei);
      this.ej = new bp(this);
      this.eb.a("Failed", (Control)this.ej);
      this.ek = new bq(this);
      this.eb.a("Damaged", (Control)this.ek);
      nomanssave.en.a(this);
   }

   public void a(bool var1) {
      if (this.dR.getSelectedRow() >= 0) {
         this.dS.Enabled = (this.ep.Length < 30 || nomanssave.en.aS());
      }

   }

   void a(gp[] var1) {
      this.ep = var1;
      this.en = null;
      this.eo = null;
      this.dR.clearSelection();
      if (var1.Length > 0) {
         this.dR.setRowSelectionInterval(0, 0);
      }

      this.dR.Refresh();
   }
   static er[] a(bl var0) {
      return var0.en;
   }
   static int b(bl var0) {
      return var0.eq;
   }
   static gp[] c(bl var0) {
      return var0.ep;
   }
   static G[] d(bl var0) {
      return var0.ea;
   }
   static DataGridView e(bl var0) {
      return var0.dR;
   }
   static er[] f(bl var0) {
      return var0.eo;
   }
   static void a(bl var0, gp[] var1) {
      var0.ep = var1;
   }
   static Button g(bl var0) {
      return var0.dS;
   }
   static void a(bl var0, int var1) {
      var0.eq = var1;
   }
   static void a(bl var0, er[] var1) {
      var0.en = var1;
   }
   static void b(bl var0, er[] var1) {
      var0.eo = var1;
   }
   static ba h(bl var0) {
      return var0.dT;
   }
   static ba i(bl var0) {
      return var0.eb;
   }
   static G j(bl var0) {
      return var0.dU;
   }
   static ComboBox k(bl var0) {
      return var0.dV;
   }
   static TextBox l(bl var0) {
      return var0.dW;
   }
   static cN m(bl var0) {
      return var0.dX;
   }
   static G n(bl var0) {
      return var0.dY;
   }
   static G o(bl var0) {
      return var0.dZ;
   }
   static ComboBox p(bl var0) {
      return var0.ec;
   }
   static ComboBox q(bl var0) {
      return var0.ed;
   }
   static ComboBox r(bl var0) {
      return var0.ee;
   }
   static ComboBox s(bl var0) {
      return var0.ef;
   }
   static ComboBox t(bl var0) {
      return var0.eg;
   }
   static G u(bl var0) {
      return var0.eh;
   }
   static G v(bl var0) {
      return var0.ei;
   }
   static G w(bl var0) {
      return var0.ej;
   }
   static G x(bl var0) {
      return var0.ek;
   }
   static Label y(bl var0) {
      return var0.el;
   }
   static Button z(bl var0) {
      return var0.em;
   }
   static Button A(bl var0) {
      return var0.bQ;
   }
}

}
