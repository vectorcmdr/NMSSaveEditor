package nomanssave;

import java.util.Collections;

public class aJ extends em {
   private static final int cV = 200;
   private static final int cW = 100;
   private static final int cX = 100;
   private static final long cY = 4294967295L;
   private static final long cZ = 4294967295L;
   private static final long da = 4294967295L;
   private G db;
   private G dc;
   private G dd;
   private G de;
   private G df;
   private G dg;
   private bO dh;
   private gz di;

   aJ(Application var1) {
      this.k("Main Stats");
      this.de = new aK(this);
      this.a("Health", this.de);
      this.df = new aL(this);
      this.a("Shield", this.df);
      this.dg = new aM(this);
      this.a("Energy", this.dg);
      this.db = new aN(this, var1);
      this.a("Units", this.db);
      this.dc = new aO(this);
      this.a("Nanites", this.dc);
      this.dd = new aP(this);
      this.a("Quicksilver", this.dd);
      this.dh = new bO(var1);
      this.b(this.dh);
   }

   void w() {
      this.dh.w();
   }

   void x() {
      this.dh.x();
   }

   void y() {
      this.dh.y();
   }

   void A() {
      this.dh.A();
   }

   void a(gt var1) {
      this.dh.a(var1);
   }

   gz X() {
      return this.di;
   }

   void a(gz var1) {
      if (var1 == null) {
         this.di = null;
         this.db.setText("");
         this.dc.setText("");
         this.dd.setText("");
         this.de.setText("");
         this.df.setText("");
         this.dg.setText("");
         this.dh.a(Collections.emptyList());
      } else {
         this.di = var1;
         this.db.setText(Long.toString(var1.dJ()));
         this.dc.setText(Long.toString(var1.dK()));
         this.dd.setText(Long.toString(var1.dL()));
         this.de.setText(Integer.toString(var1.dM()));
         this.df.setText(Integer.toString(var1.dN()));
         this.dg.setText(Integer.toString(var1.dO()));
         this.dh.a(var1.cC());
      }

   }

   // $FF: synthetic method
   static gz a(aJ var0) {
      return var0.di;
   }
}
