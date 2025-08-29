package nomanssave;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

class C extends ComponentAdapter {
  C(Application paramApplication) {}
  
  public void componentMoved(ComponentEvent paramComponentEvent) {
    Point point = Application.h(this.aZ).getBounds().getLocation();
    aH.b("MainFrame.X", point.x);
    aH.b("MainFrame.Y", point.y);
  }
  
  public void componentResized(ComponentEvent paramComponentEvent) {
    Dimension dimension = Application.h(this.aZ).getBounds().getSize();
    aH.b("MainFrame.Width", dimension.width);
    aH.b("MainFrame.Height", dimension.height);
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\C.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */