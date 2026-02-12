using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Windows.Forms;
using System.Globalization;

namespace NMSSaveEditor
{

public sealed class gy {
   public static readonly gy qR = new gy("qR", "Vy'keen", "Warriors", "MODELS/COMMON/PLAYER/PLAYERCHARACTER/NPCVYKEEN.SCENE.MBIN");
   public static readonly gy qS = new gy("qS", "Korvax", "Explorers", "MODELS/COMMON/PLAYER/PLAYERCHARACTER/NPCKORVAX.SCENE.MBIN");
   public static readonly gy qT = new gy("qT", "Gek", "Traders", "MODELS/COMMON/PLAYER/PLAYERCHARACTER/NPCGEK.SCENE.MBIN");
   public static readonly gy qU = new gy("qU", "Fourth Race", (string)null, "MODELS/COMMON/PLAYER/PLAYERCHARACTER/NPCFOURTH.SCENE.MBIN");
   public static readonly gy qV = new gy("qV", "Vy'keen (Old)", (string)null, "MODELS/PLANETS/NPCS/WARRIOR/WARRIOR.SCENE.MBIN");
   public static readonly gy qW = new gy("qW", "Korvax (Old)", (string)null, "MODELS/PLANETS/NPCS/EXPLORER/EXPLORERIPAD.SCENE.MBIN");
   public static readonly gy qX = new gy("qX", "Gek (Old)", (string)null, "MODELS/PLANETS/NPCS/LOWERORDER/LOWERORDER.SCENE.MBIN");
   public static readonly gy qY = new gy("qY", "Fourth Race (Old)", (string)null, "MODELS/PLANETS/NPCS/FOURTHRACE/FOURTHRACE.SCENE.MBIN");

   public int _ordinal;
   public string _name;
   public string displayName;
   public string qZ;
   public string filename;

   public gy(string __name, string var3, string var4, string var5) {
      this._ordinal = _nextOrdinal++;
      this._name = __name;
      this.displayName = var3;
      this.qZ = var4;
      this.filename = var5;
   }

   public static int _nextOrdinal = 0;
   public static readonly gy[] _values = new gy[] { qR, qS, qT, qU, qV, qW, qX, qY };
   public static gy[] values() { return _values; }
   public static gy valueOf(string n) { return _values.FirstOrDefault(v => v._name == n); }
   public int ordinal() { return _ordinal; }
   public string name() { return _name; }
   public override string ToString() { return _name; }

   public string K() {
      return this.filename;
   }

   public string toString() {
      return this.displayName;
   }

   public static gy a_s(string var0) {
      for(int var1 = 0; var1 < values().Length; ++var1) {
         if (var0.Equals(values()[var1].filename)) {
            return values()[var1];
         }
      }

      return null;
   }

   public static gy at(string var0) {
      for(int var1 = 0; var1 < values().Length; ++var1) {
         if (var0.Equals(values()[var1].qZ)) {
            return values()[var1];
         }
      }

      return null;
   }
}
}
