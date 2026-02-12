using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{



public class gj {
   public string Name => getName();
   public gl nF;
   public int index;
   public eY bf;

   public static bool n(eY var0) {
      // PORT_TODO: return var0.d("Pets") != null && var0.d("Eggs") != null;
      return false;
   }

   public static gj[] o(eY var0) {
      List<object> var1 = new List<object>();
      // PORT_TODO: eV var2 = var0.d("Pets");
      int var3;
      eY var4;
      // PORT_TODO: if (var2 != null) {
         // PORT_TODO: for(var3 = 0; var3 < var2.Count; ++var3) {
            // PORT_TODO: var4 = var2.V(var3);
            // PORT_TODO: if (var4.d("CreatureSeed").ab(0)) {
               // PORT_TODO: var1.Add(new gj(gl.oF, var3, var4));
            // PORT_TODO: }
         // PORT_TODO: }
      // PORT_TODO: }

      // PORT_TODO: var2 = var0.d("Eggs");
      // PORT_TODO: if (var2 != null) {
         // PORT_TODO: for(var3 = 0; var3 < var2.Count; ++var3) {
            // PORT_TODO: var4 = var2.V(var3);
            // PORT_TODO: if (var4.d("CreatureSeed").ab(0)) {
               // PORT_TODO: var1.Add(new gj(gl.oG, var3, var4));
            // PORT_TODO: }
         // PORT_TODO: }
      // PORT_TODO: }

      return (gj[])var1.ToArray();
   }

   public static gj a(eY var0, FileInfo var1) {
      eV var2 = null;
      // PORT_TODO: gl var3 = null;
      if (var1.Name.EndsWith(".pet")) {
         // PORT_TODO: var2 = var0.d("Pets");
         // PORT_TODO: var3 = gl.oF;
      }

      if (var1.Name.EndsWith(".egg")) {
         // PORT_TODO: var2 = var0.d("Eggs");
         // PORT_TODO: var3 = gl.oG;
      }

      if (var2 != null && var2.Count != 0) {
         int var4 = -1;

         for(int var5 = 0; var5 < var2.Count; ++var5) {
            eY var6 = var2.V(var5);
            // PORT_TODO: if (!var6.d("CreatureSeed").ab(0)) {
               // PORT_TODO: var4 = var5;
               // PORT_TODO: break;
            // PORT_TODO: }
         }

         if (var4 < 0) {
            throw new Exception("Companion cannot be imported to current file!");
         } else {
            eY var15 = gR.az("companion");
            Exception var16 = null;
            object var7 = null;

            try {
               ff var8 = new ff(new FileStream((var1).ToString(), System.IO.FileMode.Open));

               try {
                  if (var15 == null) {
                     var15 = var8.bK();
                  } else {
                     var15.c(var8.bK());
                  }
               } finally {
                  if (var8 != null) {
                     var8.Close();
                  }

               }
            } catch (Exception var14) {
               if (var16 == null) {
                  var16 = var14;
               } else if (var16 != var14) {
                  var16.addSuppressed(var14);
               }

               throw var16;
            }

            // PORT_TODO: eV var17 = var15.d("CreatureSeed");
            // PORT_TODO: if (var17 != null && var17.ab(0)) {
               // PORT_TODO: var2.a(var4, var15);
               // PORT_TODO: return new gj(var3, var4, var15);
            // PORT_TODO: } else {
               // PORT_TODO: throw new Exception("Invalid creature data");
            // PORT_TODO: }
         }
      } else {
         throw new Exception("Companion cannot be imported to current file!");
      }
      return default;
   }

   public gj(gl var1, int var2, eY var3) {
      this.nF = var1;
      this.index = var2;
      this.bf = var3;
   }

   public int getIndex() {
      return this.index;
   }

   public void cm() {
      // PORT_TODO: this.bf.d("CreatureSeed").a(0, Boolean.FALSE);
      // PORT_TODO: this.bf.d("CreatureSeed").a(1, "0x0");
   }

   public void j(FileInfo var1) {
      Exception var2 = null;
      object var3 = null;

      try {
         fj var4 = new fj(new FileStream((var1).ToString(), System.IO.FileMode.Open));

         try {
            eY var5 = this.bf.bE();
            var4.h(var5);
         } finally {
            if (var4 != null) {
               var4.Close();
            }

         }

      } catch (Exception var11) {
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

   public string getName() {
      // PORT_TODO: return this.bf.getValueAsString("CustomName");
      return default;
   }

   public void setName(string var1) {
      // PORT_TODO: this.bf.b("CustomName", (object)var1);
   }

   public string cM() {
      // PORT_TODO: return this.bf.getValueAsString("CreatureID");
      return default;
   }

   public string cK() {
      // PORT_TODO: return this.bf.d("CreatureSeed").X(1);
      return default;
   }

   public void aa(string var1) {
      // PORT_TODO: this.bf.d("CreatureSeed").a(1, var1);
   }

   public string cN() {
      // PORT_TODO: eV var1 = this.bf.d("CreatureSecondarySeed");
      // PORT_TODO: return var1.ab(0) ? var1.X(1) : "";
      return default;
   }

   public void ab(string var1) {
      // PORT_TODO: eV var2 = this.bf.d("CreatureSecondarySeed");
      if (var1 != null && var1.Length != 0) {
         // PORT_TODO: var2.a(0, true);
         // PORT_TODO: var2.a(1, var1);
      } else {
         // PORT_TODO: var2.a(0, false);
         // PORT_TODO: var2.a(1, "");
      }

   }

   public string cO() {
      // PORT_TODO: return this.bf.I("SpeciesSeed");
      return default;
   }

   public void ac(string var1) {
      // PORT_TODO: this.bf.b("SpeciesSeed", (object)var1);
   }

   public string cP() {
      // PORT_TODO: return this.bf.I("GenusSeed");
      return default;
   }

   public void ad(string var1) {
      // PORT_TODO: this.bf.b("GenusSeed", (object)var1);
   }

   public bool cQ() {
      // PORT_TODO: return this.bf.M("Predator");
      return false;
   }

   public void d(bool var1) {
      // PORT_TODO: this.bf.b("Predator", (object)var1);
   }

   public string cR() {
      // PORT_TODO: return this.bf.getValueAsString("Biome.Biome");
      return null;
   }

   public void ae(string var1) {
      // PORT_TODO: this.bf.b("Biome.Biome", (object)var1);
   }

   public string cS() {
      // PORT_TODO: return this.bf.getValueAsString("CreatureType.CreatureType");
      return null;
   }

   public void af(string var1) {
      // PORT_TODO: this.bf.b("CreatureType.CreatureType", (object)var1);
   }

   public string toString() {
      string var1 = this.Name;
      // PORT_TODO: return var1 != null && var1.Length != 0 ? var1 : this.nF.Name + " [" + this.index + "]";
      return default;
   }
}



}
