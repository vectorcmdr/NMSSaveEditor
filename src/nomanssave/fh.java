package nomanssave;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.function.Predicate;

public class fh {
   public static final int kU = 0;
   public static final int kV = 1;
   public static final int kW = 2;
   public static final int kX = 3;
   public static final int kY = 4;
   static final Predicate kZ = (var0) -> {
      return var0 >= 48 && var0 <= 57;
   };
   static final Predicate la = (var0) -> {
      return var0 == 46;
   };
   static final Predicate lb = (var0) -> {
      return var0 == 101 || var0 == 69;
   };
   static final Predicate lc = (var0) -> {
      return var0 >= 48 && var0 <= 57 || var0 == 43 || var0 == 45;
   };
   static final Predicate ld = (var0) -> {
      return (var0 & 192) == 128;
   };
   static final String gc = "0123456789ABCDEFabcdef";

   static boolean a(Class var0) {
      if (var0 == null) {
         return true;
      } else if (Boolean.class.isAssignableFrom(var0)) {
         return true;
      } else if (BigDecimal.class.isAssignableFrom(var0)) {
         return true;
      } else if (Number.class.isAssignableFrom(var0)) {
         return true;
      } else if (String.class.isAssignableFrom(var0)) {
         return true;
      } else if (eY.class.isAssignableFrom(var0)) {
         return !fk.class.isAssignableFrom(var0);
      } else if (eV.class.isAssignableFrom(var0)) {
         return true;
      } else {
         return fg.class.isAssignableFrom(var0);
      }
   }

   public static String b(Object var0, boolean var1) {
      return a(var0, var1 ? 7 : 0, (Predicate)null);
   }

   public static String a(Object var0, int var1, Predicate var2) {
      String var3 = null;
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

      boolean var4 = (var1 & 4) != 0;
      return a(var0, var3, var4, var2);
   }

   static String a(Object var0, String var1, boolean var2) {
      return a((Object)var0, var1, var2, (Predicate)null);
   }

   private static String a(Object var0, String var1, boolean var2, Predicate var3) {
      if (var0 == null) {
         return "null";
      } else if (var0 instanceof Boolean) {
         return var0.toString();
      } else if (var0 instanceof BigDecimal) {
         return ((BigDecimal)var0).toEngineeringString();
      } else if (var0 instanceof Number) {
         return var0.toString();
      } else if (var0 instanceof String) {
         return b((String)var0, var3);
      } else if (var0 instanceof eY) {
         return a((eY)var0, var1, var2, var3);
      } else if (var0 instanceof eV) {
         return a((eV)var0, var1, var2, var3);
      } else if (var0 instanceof fg) {
         return b((fg)var0);
      } else {
         throw new RuntimeException("unsupported data type");
      }
   }

   static String a(eV var0, String var1, boolean var2) {
      return a((eV)var0, var1, var2, (Predicate)null);
   }

   private static String a(eV var0, String var1, boolean var2, Predicate var3) {
      StringBuilder var4 = new StringBuilder();
      var4.append('[');

      for(int var5 = 0; var5 < var0.length; ++var5) {
         if (var5 > 0) {
            var4.append(',');
         }

         if (var1 != null) {
            var4.append(var1 + "\t");
         }

         var4.append(a(var0.values[var5], var1 == null ? null : var1 + "\t", var2, var3));
      }

      if (var0.length > 0) {
         var4.append(var1);
      }

      var4.append(']');
      return var4.toString();
   }

   static String a(eY var0, String var1, boolean var2) {
      return a((eY)var0, var1, var2, (Predicate)null);
   }

   private static String a(eY var0, String var1, boolean var2, Predicate var3) {
      StringBuilder var4 = new StringBuilder();
      var4.append('{');

      for(int var5 = 0; var5 < var0.length; ++var5) {
         if (var5 > 0) {
            var4.append(',');
         }

         if (var1 != null) {
            var4.append(var1 + "\t");
         }

         var4.append(b(var0.names[var5], var3));
         var4.append(':');
         if (var2) {
            var4.append(' ');
         }

         var4.append(a(var0.values[var5], var1 == null ? null : var1 + "\t", var2, var3));
      }

      if (var0.length > 0) {
         var4.append(var1);
      }

      var4.append('}');
      return var4.toString();
   }

   private static String a(fg var0) {
      StringBuilder var1 = new StringBuilder();
      byte[] var5;
      int var4 = (var5 = var0.toByteArray()).length;

      for(int var3 = 0; var3 < var4; ++var3) {
         byte var2 = var5[var3];
         int var6 = var2 & 255;
         if (var6 == 13) {
            var1.append("\\r");
         } else if (var6 == 10) {
            var1.append("\\n");
         } else if (var6 == 9) {
            var1.append("\\t");
         } else if (var6 == 12) {
            var1.append("\\f");
         } else if (var6 == 8) {
            var1.append("\\b");
         } else if (var6 == 11) {
            var1.append("\\v");
         } else if (var6 == 0) {
            var1.append("\\0");
         } else if (var6 == 34) {
            var1.append("\\\"");
         } else if (var6 == 92) {
            var1.append("\\\\");
         } else if (var6 >= 32 && var6 < 128) {
            var1.append(Character.toString((char)var6));
         } else {
            var1.append("\\x");
            var1.append("0123456789ABCDEFabcdef".charAt(var6 >> 4 & 15));
            var1.append("0123456789ABCDEFabcdef".charAt(var6 & 15));
         }
      }

      return var1.toString();
   }

   private static String b(fg var0) {
      StringBuilder var1 = new StringBuilder();
      var1.append('"');
      var1.append(a(var0));
      var1.append('"');
      return var1.toString();
   }

   private static String a(String var0, Predicate var1) {
      StringBuilder var2 = new StringBuilder();
      char[] var6;
      int var5 = (var6 = var0.toCharArray()).length;

      for(int var4 = 0; var4 < var5; ++var4) {
         char var3 = var6[var4];
         if (var3 == '\r') {
            var2.append("\\r");
         } else if (var3 == '\n') {
            var2.append("\\n");
         } else if (var3 == '\t') {
            var2.append("\\t");
         } else if (var3 == '\f') {
            var2.append("\\f");
         } else if (var3 == '\b') {
            var2.append("\\b");
         } else if (var3 == '"') {
            var2.append("\\\"");
         } else if (var3 == '\\') {
            var2.append("\\\\");
         } else if (var3 >= ' ' && (var1 == null || var1.test(var3))) {
            var2.append(Character.toString(var3));
         } else {
            var2.append("\\u");
            var2.append("0123456789ABCDEFabcdef".charAt(var3 >> 12 & 15));
            var2.append("0123456789ABCDEFabcdef".charAt(var3 >> 8 & 15));
            var2.append("0123456789ABCDEFabcdef".charAt(var3 >> 4 & 15));
            var2.append("0123456789ABCDEFabcdef".charAt(var3 & 15));
         }
      }

      return var2.toString();
   }

   static String O(String var0) {
      return b(var0, (Predicate)null);
   }

   private static String b(String var0, Predicate var1) {
      StringBuilder var2 = new StringBuilder();
      var2.append('"');
      var2.append(a(var0, var1));
      var2.append('"');
      return var2.toString();
   }

   public static Object P(String var0) {
      fi var1 = new fi(var0);
      Object var2 = a(var1, var1.bI());
      if (var1.bI() >= 0) {
         throw new eX("Invalid trailing data", var1.kF, var1.kG);
      } else {
         return var2;
      }
   }

   private static Object a(fi var0, int var1) {
      if (var1 < 0) {
         throw new eX("Short read", var0.kF, var0.kG);
      } else if (var1 == 123) {
         return a(var0);
      } else if (var1 == 91) {
         return b(var0);
      } else if (var1 == 34) {
         return d(var0);
      } else if (var1 == 102) {
         if (var0.read() != 97) {
            throw new eX("Invalid token", var0.kF, var0.kG);
         } else if (var0.read() != 108) {
            throw new eX("Invalid token", var0.kF, var0.kG);
         } else if (var0.read() != 115) {
            throw new eX("Invalid token", var0.kF, var0.kG);
         } else if (var0.read() != 101) {
            throw new eX("Invalid token", var0.kF, var0.kG);
         } else {
            return Boolean.FALSE;
         }
      } else if (var1 == 116) {
         if (var0.read() != 114) {
            throw new eX("Invalid token", var0.kF, var0.kG);
         } else if (var0.read() != 117) {
            throw new eX("Invalid token", var0.kF, var0.kG);
         } else if (var0.read() != 101) {
            throw new eX("Invalid token", var0.kF, var0.kG);
         } else {
            return Boolean.TRUE;
         }
      } else if (var1 == 110) {
         if (var0.read() != 117) {
            throw new eX("Invalid token", var0.kF, var0.kG);
         } else if (var0.read() != 108) {
            throw new eX("Invalid token", var0.kF, var0.kG);
         } else if (var0.read() != 108) {
            throw new eX("Invalid token", var0.kF, var0.kG);
         } else {
            return null;
         }
      } else if (var1 == 100) {
         if (var0.read() != 97) {
            throw new eX("Invalid token", var0.kF, var0.kG);
         } else if (var0.read() != 116) {
            throw new eX("Invalid token", var0.kF, var0.kG);
         } else if (var0.read() != 97) {
            throw new eX("Invalid token", var0.kF, var0.kG);
         } else if (var0.read() != 40) {
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

   static eY Q(String var0) {
      Throwable var1 = null;
      Object var2 = null;

      try {
         fi var3 = new fi(var0);

         Throwable var10000;
         label213: {
            eY var17;
            boolean var10001;
            try {
               if (var3.bI() != 123) {
                  throw new eX("Invalid object string", var3.kF, var3.kG);
               }

               eY var4 = a(var3);
               if (var3.bI() >= 0) {
                  throw new eX("Invalid trailing data", var3.kF, var3.kG);
               }

               var17 = var4;
            } catch (Throwable var15) {
               var10000 = var15;
               var10001 = false;
               break label213;
            }

            if (var3 != null) {
               var3.close();
            }

            label197:
            try {
               return var17;
            } catch (Throwable var14) {
               var10000 = var14;
               var10001 = false;
               break label197;
            }
         }

         var1 = var10000;
         if (var3 != null) {
            var3.close();
         }

         throw var1;
      } catch (Throwable var16) {
         if (var1 == null) {
            var1 = var16;
         } else if (var1 != var16) {
            var1.addSuppressed(var16);
         }

         throw var1;
      }
   }

   private static eY a(fi var0) {
      eY var1 = new eY();
      int var2 = var0.bI();
      if (var2 == 34) {
         while(true) {
            String var3 = c(var0);
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

   static eV R(String var0) {
      Throwable var1 = null;
      Object var2 = null;

      try {
         fi var3 = new fi(var0);

         Throwable var10000;
         label213: {
            eV var17;
            boolean var10001;
            try {
               if (var3.bI() != 91) {
                  throw new eX("Invalid array string", var3.kF, var3.kG);
               }

               eV var4 = b(var3);
               if (var3.bI() >= 0) {
                  throw new eX("Invalid trailing data", var3.kF, var3.kG);
               }

               var17 = var4;
            } catch (Throwable var15) {
               var10000 = var15;
               var10001 = false;
               break label213;
            }

            if (var3 != null) {
               var3.close();
            }

            label197:
            try {
               return var17;
            } catch (Throwable var14) {
               var10000 = var14;
               var10001 = false;
               break label197;
            }
         }

         var1 = var10000;
         if (var3 != null) {
            var3.close();
         }

         throw var1;
      } catch (Throwable var16) {
         if (var1 == null) {
            var1 = var16;
         } else if (var1 != var16) {
            var1.addSuppressed(var16);
         }

         throw var1;
      }
   }

   private static eV b(fi var0) {
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

   static int ae(int var0) {
      if (var0 < 0) {
         throw new IOException("short read");
      } else {
         var0 = "0123456789ABCDEFabcdef".indexOf((char)var0);
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

   private static String c(fi var0) {
      Object var1 = d(var0);
      if (var1 instanceof String) {
         return (String)var1;
      } else {
         throw new eX("Invalid string", var0.kF, var0.kG);
      }
   }

   private static Object d(fi var0) {
      try {
         StringBuilder var1 = new StringBuilder();
         ByteArrayOutputStream var2 = new ByteArrayOutputStream();

         int var3;
         while((var3 = var0.read()) != 34) {
            if (var3 < 0) {
               throw new eX("Short read");
            }

            if (var3 == 92) {
               var3 = var0.read();
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
                  var3 = ae(var0.read()) << 12 | ae(var0.read()) << 8 | ae(var0.read()) << 4 | ae(var0.read());
                  if (var3 <= 255) {
                     if (var1 != null) {
                        var1.append((char)var3);
                     }

                     if (var2 != null) {
                        var2.write(var3);
                     }
                  } else {
                     if (var1 == null) {
                        throw new eX("Mixed encodings detected in string");
                     }

                     var2 = null;
                     var1.append((char)var3);
                  }
                  continue;
               case 118:
                  var3 = 11;
                  break;
               case 120:
                  var3 = ae(var0.read()) << 4 | ae(var0.read());
                  if (var2 == null) {
                     throw new eX("Mixed encodings detected in stringx");
                  }

                  var2.write(var3);
                  var1 = null;
                  continue;
               }
            }

            if (var1 != null) {
               var1.append((char)var3);
            }

            if (var2 != null) {
               var2.write(var3);
            }
         }

         return var1 != null ? var1.toString() : new fg(var2.toByteArray());
      } catch (eX var4) {
         throw var4;
      } catch (IOException var5) {
         throw new eX("Invalid string", var5, var0.kF, var0.kG);
      }
   }

   private static fg e(fi var0) {
      ByteArrayOutputStream var1 = new ByteArrayOutputStream();
      if (var0.read() != 48) {
         throw new eX("Invalid hex data", var0.kF, var0.kG);
      } else if (var0.read() != 120) {
         throw new eX("Invalid hex data", var0.kF, var0.kG);
      } else {
         int var2;
         int var3;
         for(; (var2 = var0.read()) != 34; var1.write(var2 << 4 | var3)) {
            if (var2 < 0) {
               throw new eX("Short read", var0.kF, var0.kG);
            }

            if ((var3 = var0.read()) < 0) {
               throw new eX("Short read", var0.kF, var0.kG);
            }

            var2 = "0123456789abcdefABCDEF".indexOf((char)var2);
            if (var2 < 0) {
               throw new eX("Invalid hex data", var0.kF, var0.kG);
            }

            if (var2 >= 16) {
               var2 -= 6;
            }

            var3 = "0123456789abcdefABCDEF".indexOf((char)var3);
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

   private static Number b(fi var0, int var1) {
      boolean var3 = false;
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
            var2 = var2.multiply(BigDecimal.TEN).add(new BigDecimal(var1 - 48));
         }
      }

      boolean var4 = true;
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
            var2 = var2.add(var10001.scaleByPowerOfTen(var5));
         } while((var1 = fi.a(var0, kZ)) >= 0);
      }

      if (fi.a(var0, lb) >= 0) {
         var4 = false;
         var1 = fi.a(var0, lc);
         boolean var9 = false;
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

   static void i(Object var0) {
      if (var0 instanceof eY) {
         ((eY)var0).kD = null;
      }

      if (var0 instanceof eV) {
         ((eV)var0).kD = null;
      }

   }

   static void a(Object var0, Object var1) {
      if (var0 instanceof eY) {
         ((eY)var0).kD = var1;
      }

      if (var0 instanceof eV) {
         ((eV)var0).kD = var1;
      }

   }
}
