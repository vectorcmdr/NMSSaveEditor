using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class ep : em {
   public ComboBox iw = new ComboBox();
   public bO ix;
   public gO[] iy;

   public ep(Application var1) {
      this.iw.DataSource = (new eq(this));
      this.a("Vehicle", true, this.iw);
      this.ix = new bO(var1);
      this.b(this.ix);
   }

   public void w() {
      this.ix.w();
   }

   public void x() {
      this.ix.x();
   }

   public void y() {
      this.ix.y();
   }

   public void A() {
      this.ix.A();
   }

   public void a(gt var1) {
      this.ix.a(var1);
   }

   public gO[] aT() {
      return this.iy;
   }

   public void a(gO[] var1) {
      if (var1.Length == 0) {
         this.iy = new gO[0];
         this.iw.SelectedIndex = (-1);
      } else {
         this.iy = var1;
         this.iw.SelectedIndex = (0);
      }

      this.iw.Refresh();
   }
   public static gO[] a(ep var0) {
      return var0.iy;
   }
   public static bO b(ep var0) {
      return var0.ix;
   }
}


#else

public class ep
{
   public ep() { }
   public ep(params object[] args) { }
   public ComboBox iw = default;
   public bO ix = default;
   public gO[] iy = System.Array.Empty<gO>();
   public void w() { }
   public void x() { }
   public void y() { }
   public void A() { }
   public void a(gt var1) { }
   public gO[] aT() { return System.Array.Empty<gO>(); }
   public static bO b(ep var0) { return default; }
}

#endif

}
