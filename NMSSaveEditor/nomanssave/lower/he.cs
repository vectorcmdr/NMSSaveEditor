using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{



public class he : Stream {
   public StreamWriter ss;
   public string st;
   public MemoryStream su;

   public he(StreamWriter var1, string var2) {
      this.ss = var1;
      this.st = var2;
      this.su = new MemoryStream();
   }

   public void write(int var1) {
      this.ss.Write(var1);
      this.su.Write(var1);
      if (var1 == 10) {
         if (hc.en() != null) {
            lock(hc.en()) {
               hc.en().Write(this.st.GetBytes(System.Text.Encoding.UTF8));
               hc.en().Write(this.su.toByteArray());
            }
         }

         this.su.reset();
      }

   }

   public void write(byte[] var1, int var2, int var3) {
      if (this.ss != null) {
         this.ss.Write(var1, var2, var3);
      }

      for(int var4 = 0; var4 < var3; ++var4) {
         if (var1[var2 + var4] == 10) {
            this.su.Write(var1, var2, var4 + 1);
            if (hc.en() != null) {
               lock(hc.en()) {
                  hc.en().Write(this.st.GetBytes(System.Text.Encoding.UTF8));
                  hc.en().Write(this.su.toByteArray());
               }
            }

            this.su.reset();
            var3 -= var4 + 1;
            var2 = var4 + 1;
            var4 = -1;
         }
      }

      this.su.Write(var1, var2, var3);
   }

   public void flush() {
      if (this.su.Count > 0) {
         this.su.Write(Environment.NewLine.GetBytes(System.Text.Encoding.UTF8));
         if (hc.en() != null) {
            lock(hc.en()) {
               hc.en().Write(this.st.GetBytes(System.Text.Encoding.UTF8));
               hc.en().Write(this.su.toByteArray());
            }
         }

         this.su.reset();
      }

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
