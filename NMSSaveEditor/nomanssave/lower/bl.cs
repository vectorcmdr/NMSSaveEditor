using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


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
      this.bQ.Click += (new bm(this, var1));
      var5.Add(this.bQ);
      this.dS = new Button() { Text = "Copy" };
      this.dS.Enabled = (false);
      this.dS.Click += (new br(this, var1));
      var5.Add(this.dS);
      var4.Controls.Add(var5);
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
      // TODO: var7.SuspendLayout(); // TODO: set layout /* FormLayout */ null);
      this.el = new Label() { Text = "" };
      var7.Add(this.el, "2,1");
      this.em = new Button() { Text = "Repair" };
      this.em.Click += (new bz(this));
      Panel var8 = new Panel();
      var8.Add(this.em);
      var7.Controls.Add(var8);
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
      NMSSaveEditor.en.a(this);
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


#else

public class bl
{
   public bl() { }
   public bl(params object[] args) { }
   public static int dQ = 0;
   public DataGridView dR = default;
   public Button bQ = default;
   public Button dS = default;
   public ba dT = default;
   public G dU = default;
   public ComboBox dV = default;
   public TextBox dW = default;
   public cN dX = default;
   public G dY = default;
   public G dZ = default;
   public G[] ea = System.Array.Empty<G>();
   public ba eb = default;
   public ComboBox ec = default;
   public ComboBox ed = default;
   public ComboBox ee = default;
   public ComboBox ef = default;
   public ComboBox eg = default;
   public G eh = default;
   public G ei = default;
   public G ej = default;
   public G ek = default;
   public Label el = default;
   public Button em = default;
   public er[] en = System.Array.Empty<er>();
   public er[] eo = System.Array.Empty<er>();
   public gp[] ep = System.Array.Empty<gp>();
   public int eq = 0;
   public void a(bool var1) { }
   public static int b(bl var0) { return 0; }
   public static gp[] c(bl var0) { return System.Array.Empty<gp>(); }
   public static G[] d(bl var0) { return System.Array.Empty<G>(); }
   public static DataGridView e(bl var0) { return default; }
   public static er[] f(bl var0) { return System.Array.Empty<er>(); }
   public static Button g(bl var0) { return default; }
   public static ba h(bl var0) { return default; }
   public static ba i(bl var0) { return default; }
   public static G j(bl var0) { return default; }
   public static ComboBox k(bl var0) { return default; }
   public static TextBox l(bl var0) { return default; }
   public static cN m(bl var0) { return default; }
   public static G n(bl var0) { return default; }
   public static G o(bl var0) { return default; }
   public static ComboBox p(bl var0) { return default; }
   public static ComboBox q(bl var0) { return default; }
   public static ComboBox r(bl var0) { return default; }
   public static ComboBox s(bl var0) { return default; }
   public static ComboBox t(bl var0) { return default; }
   public static G u(bl var0) { return default; }
   public static G v(bl var0) { return default; }
   public static G w(bl var0) { return default; }
   public static G x(bl var0) { return default; }
   public static Label y(bl var0) { return default; }
   public static Button z(bl var0) { return default; }
   public static Button A(bl var0) { return default; }
}

#endif

}
