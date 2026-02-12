using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;

namespace NMSSaveEditor
{



public class hi {
   public static string sI = "9710BD8FCF192837DC6DEF6037AB2837";
   public static Dictionary<object, object> sJ = new Dictionary<object, object>();

   public static string h(long var0) {
      hj var2;
      lock(sJ) {
         if (sJ.ContainsKey(var0)) {
            var2 = (hj)sJ[var0];
         } else {
            var2 = new hj(var0);
         }
      }

      try {
         // PORT_TODO: var2.join(500L);
      } catch (InterruptedException var4) {
      }

      return var2.sL;
   }

   public static string i(long var0) {
      eY var2 = aC("http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?key=9710BD8FCF192837DC6DEF6037AB2837&steamids=" + var0);
      eV var3 = var2.d("response.players");
      if (var3 != null && var3.Count != 0) {
         for(int var4 = 0; var4 < var3.Count; ++var4) {
            eY var5 = var3.V(var4);
            if ((var0).ToString().Equals(var5.getValueAsString("steamid"))) {
               return var5.getValueAsString("personaname");
            }
         }

         return null;
      } else {
         return null;
      }
   }

   public static eY aC(string var0) {
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
         // PORT_TODO: string var7 = var1.getContentEncoding();
         // PORT_TODO: string var8 = new string(var5, var7 == null ? Encoding.UTF8 : var7);
         // PORT_TODO: return eY.E(var8);
      }
      return default; // PORT_TODO: auto-added
      return default; // PORT_TODO: auto-added
   }
   public static Dictionary<object, object> ep() {
      return sJ;
   }
   public static string j(long var0) {
      return i(var0);
   }
}



}
