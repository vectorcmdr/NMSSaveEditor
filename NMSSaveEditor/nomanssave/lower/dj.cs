using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{



public class dj : em {
   public static double gX = 1000.0D;
   public static double gY = 1000.0D;
   public static double gZ = 1000.0D;
   public ComboBox ha = new ComboBox();
   public G hb;
   public cN hc;
   public cN hd;
   public G he;
   public G hf;
   public G hg;
   public G hh;
   public Button bQ;
   public Button bR;
   public Button bS;
   public bO hi;
   public gv[] hj;

   public dj(Application var1) {
      this.ha.DataSource = (new dk(this));
      this.a("Multitool", true, this.ha);
      this.hb = new dl(this);
      this.a((string)"Name", (Control)this.hb);
      this.hc = new cN(typeof(gx));
      // PORT_TODO: this.hc.a((var1x) => {
         // PORT_TODO: gv var2 = (gv)this.ha.SelectedItem;
         // PORT_TODO: if (var2 != null) {
            // PORT_TODO: var2.ag(var1x);
         // PORT_TODO: }

// PORT_TODO: 
      // PORT_TODO: });
      // PORT_TODO: this.a((string)"Type", (Control)this.hc);
      // PORT_TODO: this.hd = new cN(typeof(gN));
      // PORT_TODO: this.hd.a((var1x) => {
         // PORT_TODO: gv var2 = (gv)this.ha.SelectedItem;
         // PORT_TODO: if (var2 != null) {
            // PORT_TODO: var2.aj(var1x);
         // PORT_TODO: }

// PORT_TODO: 
      // PORT_TODO: });
      // PORT_TODO: this.a((string)"Class", (Control)this.hd);
      // PORT_TODO: this.he = new dm(this);
      // PORT_TODO: this.a((string)"Seed", (G)this.he);
      // PORT_TODO: this.k("Base Stats");
      // PORT_TODO: this.hf = new dn(this);
      // PORT_TODO: this.a((string)"Damage", (Control)this.hf);
      // PORT_TODO: this.hg = new @do(this);
      // PORT_TODO: this.a((string)"Mining", (Control)this.hg);
      // PORT_TODO: this.hh = new dp(this);
      // PORT_TODO: this.a((string)"Scan", (Control)this.hh);
      // PORT_TODO: this.Y();
      // PORT_TODO: Panel var2 = new Panel();
      // PORT_TODO: this.bQ = new Button() { Text = "Delete Multitool" };
      // PORT_TODO: this.bQ.Click += (new dq(this, var1));
      // PORT_TODO: var2.Add(this.bQ);
      // PORT_TODO: this.bR = new Button() { Text = "Export" };
      // PORT_TODO: this.bR.Click += (new dr(this, var1));
      // PORT_TODO: var2.Add(this.bR);
      // PORT_TODO: this.bS = new Button() { Text = "Import" };
      // PORT_TODO: this.bS.Click += (new ds(this, var1));
      // PORT_TODO: var2.Add(this.bS);
      // PORT_TODO: this.a((Control)var2);
      // PORT_TODO: this.hi = new bO(var1);
      // PORT_TODO: this.b(this.hi);
   // PORT_TODO: }

// PORT_TODO: 
   // PORT_TODO: public void w() {
      // PORT_TODO: this.hi.w();
   // PORT_TODO: }

// PORT_TODO: 
   // PORT_TODO: public void x() {
      // PORT_TODO: this.hi.x();
   // PORT_TODO: }

// PORT_TODO: 
   // PORT_TODO: public void y() {
      // PORT_TODO: this.hi.y();
   // PORT_TODO: }

// PORT_TODO: 
   // PORT_TODO: public void z() {
      // PORT_TODO: this.hi.z();
   // PORT_TODO: }

// PORT_TODO: 
   // PORT_TODO: public void A() {
      // PORT_TODO: this.hi.A();
   // PORT_TODO: }

// PORT_TODO: 
   // PORT_TODO: public void a(gt var1) {
      // PORT_TODO: this.hi.a(var1);
   // PORT_TODO: }

// PORT_TODO: 
   // PORT_TODO: public gv[] aK() {
      // PORT_TODO: return this.hj;
   // PORT_TODO: }

// PORT_TODO: 
   // PORT_TODO: public void a(gv[] var1, gB var2) {
      // PORT_TODO: this.hj = var1;
      // PORT_TODO: if (var1.Length == 0) {
         // PORT_TODO: this.ha.SelectedIndex = (-1);
      // PORT_TODO: } else {
         // PORT_TODO: int var3 = var2 == null ? 0 : var2.dU();
         // PORT_TODO: if (var3 >= var1.Length) {
            // PORT_TODO: var3 = 0;
         // PORT_TODO: }

// PORT_TODO: 
         // PORT_TODO: this.ha.SelectedIndex = (var3);
      // PORT_TODO: }

// PORT_TODO: 
      // PORT_TODO: this.ha.Refresh();
   // PORT_TODO: }
   // PORT_TODO: public static gv[] a(dj var0) {
      // PORT_TODO: return var0.hj;
   // PORT_TODO: }
   // PORT_TODO: public static G b(dj var0) {
      // PORT_TODO: return var0.hb;
   // PORT_TODO: }
   // PORT_TODO: public static cN c(dj var0) {
      // PORT_TODO: return var0.hc;
   // PORT_TODO: }
   // PORT_TODO: public static cN d(dj var0) {
      // PORT_TODO: return var0.hd;
   // PORT_TODO: }
   // PORT_TODO: public static G e(dj var0) {
      // PORT_TODO: return var0.he;
   // PORT_TODO: }
   // PORT_TODO: public static G f(dj var0) {
      // PORT_TODO: return var0.hf;
   // PORT_TODO: }
   // PORT_TODO: public static G g(dj var0) {
      // PORT_TODO: return var0.hg;
   // PORT_TODO: }
   // PORT_TODO: public static G h(dj var0) {
      // PORT_TODO: return var0.hh;
   // PORT_TODO: }
   // PORT_TODO: public static bO i(dj var0) {
      // PORT_TODO: return var0.hi;
   // PORT_TODO: }
   // PORT_TODO: public static ComboBox j(dj var0) {
      // PORT_TODO: return var0.ha;
   // PORT_TODO: }
   // PORT_TODO: public static void a(dj var0, gv[] var1) {
      // PORT_TODO: var0.hj = var1;
   // PORT_TODO: }
// PORT_TODO: }



}
}
}
