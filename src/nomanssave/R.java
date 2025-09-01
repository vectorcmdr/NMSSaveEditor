package nomanssave;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

class R implements FocusListener {
   // $FF: synthetic field
   final Q bD;

   R(Q var1) {
      this.bD = var1;
   }

   public void focusGained(FocusEvent var1) {
   }

   public void focusLost(FocusEvent var1) {
      int var2;
      try {
         var2 = Integer.parseInt(Q.a(this.bD).getText());
         if (var2 % 250 != 0) {
            var2 = (int)Math.round((double)var2 / 250.0D);
         }

         if (var2 < Q.b(this.bD)) {
            var2 = Q.b(this.bD);
         }
      } catch (RuntimeException var4) {
         var2 = Q.c(this.bD).bE;
      }

      Q.a(this.bD).setText(Integer.toString(var2));
   }
}
