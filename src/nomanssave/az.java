package nomanssave;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

class az implements ActionListener {
   // $FF: synthetic field
   final ap cu;

   az(ap var1) {
      this.cu = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      Iterator var4 = eS.by().iterator();

      while(var4.hasNext()) {
         eS var3 = (eS)var4.next();
         gA var2 = ap.i(this.cu).a(var3);
         var2.a(eU.kr, false);
         var2.a(eU.ks, false);
         var2.a(eU.kt, false);
         var2.a(eU.kv, false);
         var2.a(eU.kz, false);
      }

      ap.j(this.cu).updateUI();
   }
}
