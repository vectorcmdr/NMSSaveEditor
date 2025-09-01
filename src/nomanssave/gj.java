package nomanssave;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class gj {
   private final gl nF;
   private final int index;
   private final eY bf;

   public static boolean n(eY var0) {
      return var0.d("Pets") != null && var0.d("Eggs") != null;
   }

   public static gj[] o(eY var0) {
      ArrayList var1 = new ArrayList();
      eV var2 = var0.d("Pets");
      int var3;
      eY var4;
      if (var2 != null) {
         for(var3 = 0; var3 < var2.size(); ++var3) {
            var4 = var2.V(var3);
            if (var4.d("CreatureSeed").ab(0)) {
               var1.add(new gj(gl.oF, var3, var4));
            }
         }
      }

      var2 = var0.d("Eggs");
      if (var2 != null) {
         for(var3 = 0; var3 < var2.size(); ++var3) {
            var4 = var2.V(var3);
            if (var4.d("CreatureSeed").ab(0)) {
               var1.add(new gj(gl.oG, var3, var4));
            }
         }
      }

      return (gj[])var1.toArray(new gj[0]);
   }

   public static gj a(eY var0, File var1) {
      eV var2 = null;
      gl var3 = null;
      if (var1.getName().endsWith(".pet")) {
         var2 = var0.d("Pets");
         var3 = gl.oF;
      }

      if (var1.getName().endsWith(".egg")) {
         var2 = var0.d("Eggs");
         var3 = gl.oG;
      }

      if (var2 != null && var2.size() != 0) {
         int var4 = -1;

         for(int var5 = 0; var5 < var2.size(); ++var5) {
            eY var6 = var2.V(var5);
            if (!var6.d("CreatureSeed").ab(0)) {
               var4 = var5;
               break;
            }
         }

         if (var4 < 0) {
            throw new RuntimeException("Companion cannot be imported to current file!");
         } else {
            eY var15 = gR.az("companion");
            Throwable var16 = null;
            Object var7 = null;

            try {
               ff var8 = new ff(new FileInputStream(var1));

               try {
                  if (var15 == null) {
                     var15 = var8.bK();
                  } else {
                     var15.c(var8.bK());
                  }
               } finally {
                  if (var8 != null) {
                     var8.close();
                  }

               }
            } catch (Throwable var14) {
               if (var16 == null) {
                  var16 = var14;
               } else if (var16 != var14) {
                  var16.addSuppressed(var14);
               }

               throw var16;
            }

            eV var17 = var15.d("CreatureSeed");
            if (var17 != null && var17.ab(0)) {
               var2.a(var4, var15);
               return new gj(var3, var4, var15);
            } else {
               throw new RuntimeException("Invalid creature data");
            }
         }
      } else {
         throw new RuntimeException("Companion cannot be imported to current file!");
      }
   }

   private gj(gl var1, int var2, eY var3) {
      this.nF = var1;
      this.index = var2;
      this.bf = var3;
   }

   public int getIndex() {
      return this.index;
   }

   public void cm() {
      this.bf.d("CreatureSeed").a(0, Boolean.FALSE);
      this.bf.d("CreatureSeed").a(1, "0x0");
   }

   public void j(File var1) {
      Throwable var2 = null;
      Object var3 = null;

      try {
         fj var4 = new fj(new FileOutputStream(var1));

         try {
            eY var5 = this.bf.bE();
            var4.h(var5);
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

   public gl cL() {
      return this.nF;
   }

   public String getName() {
      return this.bf.getValueAsString("CustomName");
   }

   public void setName(String var1) {
      this.bf.b("CustomName", (Object)var1);
   }

   public String cM() {
      return this.bf.getValueAsString("CreatureID");
   }

   public String cK() {
      return this.bf.d("CreatureSeed").X(1);
   }

   public void aa(String var1) {
      this.bf.d("CreatureSeed").a(1, var1);
   }

   public String cN() {
      eV var1 = this.bf.d("CreatureSecondarySeed");
      return var1.ab(0) ? var1.X(1) : "";
   }

   public void ab(String var1) {
      eV var2 = this.bf.d("CreatureSecondarySeed");
      if (var1 != null && var1.length() != 0) {
         var2.a(0, true);
         var2.a(1, var1);
      } else {
         var2.a(0, false);
         var2.a(1, "");
      }

   }

   public String cO() {
      return this.bf.I("SpeciesSeed");
   }

   public void ac(String var1) {
      this.bf.b("SpeciesSeed", (Object)var1);
   }

   public String cP() {
      return this.bf.I("GenusSeed");
   }

   public void ad(String var1) {
      this.bf.b("GenusSeed", (Object)var1);
   }

   public boolean cQ() {
      return this.bf.M("Predator");
   }

   public void d(boolean var1) {
      this.bf.b("Predator", (Object)var1);
   }

   public String cR() {
      return this.bf.getValueAsString("Biome.Biome");
   }

   public void ae(String var1) {
      this.bf.b("Biome.Biome", (Object)var1);
   }

   public String cS() {
      return this.bf.getValueAsString("CreatureType.CreatureType");
   }

   public void af(String var1) {
      this.bf.b("CreatureType.CreatureType", (Object)var1);
   }

   public String toString() {
      String var1 = this.getName();
      return var1 != null && var1.length() != 0 ? var1 : this.nF.name() + " [" + this.index + "]";
   }
}
