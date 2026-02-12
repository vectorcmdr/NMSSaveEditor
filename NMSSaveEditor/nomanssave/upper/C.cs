using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{



public class C : ComponentAdapter {
   public Application aZ;

   public C(Application var1) {
      this.aZ = var1;
   }

   public void componentMoved(ComponentEvent var1) {
      // PORT_TODO: Point var2 = Application.h(this.aZ).getBounds().getLocation();
      // PORT_TODO: aH.b("MainFrame.X", var2.x);
      // PORT_TODO: aH.b("MainFrame.Y", var2.y);
   }

   public void componentResized(ComponentEvent var1) {
      // PORT_TODO: Size var2 = Application.h(this.aZ).getBounds().getSize();
      // PORT_TODO: aH.b("MainFrame.Width", var2.width);
      // PORT_TODO: aH.b("MainFrame.Height", var2.height);
   }
}



}
