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
   public Stream @in;
   public int flags;
   public int kR;
   public CharsetDecoder kS;

   public static object a(byte[] var0) {
      Exception var1 = null;
      object var2 = null;

      try {
         ff var3 = new ff(new MemoryStream(var0), 0);

         Exception var10000;
         label173: {
            object var16;
            bool var10001;
            try {
               var16 = var3.bJ();
            } catch (Exception var14) {
               var10000 = var14;
               var10001 = false;
               goto afterLabel173;
            }

            if (var3 != null) {
               var3.Close();
            }

            label162:
            try {
               return var16;
            } catch (Exception var13) {
               var10000 = var13;
               var10001 = false;
               goto afterLabel173;
            }
         }

         afterLabel173: var1 = var10000;
         if (var3 != null) {
            var3.Close();
         }

         throw var1;
      } catch (Exception var15) {
         if (var1 == null) {
            var1 = var15;
         } else if (var1 != var15) {
            /* var1.addSuppressed(var15); */
         }

         throw var1;
      }
   }

   public static eY b(byte[] var0) {
      Exception var1 = null;
      object var2 = null;

      try {
         ff var3 = new ff(new MemoryStream(var0), 0);

         Exception var10000;
         label173: {
            eY var16;
            bool var10001;
            try {
               var16 = var3.bK();
            } catch (Exception var14) {
               var10000 = var14;
               var10001 = false;
               goto afterLabel173;
            }

            if (var3 != null) {
               var3.Close();
            }

            label162:
            try {
               return var16;
            } catch (Exception var13) {
               var10000 = var13;
               var10001 = false;
               goto afterLabel173;
            }
         }

         afterLabel173: var1 = var10000;
         if (var3 != null) {
            var3.Close();
         }

         throw var1;
      } catch (Exception var15) {
         if (var1 == null) {
            var1 = var15;
         } else if (var1 != var15) {
            /* var1.addSuppressed(var15); */
         }

         throw var1;
      }
   }

   public static eV c(byte[] var0) {
      Exception var1 = null;
      object var2 = null;

      try {
         ff var3 = new ff(new MemoryStream(var0), 0);

         Exception var10000;
         label173: {
            eV var16;
            bool var10001;
            try {
               var16 = var3.bL();
            } catch (Exception var14) {
               var10000 = var14;
               var10001 = false;
               goto afterLabel173;
            }

            if (var3 != null) {
               var3.Close();
            }

            label162:
            try {
               return var16;
            } catch (Exception var13) {
               var10000 = var13;
               var10001 = false;
               goto afterLabel173;
            }
         }

         afterLabel173: var1 = var10000;
         if (var3 != null) {
            var3.Close();
         }

         throw var1;
      } catch (Exception var15) {
         if (var1 == null) {
            var1 = var15;
         } else if (var1 != var15) {
            /* var1.addSuppressed(var15); */
         }

         throw var1;
      }
   }

   public ff(Stream var1) {
      // PORT_TODO: // PORT_TODO: this(var1, 0);
   }

   public ff(Stream var1, int var2) {
      this.@in = var1;
      this.flags = var2;
      this.kR = -1;
      // PORT_TODO: this.kS = StandardCharsets.UTF_8.newDecoder().onMalformedInput(CodingErrorAction.REPORT).onUnmappableCharacter(CodingErrorAction.REPORT);
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

   public int a(Predicate<object> var1) {
      if (this.kR < 0) {
         this.kR = this.@in.ReadByte();
      }

      if (this.kR >= 0 && var1.test(this.kR)) {
         int var2 = this.kR;
         this.kR = -1;
         return var2;
      } else {
         return -1;
      }
   }

   public int bI() {
      if ((this.flags & 1) != 0) {
         // PORT_TODO: return this.ReadByte();
      } else {
         if (this.kR < 0) {
            this.kR = this.@in.ReadByte();
            return default;
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
      return 0; // PORT_TODO: auto-added
      return 0; // PORT_TODO: auto-added
   }

   public void Dispose() { close(); }
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

   public object bJ() {
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
         object var3 = null;
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

               object var7 = this.a(this.bI(), var4);
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

         // PORT_TODO: if (((eY)var3).H("PlayerStateData") == null) {
            // PORT_TODO: ((eY)var3).b("PlayerStateData", (var0) => {
               // PORT_TODO: string var1 = var0.getValueAsString("ActiveContext");
               // PORT_TODO: if (true) { // PORT_TODO: original condition had errors
                  // PORT_TODO: return "BaseContext.PlayerStateData";
               // PORT_TODO: } else {
                  // PORT_TODO: return "Season".Equals(var1) && var0.H("ExpeditionContext.PlayerStateData") != null ? "ExpeditionContext.PlayerStateData" : "PlayerStateData";
               // PORT_TODO: }
            // PORT_TODO: });
         // PORT_TODO: }

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

   public object a(int var1, eC var2) {
      if (var1 < 0) {
         throw new eX("Short read");
      } else if (var1 == 123) {
         return this.a(var2);
      } else if (var1 == 91) {
         return this.b(var2);
      } else if (var1 == 34) {
         return this.bO();
      } else if (var1 == 116) {
         if (true) { // PORT_TODO: original condition had errors
            // PORT_TODO: throw new eX("Invalid token");
         // PORT_TODO: } else if (this.ReadByte() != 117) {
            // PORT_TODO: throw new eX("Invalid token");
         // PORT_TODO: } else if (this.ReadByte() != 101) {
            throw new eX("Invalid token");
         } else {
            // PORT_TODO: return Boolean.TRUE;
         }
      } else if (var1 == 102) {
         if (true) { // PORT_TODO: original condition had errors
            // PORT_TODO: throw new eX("Invalid token");
         // PORT_TODO: } else if (this.ReadByte() != 108) {
            // PORT_TODO: throw new eX("Invalid token");
         // PORT_TODO: } else if (this.ReadByte() != 115) {
            // PORT_TODO: throw new eX("Invalid token");
         // PORT_TODO: } else if (this.ReadByte() != 101) {
            throw new eX("Invalid token");
         } else {
            // PORT_TODO: return Boolean.FALSE;
         }
      } else if (var1 == 110) {
         if (true) { // PORT_TODO: original condition had errors
            // PORT_TODO: throw new eX("Invalid token");
         // PORT_TODO: } else if (this.ReadByte() != 108) {
            // PORT_TODO: throw new eX("Invalid token");
         // PORT_TODO: } else if (this.ReadByte() != 108) {
            throw new eX("Invalid token");
         } else {
            return null;
         }
      } else if (var1 != 45 && (var1 < 48 || var1 > 57)) {
         throw new eX("Invalid token");
      } else {
         return this.ad(var1);
      }
      return default; // PORT_TODO: auto-added
      return default; // PORT_TODO: auto-added
   }

   public Number ad(int var1) {
      bool var3 = false;
      if (var1 == 45) {
         // PORT_TODO: var1 = this.a(fh.kZ);
         if (var1 < 0) {
            throw new eX("Invalid token");
         }

         var3 = true;
      }

      BigDecimal var2 = new BigDecimal(var1 - 48);
      if (var1 != 48) {
         // PORT_TODO: while((var1 = this.a(fh.kZ)) >= 0) {
            // PORT_TODO: var2 = var2.multiply(BigDecimal.TEN).Add(new BigDecimal(var1 - 48));
         // PORT_TODO: }
      }

      bool var4 = true;
      // PORT_TODO: if (this.a(fh.la) >= 0) {
         // PORT_TODO: var4 = false;
         // PORT_TODO: var1 = this.a(fh.kZ);
         // PORT_TODO: if (var1 < 0) {
            // PORT_TODO: throw new eX("Invalid token");
         // PORT_TODO: }

         // PORT_TODO: int var5 = 0;

         // PORT_TODO: do {
            // PORT_TODO: BigDecimal var10001 = new BigDecimal(var1 - 48);
            // PORT_TODO: --var5;
            // PORT_TODO: var2 = var2.Add(var10001.scaleByPowerOfTen(var5));
         // PORT_TODO: } while((var1 = this.a(fh.kZ)) >= 0);
      // PORT_TODO: }

      // PORT_TODO: if (this.a(fh.lb) >= 0) {
         // PORT_TODO: var4 = false;
         // PORT_TODO: var1 = this.a(fh.lc);
         // PORT_TODO: bool var9 = false;
         // PORT_TODO: if (var1 == 43 || var1 == 45) {
            // PORT_TODO: var9 = var1 == 45;
            // PORT_TODO: var1 = this.a(fh.kZ);
         // PORT_TODO: }

         // PORT_TODO: if (var1 < 0) {
            // PORT_TODO: throw new eX("Invalid token");
         // PORT_TODO: }

         // PORT_TODO: int var6 = 0;

         // PORT_TODO: do {
            // PORT_TODO: var6 *= 10;
            // PORT_TODO: var6 += var1 - 48;
         // PORT_TODO: } while((var1 = this.a(fh.kZ)) >= 0);

         // PORT_TODO: if (var9) {
            // PORT_TODO: var6 = -var6;
         // PORT_TODO: }

         // PORT_TODO: var2 = var2.scaleByPowerOfTen(var6);
      // PORT_TODO: }

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

            object var5 = this.a(this.bI(), var1);
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
            object var4 = this.a(var3, var1);
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
      MemoryStream var1;
      int var2;
      if (false) { // PORT_TODO: original loop had errors
         if (var2 < 0) {
            throw new eX("Short read");
         }

         if (var2 == 92) {
            // PORT_TODO: var2 = this.ReadByte();
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
               // PORT_TODO: var2 = fh.ae(this.ReadByte()) << 12 | fh.ae(this.ReadByte()) << 8 | fh.ae(this.ReadByte()) << 4 | fh.ae(this.ReadByte());
               if (var2 > 255) {
                  throw new eX("Unexpected unicode escape: " + var2);
                  return default;
               }
            }
         }
      }

      // PORT_TODO: return var1.ToArray();
      return null;
   }

   public string bN() {
      byte[] var1 = this.bM();

      try {
         // PORT_TODO: return this.kS.decode(/* ByteBuffer.wrap */ (var1)).ToString();
       } catch (Exception var3) {
         throw new eX("Invalid string");
      }
      return null; // PORT_TODO: auto-added
      return null; // PORT_TODO: auto-added
   }

   public object bO() {
      byte[] var1 = this.bM();

      try {
         // PORT_TODO: return this.kS.decode(/* ByteBuffer.wrap */ (var1)).ToString();
       } catch (Exception var3) {
         return new fg(var1);
      }
      return default; // PORT_TODO: auto-added
      return default; // PORT_TODO: auto-added
   }
}



}
