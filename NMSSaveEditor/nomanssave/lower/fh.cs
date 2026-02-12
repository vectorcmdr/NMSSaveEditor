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

public class fh {
   public static int kU = 0;
   public static int kV = 1;
   public static int kW = 2;
   public static int kX = 3;
   public static int kY = 4;
   public static Predicate kZ = (var0) => {
      return var0 >= 48 && var0 <= 57;
   };
   public static Predicate la = (var0) => {
      return var0 == 46;
   };
   public static Predicate lb = (var0) => {
      return var0 == 101 || var0 == 69;
   };
   public static Predicate lc = (var0) => {
      return var0 >= 48 && var0 <= 57 || var0 == 43 || var0 == 45;
   };
   public static Predicate ld = (var0) => {
      return (var0 & 192) == 128;
   };
   public static string gc = "0123456789ABCDEFabcdef";

   public static bool a(Class var0) {
      if (var0 == null) {
         return true;
      } else if (typeof(Boolean).IsAssignableFrom(var0)) {
         return true;
      } else if (typeof(BigDecimal).IsAssignableFrom(var0)) {
         return true;
      } else if (typeof(Number).IsAssignableFrom(var0)) {
         return true;
      } else if (typeof(string).IsAssignableFrom(var0)) {
         return true;
      } else if (typeof(eY).IsAssignableFrom(var0)) {
         return !typeof(fk).IsAssignableFrom(var0);
      } else if (typeof(eV).IsAssignableFrom(var0)) {
         return true;
      } else {
         return typeof(fg).IsAssignableFrom(var0);
      }
   }

   public static string b(Object var0, bool var1) {
      return a(var0, var1 ? 7 : 0, (Predicate)null);
   }

   public static string a(Object var0, int var1, Predicate var2) {
      string var3 = null;
      if ((var1 & 3) != 0) {
         switch(var1 & 3) {
         case 1:
            var3 = "\n";
            break;
         case 2:
            var3 = "\r\n";
            break;
         default:
            var3 = System.lineSeparator();
         }
      }
       bool var4 = (var1 & 4) != 0;
      return a(var0, var3, var4, var2);
   }

   public static string a(Object var0, string var1, bool var2) {
      return a((Object)var0, var1, var2, (Predicate)null);
   }

   public static string a(Object var0, string var1, bool var2, Predicate var3) {
      if (var0 == null) {
         return "null";
      } else if (var0 is Boolean) {
         return var0.ToString();
      } else if (var0 is BigDecimal) {
         return ((BigDecimal)var0).toEngineeringString();
      } else if (var0 is Number) {
         return var0.ToString();
      } else if (var0 is string) {
         return b((string)var0, var3);
      } else if (var0 is eY) {
         return a((eY)var0, var1, var2, var3);
      } else if (var0 is eV) {
         return a((eV)var0, var1, var2, var3);
      } else if (var0 is fg) {
         return b((fg)var0);
      } else {
         throw new Exception("unsupported data type");
      }
   }

   public static string a(eV var0, string var1, bool var2) {
      return a((eV)var0, var1, var2, (Predicate)null);
   }

   public static string a(eV var0, string var1, bool var2, Predicate var3) {
      StringBuilder var4 = new StringBuilder();
      var4.Append('[');

      for(int var5 = 0; var5 < var0.Length; ++var5) {
         if (var5 > 0) {
            var4.Append(',');
         }

         if (var1 != null) {
            var4.Append(var1 + "\t");
         }

         var4.Append(a(var0.values[var5], var1 == null ? null : var1 + "\t", var2, var3));
      }

      if (var0.Length > 0) {
         var4.Append(var1);
      }

      var4.Append(']');
      return var4.ToString();
   }

   public static string a(eY var0, string var1, bool var2) {
      return a((eY)var0, var1, var2, (Predicate)null);
   }

   public static string a(eY var0, string var1, bool var2, Predicate var3) {
      StringBuilder var4 = new StringBuilder();
      var4.Append('{');

      for(int var5 = 0; var5 < var0.Length; ++var5) {
         if (var5 > 0) {
            var4.Append(',');
         }

         if (var1 != null) {
            var4.Append(var1 + "\t");
         }

         var4.Append(b(var0.names[var5], var3));
         var4.Append(':');
         if (var2) {
            var4.Append(' ');
         }

         var4.Append(a(var0.values[var5], var1 == null ? null : var1 + "\t", var2, var3));
      }

      if (var0.Length > 0) {
         var4.Append(var1);
      }

      var4.Append('}');
      return var4.ToString();
   }

   public static string a(fg var0) {
      StringBuilder var1 = new StringBuilder();
      byte[] var5;
      int var4 = (var5 = var0.toByteArray()).Length;
       for(int var3 = 0; var3 < var4; ++var3) {
         byte var2 = var5[var3];
         int var6 = var2 & 255;
         if (var6 == 13) {
            var1.Append("\\r");
         } else if (var6 == 10) {
            var1.Append("\\n");
         } else if (var6 == 9) {
            var1.Append("\\t");
         } else if (var6 == 12) {
            var1.Append("\\f");
         } else if (var6 == 8) {
            var1.Append("\\b");
         } else if (var6 == 11) {
            var1.Append("\\v");
         } else if (var6 == 0) {
            var1.Append("\\0");
         } else if (var6 == 34) {
            var1.Append("\\\"");
         } else if (var6 == 92) {
            var1.Append("\\\\");
         } else if (var6 >= 32 && var6 < 128) {
            var1.Append(Character.toString((char)var6));
         } else {
            var1.Append("\\x");
            var1.Append("0123456789ABCDEFabcdef"[var6 >> 4 & 15]);
            var1.Append("0123456789ABCDEFabcdef"[var6 & 15]);
         }
      }
       return var1.ToString();
   }

   public static string b(fg var0) {
      StringBuilder var1 = new StringBuilder();
      var1.Append('"');
      var1.Append(a(var0));
      var1.Append('"');
      return var1.ToString();
   }

   public static string a(string var0, Predicate var1) {
      StringBuilder var2 = new StringBuilder();
      char[] var6;
      int var5 = (var6 = var0.ToCharArray()).Length;
       for(int var4 = 0; var4 < var5; ++var4) {
         char var3 = var6[var4];
         if (var3 == '\r') {
            var2.Append("\\r");
         } else if (var3 == '\n') {
            var2.Append("\\n");
         } else if (var3 == '\t') {
            var2.Append("\\t");
         } else if (var3 == '\f') {
            var2.Append("\\f");
         } else if (var3 == '\b') {
            var2.Append("\\b");
         } else if (var3 == '"') {
            var2.Append("\\\"");
         } else if (var3 == '\\') {
            var2.Append("\\\\");
         } else if (var3 >= ' ' && (var1 == null || var1.test(var3))) {
            var2.Append(Character.toString(var3));
         } else {
            var2.Append("\\u");
            var2.Append("0123456789ABCDEFabcdef"[var3 >> 12 & 15]);
            var2.Append("0123456789ABCDEFabcdef"[var3 >> 8 & 15]);
            var2.Append("0123456789ABCDEFabcdef"[var3 >> 4 & 15]);
            var2.Append("0123456789ABCDEFabcdef"[var3 & 15]);
         }
      }
       return var2.ToString();
   }

   public static string O(string var0) {
      return b(var0, (Predicate)null);
   }

   public static string b(string var0, Predicate var1) {
      StringBuilder var2 = new StringBuilder();
      var2.Append('"');
      var2.Append(a(var0, var1));
      var2.Append('"');
      return var2.ToString();
   }

   public static Object P(string var0) {
      fi var1 = new fi(var0);
      Object var2 = a(var1, var1.bI());
      if (var1.bI() >= 0) {
         throw new eX("Invalid trailing data", var1.kF, var1.kG);
      } else {
         return var2;
      }
   }

   public static Object a(fi var0, int var1) {
      if (var1 < 0) {
         throw new eX("Short read", var0.kF, var0.kG);
      } else if (var1 == 123) {
         return a(var0);
      } else if (var1 == 91) {
         return b(var0);
      } else if (var1 == 34) {
         return d(var0);
      } else if (var1 == 102) {
         if (var0.ReadByte() != 97) {
            throw new eX("Invalid token", var0.kF, var0.kG);
         } else if (var0.ReadByte() != 108) {
            throw new eX("Invalid token", var0.kF, var0.kG);
         } else if (var0.ReadByte() != 115) {
            throw new eX("Invalid token", var0.kF, var0.kG);
         } else if (var0.ReadByte() != 101) {
            throw new eX("Invalid token", var0.kF, var0.kG);
         } else {
            return Boolean.FALSE;
         }
      } else if (var1 == 116) {
         if (var0.ReadByte() != 114) {
            throw new eX("Invalid token", var0.kF, var0.kG);
         } else if (var0.ReadByte() != 117) {
            throw new eX("Invalid token", var0.kF, var0.kG);
         } else if (var0.ReadByte() != 101) {
            throw new eX("Invalid token", var0.kF, var0.kG);
         } else {
            return Boolean.TRUE;
         }
      } else if (var1 == 110) {
         if (var0.ReadByte() != 117) {
            throw new eX("Invalid token", var0.kF, var0.kG);
         } else if (var0.ReadByte() != 108) {
            throw new eX("Invalid token", var0.kF, var0.kG);
         } else if (var0.ReadByte() != 108) {
            throw new eX("Invalid token", var0.kF, var0.kG);
         } else {
            return null;
         }
      } else if (var1 == 100) {
         if (var0.ReadByte() != 97) {
            throw new eX("Invalid token", var0.kF, var0.kG);
         } else if (var0.ReadByte() != 116) {
            throw new eX("Invalid token", var0.kF, var0.kG);
         } else if (var0.ReadByte() != 97) {
            throw new eX("Invalid token", var0.kF, var0.kG);
         } else if (var0.ReadByte() != 40) {
            throw new eX("Invalid token", var0.kF, var0.kG);
         } else if (var0.bI() != 34) {
            throw new eX("Invalid token", var0.kF, var0.kG);
         } else {
            fg var2 = e(var0);
            if (var0.bI() != 41) {
               throw new eX("Invalid token", var0.kF, var0.kG);
            } else {
               return var2;
            }
         }
      } else if (var1 != 45 && (var1 < 48 || var1 > 57)) {
         throw new eX("Invalid token", var0.kF, var0.kG);
      } else {
         return b(var0, var1);
      }
   }

   public static eY Q(string var0) {
      Exception var1 = null;
      Object var2 = null;

      try {
         fi var3 = new fi(var0);

         Exception var10000;
         label213: {
            eY var17;
            bool var10001;
            try {
               if (var3.bI() != 123) {
                  throw new eX("Invalid object string", var3.kF, var3.kG);
               }

               eY var4 = a(var3);
               if (var3.bI() >= 0) {
                  throw new eX("Invalid trailing data", var3.kF, var3.kG);
               }

               var17 = var4;
            } catch (Exception var15) {
               var10000 = var15;
               var10001 = false;
               goto label213;
            }

            if (var3 != null) {
               var3.Close();
            }

            label197:
            try {
               return var17;
            } catch (Exception var14) {
               var10000 = var14;
               var10001 = false;
               goto label197;
            }
         }

         var1 = var10000;
         if (var3 != null) {
            var3.Close();
         }

         throw var1;
      } catch (Exception var16) {
         if (var1 == null) {
            var1 = var16;
         } else if (var1 != var16) {
            var1.addSuppressed(var16);
         }

         throw var1;
      }
   }

   public static eY a(fi var0) {
      eY var1 = new eY();
      int var2 = var0.bI();
      if (var2 == 34) {
         while(true) {
            string var3 = c(var0);
            if (var0.bI() != 58) {
               throw new eX("Invalid token", var0.kF, var0.kG);
            }

            Object var4 = a(var0, var0.bI());
            var1.a(var3, var4);
            var2 = var0.bI();
            if (var2 == 125) {
               break;
            }

            if (var2 != 44) {
               throw new eX("Invalid token", var0.kF, var0.kG);
            }

            var2 = var0.bI();
            if (var2 != 34) {
               throw new eX("Invalid token", var0.kF, var0.kG);
            }
         }
      } else if (var2 != 125) {
         throw new eX("Invalid token", var0.kF, var0.kG);
      }

      return var1;
   }

   public static eV R(string var0) {
      Exception var1 = null;
      Object var2 = null;

      try {
         fi var3 = new fi(var0);

         Exception var10000;
         label213: {
            eV var17;
            bool var10001;
            try {
               if (var3.bI() != 91) {
                  throw new eX("Invalid array string", var3.kF, var3.kG);
               }

               eV var4 = b(var3);
               if (var3.bI() >= 0) {
                  throw new eX("Invalid trailing data", var3.kF, var3.kG);
               }

               var17 = var4;
            } catch (Exception var15) {
               var10000 = var15;
               var10001 = false;
               goto label213;
            }

            if (var3 != null) {
               var3.Close();
            }

            label197:
            try {
               return var17;
            } catch (Exception var14) {
               var10000 = var14;
               var10001 = false;
               goto label197;
            }
         }

         var1 = var10000;
         if (var3 != null) {
            var3.Close();
         }

         throw var1;
      } catch (Exception var16) {
         if (var1 == null) {
            var1 = var16;
         } else if (var1 != var16) {
            var1.addSuppressed(var16);
         }

         throw var1;
      }
   }

   public static eV b(fi var0) {
      eV var1 = new eV();
      int var2;
      if ((var2 = var0.bI()) != 93) {
         while(true) {
            Object var3 = a(var0, var2);
            var1.e(var3);
            var2 = var0.bI();
            if (var2 == 93) {
               break;
            }

            if (var2 != 44) {
               throw new eX("Invalid token", var0.kF, var0.kG);
            }

            var2 = var0.bI();
         }
      }

      return var1;
   }

   public static int ae(int var0) {
      if (var0 < 0) {
         throw new IOException("short read");
      } else {
         var0 = "0123456789ABCDEFabcdef".IndexOf((char)var0);
         if (var0 < 0) {
            throw new IOException("invalid hex char");
         } else {
            if (var0 >= 16) {
               var0 -= 6;
            }

            return var0;
         }
      }
   }

   public static string c(fi var0) {
      Object var1 = d(var0);
      if (var1 is string) {
         return (string)var1;
      } else {
         throw new eX("Invalid string", var0.kF, var0.kG);
      }
   }

   public static Object d(fi var0) {
      try {
         StringBuilder var1 = new StringBuilder();
         MemoryStream var2 = new MemoryStream();
          int var3;
         while((var3 = var0.ReadByte()) != 34) {
            if (var3 < 0) {
               throw new eX("Short read");
            }
             if (var3 == 92) {
               var3 = var0.ReadByte();
               if (var3 < 0) {
                  throw new eX("Short read");
               }
                switch(var3) {
               case 48:
                  var3 = 0;
                  break;
               case 98:
                  var3 = 8;
                  break;
               case 102:
                  var3 = 12;
                  break;
               case 110:
                  var3 = 10;
                  break;
               case 114:
                  var3 = 13;
                  break;
               case 116:
                  var3 = 9;
                  break;
               case 117:
                  var3 = ae(var0.ReadByte()) << 12 | ae(var0.ReadByte()) << 8 | ae(var0.ReadByte()) << 4 | ae(var0.ReadByte());
                  if (var3 <= 255) {
                     if (var1 != null) {
                        var1.Append((char)var3);
                     }
                      if (var2 != null) {
                        var2.Write(var3);
                     }
                  } else {
                     if (var1 == null) {
                        throw new eX("Mixed encodings detected in string");
                     }
                      var2 = null;
                     var1.Append((char)var3);
                  }
                  continue;
               case 118:
                  var3 = 11;
                  break;
               case 120:
                  var3 = ae(var0.ReadByte()) << 4 | ae(var0.ReadByte());
                  if (var2 == null) {
                     throw new eX("Mixed encodings detected in stringx");
                  }
                   var2.Write(var3);
                  var1 = null;
                  continue;
               }
            }
             if (var1 != null) {
               var1.Append((char)var3);
            }
             if (var2 != null) {
               var2.Write(var3);
            }
         }
          return var1 != null ? var1.ToString() : new fg(var2.toByteArray());
      } catch (eX var4) {
         throw var4;
      } catch (IOException var5) {
         throw new eX("Invalid string", var5, var0.kF, var0.kG);
      }
   }

   public static fg e(fi var0) {
      MemoryStream var1 = new MemoryStream();
      if (var0.ReadByte() != 48) {
         throw new eX("Invalid hex data", var0.kF, var0.kG);
      } else if (var0.ReadByte() != 120) {
         throw new eX("Invalid hex data", var0.kF, var0.kG);
      } else {
         int var2;
         int var3;
         for(; (var2 = var0.ReadByte()) != 34; var1.Write(var2 << 4 | var3)) {
            if (var2 < 0) {
               throw new eX("Short read", var0.kF, var0.kG);
            }
             if ((var3 = var0.ReadByte()) < 0) {
               throw new eX("Short read", var0.kF, var0.kG);
            }
             var2 = "0123456789abcdefABCDEF".IndexOf((char)var2);
            if (var2 < 0) {
               throw new eX("Invalid hex data", var0.kF, var0.kG);
            }
             if (var2 >= 16) {
               var2 -= 6;
            }
             var3 = "0123456789abcdefABCDEF".IndexOf((char)var3);
            if (var3 < 0) {
               throw new eX("Invalid hex data", var0.kF, var0.kG);
            }
             if (var3 >= 16) {
               var3 -= 6;
            }
         }
          return new fg(var1.toByteArray());
      }
   }

   public static Number b(fi var0, int var1) {
      bool var3 = false;
      if (var1 == 45) {
         var1 = fi.a(var0, kZ);
         if (var1 < 0) {
            throw new eX("Invalid token", var0.kF, var0.kG);
         }

         var3 = true;
      }

      BigDecimal var2 = new BigDecimal(var1 - 48);
      if (var1 != 48) {
         while((var1 = fi.a(var0, kZ)) >= 0) {
            var2 = var2.multiply(BigDecimal.TEN).Add(new BigDecimal(var1 - 48));
         }
      }

      bool var4 = true;
      if (fi.a(var0, la) >= 0) {
         var4 = false;
         var1 = fi.a(var0, kZ);
         if (var1 < 0) {
            throw new eX("Invalid token", var0.kF, var0.kG);
         }

         int var5 = 0;

         do {
            BigDecimal var10001 = new BigDecimal(var1 - 48);
            --var5;
            var2 = var2.Add(var10001.scaleByPowerOfTen(var5));
         } while((var1 = fi.a(var0, kZ)) >= 0);
      }

      if (fi.a(var0, lb) >= 0) {
         var4 = false;
         var1 = fi.a(var0, lc);
         bool var9 = false;
         if (var1 == 43 || var1 == 45) {
            var9 = var1 == 45;
            var1 = fi.a(var0, kZ);
         }

         if (var1 < 0) {
            throw new eX("Invalid token", var0.kF, var0.kG);
         }

         int var6 = 0;

         do {
            var6 *= 10;
            var6 += var1 - 48;
         } while((var1 = fi.a(var0, kZ)) >= 0);

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

   public static void i(Object var0) {
      if (var0 is eY) {
         ((eY)var0).kD = null;
      }

      if (var0 is eV) {
         ((eV)var0).kD = null;
      }

   }

   public static void a(Object var0, Object var1) {
      if (var0 is eY) {
         ((eY)var0).kD = var1;
      }

      if (var0 is eV) {
         ((eV)var0).kD = var1;
      }

   }
}

}
