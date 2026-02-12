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

public sealed class fn {
   public static readonly fn lm = new fn("lm");
   public static readonly fn ln = new fn("ln");
   public static readonly fn lo = new fn("lo");
   public static readonly fn lp = new fn("lp");
   public static readonly fn lq = new fn("lq");
   public static readonly fn lr = new fn("lr");
   public static readonly fn ls = new fn("ls");
   public static readonly fn lt = new fn("lt");

   public int _ordinal;
   public string _name;

   public fn(string __name) {
      this._ordinal = _nextOrdinal++;
      this._name = __name;
   }

   public static int _nextOrdinal = 0;
   public static readonly fn[] _values = new fn[] { lm, ln, lo, lp, lq, lr, ls, lt };
   public static fn[] values() { return _values; }
   public static fn valueOf(string n) { return _values.FirstOrDefault(v => v._name == n); }
   public int ordinal() { return _ordinal; }
   public string name() { return _name; }
   public override string ToString() { return _name; }

   public static readonly Regex lu = new Regex("\"((?:XTp)|(?:ActiveContext))\":\"([^\"]+)\",");
   public static readonly Regex lv = new Regex("\"((?:vLc)|(?:BaseContext))\":\\{\"((?:idA)|(?:GameMode))\":(\\d+)");
   public static readonly Regex lw = new Regex("\"((?:2YS)|(?:ExpeditionContext))\":\\{\"((?:idA)|(?:GameMode))\":(\\d+)");
   public static readonly Regex lx = new Regex("\"((?:7ND)|(?:DifficultyPresetType))\":\"(\\w+)\"");

   public static fn S(string var0) {
      fn[] var4;
      int var3 = (var4 = values()).Length;

      for(int var2 = 0; var2 < var3; ++var2) {
         fn var1 = var4[var2];
         if (var0.Equals(var1.name(), StringComparison.OrdinalIgnoreCase)) {
            return var1;
         }
      }

      return null;
   }

   public static fn T(string var0) {
      Match var1 = lu.Match(var0);
      if (var1.Success) {
         string var2 = var1.Groups[2].Value;
         if ("Main".Equals(var2)) {
            var1 = lv.Match(var0);
         } else if ("Season".Equals(var2)) {
            var1 = lw.Match(var0);
         }

         if (var1.Success) {
            int var3 = int.Parse(var1.Groups[3].Value);
            if (var3 > 0 && var3 <= values().Length) {
               return values()[var3 - 1];
            }
         }
      }

      var1 = lx.Match(var0);
      return var1.Success ? S(var1.Groups[2].Value) : null;
   }

   public static fn i(eY var0) {
      string var1 = var0.getValueAsString("ActiveContext");
      int var2;
      if ("Main".Equals(var1)) {
         var2 = var0.J("BaseContext.GameMode");
         if (var2 > 0 && var2 <= values().Length) {
            return values()[var2 - 1];
         }
      } else if ("Season".Equals(var1)) {
         var2 = var0.J("ExpeditionContext.GameMode");
         if (var2 > 0 && var2 <= values().Length) {
            return values()[var2 - 1];
         }
      }

      string var3 = var0.getValueAsString("PlayerStateData.DifficultyState.Preset.DifficultyPresetType");
      return var3 != null ? S(var3) : null;
   }
}
}
