package nomanssave;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JButton;

class bc implements PropertyChangeListener {
   // $FF: synthetic field
   final ba dB;
   // $FF: synthetic field
   private final JButton dD;
   // $FF: synthetic field
   private final G dC;

   bc(ba var1, JButton var2, G var3) {
      this.dB = var1;
      this.dD = var2;
      this.dC = var3;
   }

   public void propertyChange(PropertyChangeEvent var1) {
      this.dD.setEnabled(this.dC.isEnabled());
   }
}
