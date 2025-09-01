package nomanssave;

import javax.swing.JComponent;

class bN extends ba {
   // $FF: synthetic field
   final bE ey;

   bN(bE var1) {
      super(aH.cJ, 0);
      this.ey = var1;
   }

   void a(String var1, gs var2) {
      this.a(var1, (gs)var2, true, (String)null);
   }

   void a(String var1, gs var2, boolean var3, String var4) {
      bJ var5 = new bJ(this.ey, var2, var3);
      if (var4 != null) {
         var5.setToolTipText(var4);
      }

      this.a(var1, (JComponent)var5);
   }

   void a(String var1, bK var2) {
      this.a(var1, (bK)var2, true, (String)null);
   }

   void a(String var1, bK var2, boolean var3, String var4) {
      bL var5 = new bL(this.ey, var2, var3);
      if (var4 != null) {
         var5.setToolTipText(var4);
      }

      this.a(var1, (JComponent)var5);
   }
}
