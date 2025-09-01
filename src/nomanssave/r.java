package nomanssave;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class r implements ActionListener {
   // $FF: synthetic field
   final p I;

   r(p var1) {
      this.I = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      int[] var2 = p.b(this.I).getSelectedRows();
      p.a(this.I, new ArrayList());

      for(int var4 = 0; var4 < var2.length; ++var4) {
         int var3 = p.b(this.I).convertRowIndexToModel(var2[var4]);
         p.c(this.I).add((String)p.b(this.I).getModel().getValueAt(var3, 3));
      }

      this.I.setVisible(false);
   }
}
