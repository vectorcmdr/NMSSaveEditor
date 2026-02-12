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

public class ff : Closeable {
   public static int kO = 1;
   public static int kP = 2;
   public static int kQ = 4;
   public Stream @in;
   public int flags;
   public int kR;
   public CharsetDecoder kS;

   public static Object a(byte[] var0) {
      ff var3 = new ff(new MemoryStream(var0), 0);
      try {
         return var3.bJ();
      } finally {
         var3.close();
      }
   }

   public static eY b(byte[] var0) {
      ff var3 = new ff(new MemoryStream(var0), 0);
      try {
         return var3.bK();
      } finally {
         var3.close();
      }
   }

   public static eV c(byte[] var0) {
      ff var3 = new ff(new MemoryStream(var0), 0);
      try {
         return var3.bL();
      } finally {
         var3.close();
      }
   }

   public ff(Stream var1) : this(var1, 0) {
   }

   public ff(Stream var1, int var2) {
      this.@in = var1;
      this.flags = var2;
      this.kR = -1;
      this.kS = new CharsetDecoder().onMalformedInput(CodingErrorAction.REPORT).onUnmappableCharacter(CodingErrorAction.REPORT);
   }

   public int read() {
      if (this.kR >= 0) {
         int var1 = this.kR;
         this.kR = -1;
         return var1;
      } else {
         return this.@in.ReadByte();
      }
   }

   public int a(Predicate var1) {
      if (this.kR < 0) {
         this.kR = this.@in.ReadByte();
      }

      if (this.kR >= 0 && var1(this.kR)) {
         int var2 = this.kR;
         this.kR = -1;
         return var2;
      } else {
         return -1;
      }
   }

   public int bI() {
      if ((this.flags & 1) != 0) {
         return this.read();
      } else {
         if (this.kR < 0) {
            this.kR = this.@in.ReadByte();
         }

         while(this.kR == 32 || this.kR == 13 || this.kR == 10 || this.kR == 9) {
            this.kR = this.@in.ReadByte();
         }

         if (this.kR >= 0) {
            int var1 = this.kR;
            this.kR = -1;
            return var1;
         } else {
            return -1;
         }
      }
   }

   public void close() {
      try {
         if (this.kR < 0) {
            this.kR = this.@in.ReadByte();
         }

         if ((this.flags & 1) == 0) {
            while(this.kR == 32 || this.kR == 13 || this.kR == 10 || this.kR == 9) {
               this.kR = this.@in.ReadByte();
            }
         }

         if ((this.flags & 2) != 0) {
            if (this.kR != 0) {
               throw new eX("Missing null terminator");
            }

            this.kR = -1;
         }

         if (this.kR >= 0) {
            throw new eX("Unexpected termination: " + this.kR);
         }
      } finally {
         if ((this.flags & 4) == 0) {
            this.@in.Close();
         }

      }

   }

   public void Dispose() { close(); }

   public Object bJ() {
      return this.a(this.bI(), (eC)null);
   }

   public eY bK() {
      int var1 = this.bI();
      if (var1 < 0) {
         throw new eX("Short read");
      } else if (var1 != 123) {
         throw new eX("Unexpected token");
      } else {
         return this.a((eC)null);
      }
   }

   public eY a(eG var1) {
      int var2 = this.bI();
      if (var2 < 0) {
         throw new eX("Short read");
      } else if (var2 != 123) {
         throw new eX("Unexpected token");
      } else {
         Object var3 = null;
         eC var4 = null;
         int var5 = this.bI();
         if (var5 != 34) {
            if (var5 != 125) {
               throw new eX("Invalid token");
            }
         } else {
            while(true) {
               string var6 = this.bN();
               if (var3 == null) {
                  if (var1 != null && (var4 = eC.a(var1, var6)) != null) {
                     var3 = new fk(var4);
                  } else {
                     var3 = new eY();
                  }
               }
                if (var4 != null) {
                  var6 = var4.q(var6);
               }
                if (this.bI() != 58) {
                  throw new eX("Invalid token");
               }
                Object var7 = this.a(this.bI(), var4);
               ((eY)var3).a(var6, var7);
               var5 = this.bI();
               if (var5 == 125) {
                  break;
               }
                if (var5 != 44) {
                  throw new eX("Invalid token");
               }
                var5 = this.bI();
               if (var5 != 34) {
                  throw new eX("Invalid token");
               }
            }
         }
          if (var3 == null) {
            var3 = new eY();
         }
          if (((eY)var3).H("PlayerStateData") == null) {
            ((eY)var3).b("PlayerStateData", (var0) => {
               string var1x = var0.getValueAsString("ActiveContext");
               if ("Main".Equals(var1x) && var0.H("BaseContext.PlayerStateData") != null) {
                  return "BaseContext.PlayerStateData";
               } else {
                  return "Season".Equals(var1x) && var0.H("ExpeditionContext.PlayerStateData") != null ? "ExpeditionContext.PlayerStateData" : "PlayerStateData";
               }
            });
         }
          return (eY)var3;
      }
   }

   public eV bL() {
      int var1 = this.bI();
      if (var1 < 0) {
         throw new eX("Short read");
      } else if (var1 != 91) {
         throw new eX("Unexpected token");
      } else {
         return this.b((eC)null);
      }
   }

   public Object a(int var1, eC var2) {
      if (var1 < 0) {
         throw new eX("Short read");
      } else if (var1 == 123) {
         return this.a(var2);
      } else if (var1 == 91) {
         return this.b(var2);
      } else if (var1 == 34) {
         return this.bO();
      } else if (var1 == 116) {
         if (this.read() != 114) {
            throw new eX("Invalid token");
         } else if (this.read() != 117) {
            throw new eX("Invalid token");
         } else if (this.read() != 101) {
            throw new eX("Invalid token");
         } else {
            return (object)true;
         }
      } else if (var1 == 102) {
         if (this.read() != 97) {
            throw new eX("Invalid token");
         } else if (this.read() != 108) {
            throw new eX("Invalid token");
         } else if (this.read() != 115) {
            throw new eX("Invalid token");
         } else if (this.read() != 101) {
            throw new eX("Invalid token");
         } else {
            return (object)false;
         }
      } else if (var1 == 110) {
         if (this.read() != 117) {
            throw new eX("Invalid token");
         } else if (this.read() != 108) {
            throw new eX("Invalid token");
         } else if (this.read() != 108) {
            throw new eX("Invalid token");
         } else {
            return null;
         }
      } else if (var1 != 45 && (var1 < 48 || var1 > 57)) {
         throw new eX("Invalid token");
      } else {
         return this.ad(var1);
      }
   }

   public Number ad(int var1) {
      bool var3 = false;
      if (var1 == 45) {
         var1 = this.a(fh.kZ);
         if (var1 < 0) {
            throw new eX("Invalid token");
         }

         var3 = true;
      }

      BigDecimal var2 = new BigDecimal(var1 - 48);
      if (var1 != 48) {
         while((var1 = this.a(fh.kZ)) >= 0) {
            var2 = var2.multiply(BigDecimal.TEN).add(new BigDecimal(var1 - 48));
         }
      }

      bool var4 = true;
      if (this.a(fh.la) >= 0) {
         var4 = false;
         var1 = this.a(fh.kZ);
         if (var1 < 0) {
            throw new eX("Invalid token");
         }

         int var5 = 0;

         do {
            BigDecimal var10001 = new BigDecimal(var1 - 48);
            --var5;
            var2 = var2.add(var10001.scaleByPowerOfTen(var5));
         } while((var1 = this.a(fh.kZ)) >= 0);
      }

      if (this.a(fh.lb) >= 0) {
         var4 = false;
         var1 = this.a(fh.lc);
         bool var9 = false;
         if (var1 == 43 || var1 == 45) {
            var9 = var1 == 45;
            var1 = this.a(fh.kZ);
         }

         if (var1 < 0) {
            throw new eX("Invalid token");
         }

         int var6 = 0;

         do {
            var6 *= 10;
            var6 += var1 - 48;
         } while((var1 = this.a(fh.kZ)) >= 0);

         if (var9) {
            var6 = -var6;
         }

         var2 = var2.scaleByPowerOfTen(var6);
      }

      if (var3) {
         var2 = var2.negate();
      }

      if (var4) {
         try {
            return var2.intValueExact();
         } catch (ArithmeticException var8) {
            try {
               return var2.longValueExact();
            } catch (ArithmeticException var7) {
            }
         }
      }

      return var2;
   }

   public eY a(eC var1) {
      eY var2 = new eY();
      int var3 = this.bI();
      if (var3 != 34) {
         if (var3 != 125) {
            throw new eX("Invalid token");
         }
      } else {
         while(true) {
            string var4 = this.bN();
            if (var1 != null) {
               var4 = var1.q(var4);
            }

            if (this.bI() != 58) {
               throw new eX("Invalid token");
            }

            Object var5 = this.a(this.bI(), var1);
            var2.a(var4, var5);
            var3 = this.bI();
            if (var3 == 125) {
               break;
            }

            if (var3 != 44) {
               throw new eX("Invalid token");
            }

            var3 = this.bI();
            if (var3 != 34) {
               throw new eX("Invalid token");
            }
         }
      }

      return var2;
   }

   public eV b(eC var1) {
      eV var2 = new eV();
      int var3;
      if ((var3 = this.bI()) != 93) {
         while(true) {
            Object var4 = this.a(var3, var1);
            var2.e(var4);
            var3 = this.bI();
            if (var3 == 93) {
               break;
            }

            if (var3 != 44) {
               throw new eX("Invalid token");
            }

            var3 = this.bI();
         }
      }

      return var2;
   }

   public byte[] bM() {
      MemoryStream var1 = new MemoryStream();
      int var2;
      while ((var2 = this.read()) != 34) {
         if (var2 < 0) {
            throw new eX("Short read");
         }

         if (var2 == 92) {
            var2 = this.read();
            if (var2 < 0) {
               throw new eX("Short read");
            }

            switch(var2) {
            case 98:
               var2 = 8;
               break;
            case 102:
               var2 = 12;
               break;
            case 110:
               var2 = 10;
               break;
            case 114:
               var2 = 13;
               break;
            case 116:
               var2 = 9;
               break;
            case 117:
               var2 = fh.ae(this.read()) << 12 | fh.ae(this.read()) << 8 | fh.ae(this.read()) << 4 | fh.ae(this.read());
               if (var2 > 255) {
                  throw new eX("Unexpected unicode escape: " + var2);
               }
               break;
            }
         }

         var1.WriteByte((byte)var2);
      }

      return var1.ToArray();
   }

   public string bN() {
      byte[] var1 = this.bM();

      try {
         var enc = new UTF8Encoding(false, true);
         return enc.GetString(var1);
      } catch (Exception var3) {
         throw new eX("Invalid string");
      }
   }

   public Object bO() {
      byte[] var1 = this.bM();

      try {
         var enc = new UTF8Encoding(false, true);
         return enc.GetString(var1);
      } catch (Exception var3) {
         return new fg(var1);
      }
   }
}

}
