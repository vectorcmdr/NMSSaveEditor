using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{



public class fv : fr {
   public fw lI;
   public fu lJ;

   public fv(fu var1, fw var2) {
      this.lJ = var1;
      this.lI = var2;
   }

   public string K() {
      return "accountdata";
   }

   public eY M() {
      byte[] var1 = this.lI.ca();
      Exception var2 = null;
      object var3 = null;

      try {
         ff var4 = new ff(new MemoryStream(var1), 2);

         Exception var10000;
         label173: {
            eY var17;
            bool var10001;
            try {
               var17 = var4.a(eG.jW);
            } catch (Exception var15) {
               var10000 = var15;
               var10001 = false;
               goto label173;
            }

            if (var4 != null) {
               var4.Close();
            }

            label162:
            try {
               return var17;
            } catch (Exception var14) {
               var10000 = var14;
               var10001 = false;
               goto label162;
            }
         }

         var2 = var10000;
         if (var4 != null) {
            var4.Close();
         }

         throw var2;
      } catch (Exception var16) {
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
      Exception var3 = null;
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
      } catch (Exception var11) {
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
