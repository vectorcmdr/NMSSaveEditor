package nomanssave;

import java.util.Iterator;

public class gA {
   private final eS rd;
   // $FF: synthetic field
   final gz re;

   private gA(gz var1, eS var2) {
      this.re = var1;
      this.rd = var2;
   }

   public String getID() {
      return this.rd.getID();
   }

   public boolean c(eU var1) {
      Iterator var3 = this.rd.bw().iterator();

      while(var3.hasNext()) {
         String var2 = (String)var3.next();
         if (this.rd.z(var2) == var1) {
            return gz.a(this.re, var2, var1.ordinal());
         }
      }

      return false;
   }

   public void a(eU var1, boolean var2) {
      Iterator var4 = this.rd.bw().iterator();

      while(var4.hasNext()) {
         String var3 = (String)var4.next();
         if (this.rd.z(var3) == var1) {
            gz.a(this.re, var3, var1.ordinal(), var2);
         }
      }

   }

   // $FF: synthetic method
   gA(gz var1, eS var2, gA var3) {
      this(var1, var2);
   }
}
