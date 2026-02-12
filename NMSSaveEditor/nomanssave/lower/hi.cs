using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;

namespace NMSSaveEditor
{

public class hi {
   private static string sI = "9710BD8FCF192837DC6DEF6037AB2837";
   private static Dictionary<object, object> sJ = new Dictionary<object, object>();

   public static string h(long var0) {
      hj var2;
      lock(sJ) {
         if (sJ.ContainsKey(var0)) {
            var2 = (hj)sJ.get(var0);
         } else {
            var2 = new hj(var0);
         }
      }

      try {
         var2.join(500L);
      } catch (InterruptedException var4) {
      }

      return var2.sL;
   }

   private static string i(long var0) {
      eY var2 = aC("http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?key=9710BD8FCF192837DC6DEF6037AB2837&steamids=" + var0);
      eV var3 = var2.d("response.players");
      if (var3 != null && var3.Count != 0) {
         for(int var4 = 0; var4 < var3.Count; ++var4) {
            eY var5 = var3.V(var4);
            if (Long.toString(var0).equals(var5.getValueAsString("steamid"))) {
               return var5.getValueAsString("personaname");
            }
         }

         return null;
      } else {
         return null;
      }
   }

   private static eY aC(string var0) {
      URLConnection var1 = (new URL(var0)).openConnection();
      int var2 = var1.getContentLength();
      int var3 = 0;
      Stream var4 = var1.getInputStream();

      byte[] var5;
      int var6;
      for(var5 = new byte[var2]; (var6 = var4.read(var5, var3, var2)) >= 0; var2 -= var6) {
         var3 += var6;
      }

      if (var2 > 0) {
         throw new EOFException();
      } else {
         string var7 = var1.getContentEncoding();
         string var8 = new string(var5, var7 == null ? "UTF-8" : var7);
         return eY.E(var8);
      }
   }
   static Dictionary<object, object> ep() {
      return sJ;
   }
   static string j(long var0) {
      return i(var0);
   }
}

}
