using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{



public class ha : FilterInputStream {
   public bool sj;
   public byte[] buffer;
   public int sk;
   public int sg;
   public int sl;
   public bool eof;

public ha(Stream var1, int var2) : base(var1) {
      if (var2 == 0) {
         this.sj = true;
         this.buffer = new byte[1048576];
      } else {
         this.sj = false;
         this.buffer = new byte[var2];
      }

      this.sg = 0;
      this.sk = 0;
      this.eof = false;
   }

   public void aJ(int var1) {
      if (this.sg + var1 > this.buffer.Length) {
         if (!this.sj) {
            throw new IOException("buffer exceeded");
         }

         int var2 = this.buffer.Length;

         do {
            var2 += 1048576;
         } while(this.sg + var1 > var2);

         byte[] var3 = new byte[var2];
         Array.Copy(this.buffer, 0, var3, 0, this.sg);
         this.buffer = var3;
      }

   }

   public bool el() {
      if (this.eof) {
         return false;
      } else {
         int var1 = base.ReadByte();
         if (var1 < 0) {
            if (this.sj) {
               this.eof = true;
               return false;
            } else {
               throw new EOFException("Unexpected end of stream");
            }
         } else {
            int var2 = var1 >> 4;
            int var3 = var1 & 15;
            if (var2 == 15) {
               do {
                  var1 = base.ReadByte();
                  if (var1 < 0) {
                     throw new EOFException("Unexpected end of literal length");
                  }

                  var2 += var1;
               } while(var1 == 255);
            }

            int var4;
            if (var2 > 0) {
               var4 = var2;
               this.aJ(var2);

               if (false) { // PORT_TODO: original while had errors
                  this.sg += var1;
                  var4 -= var1;
                  if (var4 == 0) {
                     // PORT_TODO: break;
                  }
               }

               if (var4 > 0) {
                  throw new EOFException("Unexpected end of literal value");
               }
            }

            if (this.sg == this.buffer.Length && !this.sj) {
               this.eof = true;
               return true;
            } else {
               var4 = base.ReadByte();
               if (var4 < 0) {
                  if (this.sj) {
                     this.eof = true;
                     return true;
                  } else {
                     throw new EOFException("Unexpected end of offset");
                  }
               } else {
                  int var5 = base.ReadByte();
                  if (var5 < 0) {
                     throw new EOFException("Unexpected end of offset");
                  } else {
                     var4 |= var5 << 8;
                     if (var3 == 15) {
                        do {
                           var1 = base.ReadByte();
                           if (var1 < 0) {
                              throw new EOFException("Unexpected end of literal length");
                           }

                           var3 += var1;
                        } while(var1 == 255);
                     }

                     var3 += 4;
                     if (var4 == 0) {
                        throw new EOFException("Offset is zero!");
                     } else if (var4 > this.sg) {
                        throw new EOFException("Buffer too small");
                     } else {
                        this.aJ(var3);
                        if (var3 > var4) {
                           int var6 = this.sg - var4;

                           do {
                              Array.Copy(this.buffer, var6, this.buffer, this.sg, var4);
                              var3 -= var4;
                              this.sg += var4;
                           } while(var3 > var4);

                           Array.Copy(this.buffer, var6, this.buffer, this.sg, var3);
                           this.sg += var3;
                        } else {
                           Array.Copy(this.buffer, this.sg - var4, this.buffer, this.sg, var3);
                           this.sg += var3;
                        }

                        this.sl = Math.Max(this.sl, var4);
                        return true;
                     }
                  }
               }
            }
         }
      }
   }

   public int available() {
      return this.sk == this.sg && !this.el() ? 0 : this.sg - this.sk;
   }

   public int read() {
      return this.sk == this.sg && !this.el() ? -1 : 255 & this.buffer[this.sk++];
   }

   public int read(byte[] var1) {
      return this.read(var1, 0, var1.Length);
   }

   public int read(byte[] var1, int var2, int var3) {
      if (this.sk == this.sg && !this.el()) {
         return -1;
      } else {
         int var4 = Math.Min(var3 - var2, this.sg - this.sk);
         Array.Copy(this.buffer, this.sk, var1, var2, var4);
         this.sk += var4;
         return var4;
      }
   }
}



}
