using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{



public class hn : Stream {
   public int sc;
   public hm sW;

   public hn(hm var1, int var2) {
      this.sW = var1;
      this.sc = var2;
   }

   public int read() {
      if (this.sc == 0) {
         return -1;
      } else {
         int var1 = hm.a(this.sW).ReadByte();
         if (var1 < 0) {
            throw new IOException("short read");
         } else {
            --this.sc;
            return var1;
         }
      }
   }

   public int read(byte[] var1) {
      return this.read(var1, 0, var1.Length);
   }

   public int read(byte[] var1, int var2, int var3) {
      if (this.sc == 0) {
         return -1;
      } else {
         if (var3 > this.sc) {
            var3 = this.sc;
         }

         var3 = hm.a(this.sW).read(var1, var2, var3);
         if (var3 <= 0) {
            throw new IOException("short read");
         } else {
            this.sc -= var3;
            return var3;
         }
      }
   }
   public hn(hm var1, int var2, hn var3) {
      this(var1, var2);
   }

   // Stream abstract member stubs
   public override bool CanRead => true;
   public override bool CanSeek => false;
   public override bool CanWrite => true;
   public override long Length => 0;
   public override long Position { get => 0; set { } }
   public override void Flush() { }
   public override int Read(byte[] buffer, int offset, int count) { return 0; }
   public override long Seek(long offset, SeekOrigin origin) { return 0; }
   public override void SetLength(long value) { }
   public override void Write(byte[] buffer, int offset, int count) { }
}



}
