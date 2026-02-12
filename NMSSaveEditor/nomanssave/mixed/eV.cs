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

public class eV {
   public static int kB = 10;
   public static int kC = 10;
   public int length;
   public Object[] values;
   public Object kD;

   public static eV D(string var0) {
      return fh.R(var0);
   }

   public eV() {
      this.length = 0;
      this.values = new Object[10];
   }

   public eV(params Object[] var1) {
      this.length = var1.length;
      this.values = new Object[var1.length];

      for(int var2 = 0; var2 < this.length; ++var2) {
         if (var1[var2] != null && !fh.a(var1[var2].GetType())) {
            throw new Exception("Unsupported type: " + var1[var2].GetType().Name);
         }

         this.values[var2] = var1[var2];
         fh.a((Object)var1[var2], (Object)this);
      }

   }

   public void e(Object var1) {
      if (this.values.length == this.length) {
         Object[] var2 = new Object[this.length + 10];
         Array.Copy(this.values, 0, var2, 0, this.length);
         this.values = var2;
      }

      this.values[this.length] = var1;
      fh.a((Object)var1, (Object)this);
      ++this.length;
   }

   public Object U(int var1) {
      return this.values[var1];
   }

   public string bz() {
      return fh.a(this, System.lineSeparator(), true);
   }

   public string toString() {
      return fh.a((eV)this, (string)null, false);
   }

   public eV bA() {
      eV var1 = new eV();
      var1.values = new Object[this.values.length];

      for(int var2 = 0; var2 < this.length; ++var2) {
         if (this.values[var2] is eY) {
            var1.values[var2] = ((eY)this.values[var2]).bE();
            fh.a((Object)var1.values[var2], (Object)var1);
         } else if (this.values[var2] is eV) {
            var1.values[var2] = ((eV)this.values[var2]).bA();
            fh.a((Object)var1.values[var2], (Object)var1);
         } else {
            var1.values[var2] = this.values[var2];
         }
      }

      var1.length = this.length;
      return var1;
   }

   public int size() {
      return this.length;
   }

   public int indexOf(Object var1) {
      if (var1 == null) {
         throw new NullReferenceException();
      } else {
         for(int var2 = 0; var2 < this.length; ++var2) {
            if (var1.Equals(this.values[var2])) {
               return var2;
            }
         }

         return -1;
      }
   }

   public Object get(int var1) {
      if (var1 >= 0 && var1 < this.length) {
         return this.values[var1];
      } else {
         throw new IndexOutOfRangeException();
      }
   }

   public Object set(int var1, Object var2) {
      if (var1 >= 0 && var1 < this.length) {
         if (var2 != null && !fh.a(var2.GetType())) {
            throw new Exception("Unsupported type: " + var2.GetType().Name);
         } else {
            Object var3 = this.values[var1];
            fh.i(var3);
            this.values[var1] = var2;
            fh.a((Object)var2, (Object)this);
            this.firePropertyChange("[" + var1 + "]", var3, var2);
            return var3;
         }
      } else {
         throw new IndexOutOfRangeException();
      }
   }

   public void add(Object var1) {
      if (var1 != null && !fh.a(var1.GetType())) {
         throw new Exception("Unsupported type: " + var1.GetType().Name);
      } else {
         eV var2 = new eV();
         if (this.values.length == this.length) {
            var2.values = this.values;
            var2.length = this.length;
            Object[] var3 = new Object[this.length + 10];
            Array.Copy(this.values, 0, var3, 0, this.length);
            this.values = var3;
         } else {
            var2.values = new Object[this.values.length];
            Array.Copy(this.values, 0, var2.values, 0, this.length);
            var2.length = this.length;
         }

         this.values[this.length] = var1;
         fh.a((Object)var1, (Object)this);
         ++this.length;
         this.firePropertyChange("", var2, this);
      }
   }

   public void add(int var1, Object var2) {
      if (var1 >= 0 && var1 <= this.length) {
         if (var2 != null && !fh.a(var2.GetType())) {
            throw new Exception("Unsupported type: " + var2.GetType().Name);
         } else {
            eV var3 = new eV();
            if (this.values.length == this.length) {
               var3.values = this.values;
               var3.length = this.length;
               Object[] var4 = new Object[this.length + 10];
               Array.Copy(this.values, 0, var4, 0, this.length);
               this.values = var4;
            } else {
               var3.values = new Object[this.values.length];
               Array.Copy(this.values, 0, var3.values, 0, this.length);
               var3.length = this.length;
            }

            for(int var5 = this.length; var5 > var1; ++var5) {
               this.values[var5] = this.values[var5 - 1];
            }

            this.values[var1] = var2;
            fh.a((Object)var2, (Object)this);
            ++this.length;
            this.firePropertyChange("", var3, this);
         }
      } else {
         throw new IndexOutOfRangeException();
      }
   }

   public Object remove(int var1) {
      if (var1 >= 0 && var1 < this.length) {
         eV var2 = new eV();
         var2.values = new Object[this.values.length];
         Array.Copy(this.values, 0, var2.values, 0, this.length);
         var2.length = this.length;
         Object var3 = this.values[var1];
         fh.i(var3);

         for(int var4 = var1; var4 < this.length - 1; ++var4) {
            this.values[var4] = this.values[var4 + 1];
         }

         --this.length;
         this.firePropertyChange("", var2, this);
         return var3;
      } else {
         throw new IndexOutOfRangeException();
      }
   }

   public void clear() {
      eV var1 = new eV();
      var1.values = new Object[this.values.length];
      Array.Copy(this.values, 0, var1.values, 0, this.length);
      var1.length = this.length;

      for(int var2 = 0; var2 < this.length; ++var2) {
         fh.i(this.values[var2]);
      }

      this.length = 0;
      this.firePropertyChange("", var1, this);
   }

   public void a(Object var1, string var2, Object var3, Object var4) {
      for(int var5 = 0; var5 < this.length; ++var5) {
         if (var1 == this.values[var5]) {
            this.firePropertyChange("[" + var5 + "]" + var2, var3, var4);
            return;
         }
      }

   }

   public void firePropertyChange(string var1, Object var2, Object var3) {
      if (this.kD is eY) {
         ((eY)this.kD).a(this, var1, var2, var3);
      }

      if (this.kD is eV) {
         ((eV)this.kD).a(this, var1, var2, var3);
      }

   }

   public Object getValue(int var1) {
      return this.Get(var1);
   }

   public eY V(int var1) {
      return (eY)this.getValue(var1);
   }

   public eV W(int var1) {
      return (eV)this.getValue(var1);
   }

   public string X(int var1) {
      Object var2 = this.getValue(var1);
      return var2 is fg ? var2.ToString() : (string)var2;
   }

   public int Y(int var1) {
      Object var2 = this.getValue(var1);
      return var2 == null ? 0 : ((Number)var2).intValue();
   }

   public long Z(int var1) {
      Object var2 = this.getValue(var1);
      return var2 == null ? 0L : ((Number)var2).longValue();
   }

   public double aa(int var1) {
      Object var2 = this.getValue(var1);
      return var2 == null ? 0.0D : ((Number)var2).doubleValue();
   }

   public bool ab(int var1) {
      Object var2 = this.getValue(var1);
      return var2 == null ? false : (Boolean)var2;
   }

   public void a(int var1, Object var2) {
      this.Set(var1, var2);
   }

   public void f(Object var1) {
      this.Add(var1);
   }

   public bool hasValue(Object var1) {
      return this.IndexOf(var1) >= 0;
   }

   public bool ac(int var1) {
      this.Remove(var1);
      return true;
   }

   public bool g(Object var1) {
      int var2 = this.IndexOf(var1);
      if (var2 < 0) {
         return false;
      } else {
         this.Remove(var2);
         return true;
      }
   }

   public Stream bB() {
      eY[] var1 = new eY[this.length];
      int var2 = 0;

      for(int var3 = 0; var3 < this.length; ++var3) {
         if (this.values[var3] is eY) {
            var1[var2++] = (eY)this.values[var3];
         }
      }

      return Arrays.stream(var1, 0, var2);
   }

   // $FF: synthetic method
   public Object clone() {
      return this.bA();
   }
}

}
