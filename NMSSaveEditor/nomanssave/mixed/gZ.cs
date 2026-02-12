using K4os.Compression.LZ4;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

public class gZ : FilterOutputStream {
   public static LZ4Factory se = null /*LZ4Factory*/;
   public LZ4Compressor sf;
   public byte[] buffer;
   public int sg;
   public int sh;
   public int si;

   public gZ(Stream var1) {
      base(var1);
      this.sf = se.fastCompressor();
      this.buffer = new byte[524288];
      this.sg = 0;
      this.sh = 0;
      this.si = 0;
   }

   public void write(int var1) {
      if (this.sg == this.buffer.Length) {
         this.ek();
      }

      this.buffer[this.sg++] = (byte)var1;
   }

   public void write(byte[] var1) {
      this.Write(var1, 0, var1.Length);
   }

   public void write(byte[] var1, int var2, int var3) {
      if (var3 == this.buffer.Length) {
         this.ek();
      }

      while(var3 >= this.buffer.Length - this.sg) {
         int var4 = this.buffer.Length - this.sg;
         Array.Copy(var1, var2, this.buffer, this.sg, var4);
         this.sg = this.buffer.Length;
         this.ek();
         var2 += var4;
         var3 -= var4;
      }

      if (var3 > 0) {
         Array.Copy(var1, var2, this.buffer, this.sg, var3);
         this.sg += var3;
      }

   }

   public void ek() {
      int var1 = this.sf.maxCompressedLength(this.sg);
      byte[] var2 = new byte[var1];
      int var3 = this.sf.compress((byte[])this.buffer, 0, this.sg, (byte[])var2, 0, var1);
      byte[] var4 = new byte[]{229, 161, 237, 254, (byte)(255 & var3), (byte)(255 & var3 >> 8), (byte)(255 & var3 >> 16), (byte)(255 & var3 >> 24), (byte)(255 & this.sg), (byte)(255 & this.sg >> 8), (byte)(255 & this.sg >> 16), (byte)(255 & this.sg >> 24), 0, 0, 0, 0};
      this.@out.Write(var4);
      this.@out.Write(var2, 0, var3);
      this.sh += this.sg;
      this.sg = 0;
      this.si += var3 + 16;
   }

   public int ch() {
      return this.sh;
   }

   public int ci() {
      return this.si;
   }

   public void flush() {
      if (this.sg > 0) {
         this.ek();
      }

      this.@out.Flush();
   }

   public void close() {
      try {
         if (this.sg > 0) {
            this.ek();
         }
      } finally {
         this.@out.Close();
      }

   }
}

}
