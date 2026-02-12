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

public class dN : em {
   public static int cV = 500;
   public static int cW = 200;
   public static double gX = 1000.0D;
   public static double hI = 1000.0D;
   public static double dE = 1000.0D;
   public static double hJ = 1000.0D;
   public ComboBox hK = new ComboBox();
   public G hL;
   public cN hM;
   public cN hN;
   public G hO;
   public CheckBox hP;
   public Button bQ;
   public Button bR;
   public Button bS;
   public G hQ;
   public G hR;
   public G hS;
   public G hT;
   public G hU;
   public G hV;
   public bO hW;
   public gH[] hX;
   public gC hY;

   public dN(Application var1) {
      this.hK.SetModel(new dO(this, var1));
      this.a("Ship", true, this.hK);
      this.hL = new dT(this);
      this.a((string)"Name", (JComponent)this.hL);
      this.hM = new cN(typeof(gL));
      this.hM.a((var2x) => {
         gH var3 = (gH)this.hK.GetModel().SelectedItem;
         if (var3 != null) {
            gL var4 = gL.aw(var2x);
            int var5 = MessageBox.Show("You are about to change a ship type to " + (var4 == null ? "Unknown" : var4.ToString()) + ". Are you sure?\nNOTE: Any incompatible technology installed on the ship will be deleted.".ToString(), "Change Ship Type".ToString(), MessageBoxButtons.YesNo);
            if (var5 != 0) {
               this.hM.m(var3.cT());
               return;
            }

            var3.ag(var2x);
            this.hW.a(var3.cC());
            this.hK.updateUI();
         }

      });
      this.a((string)"Type", (JComponent)this.hM);
      this.hN = new cN(typeof(gN));
      this.hN.a((var1x) => {
         gH var2 = (gH)this.hK.GetModel().SelectedItem;
         if (var2 != null) {
            var2.aj(var1x);
         }

      });
      this.a((string)"Class", (JComponent)this.hN);
      this.hO = new dU(this);
      this.a((string)"Seed", (G)this.hO);
      this.hP = new CheckBox("Use Old Colours");
      this.hP.SetEnabled(false);
      this.hP.AddActionListener(new dV(this, var1));
      this.a((string)null, (JComponent)this.hP);
      this.k("Base Stats");
      this.hQ = new dW(this);
      this.a((string)"Health", (JComponent)this.hQ);
      this.hR = new dX(this);
      this.a((string)"Shield", (JComponent)this.hR);
      this.hS = new dY(this);
      this.a((string)"Damage", (JComponent)this.hS);
      this.hT = new dZ(this);
      this.a((string)"Shields", (JComponent)this.hT);
      this.hU = new ea(this);
      this.a((string)"Hyperdrive", (JComponent)this.hU);
      this.hV = new dP(this);
      this.a((string)"Maneuverability", (JComponent)this.hV);
      this.Y();
      Panel var2 = new Panel();
      this.bQ = new Button("Delete Ship");
      this.bQ.AddActionListener(new dQ(this, var1));
      var2.Add(this.bQ);
      this.bR = new Button("Export");
      this.bR.AddActionListener(new dR(this, var1));
      var2.Add(this.bR);
      this.bS = new Button("Import");
      this.bS.AddActionListener(new dS(this, var1));
      var2.Add(this.bS);
      this.a((JComponent)var2);
      this.hW = new bO(var1);
      this.b(this.hW);
   }

   public void w() {
      for(int var1 = 0; var1 < this.hX.Length; ++var1) {
         this.hX[var1].cC().forEach((var1x) => {
            if (var1x.dt()) {
               hc.info(var1x + ": technology recharged");
            }

            this.hW.a(var1x);
         });
      }

   }

   public void x() {
      for(int var1 = 0; var1 < this.hX.Length; ++var1) {
         this.hX[var1].cC().forEach((var1x) => {
            if (var1x.du()) {
               hc.info(var1x + ": items refilled");
            }

            this.hW.a(var1x);
         });
      }

   }

   public void y() {
      for(int var1 = 0; var1 < this.hX.Length; ++var1) {
         this.hX[var1].cC().forEach((var1x) => {
            if (var1x.dv()) {
               hc.info(var1x + ": all slots enabled");
            }

            this.hW.a(var1x);
         });
      }

   }

   public void z() {
      for(int var1 = 0; var1 < this.hX.Length; ++var1) {
         this.hX[var1].cC().forEach((var1x) => {
            if (var1x.ds()) {
               hc.info(var1x + ": all slots repaired");
            }

            this.hW.a(var1x);
         });
      }

   }

   public void A() {
      for(int var1 = 0; var1 < this.hX.Length; ++var1) {
         this.hX[var1].cC().forEach((var1x) => {
            if (var1x.dl()) {
               hc.info(var1x + ": inventory expanded");
            }

            this.hW.a(var1x);
         });
      }

   }

   public void a(gt var1) {
      this.hW.a(var1);
   }

   public gH[] aO() {
      return this.hX;
   }

   public void a(gH[] var1, gC var2) {
      this.hX = var1;
      this.hY = var2;
      if (var1.Length == 0) {
         this.hK.SetSelectedIndex(-1);
      } else {
         int var3 = var2 == null ? 0 : var2.dV();
         if (var3 >= var1.Length) {
            var3 = 0;
         }

         this.hK.SetSelectedIndex(var3);
      }

      if (var2 == null) {
         this.hQ.SetText("");
         this.hR.SetText("");
      } else {
         this.hQ.SetText(Convert.ToString((long)var2.dM()));
         this.hR.SetText(Convert.ToString((long)var2.dN()));
      }

      this.hK.updateUI();
   }

   // $FF: synthetic method
   public static gH[] a(dN var0) {
      return var0.hX;
   }

   // $FF: synthetic method
   public static G b(dN var0) {
      return var0.hL;
   }

   // $FF: synthetic method
   public static cN c(dN var0) {
      return var0.hM;
   }

   // $FF: synthetic method
   public static cN d(dN var0) {
      return var0.hN;
   }

   // $FF: synthetic method
   public static G e(dN var0) {
      return var0.hO;
   }

   // $FF: synthetic method
   public static CheckBox f(dN var0) {
      return var0.hP;
   }

   // $FF: synthetic method
   public static Button g(dN var0) {
      return var0.bQ;
   }

   // $FF: synthetic method
   public static G h(dN var0) {
      return var0.hS;
   }

   // $FF: synthetic method
   public static G i(dN var0) {
      return var0.hT;
   }

   // $FF: synthetic method
   public static G j(dN var0) {
      return var0.hU;
   }

   // $FF: synthetic method
   public static G k(dN var0) {
      return var0.hV;
   }

   // $FF: synthetic method
   public static bO l(dN var0) {
      return var0.hW;
   }

   // $FF: synthetic method
   public static G m(dN var0) {
      return var0.hQ;
   }

   // $FF: synthetic method
   public static G n(dN var0) {
      return var0.hR;
   }

   // $FF: synthetic method
   public static gC o(dN var0) {
      return var0.hY;
   }

   // $FF: synthetic method
   public static ComboBox p(dN var0) {
      return var0.hK;
   }

   // $FF: synthetic method
   public static void a(dN var0, gH[] var1) {
      var0.hX = var1;
   }
}

}
