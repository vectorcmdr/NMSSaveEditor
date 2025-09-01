package nomanssave;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

class H implements FocusListener {
   // $FF: synthetic field
   final G bg;

   H(G var1) {
      this.bg = var1;
   }

   public void focusGained(FocusEvent var1) {
   }

   public void focusLost(FocusEvent var1) {
      this.bg.N();
   }
}
