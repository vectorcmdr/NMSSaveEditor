package nomanssave;

import java.io.File;
import java.io.IOException;

class fM extends fQ implements fs {
   fn me;
   // $FF: synthetic field
   final fJ mt;

   fM(fJ var1, int var2) {
      super(var1, var2 == 0 ? "save.hg" : "save" + (var2 + 1) + ".hg", var2, true);
      this.mt = var1;

      try {
         String var3 = new String(this.ah(65536));
         this.me = fn.T(var3);
      } catch (IOException var4) {
         hc.a("Could not read game mode from " + this.filename, var4);
      }

   }

   fM(fJ var1, int var2, eY var3) {
      super(var1, var2 == 0 ? "save.hg" : "save" + (var2 + 1) + ".hg", var2, false);
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
      this.a(this.lO == 0 ? "backup" : "backup" + (this.lO + 1), this.me, this.getName(), this.getDescription());
      (new File(fJ.a(this.mt), this.filename)).delete();
      (new File(fJ.a(this.mt), "mf_" + this.filename)).delete();
   }

   public String b(eY var1) {
      this.a(this.lO == 0 ? "backup" : "backup" + (this.lO + 1), this.me, this.getName(), this.getDescription());
      this.mx.Y(var1.getValueAsString("CommonStateData.SaveName"));
      this.me = fn.i(var1);
      this.mx.al((int)var1.K("CommonStateData.TotalPlayTime"));
      this.a(var1, true);
      return this.filename;
   }

   public String toString() {
      return this.filename;
   }

   public String getName() {
      return this.mx.ck();
   }

   public String getDescription() {
      return this.mx.getDescription();
   }
}
