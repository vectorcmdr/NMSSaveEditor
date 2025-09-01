package nomanssave;

import java.awt.EventQueue;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

class bt implements ListSelectionListener {
   // $FF: synthetic field
   final bl er;
   // $FF: synthetic field
   private final Application bv;

   bt(bl var1, Application var2) {
      this.er = var1;
      this.bv = var2;
   }

   public void valueChanged(ListSelectionEvent var1) {
      EventQueue.invokeLater(new bu(this, this.bv));
   }

   // $FF: synthetic method
   static bl a(bt var0) {
      return var0.er;
   }
}
