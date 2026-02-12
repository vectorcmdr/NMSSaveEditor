using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

#if PORT_COMPLETE

public sealed class gy {
   public static readonly gy qR = new gy("Vy’keen", "Warriors", "MODELS/COMMON/PLAYER/PLAYERCHARACTER/NPCVYKEEN.SCENE.MBIN");
   public static readonly gy qS = new gy("Korvax", "Explorers", "MODELS/COMMON/PLAYER/PLAYERCHARACTER/NPCKORVAX.SCENE.MBIN");
   public static readonly gy qT = new gy("Gek", "Traders", "MODELS/COMMON/PLAYER/PLAYERCHARACTER/NPCGEK.SCENE.MBIN");
   public static readonly gy qU = new gy("Fourth Race", null, "MODELS/COMMON/PLAYER/PLAYERCHARACTER/NPCFOURTH.SCENE.MBIN");
   public static readonly gy qV = new gy("Vy’keen (Old)", null, "MODELS/PLANETS/NPCS/WARRIOR/WARRIOR.SCENE.MBIN");
   public static readonly gy qW = new gy("Korvax (Old)", null, "MODELS/PLANETS/NPCS/EXPLORER/EXPLORERIPAD.SCENE.MBIN");
   public static readonly gy qX = new gy("Gek (Old)", null, "MODELS/PLANETS/NPCS/LOWERORDER/LOWERORDER.SCENE.MBIN");
   public static readonly gy qY = new gy("Fourth Race (Old)", null, "MODELS/PLANETS/NPCS/FOURTHRACE/FOURTHRACE.SCENE.MBIN");

   public string name;
   public string qZ;
   public string filename;

   public gy(string var3, string var4, string var5) {
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

   public static gy @as(string var0) {
      for(int var1 = 0; var1 < Values().Length; ++var1) {
         if (var0.Equals(Values()[var1].filename)) {
            return Values()[var1];
         }
      }

      return null;
   }

   public static gy at(string var0) {
      for(int var1 = 0; var1 < Values().Length; ++var1) {
         if (var0.Equals(Values()[var1].qZ)) {
            return Values()[var1];
         }
      }

      return null;
   }
}


#else

public class gy
{
   public gy() { }
   public gy(params object[] args) { }
   public static readonly gy qR = default;
   public static readonly gy qS = default;
   public static readonly gy qT = default;
   public static readonly gy qU = default;
   public static readonly gy qV = default;
   public static readonly gy qW = default;
   public static readonly gy qX = default;
   public static readonly gy qY = default;
   public string name = "";
   public string qZ = "";
   public string filename = "";
   public string K() { return ""; }
   public string toString() { return ""; }
}

#endif

}
