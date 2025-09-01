package nomanssave;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

class aR implements FocusListener {
   // $FF: synthetic field
   final aQ dr;

   aR(aQ var1) {
      this.dr = var1;
   }

   public void focusGained(FocusEvent var1) {
   }

   public void focusLost(FocusEvent var1) {
      int var2;
      try {
         var2 = Integer.parseInt(aQ.a(this.dr).getText());
         if (var2 != aQ.b(this.dr).width) {
            if (var2 < aQ.c(this.dr).width) {
               var2 = aQ.c(this.dr).width;
            } else if (var2 > aQ.d(this.dr).width && !en.aS()) {
               var2 = aQ.d(this.dr).width;
            }
         }
      } catch (RuntimeException var4) {
         var2 = aQ.b(this.dr).width;
      }

      aQ.a(this.dr).setText(Integer.toString(var2));
   }
}
