using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class cz : ComponentAdapter {
   public cy gg;

   public cz(cy var1) {
      this.gg = var1;
   }

   public void componentMoved(ComponentEvent var1) {
      Point var2 = this.gg.getBounds().getLocation();
      aH.b("JSONEditor.X", var2.x);
      aH.b("JSONEditor.Y", var2.y);
   }

   public void componentResized(ComponentEvent var1) {
      Size var2 = this.gg.getBounds().getSize();
      aH.b("JSONEditor.Width", var2.width);
      aH.b("JSONEditor.Height", var2.height);
   }
}


#else

public class cz
{
   public cz() { }
   public cz(params object[] args) { }
   public cy gg = default;
   public void componentMoved(ComponentEvent var1) { }
   public void componentResized(ComponentEvent var1) { }
}

#endif

}
