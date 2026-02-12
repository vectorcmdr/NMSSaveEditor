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
      GridLayout var2 = new GridLayout(1, 3);
      this.SetLayout(var2);
      Panel var3 = new Panel();
      var3.SetMinimumSize(new Size(300, 0));
      var3.SetMaximumSize(new Size(300, int.MaxValue));
      var3.SetPreferredSize(new Size(300, 0));
      Panel var4 = new Panel();
      var4.SetLayout(new BorderLayout());
      var4.Add(var3, "Center");
      Panel var5 = new Panel();
      this.bQ = new Button() { Text = "Delete" };
      this.bQ.SetEnabled(false);
      this.bQ.AddActionListener(new bm(this, var1));
      var5.Add(this.bQ);
      this.dS = new Button() { Text = "Copy" };
      this.dS.SetEnabled(false);
      this.dS.AddActionListener(new br(this, var1));
      var5.Add(this.dS);
      var4.Add(var5, "South");
      this.Add(var4);
      this.dR = new DataGridView();
      this.dR.SetSelectionMode(0);
      this.dR.SetModel(new bs(this));
      this.dR.GetColumnModel().getColumn(2).setMaxWidth(60);
      this.dR.getSelectionModel().AddListSelectionListener(new bt(this, var1));
      var3.setViewportView(this.dR);
      this.dT = new ba(new int[]{aH.cJ, 0});
      this.dT.SetVisible(false);
      this.Add(this.dT);
      this.dT.k("Frigate Info");
      this.dU = new bv(this);
      this.dT.a("Name", (Control)this.dU);
      this.dV = new ComboBox();
      this.dV.SetModel(new bw(this));
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
      this.ec.SetModel(new bB(this, (bB)null));
      this.ec.setRenderer(var6);
      this.dT.a(this.ec);
      this.ed = new ComboBox();
      this.ed.SetModel(new bC(this, 1));
      this.ed.setRenderer(var6);
      this.dT.a(this.ed);
      this.ee = new ComboBox();
      this.ee.SetModel(new bC(this, 2));
      this.ee.setRenderer(var6);
      this.dT.a(this.ee);
      this.ef = new ComboBox();
      this.ef.SetModel(new bC(this, 3));
      this.ef.setRenderer(var6);
      this.dT.a(this.ef);
      this.eg = new ComboBox();
      this.eg.SetModel(new bC(this, 4));
      this.eg.setRenderer(var6);
      this.dT.a(this.eg);
      this.dT.Y();
      Panel var7 = new Panel();
      var7.SetLayout(new FormLayout(new ColumnSpec[]{FormFactory.GLUE_COLSPEC, "default", FormFactory.GLUE_COLSPEC}, new RowSpec[]{"default", "default"}));
      this.el = new Label() { Text = "" };
      var7.Add(this.el, "2,1");
      this.em = new Button() { Text = "Repair" };
      this.em.AddActionListener(new bz(this));
      Panel var8 = new Panel();
      var8.Add(this.em);
      var7.Add(var8, "2,2");
      this.dT.a(var7);
      this.eb = new ba(new int[]{aH.cJ, 0});
      this.eb.SetVisible(false);
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
      if (this.dR.GetSelectedRow() >= 0) {
         this.dS.SetEnabled(this.ep.Length < 30 || NMSSaveEditor.en.aS());
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
       this.dR.updateUI();
   }

   // $FF: synthetic method
   public static er[] a(bl var0) {
      return var0.en;
   }

   // $FF: synthetic method
   public static int b(bl var0) {
      return var0.eq;
   }

   // $FF: synthetic method
   public static gp[] c(bl var0) {
      return var0.ep;
   }

   // $FF: synthetic method
   public static G[] d(bl var0) {
      return var0.ea;
   }

   // $FF: synthetic method
   public static DataGridView e(bl var0) {
      return var0.dR;
   }

   // $FF: synthetic method
   public static er[] f(bl var0) {
      return var0.eo;
   }

   // $FF: synthetic method
   public static void a(bl var0, gp[] var1) {
      var0.ep = var1;
   }

   // $FF: synthetic method
   public static Button g(bl var0) {
      return var0.dS;
   }

   // $FF: synthetic method
   public static void a(bl var0, int var1) {
      var0.eq = var1;
   }

   // $FF: synthetic method
   public static void a(bl var0, er[] var1) {
      var0.en = var1;
   }

   // $FF: synthetic method
   public static void b(bl var0, er[] var1) {
      var0.eo = var1;
   }

   // $FF: synthetic method
   public static ba h(bl var0) {
      return var0.dT;
   }

   // $FF: synthetic method
   public static ba i(bl var0) {
      return var0.eb;
   }

   // $FF: synthetic method
   public static G j(bl var0) {
      return var0.dU;
   }

   // $FF: synthetic method
   public static ComboBox k(bl var0) {
      return var0.dV;
   }

   // $FF: synthetic method
   public static TextBox l(bl var0) {
      return var0.dW;
   }

   // $FF: synthetic method
   public static cN m(bl var0) {
      return var0.dX;
   }

   // $FF: synthetic method
   public static G n(bl var0) {
      return var0.dY;
   }

   // $FF: synthetic method
   public static G o(bl var0) {
      return var0.dZ;
   }

   // $FF: synthetic method
   public static ComboBox p(bl var0) {
      return var0.ec;
   }

   // $FF: synthetic method
   public static ComboBox q(bl var0) {
      return var0.ed;
   }

   // $FF: synthetic method
   public static ComboBox r(bl var0) {
      return var0.ee;
   }

   // $FF: synthetic method
   public static ComboBox s(bl var0) {
      return var0.ef;
   }

   // $FF: synthetic method
   public static ComboBox t(bl var0) {
      return var0.eg;
   }

   // $FF: synthetic method
   public static G u(bl var0) {
      return var0.eh;
   }

   // $FF: synthetic method
   public static G v(bl var0) {
      return var0.ei;
   }

   // $FF: synthetic method
   public static G w(bl var0) {
      return var0.ej;
   }

   // $FF: synthetic method
   public static G x(bl var0) {
      return var0.ek;
   }

   // $FF: synthetic method
   public static Label y(bl var0) {
      return var0.el;
   }

   // $FF: synthetic method
   public static Button z(bl var0) {
      return var0.em;
   }

   // $FF: synthetic method
   public static Button A(bl var0) {
      return var0.bQ;
   }

}


}