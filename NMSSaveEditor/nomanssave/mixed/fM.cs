using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{



public class fM : fQ, fs {
   public DateTime LastWriteTimeUtc => DateTimeOffset.FromUnixTimeMilliseconds(lastModified()).UtcDateTime;
   public string Name => getName();
   public fn me;
   public fJ mt;

public fM(fJ var1, int var2) : base(var1, var2 == 0 ? "save.hg" : "save" + (var2 + 1) + ".hg", var2, true) {
      this.mt = var1;

      try {
         // PORT_TODO: string var3 = new string(this.ah(65536));
         // PORT_TODO: this.me = fn.T(var3);
      } catch (IOException var4) {
         hc.a("Could not read game mode from " + this.filename, var4);
      }

   }

public fM(fJ var1, int var2, eY var3) : base(var1, var2 == 0 ? "save.hg" : "save" + (var2 + 1) + ".hg", var2, false) {
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

   public void cm() {
      this.a(this.lO == 0 ? "backup" : "backup" + (this.lO + 1), this.me, this.Name, this.getDescription());
      (new FileInfo(System.IO.Path.Combine((fJ.a(this.mt)).ToString(), (this.filename).ToString()))).Delete();
      (new FileInfo(System.IO.Path.Combine((fJ.a(this.mt)).ToString(), ("mf_" + this.filename).ToString()))).Delete();
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
