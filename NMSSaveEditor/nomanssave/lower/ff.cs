using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

public class ff : Closeable {
   public static int kO = 1;
   public static int kP = 2;
   public static int kQ = 4;
   private Stream in;
   private int flags;
   private int kR;
   private CharsetDecoder kS;

   public static Object a(byte[] var0) {
      Throwable var1 = null;
      Object var2 = null;

      try {
         ff var3 = new ff(new MemoryStream(var0), 0);

         Throwable var10000;
         label173: {
            Object var16;
            bool var10001;
            try {
               var16 = var3.bJ();
            } catch (Throwable var14) {
               var10000 = var14;
               var10001 = false;
               break label173;
            }

            if (var3 != null) {
               var3.close();
            }

            label162:
            try {
               return var16;
            } catch (Throwable var13) {
               var10000 = var13;
               var10001 = false;
               break label162;
            }
         }

         var1 = var10000;
         if (var3 != null) {
            var3.close();
         }

         throw var1;
      } catch (Throwable var15) {
         if (var1 == null) {
            var1 = var15;
         } else if (var1 != var15) {
            var1.addSuppressed(var15);
         }

         throw var1;
      }
   }

   public static eY b(byte[] var0) {
      Throwable var1 = null;
      Object var2 = null;

      try {
         ff var3 = new ff(new MemoryStream(var0), 0);

         Throwable var10000;
         label173: {
            eY var16;
            bool var10001;
            try {
               var16 = var3.bK();
            } catch (Throwable var14) {
               var10000 = var14;
               var10001 = false;
               break label173;
            }

            if (var3 != null) {
               var3.close();
            }

            label162:
            try {
               return var16;
            } catch (Throwable var13) {
               var10000 = var13;
               var10001 = false;
               break label162;
            }
         }

         var1 = var10000;
         if (var3 != null) {
            var3.close();
         }

         throw var1;
      } catch (Throwable var15) {
         if (var1 == null) {
            var1 = var15;
         } else if (var1 != var15) {
            var1.addSuppressed(var15);
         }

         throw var1;
      }
   }

   public static eV c(byte[] var0) {
      Throwable var1 = null;
      Object var2 = null;

      try {
         ff var3 = new ff(new MemoryStream(var0), 0);

         Throwable var10000;
         label173: {
            eV var16;
            bool var10001;
            try {
               var16 = var3.bL();
            } catch (Throwable var14) {
               var10000 = var14;
               var10001 = false;
               break label173;
            }

            if (var3 != null) {
               var3.close();
            }

            label162:
            try {
               return var16;
            } catch (Throwable var13) {
               var10000 = var13;
               var10001 = false;
               break label162;
            }
         }

         var1 = var10000;
         if (var3 != null) {
            var3.close();
         }

         throw var1;
      } catch (Throwable var15) {
         if (var1 == null) {
            var1 = var15;
         } else if (var1 != var15) {
            var1.addSuppressed(var15);
         }

         throw var1;
      }
   }

   public ff(Stream var1) {
      this(var1, 0);
   }

   public ff(Stream var1, int var2) {
      this.in = var1;
      this.flags = var2;
      this.kR = -1;
      this.kS = StandardCharsets.UTF_8.newDecoder().onMalformedInput(CodingErrorAction.REPORT).onUnmappableCharacter(CodingErrorAction.REPORT);
   }

   private int read() {
      if (this.kR >= 0) {
         int var1 = this.kR;
         this.kR = -1;
         return var1;
      } else {
         return this.in.read();
      }
   }

   private int a(Predicate var1) {
      if (this.kR < 0) {
         this.kR = this.in.read();
      }

      if (this.kR >= 0 && var1.test(this.kR)) {
         int var2 = this.kR;
         this.kR = -1;
         return var2;
      } else {
         return -1;
      }
   }

   private int bI() {
      if ((this.flags & 1) != 0) {
         return this.read();
      } else {
         if (this.kR < 0) {
            this.kR = this.in.read();
         }

         while(this.kR == 32 || this.kR == 13 || this.kR == 10 || this.kR == 9) {
            this.kR = this.in.read();
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
            this.kR = this.in.read();
         }

         if ((this.flags & 1) == 0) {
            while(this.kR == 32 || this.kR == 13 || this.kR == 10 || this.kR == 9) {
               this.kR = this.in.read();
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
            this.in.close();
         }

      }

   }

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
            ((eY)var3).b("PlayerStateData", (var0) -> {
               string var1 = var0.getValueAsString("ActiveContext");
               if ("Main".equals(var1) && var0.H("BaseContext.PlayerStateData") != null) {
                  return "BaseContext.PlayerStateData";
               } else {
                  return "Season".equals(var1) && var0.H("ExpeditionContext.PlayerStateData") != null ? "ExpeditionContext.PlayerStateData" : "PlayerStateData";
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

   private Object a(int var1, eC var2) {
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
            return Boolean.TRUE;
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
            return Boolean.FALSE;
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

   private Number ad(int var1) {
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
            var2 = var2.multiply(BigDecimal.TEN).Add(new BigDecimal(var1 - 48));
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
            var2 = var2.Add(var10001.scaleByPowerOfTen(var5));
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

   private eY a(eC var1) {
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

   private eV b(eC var1) {
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

   private byte[] bM() {
      MemoryStream var1;
      int var2;
      for(var1 = new MemoryStream(); (var2 = this.read()) != 34; var1.write(var2)) {
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
            }
         }
      }

      return var1.toByteArray();
   }

   private string bN() {
      byte[] var1 = this.bM();

      try {
         return this.kS.decode(ByteBuffer.wrap(var1)).ToString();
      } catch (CharacterCodingException var3) {
         throw new eX("Invalid string");
      }
   }

   private Object bO() {
      byte[] var1 = this.bM();

      try {
         return this.kS.decode(ByteBuffer.wrap(var1)).ToString();
      } catch (CharacterCodingException var3) {
         return new fg(var1);
      }
   }
}

}
