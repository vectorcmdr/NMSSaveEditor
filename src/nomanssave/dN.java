package nomanssave;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class dN extends em {
   private static final int cV = 500;
   private static final int cW = 200;
   private static final double gX = 1000.0D;
   private static final double hI = 1000.0D;
   private static final double dE = 1000.0D;
   private static final double hJ = 1000.0D;
   private JComboBox hK = new JComboBox();
   private G hL;
   private cN hM;
   private cN hN;
   private G hO;
   private JCheckBox hP;
   private JButton bQ;
   private JButton bR;
   private JButton bS;
   private G hQ;
   private G hR;
   private G hS;
   private G hT;
   private G hU;
   private G hV;
   private bO hW;
   private gH[] hX;
   private gC hY;

   dN(Application var1) {
      this.hK.setModel(new dO(this, var1));
      this.a("Ship", true, this.hK);
      this.hL = new dT(this);
      this.a((String)"Name", (JComponent)this.hL);
      this.hM = new cN(gL.class);
      this.hM.a((var2x) -> {
         gH var3 = (gH)this.hK.getModel().getSelectedItem();
         if (var3 != null) {
            gL var4 = gL.aw(var2x);
            int var5 = JOptionPane.showConfirmDialog(var1.g(), "You are about to change a ship type to " + (var4 == null ? "Unknown" : var4.toString()) + ". Are you sure?\nNOTE: Any incompatible technology installed on the ship will be deleted.", "Change Ship Type", 0);
            if (var5 != 0) {
               this.hM.m(var3.cT());
               return;
            }

            var3.ag(var2x);
            this.hW.a(var3.cC());
            this.hK.updateUI();
         }

      });
      this.a((String)"Type", (JComponent)this.hM);
      this.hN = new cN(gN.class);
      this.hN.a((var1x) -> {
         gH var2 = (gH)this.hK.getModel().getSelectedItem();
         if (var2 != null) {
            var2.aj(var1x);
         }

      });
      this.a((String)"Class", (JComponent)this.hN);
      this.hO = new dU(this);
      this.a((String)"Seed", (G)this.hO);
      this.hP = new JCheckBox("Use Old Colours");
      this.hP.setEnabled(false);
      this.hP.addActionListener(new dV(this, var1));
      this.a((String)null, (JComponent)this.hP);
      this.k("Base Stats");
      this.hQ = new dW(this);
      this.a((String)"Health", (JComponent)this.hQ);
      this.hR = new dX(this);
      this.a((String)"Shield", (JComponent)this.hR);
      this.hS = new dY(this);
      this.a((String)"Damage", (JComponent)this.hS);
      this.hT = new dZ(this);
      this.a((String)"Shields", (JComponent)this.hT);
      this.hU = new ea(this);
      this.a((String)"Hyperdrive", (JComponent)this.hU);
      this.hV = new dP(this);
      this.a((String)"Maneuverability", (JComponent)this.hV);
      this.Y();
      JPanel var2 = new JPanel();
      this.bQ = new JButton("Delete Ship");
      this.bQ.addActionListener(new dQ(this, var1));
      var2.add(this.bQ);
      this.bR = new JButton("Export");
      this.bR.addActionListener(new dR(this, var1));
      var2.add(this.bR);
      this.bS = new JButton("Import");
      this.bS.addActionListener(new dS(this, var1));
      var2.add(this.bS);
      this.a((JComponent)var2);
      this.hW = new bO(var1);
      this.b(this.hW);
   }

   void w() {
      for(int var1 = 0; var1 < this.hX.length; ++var1) {
         this.hX[var1].cC().stream().forEach((var1x) -> {
            if (var1x.dt()) {
               hc.info(var1x + ": technology recharged");
            }

            this.hW.a(var1x);
         });
      }

   }

   void x() {
      for(int var1 = 0; var1 < this.hX.length; ++var1) {
         this.hX[var1].cC().stream().forEach((var1x) -> {
            if (var1x.du()) {
               hc.info(var1x + ": items refilled");
            }

            this.hW.a(var1x);
         });
      }

   }

   void y() {
      for(int var1 = 0; var1 < this.hX.length; ++var1) {
         this.hX[var1].cC().stream().forEach((var1x) -> {
            if (var1x.dv()) {
               hc.info(var1x + ": all slots enabled");
            }

            this.hW.a(var1x);
         });
      }

   }

   void z() {
      for(int var1 = 0; var1 < this.hX.length; ++var1) {
         this.hX[var1].cC().stream().forEach((var1x) -> {
            if (var1x.ds()) {
               hc.info(var1x + ": all slots repaired");
            }

            this.hW.a(var1x);
         });
      }

   }

   void A() {
      for(int var1 = 0; var1 < this.hX.length; ++var1) {
         this.hX[var1].cC().stream().forEach((var1x) -> {
            if (var1x.dl()) {
               hc.info(var1x + ": inventory expanded");
            }

            this.hW.a(var1x);
         });
      }

   }

   void a(gt var1) {
      this.hW.a(var1);
   }

   gH[] aO() {
      return this.hX;
   }

   void a(gH[] var1, gC var2) {
      this.hX = var1;
      this.hY = var2;
      if (var1.length == 0) {
         this.hK.setSelectedIndex(-1);
      } else {
         int var3 = var2 == null ? 0 : var2.dV();
         if (var3 >= var1.length) {
            var3 = 0;
         }

         this.hK.setSelectedIndex(var3);
      }

      if (var2 == null) {
         this.hQ.setText("");
         this.hR.setText("");
      } else {
         this.hQ.setText(Long.toString((long)var2.dM()));
         this.hR.setText(Long.toString((long)var2.dN()));
      }

      this.hK.updateUI();
   }

   // $FF: synthetic method
   static gH[] a(dN var0) {
      return var0.hX;
   }

   // $FF: synthetic method
   static G b(dN var0) {
      return var0.hL;
   }

   // $FF: synthetic method
   static cN c(dN var0) {
      return var0.hM;
   }

   // $FF: synthetic method
   static cN d(dN var0) {
      return var0.hN;
   }

   // $FF: synthetic method
   static G e(dN var0) {
      return var0.hO;
   }

   // $FF: synthetic method
   static JCheckBox f(dN var0) {
      return var0.hP;
   }

   // $FF: synthetic method
   static JButton g(dN var0) {
      return var0.bQ;
   }

   // $FF: synthetic method
   static G h(dN var0) {
      return var0.hS;
   }

   // $FF: synthetic method
   static G i(dN var0) {
      return var0.hT;
   }

   // $FF: synthetic method
   static G j(dN var0) {
      return var0.hU;
   }

   // $FF: synthetic method
   static G k(dN var0) {
      return var0.hV;
   }

   // $FF: synthetic method
   static bO l(dN var0) {
      return var0.hW;
   }

   // $FF: synthetic method
   static G m(dN var0) {
      return var0.hQ;
   }

   // $FF: synthetic method
   static G n(dN var0) {
      return var0.hR;
   }

   // $FF: synthetic method
   static gC o(dN var0) {
      return var0.hY;
   }

   // $FF: synthetic method
   static JComboBox p(dN var0) {
      return var0.hK;
   }

   // $FF: synthetic method
   static void a(dN var0, gH[] var1) {
      var0.hX = var1;
   }
}
