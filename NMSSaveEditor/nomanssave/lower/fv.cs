using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

class fv : fr {
   fw lI;
   fu lJ;

   fv(fu var1, fw var2) {
      this.lJ = var1;
      this.lI = var2;
   }

   public string K() {
      return "accountdata";
   }

   public eY M() {
      byte[] var1 = this.lI.ca();
      Throwable var2 = null;
      object var3 = null;

      try {
         ff var4 = new ff(new MemoryStream(var1), 2);

         Throwable var10000;
         label173: {
            eY var17;
            bool var10001;
            try {
               var17 = var4.a(eG.jW);
            } catch (Throwable var15) {
               var10000 = var15;
               var10001 = false;
               break label173;
            }

            if (var4 != null) {
               var4.Close();
            }

            label162:
            try {
               return var17;
            } catch (Throwable var14) {
               var10000 = var14;
               var10001 = false;
               break label162;
            }
         }

         var2 = var10000;
         if (var4 != null) {
            var4.Close();
         }

         throw var2;
      } catch (Throwable var16) {
         if (var2 == null) {
            var2 = var16;
         } else if (var2 != var16) {
            var2.addSuppressed(var16);
         }

         throw var2;
      }
   }

   public void k(eY var1) {
      MemoryStream var2 = new MemoryStream();
      Throwable var3 = null;
      object var4 = null;

      try {
         fj var5 = new fj(var2, 2);

         try {
            var5.h(var1);
         } finally {
            if (var5 != null) {
               var5.Close();
            }

         }
      } catch (Throwable var11) {
         if (var3 == null) {
            var3 = var11;
         } else if (var3 != var11) {
            var3.addSuppressed(var11);
         }

         throw var3;
      }

      this.lI.d(var2.toByteArray());
   }
}

}
