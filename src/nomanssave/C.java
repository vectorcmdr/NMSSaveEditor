package nomanssave;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

class C extends ComponentAdapter {
   // $FF: synthetic field
   final Application aZ;

   C(Application var1) {
      this.aZ = var1;
   }

   public void componentMoved(ComponentEvent var1) {
      Point var2 = Application.h(this.aZ).getBounds().getLocation();
      aH.b("MainFrame.X", var2.x);
      aH.b("MainFrame.Y", var2.y);
   }

   public void componentResized(ComponentEvent var1) {
      Dimension var2 = Application.h(this.aZ).getBounds().getSize();
      aH.b("MainFrame.Width", var2.width);
      aH.b("MainFrame.Height", var2.height);
   }
}
