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

public class fM : fQ, fs {
   fn me;
   // $FF: synthetic field
   fJ mt;

   fM(fJ var1, int var2) {
      base(var1, var2 == 0 ? "save.hg" : "save" + (var2 + 1) + ".hg", var2, true);
      this.mt = var1;

      try {
         string var3 = new string(this.ah(65536));
         this.me = fn.T(var3);
      } catch (IOException var4) {
         hc.a("Could not read game mode from " + this.filename, var4);
      }

   }

   fM(fJ var1, int var2, eY var3) {
      base(var1, var2 == 0 ? "save.hg" : "save" + (var2 + 1) + ".hg", var2, false);
      this.mt = var1;
      this.me = fn.i(var3);
      this.a(var3, true);
   }

   public fn L() {
      return this.me;
   }

   public eY M() {
      return this.a(eG.jV);
   }

   void cm() {
      this.a(this.lO == 0 ? "backup" : "backup" + (this.lO + 1), this.me, this.Name, this.getDescription());
      (new File(fJ.a(this.mt), this.filename)).Delete();
      (new File(fJ.a(this.mt), "mf_" + this.filename)).Delete();
   }

   public string b(eY var1) {
      this.a(this.lO == 0 ? "backup" : "backup" + (this.lO + 1), this.me, this.Name, this.getDescription());
      this.mx.Y(var1.getValueAsString("CommonStateData.SaveName"));
      this.me = fn.i(var1);
      this.mx.al((int)var1.K("CommonStateData.TotalPlayTime"));
      this.a(var1, true);
      return this.filename;
   }

   public string toString() {
      return this.filename;
   }

   public string getName() {
      return this.mx.ck();
   }

   public string getDescription() {
      return this.mx.getDescription();
   }
}

}
