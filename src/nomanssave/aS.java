package nomanssave;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

class aS implements FocusListener {
   // $FF: synthetic field
   final aQ dr;

   aS(aQ var1) {
      this.dr = var1;
   }

   public void focusGained(FocusEvent var1) {
   }

   public void focusLost(FocusEvent var1) {
      int var2;
      try {
         var2 = Integer.parseInt(aQ.e(this.dr).getText());
         if (var2 != aQ.b(this.dr).height) {
            if (var2 < aQ.c(this.dr).height) {
               var2 = aQ.c(this.dr).height;
            } else if (var2 > aQ.d(this.dr).height && !en.aS()) {
               var2 = aQ.d(this.dr).height;
            }
         }
      } catch (RuntimeException var4) {
         var2 = aQ.b(this.dr).height;
      }

      aQ.e(this.dr).setText(Integer.toString(var2));
   }
}
