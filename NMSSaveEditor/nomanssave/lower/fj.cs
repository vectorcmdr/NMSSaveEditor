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

public class fj : Closeable {
   public static int kP = 2;
   public static int kQ = 8;
   public static byte[] le = "null".GetBytes();
   public static byte[] lf = "true".GetBytes();
   public static byte[] lg = "false".GetBytes();
   public Stream lh;
   public int flags;

   public static byte[] j(Object var0) {
      MemoryStream var1 = new MemoryStream();
      Exception var2 = null;
      Object var3 = null;

      try {
         fj var4 = new fj(var1, 0);

         try {
            var4.k(var0);
         } finally {
            if (var4 != null) {
               var4.Close();
            }

         }
      } catch (Exception var10) {
         if (var2 == null) {
            var2 = var10;
         } else if (var2 != var10) {
            var2.addSuppressed(var10);
         }

         throw var2;
      }

      return var1.toByteArray();
   }

   public static byte[] g(eY var0) {
      MemoryStream var1 = new MemoryStream();
      Exception var2 = null;
      Object var3 = null;

      try {
         fj var4 = new fj(var1, 0);

         try {
            var4.h(var0);
         } finally {
            if (var4 != null) {
               var4.Close();
            }

         }
      } catch (Exception var10) {
         if (var2 == null) {
            var2 = var10;
         } else if (var2 != var10) {
            var2.addSuppressed(var10);
         }

         throw var2;
      }

      return var1.toByteArray();
   }

   public static byte[] b(eV var0) {
      MemoryStream var1 = new MemoryStream();
      Exception var2 = null;
      Object var3 = null;

      try {
         fj var4 = new fj(var1, 0);

         try {
            var4.c(var0);
         } finally {
            if (var4 != null) {
               var4.Close();
            }

         }
      } catch (Exception var10) {
         if (var2 == null) {
            var2 = var10;
         } else if (var2 != var10) {
            var2.addSuppressed(var10);
         }

         throw var2;
      }

      return var1.toByteArray();
   }

   public fj(Stream var1) {
      // Constructor chain: base(var1, 0)
   }

   public fj(Stream var1, int var2) {
      this.lh = var1;
      this.flags = var2;
   }

   public void k(Object var1) {
      if (var1 == null) {
         this.lh.Write(le);
      } else if (var1.Equals(true)) {
         this.lh.Write(lf);
      } else if (var1.Equals(false)) {
         this.lh.Write(lg);
      } else if (var1 is string) {
         this.writeString((string)var1);
      } else if (var1 is fg) {
         this.c((fg)var1);
      } else if (var1 is fk) {
         this.a((eY)var1, ((fk)var1).li);
      } else if (var1 is eY) {
         this.a((eY)((eY)var1), (eC)null);
      } else if (var1 is eV) {
         this.a((eV)((eV)var1), (eC)null);
      } else {
         if (!(var1 is Number)) {
            throw new IOException("Cannot write value");
         }
          this.a((Number)var1);
      }
    }

   public void a(Object var1, eC var2) {
      if (var1 == null) {
         this.lh.Write(le);
      } else if (var1.Equals(true)) {
         this.lh.Write(lf);
      } else if (var1.Equals(false)) {
         this.lh.Write(lg);
      } else if (var1 is string) {
         this.writeString((string)var1);
      } else if (var1 is fg) {
         this.c((fg)var1);
      } else if (var1 is eY) {
         this.a((eY)var1, var2);
      } else if (var1 is eV) {
         this.a((eV)var1, var2);
      } else {
         if (!(var1 is Number)) {
            throw new IOException("Cannot write value");
         }
          this.a((Number)var1);
      }
    }

   public void writeString(string var1) {
      this.lh.Write(fh.O(var1).GetBytes(StandardCharsets.UTF_8));
   }

   public void c(fg var1) {
      this.lh.Write(34);
      byte[] var5;
      int var4 = (var5 = var1.toByteArray()).Length;
       for(int var3 = 0; var3 < var4; ++var3) {
         byte var2 = var5[var3];
         int var6 = var2 & 255;
         if (var6 == 13) {
            this.lh.Write("\\r".GetBytes(StandardCharsets.UTF_8));
         } else if (var6 == 10) {
            this.lh.Write("\\n".GetBytes(StandardCharsets.UTF_8));
         } else if (var6 == 9) {
            this.lh.Write("\\t".GetBytes(StandardCharsets.UTF_8));
         } else if (var6 == 12) {
            this.lh.Write("\\f".GetBytes(StandardCharsets.UTF_8));
         } else if (var6 == 8) {
            this.lh.Write("\\b".GetBytes(StandardCharsets.UTF_8));
         } else if (var6 == 34) {
            this.lh.Write("\\\"".GetBytes(StandardCharsets.UTF_8));
         } else if (var6 == 92) {
            this.lh.Write("\\\\".GetBytes(StandardCharsets.UTF_8));
         } else if (var6 >= 32) {
            this.lh.Write(var6);
         } else {
            StringBuffer var7 = new StringBuffer();
            var7.Append("\\u00");
            var7.Append("0123456789ABCDEFabcdef"[var6 >> 4 & 15]);
            var7.Append("0123456789ABCDEFabcdef"[var6 & 15]);
            this.lh.Write(var7.ToString().GetBytes(StandardCharsets.UTF_8));
         }
      }
       this.lh.Write(34);
   }

   public void h(eY var1) {
      this.a(var1, var1 is fk ? ((fk)var1).li : null);
   }

   public void a(eY var1, eC var2) {
      this.lh.Write(123);
      if (var1.Length > 0) {
         for(int var3 = 0; var3 < var1.Length; ++var3) {
            if (var3 > 0) {
               this.lh.Write(44);
            }

            this.writeString(var2 == null ? var1.names[var3] : var2.r(var1.names[var3]));
            this.lh.Write(58);
            this.a(var1.values[var3], var2);
         }
      }

      this.lh.Write(125);
   }

   public void c(eV var1) {
      this.a((eV)var1, (eC)null);
   }

   public void a(eV var1, eC var2) {
      this.lh.Write(91);
      if (var1.Length > 0) {
         for(int var3 = 0; var3 < var1.Length; ++var3) {
            if (var3 > 0) {
               this.lh.Write(44);
            }

            this.a(var1.values[var3], var2);
         }
      }

      this.lh.Write(93);
   }

   public void a(Number var1) {
      if (var1 is BigDecimal) {
         this.lh.Write(((BigDecimal)var1).ToString().Replace('E', 'e').GetBytes(StandardCharsets.UTF_8));
      } else {
         this.lh.Write(var1.ToString().GetBytes(StandardCharsets.UTF_8));
      }
    }

   public void close() {
      try {
         if ((this.flags & 2) != 0) {
            this.lh.Write(0);
         }
      } finally {
         if ((this.flags & 8) == 0) {
            this.lh.Close();
         }

      }

   }
   public void Dispose() {}
}

}