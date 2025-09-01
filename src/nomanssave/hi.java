package nomanssave;

import java.io.EOFException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

public class hi {
   private static final String sI = "9710BD8FCF192837DC6DEF6037AB2837";
   private static final HashMap sJ = new HashMap();

   public static String h(long var0) {
      hj var2;
      synchronized(sJ) {
         if (sJ.containsKey(var0)) {
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

   private static String i(long var0) {
      eY var2 = aC("http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?key=9710BD8FCF192837DC6DEF6037AB2837&steamids=" + var0);
      eV var3 = var2.d("response.players");
      if (var3 != null && var3.size() != 0) {
         for(int var4 = 0; var4 < var3.size(); ++var4) {
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

   private static eY aC(String var0) {
      URLConnection var1 = (new URL(var0)).openConnection();
      int var2 = var1.getContentLength();
      int var3 = 0;
      InputStream var4 = var1.getInputStream();

      byte[] var5;
      int var6;
      for(var5 = new byte[var2]; (var6 = var4.read(var5, var3, var2)) >= 0; var2 -= var6) {
         var3 += var6;
      }

      if (var2 > 0) {
         throw new EOFException();
      } else {
         String var7 = var1.getContentEncoding();
         String var8 = new String(var5, var7 == null ? "UTF-8" : var7);
         return eY.E(var8);
      }
   }

   // $FF: synthetic method
   static HashMap ep() {
      return sJ;
   }

   // $FF: synthetic method
   static String j(long var0) {
      return i(var0);
   }
}
