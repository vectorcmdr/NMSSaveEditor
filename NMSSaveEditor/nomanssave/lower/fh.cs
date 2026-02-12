using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{



public class fh {
   public static int kU = 0;
   public static int kV = 1;
   public static int kW = 2;
   public static int kX = 3;
   public static int kY = 4;
   // PORT_TODO: public static Predicate<object> kZ = (var0) => {
      // PORT_TODO: return var0 >= 48 && var0 <= 57;
   // PORT_TODO: };
   // PORT_TODO: public static Predicate<object> la = (var0) => {
      // PORT_TODO: return var0 == 46;
   // PORT_TODO: };
   // PORT_TODO: public static Predicate<object> lb = (var0) => {
      // PORT_TODO: return var0 == 101 || var0 == 69;
   // PORT_TODO: };
   // PORT_TODO: public static Predicate<object> lc = (var0) => {
      // PORT_TODO: return var0 >= 48 && var0 <= 57 || var0 == 43 || var0 == 45;
   // PORT_TODO: };
   // PORT_TODO: public static Predicate<object> ld = (var0) => {
      // PORT_TODO: return (var0 & 192) == 128;
   // PORT_TODO: };
   public static string gc = "0123456789ABCDEFabcdef";

   public static bool a(Class var0) {
      if (var0 == null) {
         return true;
      } else if (typeof(Boolean).isAssignableFrom(var0)) {
         return true;
      } else if (typeof(BigDecimal).isAssignableFrom(var0)) {
         return true;
      } else if (typeof(Number).isAssignableFrom(var0)) {
         return true;
      } else if (typeof(string).isAssignableFrom(var0)) {
         return true;
      } else if (typeof(eY).isAssignableFrom(var0)) {
         return !typeof(fk).isAssignableFrom(var0);
      } else if (typeof(eV).isAssignableFrom(var0)) {
         return true;
      } else {
         return typeof(fg).isAssignableFrom(var0);
      }
   }

   public static string b(object var0, bool var1) {
      // PORT_TODO: return a(var0, var1 ? 7 : 0, (Predicate)null);
      return default;
   }

   public static string a(object var0, int var1, Predicate<object> var2) {
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
            var3 = Environment.NewLine;
            return default;
         }
      }

      bool var4 = (var1 & 4) != 0;
      return a(var0, var3, var4, var2);
   }

   public static string a(object var0, string var1, bool var2) {
      // PORT_TODO: return a((object)var0, var1, var2, (Predicate)null);
      return default;
   }

   public static string a(object var0, string var1, bool var2, Predicate<object> var3) {
      if (var0 == null) {
         return "null";
      } else if (var0 is Boolean) {
         return var0.ToString();
      } else if (var0 is BigDecimal) {
         // PORT_TODO: return ((BigDecimal)var0).toEngineeringString();
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
      return default;
   }

   public static string a(eV var0, string var1, bool var2) {
      // PORT_TODO: return a((eV)var0, var1, var2, (Predicate)null);
      return default;
   }

   public static string a(eV var0, string var1, bool var2, Predicate<object> var3) {
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
      // PORT_TODO: return a((eY)var0, var1, var2, (Predicate)null);
      return default;
   }

   public static string a(eY var0, string var1, bool var2, Predicate<object> var3) {
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
      // PORT_TODO: byte[] var5;
      // PORT_TODO: int var4 = (var5 = var0.ToArray()).Length;

      if (false) { // PORT_TODO: original loop had errors
         // PORT_TODO: byte var2 = var5[var3];
         // PORT_TODO: int var6 = var2 & 255;
         // PORT_TODO: if (var6 == 13) {
            // PORT_TODO: var1.Append("\\r");
         // PORT_TODO: } else if (var6 == 10) {
            // PORT_TODO: var1.Append("\\n");
         // PORT_TODO: } else if (var6 == 9) {
            // PORT_TODO: var1.Append("\\t");
         // PORT_TODO: } else if (var6 == 12) {
            // PORT_TODO: var1.Append("\\f");
         // PORT_TODO: } else if (var6 == 8) {
            // PORT_TODO: var1.Append("\\b");
         // PORT_TODO: } else if (var6 == 11) {
            // PORT_TODO: var1.Append("\\v");
         // PORT_TODO: } else if (var6 == 0) {
            // PORT_TODO: var1.Append("\\0");
         // PORT_TODO: } else if (var6 == 34) {
            // PORT_TODO: var1.Append("\\\"");
         // PORT_TODO: } else if (var6 == 92) {
            // PORT_TODO: var1.Append("\\\\");
         // PORT_TODO: } else if (var6 >= 32 && var6 < 128) {
            // PORT_TODO: var1.Append(Character.toString((char)var6));
         // PORT_TODO: } else {
            // PORT_TODO: var1.Append("\\x");
            // PORT_TODO: var1.Append("0123456789ABCDEFabcdef"[var6 >> 4 & 15]);
            // PORT_TODO: var1.Append("0123456789ABCDEFabcdef"[var6 & 15]);
         // PORT_TODO: }
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

   public static string a(string var0, Predicate<object> var1) {
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
            // PORT_TODO: var2.Append(Character.toString(var3));
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
      // PORT_TODO: return b(var0, (Predicate)null);
      return default;
   }

   public static string b(string var0, Predicate<object> var1) {
      StringBuilder var2 = new StringBuilder();
      var2.Append('"');
      var2.Append(a(var0, var1));
      var2.Append('"');
      return var2.ToString();
   }

   public static object P(string var0) {
      fi var1 = new fi(var0);
      object var2 = a(var1, var1.bI());
      if (var1.bI() >= 0) {
         throw new eX("Invalid trailing data", var1.kF, var1.kG);
      } else {
         return var2;
      }
   }

   public static object a(fi var0, int var1) {
      if (var1 < 0) {
         throw new eX("Short read", var0.kF, var0.kG);
      } else if (var1 == 123) {
         return a(var0);
      } else if (var1 == 91) {
         return b(var0);
      } else if (var1 == 34) {
         return d(var0);
      } else if (var1 == 102) {
         // PORT_TODO: if (var0.ReadByte() != 97) {
            // PORT_TODO: throw new eX("Invalid token", var0.kF, var0.kG);
         // PORT_TODO: } else if (var0.ReadByte() != 108) {
            // PORT_TODO: throw new eX("Invalid token", var0.kF, var0.kG);
         // PORT_TODO: } else if (var0.ReadByte() != 115) {
            // PORT_TODO: throw new eX("Invalid token", var0.kF, var0.kG);
         // PORT_TODO: } else if (var0.ReadByte() != 101) {
            // PORT_TODO: throw new eX("Invalid token", var0.kF, var0.kG);
         // PORT_TODO: } else {
            // PORT_TODO: return Boolean.FALSE;
         // PORT_TODO: }
      } else if (var1 == 116) {
         // PORT_TODO: if (var0.ReadByte() != 114) {
            // PORT_TODO: throw new eX("Invalid token", var0.kF, var0.kG);
         // PORT_TODO: } else if (var0.ReadByte() != 117) {
            // PORT_TODO: throw new eX("Invalid token", var0.kF, var0.kG);
         // PORT_TODO: } else if (var0.ReadByte() != 101) {
            // PORT_TODO: throw new eX("Invalid token", var0.kF, var0.kG);
         // PORT_TODO: } else {
            // PORT_TODO: return Boolean.TRUE;
         // PORT_TODO: }
      } else if (var1 == 110) {
         // PORT_TODO: if (var0.ReadByte() != 117) {
            // PORT_TODO: throw new eX("Invalid token", var0.kF, var0.kG);
         // PORT_TODO: } else if (var0.ReadByte() != 108) {
            // PORT_TODO: throw new eX("Invalid token", var0.kF, var0.kG);
         // PORT_TODO: } else if (var0.ReadByte() != 108) {
            // PORT_TODO: throw new eX("Invalid token", var0.kF, var0.kG);
         // PORT_TODO: } else {
            // PORT_TODO: return null;
         // PORT_TODO: }
      } else if (var1 == 100) {
         // PORT_TODO: if (var0.ReadByte() != 97) {
            // PORT_TODO: throw new eX("Invalid token", var0.kF, var0.kG);
         // PORT_TODO: } else if (var0.ReadByte() != 116) {
            // PORT_TODO: throw new eX("Invalid token", var0.kF, var0.kG);
         // PORT_TODO: } else if (var0.ReadByte() != 97) {
            // PORT_TODO: throw new eX("Invalid token", var0.kF, var0.kG);
         // PORT_TODO: } else if (var0.ReadByte() != 40) {
            // PORT_TODO: throw new eX("Invalid token", var0.kF, var0.kG);
         // PORT_TODO: } else if (var0.bI() != 34) {
            // PORT_TODO: throw new eX("Invalid token", var0.kF, var0.kG);
         // PORT_TODO: } else {
            // PORT_TODO: fg var2 = e(var0);
            // PORT_TODO: if (var0.bI() != 41) {
               // PORT_TODO: throw new eX("Invalid token", var0.kF, var0.kG);
            // PORT_TODO: } else {
               // PORT_TODO: return var2;
            // PORT_TODO: }
         // PORT_TODO: }
      } else if (var1 != 45 && (var1 < 48 || var1 > 57)) {
         throw new eX("Invalid token", var0.kF, var0.kG);
      } else {
         return b(var0, var1);
      }
      return default; // PORT_TODO: auto
   }

   public static eY Q(string var0) {
      Exception var1 = null;
      object var2 = null;

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

            object var4 = a(var0, var0.bI());
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
      object var2 = null;

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
            object var3 = a(var0, var2);
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
      object var1 = d(var0);
      if (var1 is string) {
         return (string)var1;
      } else {
         throw new eX("Invalid string", var0.kF, var0.kG);
      }
   }

   public static object d(fi var0) {
      try {
         StringBuilder var1 = new StringBuilder();
         MemoryStream var2 = new MemoryStream();

         // PORT_TODO: int var3;
         if (false) { // PORT_TODO: original while had errors
            // PORT_TODO: if (var3 < 0) {
               // PORT_TODO: throw new eX("Short read");
            // PORT_TODO: }

            if (true) { // PORT_TODO: original condition had errors
               // PORT_TODO: var3 = var0.ReadByte();
               // PORT_TODO: if (var3 < 0) {
                  // PORT_TODO: throw new eX("Short read");
               // PORT_TODO: }

               // PORT_TODO: switch(var3) {
               // PORT_TODO: case 48:
                  // PORT_TODO: var3 = 0;
                  // PORT_TODO: break;
               // PORT_TODO: case 98:
                  // PORT_TODO: var3 = 8;
                  // PORT_TODO: break;
               // PORT_TODO: case 102:
                  // PORT_TODO: var3 = 12;
                  // PORT_TODO: break;
               // PORT_TODO: case 110:
                  // PORT_TODO: var3 = 10;
                  // PORT_TODO: break;
               // PORT_TODO: case 114:
                  // PORT_TODO: var3 = 13;
                  // PORT_TODO: break;
               // PORT_TODO: case 116:
                  // PORT_TODO: var3 = 9;
                  // PORT_TODO: break;
               // PORT_TODO: case 117:
                  // PORT_TODO: var3 = ae(var0.ReadByte()) << 12 | ae(var0.ReadByte()) << 8 | ae(var0.ReadByte()) << 4 | ae(var0.ReadByte());
                  // PORT_TODO: if (var3 <= 255) {
                     // PORT_TODO: if (true) { // PORT_TODO: original condition had errors
                        // PORT_TODO: var1.Append((char)var3);
                     // PORT_TODO: }

// PORT_TODO: 
                     // PORT_TODO: if (true) { // PORT_TODO: original condition had errors
                        // PORT_TODO: var2.Write(var3);
                     // PORT_TODO: }
                  // PORT_TODO: } else {
                     // PORT_TODO: if (var1 == null) {
                        // PORT_TODO: throw new eX("Mixed encodings detected in string");
                     // PORT_TODO: }

// PORT_TODO: 
                     // PORT_TODO: var2 = null;
                     // PORT_TODO: var1.Append((char)var3);
                  // PORT_TODO: }
                  // PORT_TODO: continue;
               // PORT_TODO: case 118:
                  // PORT_TODO: var3 = 11;
                  // PORT_TODO: break;
               // PORT_TODO: case 120:
                  // PORT_TODO: var3 = ae(var0.ReadByte()) << 4 | ae(var0.ReadByte());
                  // PORT_TODO: if (var2 == null) {
                     // PORT_TODO: throw new eX("Mixed encodings detected in stringx");
                  // PORT_TODO: }

// PORT_TODO: 
                  // PORT_TODO: var2.Write(var3);
                  // PORT_TODO: var1 = null;
                  // PORT_TODO: continue;
               // PORT_TODO: }
            }

            if (true) { // PORT_TODO: original condition had errors
               // PORT_TODO: var1.Append((char)var3);
            }

            if (true) { // PORT_TODO: original condition had errors
               // PORT_TODO: var2.Write(var3);
            }
         }

         return var1 != null ? var1.ToString() : new fg(var2.ToArray());
      } catch (eX var4) {
         throw var4;
      } catch (IOException var5) {
         throw new eX("Invalid string", var5, var0.kF, var0.kG);
      }
   }

   public static fg e(fi var0) {
      // PORT_TODO: MemoryStream var1 = new MemoryStream();
      // PORT_TODO: if (var0.ReadByte() != 48) {
         // PORT_TODO: throw new eX("Invalid hex data", var0.kF, var0.kG);
      // PORT_TODO: } else if (var0.ReadByte() != 120) {
         // PORT_TODO: throw new eX("Invalid hex data", var0.kF, var0.kG);
      // PORT_TODO: } else {
         // PORT_TODO: int var2;
         // PORT_TODO: int var3;
         // PORT_TODO: for(; (var2 = var0.ReadByte()) != 34; var1.Write(var2 << 4 | var3)) {
            // PORT_TODO: if (var2 < 0) {
               // PORT_TODO: throw new eX("Short read", var0.kF, var0.kG);
            // PORT_TODO: }

// PORT_TODO: 
            // PORT_TODO: if ((var3 = var0.ReadByte()) < 0) {
               // PORT_TODO: throw new eX("Short read", var0.kF, var0.kG);
            // PORT_TODO: }

// PORT_TODO: 
            // PORT_TODO: var2 = "0123456789abcdefABCDEF".IndexOf((char)var2);
            // PORT_TODO: if (var2 < 0) {
               // PORT_TODO: throw new eX("Invalid hex data", var0.kF, var0.kG);
            // PORT_TODO: }

// PORT_TODO: 
            // PORT_TODO: if (var2 >= 16) {
               // PORT_TODO: var2 -= 6;
            // PORT_TODO: }

// PORT_TODO: 
            // PORT_TODO: var3 = "0123456789abcdefABCDEF".IndexOf((char)var3);
            // PORT_TODO: if (var3 < 0) {
               // PORT_TODO: throw new eX("Invalid hex data", var0.kF, var0.kG);
            // PORT_TODO: }

// PORT_TODO: 
            // PORT_TODO: if (true) { // PORT_TODO: original condition had errors
               // PORT_TODO: var3 -= 6;
            // PORT_TODO: }
         // PORT_TODO: }

// PORT_TODO: 
         // PORT_TODO: return new fg(var1.ToArray());
      // PORT_TODO: }
      return default;
   }

   public static Number b(fi var0, int var1) {
      bool var3 = false;
      if (var1 == 45) {
         // PORT_TODO: var1 = fi.a(var0, kZ);
         if (var1 < 0) {
            throw new eX("Invalid token", var0.kF, var0.kG);
         }

         var3 = true;
      }

      BigDecimal var2 = new BigDecimal(var1 - 48);
      if (var1 != 48) {
         // PORT_TODO: while((var1 = fi.a(var0, kZ)) >= 0) {
            // PORT_TODO: var2 = var2.multiply(BigDecimal.TEN).Add(new BigDecimal(var1 - 48));
         // PORT_TODO: }
      }

      bool var4 = true;
      // PORT_TODO: if (fi.a(var0, la) >= 0) {
         // PORT_TODO: var4 = false;
         // PORT_TODO: var1 = fi.a(var0, kZ);
         // PORT_TODO: if (var1 < 0) {
            // PORT_TODO: throw new eX("Invalid token", var0.kF, var0.kG);
         // PORT_TODO: }

         // PORT_TODO: int var5 = 0;

         // PORT_TODO: do {
            // PORT_TODO: BigDecimal var10001 = new BigDecimal(var1 - 48);
            // PORT_TODO: --var5;
            // PORT_TODO: var2 = var2.Add(var10001.scaleByPowerOfTen(var5));
         // PORT_TODO: } while((var1 = fi.a(var0, kZ)) >= 0);
      // PORT_TODO: }

      // PORT_TODO: if (fi.a(var0, lb) >= 0) {
         // PORT_TODO: var4 = false;
         // PORT_TODO: var1 = fi.a(var0, lc);
         // PORT_TODO: bool var9 = false;
         // PORT_TODO: if (var1 == 43 || var1 == 45) {
            // PORT_TODO: var9 = var1 == 45;
            // PORT_TODO: var1 = fi.a(var0, kZ);
         // PORT_TODO: }

         // PORT_TODO: if (var1 < 0) {
            // PORT_TODO: throw new eX("Invalid token", var0.kF, var0.kG);
         // PORT_TODO: }

         // PORT_TODO: int var6 = 0;

         // PORT_TODO: do {
            // PORT_TODO: var6 *= 10;
            // PORT_TODO: var6 += var1 - 48;
         // PORT_TODO: } while((var1 = fi.a(var0, kZ)) >= 0);

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

   public static void i(object var0) {
      if (var0 is eY) {
         ((eY)var0).kD = null;
      }

      if (var0 is eV) {
         ((eV)var0).kD = null;
      }

   }

   public static void a(object var0, object var1) {
      if (var0 is eY) {
         ((eY)var0).kD = var1;
      }

      if (var0 is eV) {
         ((eV)var0).kD = var1;
      }

   }
}



}
