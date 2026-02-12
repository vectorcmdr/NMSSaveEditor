using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{



public class X : Panel {
   public ComboBox bG;
   public ComboBox bH;
   public G bI;
   public G bJ;
   public G bK;
   public G bL;
   public G bM;
   public CheckBox bN;
   public cN bO;
   public cN bP;
   public Button bQ;
   public Button bR;
   public Button bS;
   public gj[] bT;

   public X(Application var1) {
      TableLayoutPanel var2 = new TableLayoutPanel();
      this.SuspendLayout(); // TODO: set layout var2);
      ba var3 = new ba(new int[]{aH.cJ, 0});
      this.Add(var3);
      this.Add(new Panel());
      this.Add(new Panel());
      this.bG = new ComboBox();
      this.bG.DataSource = (new Y(this));
      var3.a("Companion", true, this.bG);
      this.bH = new ComboBox();
      this.bH.DataSource = (new ab(this));
      this.bH.Enabled = (false);
      var3.a("Type", (Control)this.bH);
      this.bI = new ac(this);
      var3.a("Name", (Control)this.bI);
      this.bJ = new ad(this);
      var3.a("Creature Seed", this.bJ);
      this.bK = new ae(this);
      var3.a("Secondary Seed", (Control)this.bK);
      this.bL = new af(this);
      var3.a("Species Seed", (Control)this.bL);
      this.bM = new ag(this);
      var3.a("Genus Seed", (Control)this.bM);
      this.bN = new CheckBox() { Text = "Predator" };
      this.bN.Enabled = (false);
      // PORT_TODO: this.bN.Click += (new ah(this));
      var3.a(null, (Control)this.bN);
      this.bO = new cN(typeof(gi));
      // PORT_TODO: this.bO.a((var1x) => {
         // PORT_TODO: gj var2 = (gj)this.bG.SelectedItem;
         // PORT_TODO: if (var2 != null) {
            // PORT_TODO: var2.ae(var1x);
         // PORT_TODO: }

// PORT_TODO: 
      // PORT_TODO: });
      // PORT_TODO: var3.a("Biome", (Control)this.bO);
      // PORT_TODO: this.bP = new cN(typeof(gk));
      // PORT_TODO: this.bP.a((var1x) => {
         // PORT_TODO: gj var2 = (gj)this.bG.SelectedItem;
         // PORT_TODO: if (var2 != null) {
            // PORT_TODO: var2.af(var1x);
         // PORT_TODO: }

// PORT_TODO: 
      // PORT_TODO: });
      // PORT_TODO: var3.a("Type", (Control)this.bP);
      // PORT_TODO: var3.Y();
      // PORT_TODO: Panel var4 = new Panel();
      // PORT_TODO: this.bQ = new Button() { Text = "Delete" };
      // PORT_TODO: this.bQ.Click += (new ai(this, var1));
      // PORT_TODO: var4.Add(this.bQ);
      // PORT_TODO: this.bR = new Button() { Text = "Export" };
      // PORT_TODO: this.bR.Click += (new Z(this, var1));
      // PORT_TODO: var4.Add(this.bR);
      // PORT_TODO: this.bS = new Button() { Text = "Import" };
      // PORT_TODO: this.bS.Click += (new aa(this, var1));
      // PORT_TODO: var4.Add(this.bS);
      // PORT_TODO: var3.a(var4);
   // PORT_TODO: }

// PORT_TODO: 
   // PORT_TODO: public void a(gj[] var1) {
      // PORT_TODO: this.bT = var1;
      // PORT_TODO: if (var1.Length == 0) {
         // PORT_TODO: this.bG.SelectedIndex = (-1);
      // PORT_TODO: } else {
         // PORT_TODO: this.bG.SelectedIndex = (0);
      // PORT_TODO: }

// PORT_TODO: 
      // PORT_TODO: this.bG.Refresh();
   // PORT_TODO: }
   // PORT_TODO: public static gj[] a(X var0) {
      // PORT_TODO: return var0.bT;
   // PORT_TODO: }
   // PORT_TODO: public static ComboBox b(X var0) {
      // PORT_TODO: return var0.bH;
   // PORT_TODO: }
   // PORT_TODO: public static G c(X var0) {
      // PORT_TODO: return var0.bI;
   // PORT_TODO: }
   // PORT_TODO: public static G d(X var0) {
      // PORT_TODO: return var0.bJ;
   // PORT_TODO: }
   // PORT_TODO: public static G e(X var0) {
      // PORT_TODO: return var0.bK;
   // PORT_TODO: }
   // PORT_TODO: public static G f(X var0) {
      // PORT_TODO: return var0.bL;
   // PORT_TODO: }
   // PORT_TODO: public static G g(X var0) {
      // PORT_TODO: return var0.bM;
   // PORT_TODO: }
   // PORT_TODO: public static CheckBox h(X var0) {
      // PORT_TODO: return var0.bN;
   // PORT_TODO: }
   // PORT_TODO: public static cN i(X var0) {
      // PORT_TODO: return var0.bO;
   // PORT_TODO: }
   // PORT_TODO: public static cN j(X var0) {
      // PORT_TODO: return var0.bP;
   // PORT_TODO: }
   // PORT_TODO: public static ComboBox k(X var0) {
      // PORT_TODO: return var0.bG;
   // PORT_TODO: }
   // PORT_TODO: public static void a(X var0, gj[] var1) {
      // PORT_TODO: var0.bT = var1;
   // PORT_TODO: }
// PORT_TODO: }



}
}
}
