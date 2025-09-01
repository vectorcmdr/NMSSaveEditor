package nomanssave;

import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ha extends FilterInputStream {
   private boolean sj;
   private byte[] buffer;
   private int sk;
   private int sg;
   private int sl;
   private boolean eof;

   public ha(InputStream var1, int var2) {
      super(var1);
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

   private void aJ(int var1) {
      if (this.sg + var1 > this.buffer.length) {
         if (!this.sj) {
            throw new IOException("buffer exceeded");
         }

         int var2 = this.buffer.length;

         do {
            var2 += 1048576;
         } while(this.sg + var1 > var2);

         byte[] var3 = new byte[var2];
         System.arraycopy(this.buffer, 0, var3, 0, this.sg);
         this.buffer = var3;
      }

   }

   private boolean el() {
      if (this.eof) {
         return false;
      } else {
         int var1 = super.read();
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
                  var1 = super.read();
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

               while((var1 = super.read(this.buffer, this.sg, var4)) > 0) {
                  this.sg += var1;
                  var4 -= var1;
                  if (var4 == 0) {
                     break;
                  }
               }

               if (var4 > 0) {
                  throw new EOFException("Unexpected end of literal value");
               }
            }

            if (this.sg == this.buffer.length && !this.sj) {
               this.eof = true;
               return true;
            } else {
               var4 = super.read();
               if (var4 < 0) {
                  if (this.sj) {
                     this.eof = true;
                     return true;
                  } else {
                     throw new EOFException("Unexpected end of offset");
                  }
               } else {
                  int var5 = super.read();
                  if (var5 < 0) {
                     throw new EOFException("Unexpected end of offset");
                  } else {
                     var4 |= var5 << 8;
                     if (var3 == 15) {
                        do {
                           var1 = super.read();
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
                              System.arraycopy(this.buffer, var6, this.buffer, this.sg, var4);
                              var3 -= var4;
                              this.sg += var4;
                           } while(var3 > var4);

                           System.arraycopy(this.buffer, var6, this.buffer, this.sg, var3);
                           this.sg += var3;
                        } else {
                           System.arraycopy(this.buffer, this.sg - var4, this.buffer, this.sg, var3);
                           this.sg += var3;
                        }

                        this.sl = Math.max(this.sl, var4);
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
      return this.read(var1, 0, var1.length);
   }

   public int read(byte[] var1, int var2, int var3) {
      if (this.sk == this.sg && !this.el()) {
         return -1;
      } else {
         int var4 = Math.min(var3 - var2, this.sg - this.sk);
         System.arraycopy(this.buffer, this.sk, var1, var2, var4);
         this.sk += var4;
         return var4;
      }
   }
}
