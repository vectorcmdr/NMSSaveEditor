using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Windows.Forms;
using System.Globalization;

namespace NMSSaveEditor
{

public class ep : em {
   public ComboBox iw = new ComboBox();
   public bO ix;
   public gO[] iy;

   public ep(Application var1) {
      this.iw.SetModel(new eq(this));
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
         this.iw.SetSelectedIndex(-1);
      } else {
         this.iy = var1;
         this.iw.SetSelectedIndex(0);
      }

      this.iw.updateUI();
   }

   // $FF: synthetic method
   public static gO[] a(ep var0) {
      return var0.iy;
   }

   // $FF: synthetic method
   public static bO b(ep var0) {
      return var0.ix;
   }
}

}
