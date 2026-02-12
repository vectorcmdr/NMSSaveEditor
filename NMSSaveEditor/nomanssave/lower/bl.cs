using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{



public class bl : Panel, eo {
   public static int dQ = 50;
   public DataGridView dR;
   public Button bQ;
   public Button dS;
   public ba dT;
   public G dU;
   public ComboBox dV;
   public TextBox dW;
   public cN dX;
   public G dY;
   public G dZ;
   public G[] ea;
   public ba eb;
   public ComboBox ec;
   public ComboBox ed;
   public ComboBox ee;
   public ComboBox ef;
   public ComboBox eg;
   public G eh;
   public G ei;
   public G ej;
   public G ek;
   public Label el;
   public Button em;
   public er[] en;
   public er[] eo;
   public gp[] ep;
   public int eq;

   public bl(Application var1) {
      TableLayoutPanel var2 = new TableLayoutPanel();
      this.SuspendLayout(); // TODO: set layout var2);
      Panel var3 = new Panel();
      var3.setMinimumSize(new Size(300, 0));
      var3.setMaximumSize(new Size(300, int.MaxValue));
      var3.Size = (new Size(300, 0));
      Panel var4 = new Panel();
      var4.SuspendLayout(); // TODO: set layout new TableLayoutPanel());
      var4.Controls.Add(var3);
      Panel var5 = new Panel();
      this.bQ = new Button() { Text = "Delete" };
      this.bQ.Enabled = (false);
      // PORT_TODO: this.bQ.Click += (new bm(this, var1));
      var5.Add(this.bQ);
      this.dS = new Button() { Text = "Copy" };
      this.dS.Enabled = (false);
      // PORT_TODO: this.dS.Click += (new br(this, var1));
      var5.Add(this.dS);
      var4.Controls.Add(var5);
      this.Add(var4);
      this.dR = new DataGridView();
      this.dR.setSelectionMode(0);
      this.dR.DataSource = (new bs(this));
      // PORT_TODO: this.dR.getColumnModel().getColumn(2).setMaxWidth(60);
      // PORT_TODO: this.dR.getSelectionModel().addListSelectionListener(new bt(this, var1));
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
      // PORT_TODO: this.dX.a((var1x) => {
         // PORT_TODO: if (this.eq >= 0) {
            // PORT_TODO: this.ep[this.eq].am(var1x);
         // PORT_TODO: }

// PORT_TODO: 
      // PORT_TODO: });
      // PORT_TODO: this.dT.a("NPC Race", (Control)this.dX);
      // PORT_TODO: this.dY = new bx(this);
      // PORT_TODO: this.dT.a("Home Seed", (Control)this.dY);
      // PORT_TODO: this.dZ = new by(this);
      // PORT_TODO: this.dT.a("Model Seed", this.dZ);
      // PORT_TODO: this.dT.k("Traits");
      // PORT_TODO: bD var6 = new bD(this, (bD)null);
      // PORT_TODO: this.ec = new ComboBox();
      // PORT_TODO: this.ec.DataSource = (new bB(this, (bB)null));
      // PORT_TODO: this.ec.setRenderer(var6);
      // PORT_TODO: this.dT.a(this.ec);
      // PORT_TODO: this.ed = new ComboBox();
      // PORT_TODO: this.ed.DataSource = (new bC(this, 1));
      // PORT_TODO: this.ed.setRenderer(var6);
      // PORT_TODO: this.dT.a(this.ed);
      // PORT_TODO: this.ee = new ComboBox();
      // PORT_TODO: this.ee.DataSource = (new bC(this, 2));
      // PORT_TODO: this.ee.setRenderer(var6);
      // PORT_TODO: this.dT.a(this.ee);
      // PORT_TODO: this.ef = new ComboBox();
      // PORT_TODO: this.ef.DataSource = (new bC(this, 3));
      // PORT_TODO: this.ef.setRenderer(var6);
      // PORT_TODO: this.dT.a(this.ef);
      // PORT_TODO: this.eg = new ComboBox();
      // PORT_TODO: this.eg.DataSource = (new bC(this, 4));
      // PORT_TODO: this.eg.setRenderer(var6);
      // PORT_TODO: this.dT.a(this.eg);
      // PORT_TODO: this.dT.Y();
      // PORT_TODO: Panel var7 = new Panel();
      // TODO: var7.SuspendLayout(); // TODO: set layout /* FormLayout */ null);
      // PORT_TODO: this.el = new Label() { Text = "" };
      // PORT_TODO: var7.Add(this.el, "2,1");
      // PORT_TODO: this.em = new Button() { Text = "Repair" };
      // PORT_TODO: this.em.Click += (new bz(this));
      // PORT_TODO: Panel var8 = new Panel();
      // PORT_TODO: var8.Add(this.em);
      // PORT_TODO: var7.Controls.Add(var8);
      // PORT_TODO: this.dT.a(var7);
      // PORT_TODO: this.eb = new ba(new int[]{aH.cJ, 0});
      // PORT_TODO: this.eb.Hide();
      // PORT_TODO: this.Add(this.eb);
      // PORT_TODO: this.eb.k("Stats");
      // PORT_TODO: this.ea = new G[gq.Values.Length];

// PORT_TODO: 
      // PORT_TODO: for(int var9 = 0; var9 < this.ea.Length; ++var9) {
         // PORT_TODO: this.ea[var9] = new bA(this, var9, (bA)null);
         // PORT_TODO: this.eb.a(gq.Values[var9].ToString(), (Control)this.ea[var9]);
      // PORT_TODO: }

// PORT_TODO: 
      // PORT_TODO: this.eb.Y();
      // PORT_TODO: this.eb.k("Totals");
      // PORT_TODO: this.eh = new bn(this);
      // PORT_TODO: this.eb.a("Expeditions", (Control)this.eh);
      // PORT_TODO: this.ei = new bo(this);
      // PORT_TODO: this.eb.a("Successful", (Control)this.ei);
      // PORT_TODO: this.ej = new bp(this);
      // PORT_TODO: this.eb.a("Failed", (Control)this.ej);
      // PORT_TODO: this.ek = new bq(this);
      // PORT_TODO: this.eb.a("Damaged", (Control)this.ek);
      // PORT_TODO: NMSSaveEditor.en.a(this);
   }

   public void a(bool var1) {
      if (this.dR.getSelectedRow() >= 0) {
         this.dS.Enabled = (this.ep.Length < 30 || NMSSaveEditor.en.aS());
      }

   }

   public void a(gp[] var1) {
      this.ep = var1;
      this.en = null;
      this.eo = null;
      this.dR.clearSelection();
      if (var1.Length > 0) {
         this.dR.setRowSelectionInterval(0, 0);
      }

      this.dR.Refresh();
   }
   public static er[] a(bl var0) {
      return var0.en;
   }
   public static int b(bl var0) {
      return var0.eq;
   }
   public static gp[] c(bl var0) {
      return var0.ep;
   }
   public static G[] d(bl var0) {
      return var0.ea;
   }
   public static DataGridView e(bl var0) {
      return var0.dR;
   }
   public static er[] f(bl var0) {
      return var0.eo;
   }
   public static void a(bl var0, gp[] var1) {
      var0.ep = var1;
   }
   public static Button g(bl var0) {
      return var0.dS;
   }
   public static void a(bl var0, int var1) {
      var0.eq = var1;
   }
   public static void a(bl var0, er[] var1) {
      var0.en = var1;
   }
   public static void b(bl var0, er[] var1) {
      var0.eo = var1;
   }
   public static ba h(bl var0) {
      return var0.dT;
   }
   public static ba i(bl var0) {
      return var0.eb;
   }
   public static G j(bl var0) {
      return var0.dU;
   }
   public static ComboBox k(bl var0) {
      return var0.dV;
   }
   public static TextBox l(bl var0) {
      return var0.dW;
   }
   public static cN m(bl var0) {
      return var0.dX;
   }
   public static G n(bl var0) {
      return var0.dY;
   }
   public static G o(bl var0) {
      return var0.dZ;
   }
   public static ComboBox p(bl var0) {
      return var0.ec;
   }
   public static ComboBox q(bl var0) {
      return var0.ed;
   }
   public static ComboBox r(bl var0) {
      return var0.ee;
   }
   public static ComboBox s(bl var0) {
      return var0.ef;
   }
   public static ComboBox t(bl var0) {
      return var0.eg;
   }
   public static G u(bl var0) {
      return var0.eh;
   }
   public static G v(bl var0) {
      return var0.ei;
   }
   public static G w(bl var0) {
      return var0.ej;
   }
   public static G x(bl var0) {
      return var0.ek;
   }
   public static Label y(bl var0) {
      return var0.el;
   }
   public static Button z(bl var0) {
      return var0.em;
   }
   public static Button A(bl var0) {
      return var0.bQ;
   }
}



}
