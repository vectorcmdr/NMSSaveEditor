using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

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
      this.hK.DataSource = (new dO(this, var1));
      this.a("Ship", true, this.hK);
      this.hL = new dT(this);
      this.a((string)"Name", (Control)this.hL);
      this.hM = new cN(typeof(gL));
      // PORT_TODO: this.hM.a((var2x) => {
         // PORT_TODO: gH var3 = (gH)this.hK.DataSource.SelectedItem;
         // PORT_TODO: if (var3 != null) {
            // PORT_TODO: gL var4 = gL.aw(var2x);
            // PORT_TODO: int var5 = MessageBox.Show(var1.g(), "You are about to change a ship type to " + (var4 == null ? "Unknown" : var4.ToString()) + ". Are you sure?\nNOTE: Any incompatible technology installed on the ship will be deleted.", "Change Ship Type", 0);
            // PORT_TODO: if (var5 != 0) {
               // PORT_TODO: this.hM.m(var3.cT());
               // PORT_TODO: return;
            // PORT_TODO: }

// PORT_TODO: 
            // PORT_TODO: var3.ag(var2x);
            // PORT_TODO: this.hW.a(var3.cC());
            // PORT_TODO: this.hK.Refresh();
         // PORT_TODO: }

// PORT_TODO: 
      // PORT_TODO: });
      // PORT_TODO: this.a((string)"Type", (Control)this.hM);
      // PORT_TODO: this.hN = new cN(typeof(gN));
      // PORT_TODO: this.hN.a((var1x) => {
         // PORT_TODO: gH var2 = (gH)this.hK.DataSource.SelectedItem;
         // PORT_TODO: if (var2 != null) {
            // PORT_TODO: var2.aj(var1x);
         // PORT_TODO: }

// PORT_TODO: 
      // PORT_TODO: });
      // PORT_TODO: this.a((string)"Class", (Control)this.hN);
      // PORT_TODO: this.hO = new dU(this);
      // PORT_TODO: this.a((string)"Seed", (G)this.hO);
      // PORT_TODO: this.hP = new CheckBox() { Text = "Use Old Colours" };
      // PORT_TODO: this.hP.Enabled = (false);
      // PORT_TODO: this.hP.Click += (new dV(this, var1));
      // PORT_TODO: this.a(null, (Control)this.hP);
      // PORT_TODO: this.k("Base Stats");
      // PORT_TODO: this.hQ = new dW(this);
      // PORT_TODO: this.a((string)"Health", (Control)this.hQ);
      // PORT_TODO: this.hR = new dX(this);
      // PORT_TODO: this.a((string)"Shield", (Control)this.hR);
      // PORT_TODO: this.hS = new dY(this);
      // PORT_TODO: this.a((string)"Damage", (Control)this.hS);
      // PORT_TODO: this.hT = new dZ(this);
      // PORT_TODO: this.a((string)"Shields", (Control)this.hT);
      // PORT_TODO: this.hU = new ea(this);
      // PORT_TODO: this.a((string)"Hyperdrive", (Control)this.hU);
      // PORT_TODO: this.hV = new dP(this);
      // PORT_TODO: this.a((string)"Maneuverability", (Control)this.hV);
      // PORT_TODO: this.Y();
      // PORT_TODO: Panel var2 = new Panel();
      // PORT_TODO: this.bQ = new Button() { Text = "Delete Ship" };
      // PORT_TODO: this.bQ.Click += (new dQ(this, var1));
      // PORT_TODO: var2.Add(this.bQ);
      // PORT_TODO: this.bR = new Button() { Text = "Export" };
      // PORT_TODO: this.bR.Click += (new dR(this, var1));
      // PORT_TODO: var2.Add(this.bR);
      // PORT_TODO: this.bS = new Button() { Text = "Import" };
      // PORT_TODO: this.bS.Click += (new dS(this, var1));
      // PORT_TODO: var2.Add(this.bS);
      // PORT_TODO: this.a((Control)var2);
      // PORT_TODO: this.hW = new bO(var1);
      // PORT_TODO: this.b(this.hW);
   // PORT_TODO: }

// PORT_TODO: 
   // PORT_TODO: public void w() {
      // PORT_TODO: for(int var1 = 0; var1 < this.hX.Length; ++var1) {
         // PORT_TODO: this.hX[var1].cC().stream().forEach((var1x) => {
            // PORT_TODO: if (true) { // PORT_TODO: original condition had errors
               // PORT_TODO: hc.info(var1x + ": technology recharged");
            // PORT_TODO: }

// PORT_TODO: 
            // PORT_TODO: this.hW.a(var1x);
         // PORT_TODO: });
      // PORT_TODO: }

// PORT_TODO: 
   // PORT_TODO: }

// PORT_TODO: 
   // PORT_TODO: public void x() {
      // PORT_TODO: for(int var1 = 0; var1 < this.hX.Length; ++var1) {
         // PORT_TODO: this.hX[var1].cC().stream().forEach((var1x) => {
            // PORT_TODO: if (true) { // PORT_TODO: original condition had errors
               // PORT_TODO: hc.info(var1x + ": items refilled");
            // PORT_TODO: }

// PORT_TODO: 
            // PORT_TODO: this.hW.a(var1x);
         // PORT_TODO: });
      // PORT_TODO: }

// PORT_TODO: 
   // PORT_TODO: }

// PORT_TODO: 
   // PORT_TODO: public void y() {
      // PORT_TODO: for(int var1 = 0; var1 < this.hX.Length; ++var1) {
         // PORT_TODO: this.hX[var1].cC().stream().forEach((var1x) => {
            // PORT_TODO: if (true) { // PORT_TODO: original condition had errors
               // PORT_TODO: hc.info(var1x + ": all slots enabled");
            // PORT_TODO: }

// PORT_TODO: 
            // PORT_TODO: this.hW.a(var1x);
         // PORT_TODO: });
      // PORT_TODO: }

// PORT_TODO: 
   // PORT_TODO: }

// PORT_TODO: 
   // PORT_TODO: public void z() {
      // PORT_TODO: for(int var1 = 0; var1 < this.hX.Length; ++var1) {
         // PORT_TODO: this.hX[var1].cC().stream().forEach((var1x) => {
            // PORT_TODO: if (true) { // PORT_TODO: original condition had errors
               // PORT_TODO: hc.info(var1x + ": all slots repaired");
            // PORT_TODO: }

// PORT_TODO: 
            // PORT_TODO: this.hW.a(var1x);
         // PORT_TODO: });
      // PORT_TODO: }

// PORT_TODO: 
   // PORT_TODO: }

// PORT_TODO: 
   // PORT_TODO: public void A() {
      // PORT_TODO: for(int var1 = 0; var1 < this.hX.Length; ++var1) {
         // PORT_TODO: this.hX[var1].cC().stream().forEach((var1x) => {
            // PORT_TODO: if (true) { // PORT_TODO: original condition had errors
               // PORT_TODO: hc.info(var1x + ": inventory expanded");
            // PORT_TODO: }

// PORT_TODO: 
            // PORT_TODO: this.hW.a(var1x);
         // PORT_TODO: });
      // PORT_TODO: }

// PORT_TODO: 
   // PORT_TODO: }

// PORT_TODO: 
   // PORT_TODO: public void a(gt var1) {
      // PORT_TODO: this.hW.a(var1);
   // PORT_TODO: }

// PORT_TODO: 
   // PORT_TODO: public gH[] aO() {
      // PORT_TODO: return this.hX;
   // PORT_TODO: }

// PORT_TODO: 
   // PORT_TODO: public void a(gH[] var1, gC var2) {
      // PORT_TODO: this.hX = var1;
      // PORT_TODO: this.hY = var2;
      // PORT_TODO: if (var1.Length == 0) {
         // PORT_TODO: this.hK.SelectedIndex = (-1);
      // PORT_TODO: } else {
         // PORT_TODO: int var3 = var2 == null ? 0 : var2.dV();
         // PORT_TODO: if (var3 >= var1.Length) {
            // PORT_TODO: var3 = 0;
         // PORT_TODO: }

// PORT_TODO: 
         // PORT_TODO: this.hK.SelectedIndex = (var3);
      // PORT_TODO: }

// PORT_TODO: 
      // PORT_TODO: if (var2 == null) {
         // PORT_TODO: this.hQ.Text = ("");
         // PORT_TODO: this.hR.Text = ("");
      // PORT_TODO: } else {
         // PORT_TODO: this.hQ.Text = ((long)var2.dM()).ToString();
         // PORT_TODO: this.hR.Text = ((long)var2.dN()).ToString();
      // PORT_TODO: }

// PORT_TODO: 
      // PORT_TODO: this.hK.Refresh();
   // PORT_TODO: }
   // PORT_TODO: public static gH[] a(dN var0) {
      // PORT_TODO: return var0.hX;
   // PORT_TODO: }
   // PORT_TODO: public static G b(dN var0) {
      // PORT_TODO: return var0.hL;
   // PORT_TODO: }
   // PORT_TODO: public static cN c(dN var0) {
      // PORT_TODO: return var0.hM;
   // PORT_TODO: }
   // PORT_TODO: public static cN d(dN var0) {
      // PORT_TODO: return var0.hN;
   // PORT_TODO: }
   // PORT_TODO: public static G e(dN var0) {
      // PORT_TODO: return var0.hO;
   // PORT_TODO: }
   // PORT_TODO: public static CheckBox f(dN var0) {
      // PORT_TODO: return var0.hP;
   // PORT_TODO: }
   // PORT_TODO: public static Button g(dN var0) {
      // PORT_TODO: return var0.bQ;
   // PORT_TODO: }
   // PORT_TODO: public static G h(dN var0) {
      // PORT_TODO: return var0.hS;
   // PORT_TODO: }
   // PORT_TODO: public static G i(dN var0) {
      // PORT_TODO: return var0.hT;
   // PORT_TODO: }
   // PORT_TODO: public static G j(dN var0) {
      // PORT_TODO: return var0.hU;
   // PORT_TODO: }
   // PORT_TODO: public static G k(dN var0) {
      // PORT_TODO: return var0.hV;
   // PORT_TODO: }
   // PORT_TODO: public static bO l(dN var0) {
      // PORT_TODO: return var0.hW;
   // PORT_TODO: }
   // PORT_TODO: public static G m(dN var0) {
      // PORT_TODO: return var0.hQ;
   // PORT_TODO: }
   // PORT_TODO: public static G n(dN var0) {
      // PORT_TODO: return var0.hR;
   // PORT_TODO: }
   // PORT_TODO: public static gC o(dN var0) {
      // PORT_TODO: return var0.hY;
   // PORT_TODO: }
   // PORT_TODO: public static ComboBox p(dN var0) {
      // PORT_TODO: return var0.hK;
   // PORT_TODO: }
   // PORT_TODO: public static void a(dN var0, gH[] var1) {
      // PORT_TODO: var0.hX = var1;
   // PORT_TODO: }
// PORT_TODO: }



}
}
}
