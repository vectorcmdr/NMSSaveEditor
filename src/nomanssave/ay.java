package nomanssave;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

class ay implements ActionListener {
   // $FF: synthetic field
   final ap cu;

   ay(ap var1) {
      this.cu = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      Iterator var4 = eS.by().iterator();

      while(var4.hasNext()) {
         eS var3 = (eS)var4.next();
         gA var2 = ap.i(this.cu).a(var3);
         if (var3.a(eU.kr)) {
            var2.a(eU.kr, true);
         }

         if (var3.a(eU.ks)) {
            var2.a(eU.ks, true);
         }

         if (var3.a(eU.kt)) {
            var2.a(eU.kt, true);
         }

         if (var3.a(eU.kv)) {
            var2.a(eU.kv, true);
         }

         if (var3.a(eU.kz)) {
            var2.a(eU.kz, true);
         }
      }

      ap.j(this.cu).updateUI();
   }
}
