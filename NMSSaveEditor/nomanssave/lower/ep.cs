using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

public class ep : em {
   private ComboBox iw = new ComboBox();
   private bO ix;
   private gO[] iy;

   ep(Application var1) {
      this.iw.DataSource = (new eq(this));
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
      if (var1.Length == 0) {
         this.iy = new gO[0];
         this.iw.SelectedIndex = (-1);
      } else {
         this.iy = var1;
         this.iw.SelectedIndex = (0);
      }

      this.iw.Refresh();
   }
   static gO[] a(ep var0) {
      return var0.iy;
   }
   static bO b(ep var0) {
      return var0.ix;
   }
}

}
