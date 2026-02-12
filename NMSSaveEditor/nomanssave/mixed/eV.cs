using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{



public class eV {
   public static int kB = 10;
   public static int kC = 10;
   public int length;
   public int Length { get => length; set => length = value; }
   public int Count => length;
   public object[] values;
   public object kD;

   public void Add(object var1) => add(var1);
   public void Add(int var1, object var2) => add(var1, var2);

   public static eV D(string var0) {
      return fh.R(var0);
   }

   public eV() {
      this.Length = 0;
      this.values = new object[10];
   }

   public eV(params object[] var1) {
      this.Length = var1.Length;
      this.values = new object[var1.Length];

      for(int var2 = 0; var2 < this.Length; ++var2) {
         if (var1[var2] != null && !fh.a(var1[var2].GetType())) {
            throw new Exception("Unsupported type: " + var1[var2].GetType().getSimpleName());
         }

         this.values[var2] = var1[var2];
         fh.a((object)var1[var2], (object)this);
      }

   }

   public void e(object var1) {
      if (this.values.Length == this.Length) {
         object[] var2 = new object[this.Length + 10];
         Array.Copy(this.values, 0, var2, 0, this.Length);
         this.values = var2;
      }

      this.values[this.Length] = var1;
      fh.a((object)var1, (object)this);
      ++this.Length;
   }

   public object U(int var1) {
      return this.values[var1];
   }

   public string bz() {
      return fh.a(this, Environment.NewLine, true);
   }

   public string toString() {
      return fh.a((eV)this, null, false);
   }

   public eV bA() {
      eV var1 = new eV();
      var1.values = new object[this.values.Length];

      for(int var2 = 0; var2 < this.Length; ++var2) {
         if (this.values[var2] is eY) {
            var1.values[var2] = ((eY)this.values[var2]).bE();
            fh.a((object)var1.values[var2], (object)var1);
         } else if (this.values[var2] is eV) {
            var1.values[var2] = ((eV)this.values[var2]).bA();
            fh.a((object)var1.values[var2], (object)var1);
         } else {
            var1.values[var2] = this.values[var2];
         }
      }

      var1.Length = this.Length;
      return var1;
   }

   public int size() {
      return this.Length;
   }

   public int indexOf(object var1) {
      if (var1 == null) {
         throw new NullReferenceException();
      } else {
         for(int var2 = 0; var2 < this.Length; ++var2) {
            if (var1.Equals(this.values[var2])) {
               return var2;
            }
         }

         return -1;
      }
   }

   public object get(int var1) {
      if (var1 >= 0 && var1 < this.Length) {
         return this.values[var1];
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public object set(int var1, object var2) {
      if (var1 >= 0 && var1 < this.Length) {
         if (var2 != null && !fh.a(var2.GetType())) {
            throw new Exception("Unsupported type: " + var2.GetType().getSimpleName());
         } else {
            object var3 = this.values[var1];
            fh.i(var3);
            this.values[var1] = var2;
            fh.a((object)var2, (object)this);
            this.firePropertyChange("[" + var1 + "]", var3, var2);
            return var3;
         }
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public void add(object var1) {
      if (var1 != null && !fh.a(var1.GetType())) {
         throw new Exception("Unsupported type: " + var1.GetType().getSimpleName());
      } else {
         eV var2 = new eV();
         if (this.values.Length == this.Length) {
            var2.values = this.values;
            var2.Length = this.Length;
            object[] var3 = new object[this.Length + 10];
            Array.Copy(this.values, 0, var3, 0, this.Length);
            this.values = var3;
         } else {
            var2.values = new object[this.values.Length];
            Array.Copy(this.values, 0, var2.values, 0, this.Length);
            var2.Length = this.Length;
         }

         this.values[this.Length] = var1;
         fh.a((object)var1, (object)this);
         ++this.Length;
         this.firePropertyChange("", var2, this);
      }
   }

   public void add(int var1, object var2) {
      if (var1 >= 0 && var1 <= this.Length) {
         if (var2 != null && !fh.a(var2.GetType())) {
            throw new Exception("Unsupported type: " + var2.GetType().getSimpleName());
         } else {
            eV var3 = new eV();
            if (this.values.Length == this.Length) {
               var3.values = this.values;
               var3.Length = this.Length;
               object[] var4 = new object[this.Length + 10];
               Array.Copy(this.values, 0, var4, 0, this.Length);
               this.values = var4;
            } else {
               var3.values = new object[this.values.Length];
               Array.Copy(this.values, 0, var3.values, 0, this.Length);
               var3.Length = this.Length;
            }

            for(int var5 = this.Length; var5 > var1; ++var5) {
               this.values[var5] = this.values[var5 - 1];
            }

            this.values[var1] = var2;
            fh.a((object)var2, (object)this);
            ++this.Length;
            this.firePropertyChange("", var3, this);
         }
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public object remove(int var1) {
      if (var1 >= 0 && var1 < this.Length) {
         eV var2 = new eV();
         var2.values = new object[this.values.Length];
         Array.Copy(this.values, 0, var2.values, 0, this.Length);
         var2.Length = this.Length;
         object var3 = this.values[var1];
         fh.i(var3);

         for(int var4 = var1; var4 < this.Length - 1; ++var4) {
            this.values[var4] = this.values[var4 + 1];
         }

         --this.Length;
         this.firePropertyChange("", var2, this);
         return var3;
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public void clear() {
      eV var1 = new eV();
      var1.values = new object[this.values.Length];
      Array.Copy(this.values, 0, var1.values, 0, this.Length);
      var1.Length = this.Length;

      for(int var2 = 0; var2 < this.Length; ++var2) {
         fh.i(this.values[var2]);
      }

      this.Length = 0;
      this.firePropertyChange("", var1, this);
   }

   public void a(object var1, string var2, object var3, object var4) {
      for(int var5 = 0; var5 < this.Length; ++var5) {
         if (var1 == this.values[var5]) {
            this.firePropertyChange("[" + var5 + "]" + var2, var3, var4);
            return;
         }
      }

   }

   public void firePropertyChange(string var1, object var2, object var3) {
      if (this.kD is eY) {
         ((eY)this.kD).a(this, var1, var2, var3);
      }

      if (this.kD is eV) {
         ((eV)this.kD).a(this, var1, var2, var3);
      }

   }

   public object getValue(int var1) {
      return this[var1];
   }

   public eY V(int var1) {
      return (eY)this.getValue(var1);
   }

   public eV W(int var1) {
      return (eV)this.getValue(var1);
   }

   public string X(int var1) {
      object var2 = this.getValue(var1);
      return var2 is fg ? var2.ToString() : (string)var2;
   }

   public int Y(int var1) {
      object var2 = this.getValue(var1);
      return var2 == null ? 0 : ((Number)var2).intValue();
   }

   public long Z(int var1) {
      object var2 = this.getValue(var1);
      return var2 == null ? 0L : ((Number)var2).longValue();
   }

   public double aa(int var1) {
      object var2 = this.getValue(var1);
      return var2 == null ? 0.0D : ((Number)var2).doubleValue();
   }

   public bool ab(int var1) {
      object var2 = this.getValue(var1);
      return var2 == null ? false : (Boolean)var2;
   }

   public void a(int var1, object var2) {
      this.set(var1, var2);
   }

   public void f(object var1) {
      this.Add(var1);
   }

   public bool hasValue(object var1) {
      return this.IndexOf(var1) >= 0;
   }

   public bool ac(int var1) {
      this.Remove(var1);
      return true;
   }

   public bool g(object var1) {
      int var2 = this.IndexOf(var1);
      if (var2 < 0) {
         return false;
      } else {
         this.Remove(var2);
         return true;
      }
   }

   public Stream bB() {
      eY[] var1 = new eY[this.Length];
      int var2 = 0;

      for(int var3 = 0; var3 < this.Length; ++var3) {
         if (this.values[var3] is eY) {
            var1[var2++] = (eY)this.values[var3];
         }
      }

      return Arrays.stream(var1, 0, var2);
   }
   public object clone() {
      return this.bA();
   }
}



}
