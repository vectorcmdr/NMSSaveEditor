package nomanssave;

public enum eU {
   kr("Gek"),
   ks("Vy'keen"),
   kt("Korvax"),
   ku("Robot"),
   kv("Atlas"),
   kw("Diplomats"),
   kx("Exotics"),
   ky("None"),
   kz("Autophage");

   private String name;

   private eU(String var3) {
      this.name = var3;
   }

   public String toString() {
      return this.name;
   }

   public static eU C(String var0) {
      eU[] var4;
      int var3 = (var4 = values()).length;

      for(int var2 = 0; var2 < var3; ++var2) {
         eU var1 = var4[var2];
         if (var1.name().equals(var0)) {
            return var1;
         }
      }

      return null;
   }
}
