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

public class fg {
   private static Charset kT = Charset.forName("Windows-1252");
   byte[] bytes;

   public fg(byte[] var1) {
      this.bytes = var1;
   }

   byte[] toByteArray() {
      byte[] var1 = new byte[this.bytes.length];
      Array.Copy(this.bytes, 0, var1, 0, this.bytes.length);
      return var1;
   }

   public int indexOf(int var1) {
      return this.IndexOf(var1, 0);
   }

   public int indexOf(int var1, int var2) {
      for(int var3 = var2; var3 < this.bytes.length; ++var3) {
         if (var1 == (this.bytes[var3] & 255)) {
            return var3;
         }
      }

      return -1;
   }

   public string substring(int var1) {
      return this.Substring(var1, this.bytes.length - var1);
   }

   public string substring(int var1, int var2) {
      return new string(this.bytes, var1, var2, kT);
   }

   public string bP() {
      StringBuilder var1 = new StringBuilder();
      bool var2 = false;

      for(int var3 = 0; var3 < this.bytes.length; ++var3) {
         int var4 = this.bytes[var3] & 255;
         if (var3 == 0) {
            if (var4 != 94) {
               return this.ToString();
            }

            var1.append('^');
         } else if (var4 == 35) {
            var1.append('#');
            var2 = true;
         } else if (var2) {
            var1.append((char)var4);
         } else {
            var1.append("0123456789ABCDEFabcdef"[(this.bytes[var3] & 240) >> 4));
            var1.append("0123456789ABCDEFabcdef"[this.bytes[var3] & 15));
         }
      }

      return var1.ToString();
   }

   public bool equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 is fg)) {
         return false;
      } else {
         fg var2 = (fg)var1;
         if (this.bytes.length != var2.bytes.length) {
            return false;
         } else {
            for(int var3 = 0; var3 < this.bytes.length; ++var3) {
               if (this.bytes[var3] != var2.bytes[var3]) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   public string toString() {
      return new string(this.bytes, kT);
   }
}

}
