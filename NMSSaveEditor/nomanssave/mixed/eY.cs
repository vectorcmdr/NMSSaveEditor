using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;

namespace NMSSaveEditor
{



public class eY {
   public static int kB = 10;
   public static int kC = 10;
   public static Pattern kH = Pattern.compile("[^\"\\.\\[\\]]+");
   public int length = 0;
   public int Length { get => length; set => length = value; }
   public int Count => length;
   public string[] names = new string[10];
   public object[] values = new object[10];
   public object kD;
   public fe kI;
   public Dictionary<object, object> kJ = new Dictionary<object, object>();
   public static Pattern kK = Pattern.compile("([^\\.\\[\\]]+)|(?:\\.([^\\.\\[\\]]+))|(?:\\[(\\d+)\\])");

   public static eY E(string var0) {
      return fh.Q(var0);
   }

   public void b(string var1, Function var2) {
      this.kJ.Put(var1, var2);
   }

   public void a(string var1, object var2) {
      for(int var3 = 0; var3 < this.Length; ++var3) {
         if (this.names[var3].Equals(var1)) {
            throw new Exception("duplicate key: " + var1);
         }
      }

      if (this.values.Length == this.Length) {
         string[] var5 = new string[this.Length + 10];
         object[] var4 = new object[this.Length + 10];
         Array.Copy(this.names, 0, var5, 0, this.Length);
         Array.Copy(this.values, 0, var4, 0, this.Length);
         this.names = var5;
         this.values = var4;
      }

      this.names[this.Length] = var1;
      this.values[this.Length] = var2;
      fh.a((object)var2, (object)this);
      ++this.Length;
   }

   public string bz() {
      return fh.a(this, Environment.NewLine, true);
   }

   public string toString() {
      return fh.a((eY)this, null, false);
   }

   public eY bE() {
      eY var1 = new eY();
      var1.names = new string[this.values.Length];
      var1.values = new object[this.values.Length];
      Array.Copy(this.names, 0, var1.names, 0, this.Length);

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

   public List<object> getNames() {
      string[] var1 = new string[this.Length];
      Array.Copy(this.names, 0, var1, 0, this.Length);
      return new List<object>(new object[]{var1});
   }

   public bool contains(string var1) {
      if (var1 == null) {
         throw new NullReferenceException();
      } else if (!kH.matcher(var1).matches()) {
         throw new Exception("Invalid name: " + var1);
      } else {
         for(int var2 = 0; var2 < this.Length; ++var2) {
            if (var1.Equals(this.names[var2])) {
               return true;
            }
         }

         return false;
      }
   }

   public object get(string var1) {
      if (var1 == null) {
         throw new NullReferenceException();
      } else if (!kH.matcher(var1).Matches()) {
         throw new Exception("Invalid name: " + var1);
      } else {
         for(int var2 = 0; var2 < this.Length; ++var2) {
            if (var1.Equals(this.names[var2])) {
               return this.values[var2];
            }
         }

         return null;
      }
   }

   public object Put(string var1, object var2) => put(var1, var2);
   public void Clear() => clear();

   public object put(string var1, object var2) {
      if (var1 == null) {
         throw new NullReferenceException();
      } else if (!kH.matcher(var1).matches()) {
         throw new Exception("Invalid name: " + var1);
      } else if (var2 != null && !fh.a(var2.GetType())) {
         throw new Exception("Unsupported type: " + var2.GetType().getSimpleName());
      } else {
         for(int var3 = 0; var3 < this.Length; ++var3) {
            if (var1.Equals(this.names[var3])) {
               object var4 = this.values[var3];
               fh.i(var4);
               this.values[var3] = var2;
               fh.a((object)var2, (object)this);
               this.firePropertyChange(var1, var4, var2);
               return var4;
            }
         }

         if (this.values.Length == this.Length) {
            string[] var5 = new string[this.Length + 10];
            object[] var6 = new object[this.Length + 10];
            Array.Copy(this.names, 0, var5, 0, this.Length);
            Array.Copy(this.values, 0, var6, 0, this.Length);
            this.names = var5;
            this.values = var6;
         }

         this.names[this.Length] = var1;
         this.values[this.Length] = var2;
         fh.a((object)var2, (object)this);
         ++this.Length;
         this.firePropertyChange(var1, (object)null, var2);
         return null;
      }
   }

   public object F(string var1) {
      if (var1 == null) {
         throw new NullReferenceException();
      } else if (!kH.matcher(var1).matches()) {
         throw new Exception("Invalid name: " + var1);
      } else {
         for(int var2 = 0; var2 < this.Length; ++var2) {
            if (var1.Equals(this.names[var2])) {
               object var3 = this.values[var2];
               fh.i(var3);
               ++var2;

               while(var2 < this.Length) {
                  this.names[var2 - 1] = this.names[var2];
                  this.values[var2 - 1] = this.values[var2];
                  ++var2;
               }

               --this.Length;
               this.firePropertyChange(var1, var3, (object)null);
               return var3;
            }
         }

         return null;
      }
   }

   public void c(eY var1) {
      if (var1 == null) {
         throw new NullReferenceException();
      } else {
         for(int var2 = 0; var2 < var1.Length; ++var2) {
            bool var3 = false;

            for(int var4 = 0; var4 < this.Length; ++var4) {
               if (var1.names[var2].Equals(this.names[var4])) {
                  object var5 = this.values[var4];
                  fh.i(var5);
                  if (var5 is eY && var1.values[var2] is eY) {
                     ((eY)var5).c((eY)var1.values[var2]);
                     this.values[var4] = var5;
                  } else {
                     this.values[var4] = var1.values[var2];
                  }

                  fh.a((object)this.values[var4], (object)this);
                  var3 = true;
               }
            }

            if (var3 == null) {
               if (this.values.Length == this.Length) {
                  string[] var6 = new string[this.Length + 10];
                  object[] var7 = new object[this.Length + 10];
                  Array.Copy(this.names, 0, var6, 0, this.Length);
                  Array.Copy(this.values, 0, var7, 0, this.Length);
                  this.names = var6;
                  this.values = var7;
               }

               this.names[this.Length] = var1.names[var2];
               this.values[this.Length] = var1.values[var2];
               fh.a((object)this.values[this.Length], (object)this);
               ++this.Length;
            }
         }

         this.firePropertyChange("", (object)null, this);
      }
   }

   public int indexOf(string var1) {
      for(int var2 = 0; var2 < this.Length; ++var2) {
         if (var1.Equals(this.names[var2])) {
            return var2;
         }
      }

      return -1;
   }

   public object set(int var1, object var2) {
      object var3 = this.values[var1];
      fh.i(var3);
      this.values[var1] = var2;
      fh.a((object)var2, (object)this);
      this.firePropertyChange(this.names[var1], var3, (object)null);
      return var3;
   }

   public object remove(int var1) {
      string var2 = this.names[var1];
      object var3 = this.values[var1];
      fh.i(var3);

      for(int var4 = var1 + 1; var4 < this.Length; ++var4) {
         this.names[var4 - 1] = this.names[var4];
         this.values[var4 - 1] = this.values[var4];
      }

      --this.Length;
      this.firePropertyChange(var2, var3, (object)null);
      return var3;
   }

   public void clear() {
      for(int var1 = 0; var1 < this.Length; ++var1) {
         fh.i(this.values[var1]);
         this.firePropertyChange(this.names[var1], this.values[var1], (object)null);
      }

      this.Length = 0;
   }

   public void a(fe var1) {
      this.kI = var1;
   }

   public void a(object var1, string var2, object var3, object var4) {
      for(int var5 = 0; var5 < this.Length; ++var5) {
         if (var1 == this.values[var5]) {
            this.firePropertyChange(this.names[var5] + var2, var3, var4);
            return;
         }
      }

   }

   public void firePropertyChange(string var1, object var2, object var3) {
      if (this.kI != null) {
         // PORT_TODO: System.Windows.Forms.Application.Run(() => {
            // PORT_TODO: this.kI.propertyChanged(var1, var2, var3);
         // PORT_TODO: });
      }

      string var4 = var1.Length == 0 ? "" : "." + var1;
      if (this.kD is eY) {
         ((eY)this.kD).a(this, var4, var2, var3);
      }

      if (this.kD is eV) {
         ((eV)this.kD).a(this, var4, var2, var3);
      }

   }

   public fc G(string var1) {
      // PORT_TODO: IEnumerator<object> var3 = this.kJ.entrySet().GetEnumerator();

      if (false) { // PORT_TODO: original while had errors
         // PORT_TODO: KeyValuePair<object, object> var2 = (KeyValuePair<object, object>)var3.Current;
         // PORT_TODO: if (var1.Equals(var2.getKey())) {
      // PORT_TODO: KeyValuePair<object, object> var2 = null; // PORT_TODO: stub declaration
            // PORT_TODO: var1 = (string)((Function)var2.getValue()).apply(this);
            // PORT_TODO: break;
         // PORT_TODO: }

         // PORT_TODO: if (var1.StartsWith((string)var2.getKey() + ".") || var1.StartsWith((string)var2.getKey() + "[")) {
      // PORT_TODO: KeyValuePair<object, object> var2 = null; // PORT_TODO: stub declaration
            // PORT_TODO: var1 = (string)((Function)var2.getValue()).apply(this) + var1.Substring(((string)var2.getKey()).length());
            // PORT_TODO: break;
         // PORT_TODO: }
      }

// PORT_TODO: 
      // PORT_TODO: Matcher var5 = kK.matcher(var1);
      // PORT_TODO: if (var5.find() && var5.Start() == 0) {
         // PORT_TODO: int var6 = var5.end();
         // PORT_TODO: object var4;
         // PORT_TODO: if (var5.group(1) != null) {
            // PORT_TODO: var4 = new fb(this, var5.group(1), (fc)null);
         // PORT_TODO: } else {
            // PORT_TODO: if (var5.group(3) == null) {
               // PORT_TODO: throw new Exception("Invalid path");
            // PORT_TODO: }

// PORT_TODO: 
            // PORT_TODO: var4 = new eZ(this, int.Parse(var5.group(3)), (fc)null);
         // PORT_TODO: }

// PORT_TODO: 
         // PORT_TODO: while(var5.find() && var5.Start() == var6) {
            // PORT_TODO: var6 = var5.end();
            // PORT_TODO: if (var5.group(2) != null) {
               // PORT_TODO: var4 = new fb(this, var5.group(2), (fc)var4);
            // PORT_TODO: } else {
               // PORT_TODO: if (var5.group(3) == null) {
                  // PORT_TODO: throw new Exception("Invalid path");
               // PORT_TODO: }

// PORT_TODO: 
               // PORT_TODO: var4 = new eZ(this, int.Parse(var5.group(3)), (fc)var4);
            // PORT_TODO: }
         // PORT_TODO: }

// PORT_TODO: 
         // PORT_TODO: if (true) { // PORT_TODO: original condition had errors
            // PORT_TODO: return (fc)var4;
         // PORT_TODO: }
      // PORT_TODO: }

// PORT_TODO: 
      // PORT_TODO: throw new Exception("Invalid path");
   }

// PORT_TODO: 
   // PORT_TODO: public object getValue(string var1) {
      // PORT_TODO: try {
         // PORT_TODO: return this.G(var1).getValue();
      // PORT_TODO: } catch (fd var3) {
         // PORT_TODO: hc.debug("Path not found: " + var1);
         // PORT_TODO: return null;
         // PORT_TODO: hc.warn("Error getting value: " + var1 + ", " + var4.getMessage());
         // PORT_TODO: return null;
      // PORT_TODO: }
   // PORT_TODO: }

// PORT_TODO: 
   // PORT_TODO: public eY H(string var1) {
      // PORT_TODO: return (eY)this.getValue(var1);
   // PORT_TODO: }

// PORT_TODO: 
   // PORT_TODO: public eV d(string var1) {
      // PORT_TODO: return (eV)this.getValue(var1);
   // PORT_TODO: }

// PORT_TODO: 
   // PORT_TODO: public string getValueAsString(string var1) {
      // PORT_TODO: object var2 = this.getValue(var1);
      // PORT_TODO: return var2 is fg ? var2.ToString() : (string)var2;
   // PORT_TODO: }

// PORT_TODO: 
   // PORT_TODO: public string I(string var1) {
      // PORT_TODO: object var2 = this.getValue(var1);
      // PORT_TODO: if (var2 == null) {
         // PORT_TODO: return "";
      // PORT_TODO: } else if (!(var2 is Number)) {
         // PORT_TODO: return (string)this.getValue(var1);
      // PORT_TODO: } else {
         // PORT_TODO: string var3;
         // PORT_TODO: for(var3 = ((Number)var2).longValue().ToString("X"); var3.Length < 16; var3 = "0" + var3) {
         // PORT_TODO: }

// PORT_TODO: 
         // PORT_TODO: return "0x" + var3.ToUpper();
      // PORT_TODO: }
   // PORT_TODO: }

// PORT_TODO: 
   // PORT_TODO: public int J(string var1) {
      // PORT_TODO: object var2 = this.getValue(var1);
      // PORT_TODO: return var2 == null ? 0 : ((Number)var2).intValue();
   // PORT_TODO: }

// PORT_TODO: 
   // PORT_TODO: public int c(string var1, int var2) {
      // PORT_TODO: object var3 = this.getValue(var1);
      // PORT_TODO: return var3 == null ? var2 : ((Number)var3).intValue();
   // PORT_TODO: }

// PORT_TODO: 
   // PORT_TODO: public long K(string var1) {
      // PORT_TODO: object var2 = this.getValue(var1);
      // PORT_TODO: return var2 == null ? 0L : ((Number)var2).longValue();
   // PORT_TODO: }

// PORT_TODO: 
   // PORT_TODO: public long a(string var1, long var2) {
      // PORT_TODO: object var4 = this.getValue(var1);
      // PORT_TODO: return var4 == null ? var2 : ((Number)var4).longValue();
   // PORT_TODO: }

// PORT_TODO: 
   // PORT_TODO: public double L(string var1) {
      // PORT_TODO: object var2 = this.getValue(var1);
      // PORT_TODO: return var2 == null ? 0.0D : ((Number)var2).doubleValue();
   // PORT_TODO: }

// PORT_TODO: 
   // PORT_TODO: public double c(string var1, double var2) {
      // PORT_TODO: object var4 = this.getValue(var1);
      // PORT_TODO: return var4 == null ? var2 : ((Number)var4).doubleValue();
   // PORT_TODO: }

// PORT_TODO: 
   // PORT_TODO: public bool M(string var1) {
      // PORT_TODO: object var2 = this.getValue(var1);
      // PORT_TODO: return var2 == null ? false : (Boolean)var2;
   // PORT_TODO: }

// PORT_TODO: 
   // PORT_TODO: public bool a(string var1, bool var2) {
      // PORT_TODO: object var3 = this.getValue(var1);
      // PORT_TODO: return var3 == null ? var2 : (Boolean)var3;
   // PORT_TODO: }

// PORT_TODO: 
   // PORT_TODO: public object b(string var1, object var2) {
      // PORT_TODO: return this.G(var1).a(var2, false);
   // PORT_TODO: }

// PORT_TODO: 
   // PORT_TODO: public object c(string var1, object var2) {
      // PORT_TODO: return this.G(var1).a(var2, true);
   // PORT_TODO: }

// PORT_TODO: 
   // PORT_TODO: public object N(string var1) {
      // PORT_TODO: try {
         // PORT_TODO: return this.G(var1).bG();
      // PORT_TODO: } catch (fd var3) {
         // PORT_TODO: hc.debug("Path not found: " + var1);
         // PORT_TODO: return null;
         // PORT_TODO: hc.warn("Error getting value: " + var1 + ", " + var4.getMessage());
         // PORT_TODO: return null;
      // PORT_TODO: }
   // PORT_TODO: }

// PORT_TODO: 
   // PORT_TODO: public eY b(string var1, eY var2) {
      // PORT_TODO: return this.G(var1).e(var2);
   // PORT_TODO: }

// PORT_TODO: 
   // PORT_TODO: public void d(eY var1) {
      // PORT_TODO: if (var1 == null) {
         // PORT_TODO: throw new NullReferenceException();
      // PORT_TODO: } else if (var1.kD != null) {
         // PORT_TODO: throw new Exception("object must not have a parent");
      // PORT_TODO: } else {
         // PORT_TODO: this.Clear();
         // PORT_TODO: this.Length = var1.Length;
         // PORT_TODO: this.names = new string[var1.Length];
         // PORT_TODO: this.values = new object[var1.Length];
         // PORT_TODO: Array.Copy(var1.names, 0, this.names, 0, this.Length);

// PORT_TODO: 
         // PORT_TODO: for(int var2 = 0; var2 < this.Length; ++var2) {
            // PORT_TODO: if (var1.values[var2] is eY) {
               // PORT_TODO: this.values[var2] = ((eY)var1.values[var2]).bE();
               // PORT_TODO: fh.a((object)this.values[var2], (object)this);
            // PORT_TODO: } else if (var1.values[var2] is eV) {
               // PORT_TODO: this.values[var2] = ((eV)var1.values[var2]).bA();
               // PORT_TODO: fh.a((object)this.values[var2], (object)this);
            // PORT_TODO: } else {
               // PORT_TODO: this.values[var2] = var1.values[var2];
            // PORT_TODO: }

// PORT_TODO: 
            // PORT_TODO: this.firePropertyChange(this.names[var2], (object)null, this.values[var2]);
         // PORT_TODO: }

// PORT_TODO: 
      // PORT_TODO: }
   // PORT_TODO: }

// PORT_TODO: 
   // PORT_TODO: public void c(FileInfo var1) {
      // PORT_TODO: Exception var2 = null;
      // PORT_TODO: object var3 = null;

// PORT_TODO: 
      // PORT_TODO: try {
         // PORT_TODO: FileStream var4 = new FileStream((var1).ToString(), System.IO.FileMode.Open);

// PORT_TODO: 
         // PORT_TODO: try {
            // PORT_TODO: string var5 = fh.b(this, true);
            // PORT_TODO: var4.Write(var5.GetBytes(System.Text.Encoding.UTF8));
         // PORT_TODO: } finally {
            // PORT_TODO: if (var4 != null) {
               // PORT_TODO: var4.Close();
            // PORT_TODO: }

// PORT_TODO: 
         // PORT_TODO: }

// PORT_TODO: 
      // PORT_TODO: } catch (Exception var11) {
         // PORT_TODO: if (var2 == null) {
            // PORT_TODO: var2 = var11;
         // PORT_TODO: } else if (var2 != var11) {
            // PORT_TODO: var2.addSuppressed(var11);
         // PORT_TODO: }

// PORT_TODO: 
         // PORT_TODO: throw var2;
      // PORT_TODO: }
   // PORT_TODO: }

// PORT_TODO: 
   // PORT_TODO: public void d(FileInfo var1) {
      // PORT_TODO: Exception var2 = null;
      // PORT_TODO: object var3 = null;

// PORT_TODO: 
      // PORT_TODO: try {
         // PORT_TODO: FileStream var4 = new FileStream((var1).ToString(), System.IO.FileMode.Open);

// PORT_TODO: 
         // PORT_TODO: try {
      // PORT_TODO: object var6 = null; // PORT_TODO: stub declaration
            // PORT_TODO: string var5 = new string(hk.g(var4), StandardCharsets.UTF_8);
            // PORT_TODO: object var6 = fh.P(var5);
            // PORT_TODO: if (!(var6 is eY)) {
      // PORT_TODO: var6 = null; // PORT_TODO: stub declaration
               // PORT_TODO: throw new eX("object expected", 0, 0);
            // PORT_TODO: }

// PORT_TODO: 
            // PORT_TODO: this.d((eY)var6);
         // PORT_TODO: } finally {
            // PORT_TODO: if (var4 != null) {
               // PORT_TODO: var4.Close();
            // PORT_TODO: }

// PORT_TODO: 
         // PORT_TODO: }

// PORT_TODO: 
      // PORT_TODO: } catch (Exception var12) {
         // PORT_TODO: if (var2 == null) {
            // PORT_TODO: var2 = var12;
         // PORT_TODO: } else if (var2 != var12) {
            // PORT_TODO: var2.addSuppressed(var12);
         // PORT_TODO: }

// PORT_TODO: 
         // PORT_TODO: throw var2;
      // PORT_TODO: }
   // PORT_TODO: }
   // PORT_TODO: public object clone() {
      // PORT_TODO: return this.bE();
   // PORT_TODO: }
   // PORT_TODO: public static Pattern bF() {
      // PORT_TODO: return kH;
   // PORT_TODO: }
}

// PORT_TODO: 

// PORT_TODO: 

// PORT_TODO: 
}
