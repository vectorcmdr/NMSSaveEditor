using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class aJ : em {
   public static int cV = 200;
   public static int cW = 100;
   public static int cX = 100;
   public static long cY = 4294967295L;
   public static long cZ = 4294967295L;
   public static long da = 4294967295L;
   public G db;
   public G dc;
   public G dd;
   public G de;
   public G df;
   public G dg;
   public bO dh;
   public gz di;

   public aJ(Application var1) {
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

   public void w() {
      this.dh.w();
   }

   public void x() {
      this.dh.x();
   }

   public void y() {
      this.dh.y();
   }

   public void A() {
      this.dh.A();
   }

   public void a(gt var1) {
      this.dh.a(var1);
   }

   public gz X() {
      return this.di;
   }

   public void a(gz var1) {
      if (var1 == null) {
         this.di = null;
         this.db.Text = ("");
         this.dc.Text = ("");
         this.dd.Text = ("");
         this.de.Text = ("");
         this.df.Text = ("");
         this.dg.Text = ("");
         this.dh.a(new List<object>());
      } else {
         this.di = var1;
         this.db.Text = (Long.toString(var1.dJ()));
         this.dc.Text = (Long.toString(var1.dK()));
         this.dd.Text = (Long.toString(var1.dL()));
         this.de.Text = (Integer.toString(var1.dM()));
         this.df.Text = (Integer.toString(var1.dN()));
         this.dg.Text = (Integer.toString(var1.dO()));
         this.dh.a(var1.cC());
      }

   }
   public static gz a(aJ var0) {
      return var0.di;
   }
}


#else

public class aJ
{
   public aJ() { }
   public aJ(params object[] args) { }
   public static int cV = 0;
   public static int cW = 0;
   public static int cX = 0;
   public static long cY = 0;
   public static long cZ = 0;
   public static long da = 0;
   public G db = default;
   public G dc = default;
   public G dd = default;
   public G de = default;
   public G df = default;
   public G dg = default;
   public bO dh = default;
   public gz di = default;
   public void w() { }
   public void x() { }
   public void y() { }
   public void A() { }
   public void a(gt var1) { }
   public gz X() { return default; }
}

#endif

}
