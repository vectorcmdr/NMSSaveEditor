package nomanssave;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

class ai implements ActionListener {
   // $FF: synthetic field
   final X bV;
   // $FF: synthetic field
   private final Application bv;

   ai(X var1, Application var2) {
      this.bV = var1;
      this.bv = var2;
   }

   public void actionPerformed(ActionEvent var1) {
      int var2 = X.k(this.bV).getSelectedIndex();
      if (var2 >= 0 && var2 < X.a(this.bV).length) {
         if (JOptionPane.showConfirmDialog(this.bV, "Are you sure you want to delete this companion?", "Delete", 2) == 0) {
            this.bv.a(X.a(this.bV)[var2].cL(), X.a(this.bV)[var2].getIndex());
         }
      }
   }
}
