package nomanssave;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class aw implements ActionListener {
   // $FF: synthetic field
   final ap cu;

   aw(ap var1) {
      this.cu = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      int[] var2 = ap.h(this.cu).getSelectedRows();
      boolean var3 = false;

      for(int var4 = var2.length - 1; var4 >= 0; --var4) {
         int var5 = ap.h(this.cu).convertRowIndexToModel(var2[var4]);
         String var6 = (String)ap.d(this.cu).get(var5);
         ap.d(this.cu).remove(var5);

         while((var5 = ap.e(this.cu).indexOf(var6)) >= 0) {
            ap.e(this.cu).ac(var5);
         }

         while((var5 = ap.f(this.cu).indexOf(var6)) >= 0) {
            ap.f(this.cu).ac(var5);
         }

         var3 = true;
      }

      if (var3) {
         ap.h(this.cu).clearSelection();
         ap.g(this.cu).sort();
         ap.h(this.cu).updateUI();
      }

   }
}
