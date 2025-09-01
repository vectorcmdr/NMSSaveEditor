package nomanssave;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

class cD implements PropertyChangeListener {
   // $FF: synthetic field
   final cy gg;

   cD(cy var1) {
      this.gg = var1;
   }

   public void propertyChange(PropertyChangeEvent var1) {
      aH.b("JSONEditor.Divider", (Integer)var1.getNewValue());
   }
}
