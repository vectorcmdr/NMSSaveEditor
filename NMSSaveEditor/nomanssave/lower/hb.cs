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

public class hb : FilterOutputStream {
   private static LZ4Factory se = LZ4Factory.safeInstance();
   private static int sm = 65536;
   private LZ4Compressor sf;
   private byte[] buffer;
   private int sg;
   private int si;

   public hb(Stream var1) {
      base(var1);
      this.sf = se.fastCompressor();
      this.buffer = new byte[65536];
      this.sg = 0;
      this.si = 0;
   }

   private void aK(int var1) {
      if (this.sg + var1 > this.buffer.length) {
         var1 += this.buffer.length;
         int var2 = (this.buffer.length + var1) / 65536;
         if ((this.buffer.length + var1) % 65536 > 0) {
            ++var2;
         }

         var2 *= 65536;
         byte[] var3 = new byte[var2];
         Array.Copy(this.buffer, 0, var3, 0, this.sg);
         this.buffer = var3;
      }

   }

   public void write(int var1) {
      this.aK(1);
      this.buffer[this.sg++] = (byte)var1;
   }

   public void write(byte[] var1) {
      this.Write(var1, 0, var1.length);
   }

   public void write(byte[] var1, int var2, int var3) {
      this.aK(var3);
      Array.Copy(var1, var2, this.buffer, this.sg, var3);
      this.sg += var3;
   }

   public int ch() {
      return this.sg;
   }

   public int ci() {
      return this.si;
   }

   public void flush() {
      this.out.Flush();
   }

   public void close() {
      try {
         if (this.sg > 0) {
            int var1 = this.sf.maxCompressedLength(this.sg);
            byte[] var2 = new byte[var1];
            this.si = this.sf.compress((byte[])this.buffer, 0, this.sg, (byte[])var2, 0, var1);
            this.out.Write(var2, 0, this.si);
         }
      } finally {
         this.out.Close();
      }

   }
}

}
