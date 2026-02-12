using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

public class dj : em {
   private static double gX = 1000.0D;
   private static double gY = 1000.0D;
   private static double gZ = 1000.0D;
   private ComboBox ha = new ComboBox();
   private G hb;
   private cN hc;
   private cN hd;
   private G he;
   private G hf;
   private G hg;
   private G hh;
   private Button bQ;
   private Button bR;
   private Button bS;
   private bO hi;
   private gv[] hj;

   dj(Application var1) {
      this.ha.setModel(new dk(this));
      this.a("Multitool", true, this.ha);
      this.hb = new dl(this);
      this.a((string)"Name", (Control)this.hb);
      this.hc = new cN(gx.class);
      this.hc.a((var1x) -> {
         gv var2 = (gv)this.ha.getSelectedItem();
         if (var2 != null) {
            var2.ag(var1x);
         }

      });
      this.a((string)"Type", (Control)this.hc);
      this.hd = new cN(gN.class);
      this.hd.a((var1x) -> {
         gv var2 = (gv)this.ha.getSelectedItem();
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
      this.hg = new do(this);
      this.a((string)"Mining", (Control)this.hg);
      this.hh = new dp(this);
      this.a((string)"Scan", (Control)this.hh);
      this.Y();
      Panel var2 = new Panel();
      this.bQ = new Button("Delete Multitool");
      this.bQ.addActionListener(new dq(this, var1));
      var2.Add(this.bQ);
      this.bR = new Button("Export");
      this.bR.addActionListener(new dr(this, var1));
      var2.Add(this.bR);
      this.bS = new Button("Import");
      this.bS.addActionListener(new ds(this, var1));
      var2.Add(this.bS);
      this.a((Control)var2);
      this.hi = new bO(var1);
      this.b(this.hi);
   }

   void w() {
      this.hi.w();
   }

   void x() {
      this.hi.x();
   }

   void y() {
      this.hi.y();
   }

   void z() {
      this.hi.z();
   }

   void A() {
      this.hi.A();
   }

   void a(gt var1) {
      this.hi.a(var1);
   }

   gv[] aK() {
      return this.hj;
   }

   void a(gv[] var1, gB var2) {
      this.hj = var1;
      if (var1.Length == 0) {
         this.ha.setSelectedIndex(-1);
      } else {
         int var3 = var2 == null ? 0 : var2.dU();
         if (var3 >= var1.Length) {
            var3 = 0;
         }

         this.ha.setSelectedIndex(var3);
      }

      this.ha.updateUI();
   }
   static gv[] a(dj var0) {
      return var0.hj;
   }
   static G b(dj var0) {
      return var0.hb;
   }
   static cN c(dj var0) {
      return var0.hc;
   }
   static cN d(dj var0) {
      return var0.hd;
   }
   static G e(dj var0) {
      return var0.he;
   }
   static G f(dj var0) {
      return var0.hf;
   }
   static G g(dj var0) {
      return var0.hg;
   }
   static G h(dj var0) {
      return var0.hh;
   }
   static bO i(dj var0) {
      return var0.hi;
   }
   static ComboBox j(dj var0) {
      return var0.ha;
   }
   static void a(dj var0, gv[] var1) {
      var0.hj = var1;
   }
}

}
