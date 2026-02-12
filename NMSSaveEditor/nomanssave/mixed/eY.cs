using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;

namespace NMSSaveEditor
{

public class eY {
   private static int kB = 10;
   private static int kC = 10;
   private static Pattern kH = Pattern.compile("[^\"\\.\\[\\]]+");
   int length = 0;
   string[] names = new string[10];
   Object[] values = new Object[10];
   Object kD;
   fe kI;
   Map kJ = new Dictionary<object, object>();
   private static Pattern kK = Pattern.compile("([^\\.\\[\\]]+)|(?:\\.([^\\.\\[\\]]+))|(?:\\[(\\d+)\\])");

   public static eY E(string var0) {
      return fh.Q(var0);
   }

   public void b(string var1, Function var2) {
      this.kJ.put(var1, var2);
   }

   void a(string var1, Object var2) {
      for(int var3 = 0; var3 < this.Length; ++var3) {
         if (this.names[var3].equals(var1)) {
            throw new Exception("duplicate key: " + var1);
         }
      }

      if (this.values.Length == this.Length) {
         string[] var5 = new string[this.Length + 10];
         Object[] var4 = new Object[this.Length + 10];
         Array.Copy(this.names, 0, var5, 0, this.Length);
         Array.Copy(this.values, 0, var4, 0, this.Length);
         this.names = var5;
         this.values = var4;
      }

      this.names[this.Length] = var1;
      this.values[this.Length] = var2;
      fh.a((Object)var2, (Object)this);
      ++this.Length;
   }

   public string bz() {
      return fh.a(this, System.lineSeparator(), true);
   }

   public string toString() {
      return fh.a((eY)this, (string)null, false);
   }

   public eY bE() {
      eY var1 = new eY();
      var1.names = new string[this.values.Length];
      var1.values = new Object[this.values.Length];
      Array.Copy(this.names, 0, var1.names, 0, this.Length);

      for(int var2 = 0; var2 < this.Length; ++var2) {
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

      var1.Length = this.Length;
      return var1;
   }

   public int size() {
      return this.Length;
   }

   public List names() {
      string[] var1 = new string[this.Length];
      Array.Copy(this.names, 0, var1, 0, this.Length);
      return new List<object>(new object[]{var1);
   }

   public bool contains(string var1) {
      if (var1 == null) {
         throw new NullReferenceException();
      } else if (!kH.matcher(var1).matches()) {
         throw new Exception("Invalid name: " + var1);
      } else {
         for(int var2 = 0; var2 < this.Length; ++var2) {
            if (var1.equals(this.names[var2])) {
               return true;
            }
         }

         return false;
      }
   }

   public Object get(string var1) {
      if (var1 == null) {
         throw new NullReferenceException();
      } else if (!kH.matcher(var1).matches()) {
         throw new Exception("Invalid name: " + var1);
      } else {
         for(int var2 = 0; var2 < this.Length; ++var2) {
            if (var1.equals(this.names[var2])) {
               return this.values[var2];
            }
         }

         return null;
      }
   }

   public Object put(string var1, Object var2) {
      if (var1 == null) {
         throw new NullReferenceException();
      } else if (!kH.matcher(var1).matches()) {
         throw new Exception("Invalid name: " + var1);
      } else if (var2 != null && !fh.a(var2.GetType())) {
         throw new Exception("Unsupported type: " + var2.GetType().getSimpleName());
      } else {
         for(int var3 = 0; var3 < this.Length; ++var3) {
            if (var1.equals(this.names[var3])) {
               Object var4 = this.values[var3];
               fh.i(var4);
               this.values[var3] = var2;
               fh.a((Object)var2, (Object)this);
               this.firePropertyChange(var1, var4, var2);
               return var4;
            }
         }

         if (this.values.Length == this.Length) {
            string[] var5 = new string[this.Length + 10];
            Object[] var6 = new Object[this.Length + 10];
            Array.Copy(this.names, 0, var5, 0, this.Length);
            Array.Copy(this.values, 0, var6, 0, this.Length);
            this.names = var5;
            this.values = var6;
         }

         this.names[this.Length] = var1;
         this.values[this.Length] = var2;
         fh.a((Object)var2, (Object)this);
         ++this.Length;
         this.firePropertyChange(var1, (Object)null, var2);
         return null;
      }
   }

   public Object F(string var1) {
      if (var1 == null) {
         throw new NullReferenceException();
      } else if (!kH.matcher(var1).matches()) {
         throw new Exception("Invalid name: " + var1);
      } else {
         for(int var2 = 0; var2 < this.Length; ++var2) {
            if (var1.equals(this.names[var2])) {
               Object var3 = this.values[var2];
               fh.i(var3);
               ++var2;

               while(var2 < this.Length) {
                  this.names[var2 - 1] = this.names[var2];
                  this.values[var2 - 1] = this.values[var2];
                  ++var2;
               }

               --this.Length;
               this.firePropertyChange(var1, var3, (Object)null);
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
               if (var1.names[var2].equals(this.names[var4])) {
                  Object var5 = this.values[var4];
                  fh.i(var5);
                  if (var5 is eY && var1.values[var2] is eY) {
                     ((eY)var5).c((eY)var1.values[var2]);
                     this.values[var4] = var5;
                  } else {
                     this.values[var4] = var1.values[var2];
                  }

                  fh.a((Object)this.values[var4], (Object)this);
                  var3 = true;
               }
            }

            if (!var3) {
               if (this.values.Length == this.Length) {
                  string[] var6 = new string[this.Length + 10];
                  Object[] var7 = new Object[this.Length + 10];
                  Array.Copy(this.names, 0, var6, 0, this.Length);
                  Array.Copy(this.values, 0, var7, 0, this.Length);
                  this.names = var6;
                  this.values = var7;
               }

               this.names[this.Length] = var1.names[var2];
               this.values[this.Length] = var1.values[var2];
               fh.a((Object)this.values[this.Length], (Object)this);
               ++this.Length;
            }
         }

         this.firePropertyChange("", (Object)null, this);
      }
   }

   int indexOf(string var1) {
      for(int var2 = 0; var2 < this.Length; ++var2) {
         if (var1.equals(this.names[var2])) {
            return var2;
         }
      }

      return -1;
   }

   Object set(int var1, Object var2) {
      Object var3 = this.values[var1];
      fh.i(var3);
      this.values[var1] = var2;
      fh.a((Object)var2, (Object)this);
      this.firePropertyChange(this.names[var1], var3, (Object)null);
      return var3;
   }

   Object remove(int var1) {
      string var2 = this.names[var1];
      Object var3 = this.values[var1];
      fh.i(var3);

      for(int var4 = var1 + 1; var4 < this.Length; ++var4) {
         this.names[var4 - 1] = this.names[var4];
         this.values[var4 - 1] = this.values[var4];
      }

      --this.Length;
      this.firePropertyChange(var2, var3, (Object)null);
      return var3;
   }

   public void clear() {
      for(int var1 = 0; var1 < this.Length; ++var1) {
         fh.i(this.values[var1]);
         this.firePropertyChange(this.names[var1], this.values[var1], (Object)null);
      }

      this.Length = 0;
   }

   public void a(fe var1) {
      this.kI = var1;
   }

   void a(Object var1, string var2, Object var3, Object var4) {
      for(int var5 = 0; var5 < this.Length; ++var5) {
         if (var1 == this.values[var5]) {
            this.firePropertyChange(this.names[var5] + var2, var3, var4);
            return;
         }
      }

   }

   private void firePropertyChange(string var1, Object var2, Object var3) {
      if (this.kI != null) {
         System.Windows.Forms.Application.invokeLater(() -> {
            this.kI.propertyChanged(var1, var2, var3);
         });
      }

      string var4 = var1.length() == 0 ? "" : "." + var1;
      if (this.kD is eY) {
         ((eY)this.kD).a(this, var4, var2, var3);
      }

      if (this.kD is eV) {
         ((eV)this.kD).a(this, var4, var2, var3);
      }

   }

   private fc G(string var1) {
      IEnumerator var3 = this.kJ.entrySet().iterator();

      while(var3.hasNext()) {
         Entry var2 = (Entry)var3.next();
         if (var1.equals(var2.getKey())) {
            var1 = (string)((Function)var2.getValue()).apply(this);
            break;
         }

         if (var1.StartsWith((string)var2.getKey() + ".") || var1.StartsWith((string)var2.getKey() + "[")) {
            var1 = (string)((Function)var2.getValue()).apply(this) + var1.Substring(((string)var2.getKey()).length());
            break;
         }
      }

      Matcher var5 = kK.matcher(var1);
      if (var5.find() && var5.start() == 0) {
         int var6 = var5.end();
         Object var4;
         if (var5.group(1) != null) {
            var4 = new fb(this, var5.group(1), (fc)null);
         } else {
            if (var5.group(3) == null) {
               throw new Exception("Invalid path");
            }

            var4 = new eZ(this, int.Parse(var5.group(3)), (fc)null);
         }

         while(var5.find() && var5.start() == var6) {
            var6 = var5.end();
            if (var5.group(2) != null) {
               var4 = new fb(this, var5.group(2), (fc)var4);
            } else {
               if (var5.group(3) == null) {
                  throw new Exception("Invalid path");
               }

               var4 = new eZ(this, int.Parse(var5.group(3)), (fc)var4);
            }
         }

         if (var5.hitEnd()) {
            return (fc)var4;
         }
      }

      throw new Exception("Invalid path");
   }

   public Object getValue(string var1) {
      try {
         return this.G(var1).getValue();
      } catch (fd var3) {
         hc.debug("Path not found: " + var1);
         return null;
      } catch (Exception var4) {
         hc.warn("Error getting value: " + var1 + ", " + var4.getMessage());
         return null;
      }
   }

   public eY H(string var1) {
      return (eY)this.getValue(var1);
   }

   public eV d(string var1) {
      return (eV)this.getValue(var1);
   }

   public string getValueAsString(string var1) {
      Object var2 = this.getValue(var1);
      return var2 is fg ? var2.ToString() : (string)var2;
   }

   public string I(string var1) {
      Object var2 = this.getValue(var1);
      if (var2 == null) {
         return "";
      } else if (!(var2 is Number)) {
         return (string)this.getValue(var1);
      } else {
         string var3;
         for(var3 = Long.toHexString(((Number)var2).longValue()); var3.length() < 16; var3 = "0" + var3) {
         }

         return "0x" + var3.ToUpper();
      }
   }

   public int J(string var1) {
      Object var2 = this.getValue(var1);
      return var2 == null ? 0 : ((Number)var2).intValue();
   }

   public int c(string var1, int var2) {
      Object var3 = this.getValue(var1);
      return var3 == null ? var2 : ((Number)var3).intValue();
   }

   public long K(string var1) {
      Object var2 = this.getValue(var1);
      return var2 == null ? 0L : ((Number)var2).longValue();
   }

   public long a(string var1, long var2) {
      Object var4 = this.getValue(var1);
      return var4 == null ? var2 : ((Number)var4).longValue();
   }

   public double L(string var1) {
      Object var2 = this.getValue(var1);
      return var2 == null ? 0.0D : ((Number)var2).doubleValue();
   }

   public double c(string var1, double var2) {
      Object var4 = this.getValue(var1);
      return var4 == null ? var2 : ((Number)var4).doubleValue();
   }

   public bool M(string var1) {
      Object var2 = this.getValue(var1);
      return var2 == null ? false : (Boolean)var2;
   }

   public bool a(string var1, bool var2) {
      Object var3 = this.getValue(var1);
      return var3 == null ? var2 : (Boolean)var3;
   }

   public Object b(string var1, Object var2) {
      return this.G(var1).a(var2, false);
   }

   public Object c(string var1, Object var2) {
      return this.G(var1).a(var2, true);
   }

   public Object N(string var1) {
      try {
         return this.G(var1).bG();
      } catch (fd var3) {
         hc.debug("Path not found: " + var1);
         return null;
      } catch (Exception var4) {
         hc.warn("Error getting value: " + var1 + ", " + var4.getMessage());
         return null;
      }
   }

   public eY b(string var1, eY var2) {
      return this.G(var1).e(var2);
   }

   public void d(eY var1) {
      if (var1 == null) {
         throw new NullReferenceException();
      } else if (var1.kD != null) {
         throw new Exception("Object must not have a parent");
      } else {
         this.Clear();
         this.Length = var1.Length;
         this.names = new string[var1.Length];
         this.values = new Object[var1.Length];
         Array.Copy(var1.names, 0, this.names, 0, this.Length);

         for(int var2 = 0; var2 < this.Length; ++var2) {
            if (var1.values[var2] is eY) {
               this.values[var2] = ((eY)var1.values[var2]).bE();
               fh.a((Object)this.values[var2], (Object)this);
            } else if (var1.values[var2] is eV) {
               this.values[var2] = ((eV)var1.values[var2]).bA();
               fh.a((Object)this.values[var2], (Object)this);
            } else {
               this.values[var2] = var1.values[var2];
            }

            this.firePropertyChange(this.names[var2], (Object)null, this.values[var2]);
         }

      }
   }

   public void c(File var1) {
      Throwable var2 = null;
      Object var3 = null;

      try {
         FileStream var4 = new FileStream(var1);

         try {
            string var5 = fh.b(this, true);
            var4.write(var5.getBytes(StandardCharsets.UTF_8));
         } finally {
            if (var4 != null) {
               var4.close();
            }

         }

      } catch (Throwable var11) {
         if (var2 == null) {
            var2 = var11;
         } else if (var2 != var11) {
            var2.addSuppressed(var11);
         }

         throw var2;
      }
   }

   public void d(File var1) {
      Throwable var2 = null;
      Object var3 = null;

      try {
         FileStream var4 = new FileStream(var1);

         try {
            string var5 = new string(hk.g(var4), StandardCharsets.UTF_8);
            Object var6 = fh.P(var5);
            if (!(var6 is eY)) {
               throw new eX("Object expected", 0, 0);
            }

            this.d((eY)var6);
         } finally {
            if (var4 != null) {
               var4.close();
            }

         }

      } catch (Throwable var12) {
         if (var2 == null) {
            var2 = var12;
         } else if (var2 != var12) {
            var2.addSuppressed(var12);
         }

         throw var2;
      }
   }
   public Object clone() {
      return this.bE();
   }
   static Pattern bF() {
      return kH;
   }
}

}
