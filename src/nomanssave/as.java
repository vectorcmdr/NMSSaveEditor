package nomanssave;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class as implements ActionListener {
   // $FF: synthetic field
   final ap cu;

   as(ap var1) {
      this.cu = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      String[] var2 = p.b((Container)this.cu);
      boolean var3 = false;

      for(int var4 = 0; var4 < var2.length; ++var4) {
         if (!ap.a(this.cu).hasValue(var2[var4])) {
            ap.a(this.cu).f(var2[var4]);
            var3 = true;
         }
      }

      if (var3) {
         ap.b(this.cu).sort();
         ap.c(this.cu).updateUI();
      }

   }
}
