package nomanssave;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class av implements ActionListener {
   // $FF: synthetic field
   final ap cu;

   av(ap var1) {
      this.cu = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      String[] var2 = p.c((Container)this.cu);
      boolean var3 = false;

      for(int var4 = 0; var4 < var2.length; ++var4) {
         ey var5 = ey.d(var2[var4]);
         if (!ap.d(this.cu).contains(var2[var4])) {
            if (var5.be()) {
               ap.e(this.cu).f(var2[var4]);
            }

            if (var5.bd()) {
               ap.f(this.cu).f(var2[var4]);
            }

            ap.d(this.cu).add(var2[var4]);
            var3 = true;
         }
      }

      if (var3) {
         ap.g(this.cu).sort();
         ap.h(this.cu).updateUI();
      }

   }
}
