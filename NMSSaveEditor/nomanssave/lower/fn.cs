using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;

namespace NMSSaveEditor
{

public sealed class fn {
   public static readonly fn lm = new fn();
   public static readonly fn ln = new fn();
   public static readonly fn lo = new fn();
   public static readonly fn lp = new fn();
   public static readonly fn lq = new fn();
   public static readonly fn lr = new fn();
   public static readonly fn ls = new fn();
   public static readonly fn lt = new fn();


   private static Pattern lu = Pattern.compile("\"((?:XTp)|(?:ActiveContext))\":\"([^\"]+)\",");
   private static Pattern lv = Pattern.compile("\"((?:vLc)|(?:BaseContext))\":\\{\"((?:idA)|(?:GameMode))\":(\\d+)");
   private static Pattern lw = Pattern.compile("\"((?:2YS)|(?:ExpeditionContext))\":\\{\"((?:idA)|(?:GameMode))\":(\\d+)");
   private static Pattern lx = Pattern.compile("\"((?:7ND)|(?:DifficultyPresetType))\":\"(\\w+)\"");

   private static fn S(string var0) {
      fn[] var4;
      int var3 = (var4 = values()).Length;

      for(int var2 = 0; var2 < var3; ++var2) {
         fn var1 = var4[var2];
         if (var0.Equals(var1.ToString())) {
            return var1;
         }
      }

      return null;
   }

   public static fn T(string var0) {
      Matcher var1 = lu.matcher(var0);
      if (var1.find()) {
         string var2 = var1.group(2);
         if ("Main".Equals(var2)) {
            var1 = lv.matcher(var0);
         } else if ("Season".Equals(var2)) {
            var1 = lw.matcher(var0);
         }

         if (var1.find()) {
            int var3 = int.Parse(var1.group(3));
            if (var3 > 0 && var3 <= values().Length) {
               return values()[var3 - 1];
            }
         }
      }

      var1 = lx.matcher(var0);
      return var1.find() ? S(var1.group(2)) : null;
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
