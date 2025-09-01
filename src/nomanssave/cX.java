package nomanssave;

import java.awt.Rectangle;
import javax.swing.text.BadLocationException;

class cX implements Runnable {
   // $FF: synthetic field
   final cW gL;

   cX(cW var1) {
      this.gL = var1;
   }

   public void run() {
      try {
         int var1 = cW.a(this.gL).getDocument().getLength();
         Rectangle var2 = cW.a(this.gL).modelToView(var1);
         if (var2 != null && var2.y != cW.b(this.gL)) {
            cW.c(this.gL);
            this.gL.repaint();
            cW.a(this.gL, var2.y);
         }
      } catch (BadLocationException var3) {
      }

   }
}
