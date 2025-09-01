package nomanssave;

public enum gy {
   qR("Vy’keen", "Warriors", "MODELS/COMMON/PLAYER/PLAYERCHARACTER/NPCVYKEEN.SCENE.MBIN"),
   qS("Korvax", "Explorers", "MODELS/COMMON/PLAYER/PLAYERCHARACTER/NPCKORVAX.SCENE.MBIN"),
   qT("Gek", "Traders", "MODELS/COMMON/PLAYER/PLAYERCHARACTER/NPCGEK.SCENE.MBIN"),
   qU("Fourth Race", (String)null, "MODELS/COMMON/PLAYER/PLAYERCHARACTER/NPCFOURTH.SCENE.MBIN"),
   qV("Vy’keen (Old)", (String)null, "MODELS/PLANETS/NPCS/WARRIOR/WARRIOR.SCENE.MBIN"),
   qW("Korvax (Old)", (String)null, "MODELS/PLANETS/NPCS/EXPLORER/EXPLORERIPAD.SCENE.MBIN"),
   qX("Gek (Old)", (String)null, "MODELS/PLANETS/NPCS/LOWERORDER/LOWERORDER.SCENE.MBIN"),
   qY("Fourth Race (Old)", (String)null, "MODELS/PLANETS/NPCS/FOURTHRACE/FOURTHRACE.SCENE.MBIN");

   private String name;
   private String qZ;
   private String filename;

   private gy(String var3, String var4, String var5) {
      this.name = var3;
      this.qZ = var4;
      this.filename = var5;
   }

   public String K() {
      return this.filename;
   }

   public String toString() {
      return this.name;
   }

   public static gy as(String var0) {
      for(int var1 = 0; var1 < values().length; ++var1) {
         if (var0.equals(values()[var1].filename)) {
            return values()[var1];
         }
      }

      return null;
   }

   public static gy at(String var0) {
      for(int var1 = 0; var1 < values().length; ++var1) {
         if (var0.equals(values()[var1].qZ)) {
            return values()[var1];
         }
      }

      return null;
   }
}
