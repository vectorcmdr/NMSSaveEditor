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

public sealed class gL : gD {
   public static readonly gL rs = new gL("rs", "Hauler", "MODELS/COMMON/SPACECRAFT/DROPSHIPS/DROPSHIP_PROC.SCENE.MBIN", 4);
   public static readonly gL rt = new gL("rt", "Explorer", "MODELS/COMMON/SPACECRAFT/SCIENTIFIC/SCIENTIFIC_PROC.SCENE.MBIN", 4);
   public static readonly gL ru = new gL("ru", "Shuttle", "MODELS/COMMON/SPACECRAFT/SHUTTLE/SHUTTLE_PROC.SCENE.MBIN", 4);
   public static readonly gL rv = new gL("rv", "Fighter", "MODELS/COMMON/SPACECRAFT/FIGHTERS/FIGHTER_PROC.SCENE.MBIN", 4);
   public static readonly gL rw = new gL("rw", "Exotic", "MODELS/COMMON/SPACECRAFT/S-CLASS/S-CLASS_PROC.SCENE.MBIN", 4);
   public static readonly gL rx = new gL("rx", "Living", "MODELS/COMMON/SPACECRAFT/S-CLASS/BIOPARTS/BIOSHIP_PROC.SCENE.MBIN", 64);
   public static readonly gL ry = new gL("ry", "Solar", "MODELS/COMMON/SPACECRAFT/SAILSHIP/SAILSHIP_PROC.SCENE.MBIN", 4);
   public static readonly gL rz = new gL("rz", "Utopia Speeder", "MODELS/COMMON/SPACECRAFT/FIGHTERS/VRSPEEDER.SCENE.MBIN", 4);
   public static readonly gL rA = new gL("rA", "Golden Vector", "MODELS/COMMON/SPACECRAFT/FIGHTERS/FIGHTERCLASSICGOLD.SCENE.MBIN", 4);
   public static readonly gL rB = new gL("rB", "Horizon Vector NX (Switch)", "MODELS/COMMON/SPACECRAFT/FIGHTERS/FIGHTERSPECIALSWITCH.SCENE.MBIN", 4);
   public static readonly gL rC = new gL("rC", "Robot", "MODELS/COMMON/SPACECRAFT/SENTINELSHIP/SENTINELSHIP_PROC.SCENE.MBIN", 256);
   public static readonly gL rD = new gL("rD", "Starborn Runner", "MODELS/COMMON/SPACECRAFT/FIGHTERS/WRACER.SCENE.MBIN", 4);
   public static readonly gL rE = new gL("rE", "Corvette", "MODELS/COMMON/SPACECRAFT/BIGGS/BIGGS.SCENE.MBIN", 4);

   public int _ordinal;
   public string _name;
   public string displayName;
   public string filename;
   public int rF;

   public gL(string __name, string var3, string var4, int var5) {
      this._ordinal = _nextOrdinal++;
      this._name = __name;
      this.displayName = var3;
      this.filename = var4;
      this.rF = var5;
   }

   public static int _nextOrdinal = 0;
   public static readonly gL[] _values = new gL[] { rs, rt, ru, rv, rw, rx, ry, rz, rA, rB, rC, rD, rE };
   public static gL[] values() { return _values; }
   public static gL valueOf(string n) { return _values.FirstOrDefault(v => v._name == n); }
   public int ordinal() { return _ordinal; }
   public string name() { return _name; }
   public override string ToString() { return _name; }

   public string K() {
      return this.filename;
   }

   public int ea() {
      return this.rF;
   }

   public string toString() {
      return this.displayName;
   }

   public static gL aw(string var0) {
      for(int var1 = 0; var1 < values().Length; ++var1) {
         if (var0.Equals(values()[var1].filename)) {
            return values()[var1];
         }
      }

      return null;
   }
}
}
