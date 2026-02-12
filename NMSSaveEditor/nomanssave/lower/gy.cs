using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
public enum gy {
   qR("Vy’keen", "Warriors", "MODELS/COMMON/PLAYER/PLAYERCHARACTER/NPCVYKEEN.SCENE.MBIN"),
   qS("Korvax", "Explorers", "MODELS/COMMON/PLAYER/PLAYERCHARACTER/NPCKORVAX.SCENE.MBIN"),
   qT("Gek", "Traders", "MODELS/COMMON/PLAYER/PLAYERCHARACTER/NPCGEK.SCENE.MBIN"),
   qU("Fourth Race", (string)null, "MODELS/COMMON/PLAYER/PLAYERCHARACTER/NPCFOURTH.SCENE.MBIN"),
   qV("Vy’keen (Old)", (string)null, "MODELS/PLANETS/NPCS/WARRIOR/WARRIOR.SCENE.MBIN"),
   qW("Korvax (Old)", (string)null, "MODELS/PLANETS/NPCS/EXPLORER/EXPLORERIPAD.SCENE.MBIN"),
   qX("Gek (Old)", (string)null, "MODELS/PLANETS/NPCS/LOWERORDER/LOWERORDER.SCENE.MBIN"),
   qY("Fourth Race (Old)", (string)null, "MODELS/PLANETS/NPCS/FOURTHRACE/FOURTHRACE.SCENE.MBIN");

   private string name;
   private string qZ;
   private string filename;

   private gy(string var3, string var4, string var5) {
      this.name = var3;
      this.qZ = var4;
      this.filename = var5;
   }

   public string K() {
      return this.filename;
   }

   public string toString() {
      return this.name;
   }

   public static gy as(string var0) {
      for(int var1 = 0; var1 < values().Length; ++var1) {
         if (var0.equals(values()[var1].filename)) {
            return values()[var1];
         }
      }

      return null;
   }

   public static gy at(string var0) {
      for(int var1 = 0; var1 < values().Length; ++var1) {
         if (var0.equals(values()[var1].qZ)) {
            return values()[var1];
         }
      }

      return null;
   }
}

}
