package nomanssave;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

class fW {
   final String name;
   final String filename;
   final String id;
   int mT;
   final int lL;
   String mU;
   long timestamp;
   final long mV;
   long mW;
   // $FF: synthetic field
   final fT mN;

   fW(fT var1, InputStream var2) {
      this.mN = var1;
      this.name = gc.c(var2);
      hc.info("  " + this.name);
      this.filename = gc.c(var2);
      hc.debug("    filename: " + this.filename);
      this.id = gc.c(var2);
      hc.debug("    id: " + this.id);
      this.mT = var2.read();
      if (this.mT < 0) {
         throw new IOException("short read");
      } else {
         hc.debug("    suffix: " + this.mT);
         this.lL = hk.readInt(var2);
         if (this.lL != 0) {
            hc.debug("    unknown1: " + Integer.toHexString(this.lL));
         }

         this.mU = gc.a(var2);
         hc.debug("    containerPath: " + this.mU);
         this.timestamp = gc.b(var2);
         hc.debug("    timestamp: " + new Date(this.timestamp));
         this.mV = hk.f(var2);
         if (this.mV != 0L) {
            hc.debug("    unknown2: " + Long.toHexString(this.mV));
         }

         this.mW = hk.f(var2);
         hc.debug("    totalSize: " + this.mW);
      }
   }

   fW(fT var1, String var2) {
      this(var1, (InputStream)(new ByteArrayInputStream(hk.aD(var2))));
   }

   fW(fT var1, fW var2) {
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

   void write(OutputStream var1) {
      gc.b(var1, this.name);
      gc.b(var1, this.filename);
      gc.b(var1, this.id);
      var1.write(this.mT);
      hk.a(var1, this.lL);
      gc.a(var1, this.mU);
      gc.a(var1, this.timestamp);
      hk.b(var1, this.mV);
      hk.b(var1, this.mW);
   }

   String cz() {
      ByteArrayOutputStream var1 = new ByteArrayOutputStream();
      this.write(var1);
      return hk.k(var1.toByteArray());
   }
}
