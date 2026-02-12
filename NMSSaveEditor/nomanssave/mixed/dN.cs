using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

public class dN : em {
   private static int cV = 500;
   private static int cW = 200;
   private static double gX = 1000.0D;
   private static double hI = 1000.0D;
   private static double dE = 1000.0D;
   private static double hJ = 1000.0D;
   private ComboBox hK = new ComboBox();
   private G hL;
   private cN hM;
   private cN hN;
   private G hO;
   private CheckBox hP;
   private Button bQ;
   private Button bR;
   private Button bS;
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
      this.hK.DataSource = (new dO(this, var1));
      this.a("Ship", true, this.hK);
      this.hL = new dT(this);
      this.a((string)"Name", (Control)this.hL);
      this.hM = new cN(typeof(gL));
      this.hM.a((var2x) => {
         gH var3 = (gH)this.hK.DataSource.SelectedItem;
         if (var3 != null) {
            gL var4 = gL.aw(var2x);
            int var5 = MessageBox.Show(var1.g(), "You are about to change a ship type to " + (var4 == null ? "Unknown" : var4.ToString()) + ". Are you sure?\nNOTE: Any incompatible technology installed on the ship will be deleted.", "Change Ship Type", 0);
            if (var5 != 0) {
               this.hM.m(var3.cT());
               return;
            }

            var3.ag(var2x);
            this.hW.a(var3.cC());
            this.hK.Refresh();
         }

      });
      this.a((string)"Type", (Control)this.hM);
      this.hN = new cN(typeof(gN));
      this.hN.a((var1x) => {
         gH var2 = (gH)this.hK.DataSource.SelectedItem;
         if (var2 != null) {
            var2.aj(var1x);
         }

      });
      this.a((string)"Class", (Control)this.hN);
      this.hO = new dU(this);
      this.a((string)"Seed", (G)this.hO);
      this.hP = new CheckBox("Use Old Colours");
      this.hP.Enabled = (false);
      this.hP.Click += (new dV(this, var1));
      this.a(null, (Control)this.hP);
      this.k("Base Stats");
      this.hQ = new dW(this);
      this.a((string)"Health", (Control)this.hQ);
      this.hR = new dX(this);
      this.a((string)"Shield", (Control)this.hR);
      this.hS = new dY(this);
      this.a((string)"Damage", (Control)this.hS);
      this.hT = new dZ(this);
      this.a((string)"Shields", (Control)this.hT);
      this.hU = new ea(this);
      this.a((string)"Hyperdrive", (Control)this.hU);
      this.hV = new dP(this);
      this.a((string)"Maneuverability", (Control)this.hV);
      this.Y();
      Panel var2 = new Panel();
      this.bQ = new Button("Delete Ship");
      this.bQ.Click += (new dQ(this, var1));
      var2.Add(this.bQ);
      this.bR = new Button("Export");
      this.bR.Click += (new dR(this, var1));
      var2.Add(this.bR);
      this.bS = new Button("Import");
      this.bS.Click += (new dS(this, var1));
      var2.Add(this.bS);
      this.a((Control)var2);
      this.hW = new bO(var1);
      this.b(this.hW);
   }

   void w() {
      for(int var1 = 0; var1 < this.hX.Length; ++var1) {
         this.hX[var1].cC().stream().forEach((var1x) => {
            if (var1x.dt()) {
               hc.info(var1x + ": technology recharged");
            }

            this.hW.a(var1x);
         });
      }

   }

   void x() {
      for(int var1 = 0; var1 < this.hX.Length; ++var1) {
         this.hX[var1].cC().stream().forEach((var1x) => {
            if (var1x.du()) {
               hc.info(var1x + ": items refilled");
            }

            this.hW.a(var1x);
         });
      }

   }

   void y() {
      for(int var1 = 0; var1 < this.hX.Length; ++var1) {
         this.hX[var1].cC().stream().forEach((var1x) => {
            if (var1x.dv()) {
               hc.info(var1x + ": all slots enabled");
            }

            this.hW.a(var1x);
         });
      }

   }

   void z() {
      for(int var1 = 0; var1 < this.hX.Length; ++var1) {
         this.hX[var1].cC().stream().forEach((var1x) => {
            if (var1x.ds()) {
               hc.info(var1x + ": all slots repaired");
            }

            this.hW.a(var1x);
         });
      }

   }

   void A() {
      for(int var1 = 0; var1 < this.hX.Length; ++var1) {
         this.hX[var1].cC().stream().forEach((var1x) => {
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
      if (var1.Length == 0) {
         this.hK.SelectedIndex = (-1);
      } else {
         int var3 = var2 == null ? 0 : var2.dV();
         if (var3 >= var1.Length) {
            var3 = 0;
         }

         this.hK.SelectedIndex = (var3);
      }

      if (var2 == null) {
         this.hQ.Text = ("");
         this.hR.Text = ("");
      } else {
         this.hQ.Text = (Long.toString((long)var2.dM()));
         this.hR.Text = (Long.toString((long)var2.dN()));
      }

      this.hK.Refresh();
   }
   static gH[] a(dN var0) {
      return var0.hX;
   }
   static G b(dN var0) {
      return var0.hL;
   }
   static cN c(dN var0) {
      return var0.hM;
   }
   static cN d(dN var0) {
      return var0.hN;
   }
   static G e(dN var0) {
      return var0.hO;
   }
   static CheckBox f(dN var0) {
      return var0.hP;
   }
   static Button g(dN var0) {
      return var0.bQ;
   }
   static G h(dN var0) {
      return var0.hS;
   }
   static G i(dN var0) {
      return var0.hT;
   }
   static G j(dN var0) {
      return var0.hU;
   }
   static G k(dN var0) {
      return var0.hV;
   }
   static bO l(dN var0) {
      return var0.hW;
   }
   static G m(dN var0) {
      return var0.hQ;
   }
   static G n(dN var0) {
      return var0.hR;
   }
   static gC o(dN var0) {
      return var0.hY;
   }
   static ComboBox p(dN var0) {
      return var0.hK;
   }
   static void a(dN var0, gH[] var1) {
      var0.hX = var1;
   }
}

}
