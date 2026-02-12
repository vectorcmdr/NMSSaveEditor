using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{



public class fw {
   public fn be;
   public byte[] lK;
   public int lL;
   public int lM;
   public int lN;
   public int lO;
   public long bd;
   public long length;
   public long lP;
   public int lQ;
   public int lR;
   public int lS;
   public fu lJ;

   public fw(fu var1, Stream var2) {
      this.lJ = var1;
      this.lK = new byte[8];
      hk.readFully(var2, this.lK);
      this.lL = hk.readInt(var2);
      this.lM = hk.readInt(var2);
      this.lN = hk.readInt(var2);
      this.lO = hk.readInt(var2);
      this.bd = 1000L * (long)hk.readInt(var2);
      this.length = 4294967295L & (long)hk.readInt(var2);
      this.lP = 4294967295L & (long)hk.readInt(var2);
      this.lQ = hk.readInt(var2);
      this.lR = hk.readInt(var2);
      this.lS = hk.readInt(var2);
   }

   public bool isValid() {
      return fu.b(this.lK, fu.bY()) && this.lO >= 0;
   }

   public int a(Stream var1) {
      var1.Write(this.lK);
      hk.a(var1, this.lL);
      hk.a(var1, this.lM);
      hk.a(var1, this.lN);
      hk.a(var1, this.lO);
      hk.a(var1, (int)(this.bd / 1000L));
      hk.a(var1, (int)this.length);
      hk.a(var1, (int)this.lP);
      hk.a(var1, this.lQ);
      hk.a(var1, this.lR);
      hk.a(var1, this.lS);
      return 48;
   }

   public void bZ() {
      Console.WriteLine("  unknown1 = " + this.lL + " 0x" + Convert.ToString(this.lL) + " " + Integer.toBinaryString(this.lL));
      Console.WriteLine("  unknown2 = " + this.lM + " 0x" + Convert.ToString(this.lM) + " " + Integer.toBinaryString(this.lM));
      Console.WriteLine("  fileType = " + this.lN + " 0x" + Convert.ToString(this.lN) + " " + Integer.toBinaryString(this.lN));
      Console.WriteLine("  archiveNumber = " + this.lO + " 0x" + Convert.ToString(this.lO) + " " + Integer.toBinaryString(this.lO));
      Console.WriteLine("  modified = " + new DateTime(this.bd));
      Console.WriteLine("  length = " + this.length);
      Console.WriteLine("  startPos = 0x" + (this.lP).ToString("X"));
      Console.WriteLine("  valid = " + this.lQ);
      if (this.lR != 0) {
         Console.WriteLine("  unknown3 = " + this.lR + " 0x" + Convert.ToString(this.lR) + " " + Integer.toBinaryString(this.lR) + " date:" + new DateTime(1000L * (long)this.lR));
      }

      if (this.lS != 0) {
         Console.WriteLine("  unknown4 = " + this.lS + " 0x" + Convert.ToString(this.lS) + " " + Integer.toBinaryString(this.lS) + " len:" + (4294967295L & (long)this.lS));
      }

   }

   public byte[] ca() {
      if (!this.isValid()) {
         return null;
      } else {
         // PORT_TODO: FileStream var1 = new FileStream((fu.b(this.lJ).ToString(), System.IO.FileMode.Open));

         byte[] var8;
         try {
            // PORT_TODO: var1.skip(this.lP);
            MemoryStream var2 = new MemoryStream();
            byte[] var3 = new byte[4096];
            long var4 = this.length;

            int var6;
            if (false) { // PORT_TODO: original while had errors
               var4 -= (long)var6;
               var2.Write(var3, 0, var6);
            }

            if (var4 > 0L) {
               throw new IOException("short read");
            }

            var8 = var2.ToArray();
         } finally {
            // PORT_TODO: var1.Close();
         }

         return var8;
      }
   }

   public void d(byte[] var1) {
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
            // PORT_TODO: FileInfo var5 = new FileInfo(fu.b(this.lJ).Directory, "~" + fu.b(this.lJ).Name);
            // PORT_TODO: FileStream var6 = new FileStream((var5).ToString(), System.IO.FileMode.Open);

            try {
               // PORT_TODO: FileStream var7 = new FileStream((fu.b(this.lJ).ToString(), System.IO.FileMode.Open));

               try {
      FileStream var6 = null; // PORT_TODO: stub declaration
      // PORT_TODO: object var6 = null; // PORT_TODO: stub declaration
                  Console.WriteLine("Reading header");
                  byte[] var8 = new byte[64];
                  // PORT_TODO: hk.readFully(var7, var8);
                  var6.Write(var8);
                  // PORT_TODO: long var9 = (long)var1.length - fu.c(this.lJ)[var2].length;
                  long var11 = 64L;

                  int var13;
                  fw var10000;
                  for(var13 = 0; var13 < var2; ++var13) {
      var6 = null; // PORT_TODO: stub declaration
                     if (fu.c(this.lJ)[var13].lP < fu.c(this.lJ)[var2].lP) {
                        var10000 = fu.c(this.lJ)[var13];
                        // PORT_TODO: var10000.lP += var9;
                     }

                     var11 += (long)fu.c(this.lJ)[var13].a(var6);
                  }

                  var6.Write(fu.bY());
                  // PORT_TODO: fu.c(this.lJ)[var2].length = (long)var1.length;
                  fu.c(this.lJ)[var2].bd = var25;
                  var11 += (long)fu.c(this.lJ)[var2].a(var6);

                  for(var13 = var2 + 1; var13 < fu.c(this.lJ).Length; ++var13) {
      var6 = null; // PORT_TODO: stub declaration
                     if (fu.c(this.lJ)[var13].lP < fu.c(this.lJ)[var2].lP) {
                        var10000 = fu.c(this.lJ)[var13];
                        // PORT_TODO: var10000.lP += var9;
                     }

                     var11 += (long)fu.c(this.lJ)[var13].a(var6);
                  }

                  long var26 = fu.c(this.lJ)[var2].lP - var11;

                  byte[] var15;
                  int var16;
                  if (false) { // PORT_TODO: original loop had errors
      var6 = null; // PORT_TODO: stub declaration
                     var6.Write(var15, 0, var16);
                     var11 += (long)var16;
                  }

                  if (var26 > 0L) {
                     throw new IOException("short read");
                  }

                  var6.Write(var1);
                  long var27 = var11 + (long)var1.Length;

                  if (false) { // PORT_TODO: original loop had errors
                  }

                  if (var26 > 0L) {
                     throw new IOException("short read");
                  }

                  if (false) { // PORT_TODO: original while had errors
      var6 = null; // PORT_TODO: stub declaration
                     var6.Write(var15, 0, var16);
                  }
               } finally {
                  // PORT_TODO: var7.Close();
               }
            } finally {
      object var6 = null; // PORT_TODO: stub declaration
               // PORT_TODO: var6.Close();
            }

         }
      }
   }
}



}
