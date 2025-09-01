package nomanssave;

import java.awt.EventQueue;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class eY {
   private static final int kB = 10;
   private static final int kC = 10;
   private static final Pattern kH = Pattern.compile("[^\"\\.\\[\\]]+");
   int length = 0;
   String[] names = new String[10];
   Object[] values = new Object[10];
   Object kD;
   fe kI;
   Map kJ = new HashMap();
   private static final Pattern kK = Pattern.compile("([^\\.\\[\\]]+)|(?:\\.([^\\.\\[\\]]+))|(?:\\[(\\d+)\\])");

   public static eY E(String var0) {
      return fh.Q(var0);
   }

   public void b(String var1, Function var2) {
      this.kJ.put(var1, var2);
   }

   void a(String var1, Object var2) {
      for(int var3 = 0; var3 < this.length; ++var3) {
         if (this.names[var3].equals(var1)) {
            throw new RuntimeException("duplicate key: " + var1);
         }
      }

      if (this.values.length == this.length) {
         String[] var5 = new String[this.length + 10];
         Object[] var4 = new Object[this.length + 10];
         System.arraycopy(this.names, 0, var5, 0, this.length);
         System.arraycopy(this.values, 0, var4, 0, this.length);
         this.names = var5;
         this.values = var4;
      }

      this.names[this.length] = var1;
      this.values[this.length] = var2;
      fh.a((Object)var2, (Object)this);
      ++this.length;
   }

   public String bz() {
      return fh.a(this, System.lineSeparator(), true);
   }

   public String toString() {
      return fh.a((eY)this, (String)null, false);
   }

   public eY bE() {
      eY var1 = new eY();
      var1.names = new String[this.values.length];
      var1.values = new Object[this.values.length];
      System.arraycopy(this.names, 0, var1.names, 0, this.length);

      for(int var2 = 0; var2 < this.length; ++var2) {
         if (this.values[var2] instanceof eY) {
            var1.values[var2] = ((eY)this.values[var2]).bE();
            fh.a((Object)var1.values[var2], (Object)var1);
         } else if (this.values[var2] instanceof eV) {
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

   public List names() {
      String[] var1 = new String[this.length];
      System.arraycopy(this.names, 0, var1, 0, this.length);
      return Arrays.asList(var1);
   }

   public boolean contains(String var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else if (!kH.matcher(var1).matches()) {
         throw new RuntimeException("Invalid name: " + var1);
      } else {
         for(int var2 = 0; var2 < this.length; ++var2) {
            if (var1.equals(this.names[var2])) {
               return true;
            }
         }

         return false;
      }
   }

   public Object get(String var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else if (!kH.matcher(var1).matches()) {
         throw new RuntimeException("Invalid name: " + var1);
      } else {
         for(int var2 = 0; var2 < this.length; ++var2) {
            if (var1.equals(this.names[var2])) {
               return this.values[var2];
            }
         }

         return null;
      }
   }

   public Object put(String var1, Object var2) {
      if (var1 == null) {
         throw new NullPointerException();
      } else if (!kH.matcher(var1).matches()) {
         throw new RuntimeException("Invalid name: " + var1);
      } else if (var2 != null && !fh.a(var2.getClass())) {
         throw new RuntimeException("Unsupported type: " + var2.getClass().getSimpleName());
      } else {
         for(int var3 = 0; var3 < this.length; ++var3) {
            if (var1.equals(this.names[var3])) {
               Object var4 = this.values[var3];
               fh.i(var4);
               this.values[var3] = var2;
               fh.a((Object)var2, (Object)this);
               this.firePropertyChange(var1, var4, var2);
               return var4;
            }
         }

         if (this.values.length == this.length) {
            String[] var5 = new String[this.length + 10];
            Object[] var6 = new Object[this.length + 10];
            System.arraycopy(this.names, 0, var5, 0, this.length);
            System.arraycopy(this.values, 0, var6, 0, this.length);
            this.names = var5;
            this.values = var6;
         }

         this.names[this.length] = var1;
         this.values[this.length] = var2;
         fh.a((Object)var2, (Object)this);
         ++this.length;
         this.firePropertyChange(var1, (Object)null, var2);
         return null;
      }
   }

   public Object F(String var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else if (!kH.matcher(var1).matches()) {
         throw new RuntimeException("Invalid name: " + var1);
      } else {
         for(int var2 = 0; var2 < this.length; ++var2) {
            if (var1.equals(this.names[var2])) {
               Object var3 = this.values[var2];
               fh.i(var3);
               ++var2;

               while(var2 < this.length) {
                  this.names[var2 - 1] = this.names[var2];
                  this.values[var2 - 1] = this.values[var2];
                  ++var2;
               }

               --this.length;
               this.firePropertyChange(var1, var3, (Object)null);
               return var3;
            }
         }

         return null;
      }
   }

   public void c(eY var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         for(int var2 = 0; var2 < var1.length; ++var2) {
            boolean var3 = false;

            for(int var4 = 0; var4 < this.length; ++var4) {
               if (var1.names[var2].equals(this.names[var4])) {
                  Object var5 = this.values[var4];
                  fh.i(var5);
                  if (var5 instanceof eY && var1.values[var2] instanceof eY) {
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
               if (this.values.length == this.length) {
                  String[] var6 = new String[this.length + 10];
                  Object[] var7 = new Object[this.length + 10];
                  System.arraycopy(this.names, 0, var6, 0, this.length);
                  System.arraycopy(this.values, 0, var7, 0, this.length);
                  this.names = var6;
                  this.values = var7;
               }

               this.names[this.length] = var1.names[var2];
               this.values[this.length] = var1.values[var2];
               fh.a((Object)this.values[this.length], (Object)this);
               ++this.length;
            }
         }

         this.firePropertyChange("", (Object)null, this);
      }
   }

   int indexOf(String var1) {
      for(int var2 = 0; var2 < this.length; ++var2) {
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
      String var2 = this.names[var1];
      Object var3 = this.values[var1];
      fh.i(var3);

      for(int var4 = var1 + 1; var4 < this.length; ++var4) {
         this.names[var4 - 1] = this.names[var4];
         this.values[var4 - 1] = this.values[var4];
      }

      --this.length;
      this.firePropertyChange(var2, var3, (Object)null);
      return var3;
   }

   public void clear() {
      for(int var1 = 0; var1 < this.length; ++var1) {
         fh.i(this.values[var1]);
         this.firePropertyChange(this.names[var1], this.values[var1], (Object)null);
      }

      this.length = 0;
   }

   public void a(fe var1) {
      this.kI = var1;
   }

   void a(Object var1, String var2, Object var3, Object var4) {
      for(int var5 = 0; var5 < this.length; ++var5) {
         if (var1 == this.values[var5]) {
            this.firePropertyChange(this.names[var5] + var2, var3, var4);
            return;
         }
      }

   }

   private void firePropertyChange(String var1, Object var2, Object var3) {
      if (this.kI != null) {
         EventQueue.invokeLater(() -> {
            this.kI.propertyChanged(var1, var2, var3);
         });
      }

      String var4 = var1.length() == 0 ? "" : "." + var1;
      if (this.kD instanceof eY) {
         ((eY)this.kD).a(this, var4, var2, var3);
      }

      if (this.kD instanceof eV) {
         ((eV)this.kD).a(this, var4, var2, var3);
      }

   }

   private fc G(String var1) {
      Iterator var3 = this.kJ.entrySet().iterator();

      while(var3.hasNext()) {
         Entry var2 = (Entry)var3.next();
         if (var1.equals(var2.getKey())) {
            var1 = (String)((Function)var2.getValue()).apply(this);
            break;
         }

         if (var1.startsWith((String)var2.getKey() + ".") || var1.startsWith((String)var2.getKey() + "[")) {
            var1 = (String)((Function)var2.getValue()).apply(this) + var1.substring(((String)var2.getKey()).length());
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
               throw new RuntimeException("Invalid path");
            }

            var4 = new eZ(this, Integer.parseInt(var5.group(3)), (fc)null);
         }

         while(var5.find() && var5.start() == var6) {
            var6 = var5.end();
            if (var5.group(2) != null) {
               var4 = new fb(this, var5.group(2), (fc)var4);
            } else {
               if (var5.group(3) == null) {
                  throw new RuntimeException("Invalid path");
               }

               var4 = new eZ(this, Integer.parseInt(var5.group(3)), (fc)var4);
            }
         }

         if (var5.hitEnd()) {
            return (fc)var4;
         }
      }

      throw new RuntimeException("Invalid path");
   }

   public Object getValue(String var1) {
      try {
         return this.G(var1).getValue();
      } catch (fd var3) {
         hc.debug("Path not found: " + var1);
         return null;
      } catch (RuntimeException var4) {
         hc.warn("Error getting value: " + var1 + ", " + var4.getMessage());
         return null;
      }
   }

   public eY H(String var1) {
      return (eY)this.getValue(var1);
   }

   public eV d(String var1) {
      return (eV)this.getValue(var1);
   }

   public String getValueAsString(String var1) {
      Object var2 = this.getValue(var1);
      return var2 instanceof fg ? var2.toString() : (String)var2;
   }

   public String I(String var1) {
      Object var2 = this.getValue(var1);
      if (var2 == null) {
         return "";
      } else if (!(var2 instanceof Number)) {
         return (String)this.getValue(var1);
      } else {
         String var3;
         for(var3 = Long.toHexString(((Number)var2).longValue()); var3.length() < 16; var3 = "0" + var3) {
         }

         return "0x" + var3.toUpperCase();
      }
   }

   public int J(String var1) {
      Object var2 = this.getValue(var1);
      return var2 == null ? 0 : ((Number)var2).intValue();
   }

   public int c(String var1, int var2) {
      Object var3 = this.getValue(var1);
      return var3 == null ? var2 : ((Number)var3).intValue();
   }

   public long K(String var1) {
      Object var2 = this.getValue(var1);
      return var2 == null ? 0L : ((Number)var2).longValue();
   }

   public long a(String var1, long var2) {
      Object var4 = this.getValue(var1);
      return var4 == null ? var2 : ((Number)var4).longValue();
   }

   public double L(String var1) {
      Object var2 = this.getValue(var1);
      return var2 == null ? 0.0D : ((Number)var2).doubleValue();
   }

   public double c(String var1, double var2) {
      Object var4 = this.getValue(var1);
      return var4 == null ? var2 : ((Number)var4).doubleValue();
   }

   public boolean M(String var1) {
      Object var2 = this.getValue(var1);
      return var2 == null ? false : (Boolean)var2;
   }

   public boolean a(String var1, boolean var2) {
      Object var3 = this.getValue(var1);
      return var3 == null ? var2 : (Boolean)var3;
   }

   public Object b(String var1, Object var2) {
      return this.G(var1).a(var2, false);
   }

   public Object c(String var1, Object var2) {
      return this.G(var1).a(var2, true);
   }

   public Object N(String var1) {
      try {
         return this.G(var1).bG();
      } catch (fd var3) {
         hc.debug("Path not found: " + var1);
         return null;
      } catch (RuntimeException var4) {
         hc.warn("Error getting value: " + var1 + ", " + var4.getMessage());
         return null;
      }
   }

   public eY b(String var1, eY var2) {
      return this.G(var1).e(var2);
   }

   public void d(eY var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else if (var1.kD != null) {
         throw new RuntimeException("Object must not have a parent");
      } else {
         this.clear();
         this.length = var1.length;
         this.names = new String[var1.length];
         this.values = new Object[var1.length];
         System.arraycopy(var1.names, 0, this.names, 0, this.length);

         for(int var2 = 0; var2 < this.length; ++var2) {
            if (var1.values[var2] instanceof eY) {
               this.values[var2] = ((eY)var1.values[var2]).bE();
               fh.a((Object)this.values[var2], (Object)this);
            } else if (var1.values[var2] instanceof eV) {
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
         FileOutputStream var4 = new FileOutputStream(var1);

         try {
            String var5 = fh.b(this, true);
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
         FileInputStream var4 = new FileInputStream(var1);

         try {
            String var5 = new String(hk.g(var4), StandardCharsets.UTF_8);
            Object var6 = fh.P(var5);
            if (!(var6 instanceof eY)) {
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

   // $FF: synthetic method
   public Object clone() {
      return this.bE();
   }

   // $FF: synthetic method
   static Pattern bF() {
      return kH;
   }
}
