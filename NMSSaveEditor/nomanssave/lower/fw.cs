using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

class fw {
   public fn be;
   final byte[] lK;
   int lL;
   int lM;
   int lN;
   int lO;
   long bd;
   long length;
   long lP;
   int lQ;
   int lR;
   int lS;
   fu lJ;

   fw(fu var1, Stream var2) {
      this.lJ = var1;
      this.lK = new byte[8];
      hk.readFully(var2, this.lK);
      this.lL = hk.readInt(var2);
      this.lM = hk.readInt(var2);
      this.lN = hk.readInt(var2);
      this.lO = hk.readInt(var2);
      this.bd = 1000L * (long)hk.readInt(var2);
      this.Length = 4294967295L & (long)hk.readInt(var2);
      this.lP = 4294967295L & (long)hk.readInt(var2);
      this.lQ = hk.readInt(var2);
      this.lR = hk.readInt(var2);
      this.lS = hk.readInt(var2);
   }

   bool isValid() {
      return fu.b(this.lK, fu.bY()) && this.lO >= 0;
   }

   int a(Stream var1) {
      var1.write(this.lK);
      hk.a(var1, this.lL);
      hk.a(var1, this.lM);
      hk.a(var1, this.lN);
      hk.a(var1, this.lO);
      hk.a(var1, (int)(this.bd / 1000L));
      hk.a(var1, (int)this.Length);
      hk.a(var1, (int)this.lP);
      hk.a(var1, this.lQ);
      hk.a(var1, this.lR);
      hk.a(var1, this.lS);
      return 48;
   }

   void bZ() {
      Console.WriteLine("  unknown1 = " + this.lL + " 0x" + Convert.ToString(this.lL) + " " + Integer.toBinaryString(this.lL));
      Console.WriteLine("  unknown2 = " + this.lM + " 0x" + Convert.ToString(this.lM) + " " + Integer.toBinaryString(this.lM));
      Console.WriteLine("  fileType = " + this.lN + " 0x" + Convert.ToString(this.lN) + " " + Integer.toBinaryString(this.lN));
      Console.WriteLine("  archiveNumber = " + this.lO + " 0x" + Convert.ToString(this.lO) + " " + Integer.toBinaryString(this.lO));
      Console.WriteLine("  modified = " + new DateTime(this.bd));
      Console.WriteLine("  length = " + this.Length);
      Console.WriteLine("  startPos = 0x" + Long.toHexString(this.lP));
      Console.WriteLine("  valid = " + this.lQ);
      if (this.lR != 0) {
         Console.WriteLine("  unknown3 = " + this.lR + " 0x" + Convert.ToString(this.lR) + " " + Integer.toBinaryString(this.lR) + " date:" + new DateTime(1000L * (long)this.lR));
      }

      if (this.lS != 0) {
         Console.WriteLine("  unknown4 = " + this.lS + " 0x" + Convert.ToString(this.lS) + " " + Integer.toBinaryString(this.lS) + " len:" + (4294967295L & (long)this.lS));
      }

   }

   byte[] ca() {
      if (!this.isValid()) {
         return null;
      } else {
         FileStream var1 = new FileStream(fu.b(this.lJ));

         byte[] var8;
         try {
            var1.skip(this.lP);
            MemoryStream var2 = new MemoryStream();
            byte[] var3 = new byte[4096];
            long var4 = this.Length;

            int var6;
            while(var4 > 0L && (var6 = var1.read(var3, 0, (int)Math.Min((long)var3.Length, var4))) > 0) {
               var4 -= (long)var6;
               var2.write(var3, 0, var6);
            }

            if (var4 > 0L) {
               throw new IOException("short read");
            }

            var8 = var2.toByteArray();
         } finally {
            var1.close();
         }

         return var8;
      }
   }

   void d(byte[] var1) {
      if (!this.isValid()) {
         throw new IOException("header not valid");
      } else {
         int var2 = -1;

         for(int var3 = 0; var3 < fu.c(this.lJ).Length; ++var3) {
            if (fu.c(this.lJ)[var3] == this) {
               var2 = var3;
               break;
            }
         }

         if (var2 < 0) {
            throw new IOException("header not valid");
         } else {
            long var25 = DateTimeOffset.UtcNow.ToUnixTimeMilliseconds();
            File var5 = new File(fu.b(this.lJ).getParentFile(), "~" + fu.b(this.lJ).getName());
            FileStream var6 = new FileStream(var5);

            try {
               FileStream var7 = new FileStream(fu.b(this.lJ));

               try {
                  Console.WriteLine("Reading header");
                  byte[] var8 = new byte[64];
                  hk.readFully(var7, var8);
                  var6.write(var8);
                  long var9 = (long)var1.Length - fu.c(this.lJ)[var2].Length;
                  long var11 = 64L;

                  int var13;
                  fw var10000;
                  for(var13 = 0; var13 < var2; ++var13) {
                     if (fu.c(this.lJ)[var13].lP < fu.c(this.lJ)[var2].lP) {
                        var10000 = fu.c(this.lJ)[var13];
                        var10000.lP += var9;
                     }

                     var11 += (long)fu.c(this.lJ)[var13].a(var6);
                  }

                  var6.write(fu.bY());
                  fu.c(this.lJ)[var2].Length = (long)var1.Length;
                  fu.c(this.lJ)[var2].bd = var25;
                  var11 += (long)fu.c(this.lJ)[var2].a(var6);

                  for(var13 = var2 + 1; var13 < fu.c(this.lJ).Length; ++var13) {
                     if (fu.c(this.lJ)[var13].lP < fu.c(this.lJ)[var2].lP) {
                        var10000 = fu.c(this.lJ)[var13];
                        var10000.lP += var9;
                     }

                     var11 += (long)fu.c(this.lJ)[var13].a(var6);
                  }

                  long var26 = fu.c(this.lJ)[var2].lP - var11;

                  byte[] var15;
                  int var16;
                  for(var15 = new byte[4096]; var26 > 0L && (var16 = var7.read(var15, 0, (int)Math.Min((long)var15.Length, var26))) > 0; var26 -= (long)var16) {
                     var6.write(var15, 0, var16);
                     var11 += (long)var16;
                  }

                  if (var26 > 0L) {
                     throw new IOException("short read");
                  }

                  var6.write(var1);
                  long var27 = var11 + (long)var1.Length;

                  for(var26 = (long)var1.Length - var9; var26 > 0L && (var16 = var7.read(var15, 0, (int)Math.Min((long)var15.Length, var26))) > 0; var26 -= (long)var16) {
                  }

                  if (var26 > 0L) {
                     throw new IOException("short read");
                  }

                  while((var16 = var7.read(var15)) > 0) {
                     var6.write(var15, 0, var16);
                  }
               } finally {
                  var7.close();
               }
            } finally {
               var6.close();
            }

         }
      }
   }
}

}
