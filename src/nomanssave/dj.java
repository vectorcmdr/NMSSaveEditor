package nomanssave;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class dj extends em {
   private static final double gX = 1000.0D;
   private static final double gY = 1000.0D;
   private static final double gZ = 1000.0D;
   private JComboBox ha = new JComboBox();
   private G hb;
   private cN hc;
   private cN hd;
   private G he;
   private G hf;
   private G hg;
   private G hh;
   private JButton bQ;
   private JButton bR;
   private JButton bS;
   private bO hi;
   private gv[] hj;

   dj(Application var1) {
      this.ha.setModel(new dk(this));
      this.a("Multitool", true, this.ha);
      this.hb = new dl(this);
      this.a((String)"Name", (JComponent)this.hb);
      this.hc = new cN(gx.class);
      this.hc.a((var1x) -> {
         gv var2 = (gv)this.ha.getSelectedItem();
         if (var2 != null) {
            var2.ag(var1x);
         }

      });
      this.a((String)"Type", (JComponent)this.hc);
      this.hd = new cN(gN.class);
      this.hd.a((var1x) -> {
         gv var2 = (gv)this.ha.getSelectedItem();
         if (var2 != null) {
            var2.aj(var1x);
         }

      });
      this.a((String)"Class", (JComponent)this.hd);
      this.he = new dm(this);
      this.a((String)"Seed", (G)this.he);
      this.k("Base Stats");
      this.hf = new dn(this);
      this.a((String)"Damage", (JComponent)this.hf);
      this.hg = new do(this);
      this.a((String)"Mining", (JComponent)this.hg);
      this.hh = new dp(this);
      this.a((String)"Scan", (JComponent)this.hh);
      this.Y();
      JPanel var2 = new JPanel();
      this.bQ = new JButton("Delete Multitool");
      this.bQ.addActionListener(new dq(this, var1));
      var2.add(this.bQ);
      this.bR = new JButton("Export");
      this.bR.addActionListener(new dr(this, var1));
      var2.add(this.bR);
      this.bS = new JButton("Import");
      this.bS.addActionListener(new ds(this, var1));
      var2.add(this.bS);
      this.a((JComponent)var2);
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
      if (var1.length == 0) {
         this.ha.setSelectedIndex(-1);
      } else {
         int var3 = var2 == null ? 0 : var2.dU();
         if (var3 >= var1.length) {
            var3 = 0;
         }

         this.ha.setSelectedIndex(var3);
      }

      this.ha.updateUI();
   }

   // $FF: synthetic method
   static gv[] a(dj var0) {
      return var0.hj;
   }

   // $FF: synthetic method
   static G b(dj var0) {
      return var0.hb;
   }

   // $FF: synthetic method
   static cN c(dj var0) {
      return var0.hc;
   }

   // $FF: synthetic method
   static cN d(dj var0) {
      return var0.hd;
   }

   // $FF: synthetic method
   static G e(dj var0) {
      return var0.he;
   }

   // $FF: synthetic method
   static G f(dj var0) {
      return var0.hf;
   }

   // $FF: synthetic method
   static G g(dj var0) {
      return var0.hg;
   }

   // $FF: synthetic method
   static G h(dj var0) {
      return var0.hh;
   }

   // $FF: synthetic method
   static bO i(dj var0) {
      return var0.hi;
   }

   // $FF: synthetic method
   static JComboBox j(dj var0) {
      return var0.ha;
   }

   // $FF: synthetic method
   static void a(dj var0, gv[] var1) {
      var0.hj = var1;
   }
}
