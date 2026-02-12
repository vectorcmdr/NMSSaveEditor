using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{



public class cz : ComponentAdapter {
   public cy gg;

   public cz(cy var1) {
      this.gg = var1;
   }

   public void componentMoved(ComponentEvent var1) {
      // PORT_TODO: Point var2 = this.gg.getBounds().getLocation();
      // PORT_TODO: aH.b("JSONEditor.X", var2.x);
      // PORT_TODO: aH.b("JSONEditor.Y", var2.y);
   }

   public void componentResized(ComponentEvent var1) {
      // PORT_TODO: Size var2 = this.gg.getBounds().getSize();
      // PORT_TODO: aH.b("JSONEditor.Width", var2.width);
      // PORT_TODO: aH.b("JSONEditor.Height", var2.height);
   }
}



}
