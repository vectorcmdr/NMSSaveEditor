package nomanssave;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

class cz extends ComponentAdapter {
   // $FF: synthetic field
   final cy gg;

   cz(cy var1) {
      this.gg = var1;
   }

   public void componentMoved(ComponentEvent var1) {
      Point var2 = this.gg.getBounds().getLocation();
      aH.b("JSONEditor.X", var2.x);
      aH.b("JSONEditor.Y", var2.y);
   }

   public void componentResized(ComponentEvent var1) {
      Dimension var2 = this.gg.getBounds().getSize();
      aH.b("JSONEditor.Width", var2.width);
      aH.b("JSONEditor.Height", var2.height);
   }
}
