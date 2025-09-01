package nomanssave;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

class eD extends eE {
   private final String version;

   private eD(InputStream var1, String var2) {
      super((eE)null);
      this.version = var2;
      ArrayList var3 = new ArrayList();
      BufferedReader var4 = new BufferedReader(new InputStreamReader(var1));

      String var5;
      try {
         while((var5 = var4.readLine()) != null) {
            try {
               if (var5.length() != 0) {
                  int var9 = var5.indexOf("\t");
                  if (var9 < 0) {
                     hc.debug("Mapping not available: " + var5);
                     var3.add(var5);
                  } else {
                     String var6 = var5.substring(0, var9);
                     String var7 = var5.substring(var9 + 1, var5.length());
                     eF var8;
                     if ((var8 = this.t(var6)) != null) {
                        if (!var7.equals(var8.name)) {
                           throw new IOException("Mapping error: " + var6);
                        }

                        hc.debug("Mapping duplicated: " + var6);
                     } else if ((var8 = this.u(var7)) != null) {
                        if (!var6.equals(var8.key)) {
                           throw new IOException("Reverse error: " + var7);
                        }

                        hc.debug("Reverse duplicated: " + var7);
                     } else {
                        this.add(var6, var7);
                     }
                  }
               }
            } catch (RuntimeException var13) {
               hc.a("Ignoring: " + var5, var13);
            }
         }
      } finally {
         var4.close();
      }

      Iterator var15 = var3.iterator();

      while(var15.hasNext()) {
         var5 = (String)var15.next();
         if (this.t(var5) != null) {
            throw new IOException("Mapping error: " + var5);
         }

         if (this.u(var5) != null) {
            throw new IOException("Reverse error: " + var5);
         }

         this.add(var5, var5);
      }

   }

   public String toString() {
      return this.version;
   }

   // $FF: synthetic method
   eD(InputStream var1, String var2, eD var3) {
      this(var1, var2);
   }
}
