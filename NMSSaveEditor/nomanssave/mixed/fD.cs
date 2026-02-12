using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class fD : fH, fs {
   public DateTime LastWriteTimeUtc => DateTimeOffset.FromUnixTimeMilliseconds(lastModified()).UtcDateTime;
   public new string Name => getName();
   public int lO;
   public fn me;
   public fA ma;

   public fD(fA var1, int var2) {
      base(var1, "savedata" + (var2 < 8 ? "0" : "") + Integer.toString(var2 + 2) + ".hg", true);
      this.ma = var1;
      this.lO = var2;

      try {
         string var3 = new string(this.ah(65536));
         this.me = fn.T(var3);
      } catch (IOException var4) {
         hc.a("Could not read game mode from " + this.mh.Name, var4);
      }

   }

   public fD(fA var1, int var2, byte[] var3, eY var4) {
      base(var1, "savedata" + (var2 < 8 ? "0" : "") + Integer.toString(var2 + 2) + ".hg", false);
      this.ma = var1;
      this.lO = var2;
      this.lK = var3;
      this.me = fn.i(var4);
      this.writeBytes(fA.l(var4));
   }

   public fn L() {
      return this.me;
   }

   public eY M() {
      return fA.b(this.readBytes(), eG.jV);
   }

   public string b(eY var1) {
      this.a(this.lO == 0 ? "ps4_backup" : "ps4_backup" + (this.lO + 1), this.me, this.Name, this.getDescription());
      this.writeBytes(fA.l(var1));
      return this.K();
   }

   public long lastModified() {
      return this.mh.LastWriteTimeUtc.Ticks;
   }

   public string toString() {
      return this.K();
   }
}


#else

public class fD
{
   public fD() { }
   public fD(params object[] args) { }
   public DateTime LastWriteTimeUtc = default;
   public int lO = 0;
   public fn me = default;
   public fA ma = default;
   public fn L() { return default; }
   public eY M() { return default; }
   public string b(eY var1) { return ""; }
   public long lastModified() { return 0; }
   public string toString() { return ""; }
}

#endif

}
