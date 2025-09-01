package nomanssave;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class bl extends JPanel implements eo {
   private static final int dQ = 50;
   private JTable dR;
   private JButton bQ;
   private JButton dS;
   private ba dT;
   private G dU;
   private JComboBox dV;
   private JTextField dW;
   private cN dX;
   private G dY;
   private G dZ;
   private G[] ea;
   private ba eb;
   private JComboBox ec;
   private JComboBox ed;
   private JComboBox ee;
   private JComboBox ef;
   private JComboBox eg;
   private G eh;
   private G ei;
   private G ej;
   private G ek;
   private JLabel el;
   private JButton em;
   private er[] en;
   private er[] eo;
   private gp[] ep;
   private int eq;

   bl(Application var1) {
      GridLayout var2 = new GridLayout(1, 3);
      this.setLayout(var2);
      JScrollPane var3 = new JScrollPane();
      var3.setMinimumSize(new Dimension(300, 0));
      var3.setMaximumSize(new Dimension(300, Integer.MAX_VALUE));
      var3.setPreferredSize(new Dimension(300, 0));
      JPanel var4 = new JPanel();
      var4.setLayout(new BorderLayout());
      var4.add(var3, "Center");
      JPanel var5 = new JPanel();
      this.bQ = new JButton("Delete");
      this.bQ.setEnabled(false);
      this.bQ.addActionListener(new bm(this, var1));
      var5.add(this.bQ);
      this.dS = new JButton("Copy");
      this.dS.setEnabled(false);
      this.dS.addActionListener(new br(this, var1));
      var5.add(this.dS);
      var4.add(var5, "South");
      this.add(var4);
      this.dR = new JTable();
      this.dR.setSelectionMode(0);
      this.dR.setModel(new bs(this));
      this.dR.getColumnModel().getColumn(2).setMaxWidth(60);
      this.dR.getSelectionModel().addListSelectionListener(new bt(this, var1));
      var3.setViewportView(this.dR);
      this.dT = new ba(new int[]{aH.cJ, 0});
      this.dT.setVisible(false);
      this.add(this.dT);
      this.dT.k("Frigate Info");
      this.dU = new bv(this);
      this.dT.a("Name", (JComponent)this.dU);
      this.dV = new JComboBox();
      this.dV.setModel(new bw(this));
      this.dT.a("Type", (JComponent)this.dV);
      this.dW = new JTextField();
      this.dW.setEditable(false);
      this.dT.a("Class", (JComponent)this.dW);
      this.dX = new cN(gd.class);
      this.dX.a((var1x) -> {
         if (this.eq >= 0) {
            this.ep[this.eq].am(var1x);
         }

      });
      this.dT.a("NPC Race", (JComponent)this.dX);
      this.dY = new bx(this);
      this.dT.a("Home Seed", (JComponent)this.dY);
      this.dZ = new by(this);
      this.dT.a("Model Seed", this.dZ);
      this.dT.k("Traits");
      bD var6 = new bD(this, (bD)null);
      this.ec = new JComboBox();
      this.ec.setModel(new bB(this, (bB)null));
      this.ec.setRenderer(var6);
      this.dT.a(this.ec);
      this.ed = new JComboBox();
      this.ed.setModel(new bC(this, 1));
      this.ed.setRenderer(var6);
      this.dT.a(this.ed);
      this.ee = new JComboBox();
      this.ee.setModel(new bC(this, 2));
      this.ee.setRenderer(var6);
      this.dT.a(this.ee);
      this.ef = new JComboBox();
      this.ef.setModel(new bC(this, 3));
      this.ef.setRenderer(var6);
      this.dT.a(this.ef);
      this.eg = new JComboBox();
      this.eg.setModel(new bC(this, 4));
      this.eg.setRenderer(var6);
      this.dT.a(this.eg);
      this.dT.Y();
      JPanel var7 = new JPanel();
      var7.setLayout(new FormLayout(new ColumnSpec[]{FormFactory.GLUE_COLSPEC, FormFactory.DEFAULT_COLSPEC, FormFactory.GLUE_COLSPEC}, new RowSpec[]{FormFactory.DEFAULT_ROWSPEC, FormFactory.DEFAULT_ROWSPEC}));
      this.el = new JLabel("");
      var7.add(this.el, "2,1");
      this.em = new JButton("Repair");
      this.em.addActionListener(new bz(this));
      JPanel var8 = new JPanel();
      var8.add(this.em);
      var7.add(var8, "2,2");
      this.dT.a(var7);
      this.eb = new ba(new int[]{aH.cJ, 0});
      this.eb.setVisible(false);
      this.add(this.eb);
      this.eb.k("Stats");
      this.ea = new G[gq.values().length];

      for(int var9 = 0; var9 < this.ea.length; ++var9) {
         this.ea[var9] = new bA(this, var9, (bA)null);
         this.eb.a(gq.values()[var9].toString(), (JComponent)this.ea[var9]);
      }

      this.eb.Y();
      this.eb.k("Totals");
      this.eh = new bn(this);
      this.eb.a("Expeditions", (JComponent)this.eh);
      this.ei = new bo(this);
      this.eb.a("Successful", (JComponent)this.ei);
      this.ej = new bp(this);
      this.eb.a("Failed", (JComponent)this.ej);
      this.ek = new bq(this);
      this.eb.a("Damaged", (JComponent)this.ek);
      nomanssave.en.a(this);
   }

   public void a(boolean var1) {
      if (this.dR.getSelectedRow() >= 0) {
         this.dS.setEnabled(this.ep.length < 30 || nomanssave.en.aS());
      }

   }

   void a(gp[] var1) {
      this.ep = var1;
      this.en = null;
      this.eo = null;
      this.dR.clearSelection();
      if (var1.length > 0) {
         this.dR.setRowSelectionInterval(0, 0);
      }

      this.dR.updateUI();
   }

   // $FF: synthetic method
   static er[] a(bl var0) {
      return var0.en;
   }

   // $FF: synthetic method
   static int b(bl var0) {
      return var0.eq;
   }

   // $FF: synthetic method
   static gp[] c(bl var0) {
      return var0.ep;
   }

   // $FF: synthetic method
   static G[] d(bl var0) {
      return var0.ea;
   }

   // $FF: synthetic method
   static JTable e(bl var0) {
      return var0.dR;
   }

   // $FF: synthetic method
   static er[] f(bl var0) {
      return var0.eo;
   }

   // $FF: synthetic method
   static void a(bl var0, gp[] var1) {
      var0.ep = var1;
   }

   // $FF: synthetic method
   static JButton g(bl var0) {
      return var0.dS;
   }

   // $FF: synthetic method
   static void a(bl var0, int var1) {
      var0.eq = var1;
   }

   // $FF: synthetic method
   static void a(bl var0, er[] var1) {
      var0.en = var1;
   }

   // $FF: synthetic method
   static void b(bl var0, er[] var1) {
      var0.eo = var1;
   }

   // $FF: synthetic method
   static ba h(bl var0) {
      return var0.dT;
   }

   // $FF: synthetic method
   static ba i(bl var0) {
      return var0.eb;
   }

   // $FF: synthetic method
   static G j(bl var0) {
      return var0.dU;
   }

   // $FF: synthetic method
   static JComboBox k(bl var0) {
      return var0.dV;
   }

   // $FF: synthetic method
   static JTextField l(bl var0) {
      return var0.dW;
   }

   // $FF: synthetic method
   static cN m(bl var0) {
      return var0.dX;
   }

   // $FF: synthetic method
   static G n(bl var0) {
      return var0.dY;
   }

   // $FF: synthetic method
   static G o(bl var0) {
      return var0.dZ;
   }

   // $FF: synthetic method
   static JComboBox p(bl var0) {
      return var0.ec;
   }

   // $FF: synthetic method
   static JComboBox q(bl var0) {
      return var0.ed;
   }

   // $FF: synthetic method
   static JComboBox r(bl var0) {
      return var0.ee;
   }

   // $FF: synthetic method
   static JComboBox s(bl var0) {
      return var0.ef;
   }

   // $FF: synthetic method
   static JComboBox t(bl var0) {
      return var0.eg;
   }

   // $FF: synthetic method
   static G u(bl var0) {
      return var0.eh;
   }

   // $FF: synthetic method
   static G v(bl var0) {
      return var0.ei;
   }

   // $FF: synthetic method
   static G w(bl var0) {
      return var0.ej;
   }

   // $FF: synthetic method
   static G x(bl var0) {
      return var0.ek;
   }

   // $FF: synthetic method
   static JLabel y(bl var0) {
      return var0.el;
   }

   // $FF: synthetic method
   static JButton z(bl var0) {
      return var0.em;
   }

   // $FF: synthetic method
   static JButton A(bl var0) {
      return var0.bQ;
   }
}
