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
      this.hc.a((var1x) => {
         gv var2 = (gv)this.ha.SelectedItem;
         if (var2 != null) {
            var2.ag(var1x);
         }

      });
      this.a((string)"Type", (Control)this.hc);
      this.hd = new cN(typeof(gN));
      this.hd.a((var1x) => {
         gv var2 = (gv)this.ha.SelectedItem;
         if (var2 != null) {
            var2.aj(var1x);
         }

      });
      this.a((string)"Class", (Control)this.hd);
      this.he = new dm(this);
      this.a((string)"Seed", (G)this.he);
      this.k("Base Stats");
      this.hf = new dn(this);
      this.a((string)"Damage", (Control)this.hf);
      this.hg = new @do(this);
      this.a((string)"Mining", (Control)this.hg);
      this.hh = new dp(this);
      this.a((string)"Scan", (Control)this.hh);
      this.Y();
      Panel var2 = new Panel();
      this.bQ = new Button() { Text = "Delete Multitool" };
      this.bQ.Click += (new dq(this, var1));
      var2.Add(this.bQ);
      this.bR = new Button() { Text = "Export" };
      this.bR.Click += (new dr(this, var1));
      var2.Add(this.bR);
      this.bS = new Button() { Text = "Import" };
      this.bS.Click += (new ds(this, var1));
      var2.Add(this.bS);
      this.a((Control)var2);
      this.hi = new bO(var1);
      this.b(this.hi);
   }

   public void w() {
      this.hi.w();
   }

   public void x() {
      this.hi.x();
   }

   public void y() {
      this.hi.y();
   }

   public void z() {
      this.hi.z();
   }

   public void A() {
      this.hi.A();
   }

   public void a(gt var1) {
      this.hi.a(var1);
   }

   public gv[] aK() {
      return this.hj;
   }

   public void a(gv[] var1, gB var2) {
      this.hj = var1;
      if (var1.Length == 0) {
         this.ha.SelectedIndex = (-1);
      } else {
         int var3 = var2 == null ? 0 : var2.dU();
         if (var3 >= var1.Length) {
            var3 = 0;
         }

         this.ha.SelectedIndex = (var3);
      }

      this.ha.Refresh();
   }
   public static gv[] a(dj var0) {
      return var0.hj;
   }
   public static G b(dj var0) {
      return var0.hb;
   }
   public static cN c(dj var0) {
      return var0.hc;
   }
   public static cN d(dj var0) {
      return var0.hd;
   }
   public static G e(dj var0) {
      return var0.he;
   }
   public static G f(dj var0) {
      return var0.hf;
   }
   public static G g(dj var0) {
      return var0.hg;
   }
   public static G h(dj var0) {
      return var0.hh;
   }
   public static bO i(dj var0) {
      return var0.hi;
   }
   public static ComboBox j(dj var0) {
      return var0.ha;
   }
   public static void a(dj var0, gv[] var1) {
      var0.hj = var1;
   }
}



}
