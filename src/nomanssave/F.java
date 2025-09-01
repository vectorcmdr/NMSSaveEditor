package nomanssave;

import java.io.IOException;

class F implements fs {
   final String filename;
   final long bd;
   final fn be;
   final eY bf;
   // $FF: synthetic field
   final Application aZ;

   public F(Application var1, String var2, long var3, fn var5, eY var6) {
      this.aZ = var1;
      this.filename = var2;
      this.bd = var3;
      this.be = var5;
      this.bf = var6;
   }

   public String K() {
      return this.filename;
   }

   public fn L() {
      return this.be;
   }

   public long lastModified() {
      return this.bd;
   }

   public eY M() {
      return this.bf;
   }

   public String b(eY var1) {
      throw new IOException("Save not supported!");
   }

   public String toString() {
      return this.filename;
   }
}
