using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class fW {
   public string name;
   public string filename;
   public string id;
   public int mT;
   public int lL;
   public string mU;
   public long timestamp;
   public long mV;
   public long mW;
   public fT mN;

   public fW(fT var1, Stream var2) {
      this.mN = var1;
      this.name = gc.c(var2);
      hc.info("  " + this.name);
      this.filename = gc.c(var2);
      hc.debug("    filename: " + this.filename);
      this.id = gc.c(var2);
      hc.debug("    id: " + this.id);
      this.mT = var2.ReadByte();
      if (this.mT < 0) {
         throw new IOException("short read");
      } else {
         hc.debug("    suffix: " + this.mT);
         this.lL = hk.readInt(var2);
         if (this.lL != 0) {
            hc.debug("    unknown1: " + Convert.ToString(this.lL));
         }

         this.mU = gc.a(var2);
         hc.debug("    containerPath: " + this.mU);
         this.timestamp = gc.b(var2);
         hc.debug("    timestamp: " + new DateTime(this.timestamp));
         this.mV = hk.f(var2);
         if (this.mV != 0L) {
            hc.debug("    unknown2: " + (this.mV).ToString("X"));
         }

         this.mW = hk.f(var2);
         hc.debug("    totalSize: " + this.mW);
      }
   }

   public fW(fT var1, string var2) {
      this(var1, (Stream)(new MemoryStream(hk.aD(var2))));
   }

   public fW(fT var1, fW var2) {
      this.mN = var1;
      this.name = var2.name;
      this.filename = var2.filename;
      this.id = var2.id;
      this.mT = var2.mT;
      this.lL = var2.lL;
      this.mU = fT.a(var1);
      this.timestamp = var2.timestamp;
      this.mV = var2.mV;
      this.mW = var2.mW;
   }

   public void write(Stream var1) {
      gc.b(var1, this.name);
      gc.b(var1, this.filename);
      gc.b(var1, this.id);
      var1.Write(this.mT);
      hk.a(var1, this.lL);
      gc.a(var1, this.mU);
      gc.a(var1, this.timestamp);
      hk.b(var1, this.mV);
      hk.b(var1, this.mW);
   }

   public string cz() {
      MemoryStream var1 = new MemoryStream();
      this.Write(var1);
      return hk.k(var1.toByteArray());
   }
}


#else

public class fW
{
   public fW() { }
   public fW(params object[] args) { }
   public string name = "";
   public string filename = "";
   public string id = "";
   public int mT = 0;
   public int lL = 0;
   public string mU = "";
   public long timestamp = 0;
   public long mV = 0;
   public long mW = 0;
   public fT mN = default;
   public void write(Stream var1) { }
   public string cz() { return ""; }
}

#endif

}
