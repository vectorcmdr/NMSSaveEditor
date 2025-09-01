package nomanssave;

import javax.swing.JComboBox;

public class ep extends em {
   private JComboBox iw = new JComboBox();
   private bO ix;
   private gO[] iy;

   ep(Application var1) {
      this.iw.setModel(new eq(this));
      this.a("Vehicle", true, this.iw);
      this.ix = new bO(var1);
      this.b(this.ix);
   }

   void w() {
      this.ix.w();
   }

   void x() {
      this.ix.x();
   }

   void y() {
      this.ix.y();
   }

   void A() {
      this.ix.A();
   }

   void a(gt var1) {
      this.ix.a(var1);
   }

   gO[] aT() {
      return this.iy;
   }

   void a(gO[] var1) {
      if (var1.length == 0) {
         this.iy = new gO[0];
         this.iw.setSelectedIndex(-1);
      } else {
         this.iy = var1;
         this.iw.setSelectedIndex(0);
      }

      this.iw.updateUI();
   }

   // $FF: synthetic method
   static gO[] a(ep var0) {
      return var0.iy;
   }

   // $FF: synthetic method
   static bO b(ep var0) {
      return var0.ix;
   }
}
