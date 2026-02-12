using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Windows.Forms;
using System.Globalization;

namespace NMSSaveEditor
{

public class cz : ComponentAdapter {
   // $FF: synthetic field
   cy gg;

   cz(cy var1) {
      this.gg = var1;
   }

   public void componentMoved(ComponentEvent var1) {
      Point var2 = this.gg.Bounds.Location;
      aH.b("JSONEditor.X", var2.x);
      aH.b("JSONEditor.Y", var2.y);
   }

   public void componentResized(ComponentEvent var1) {
      Size var2 = this.gg.Bounds.Size;
      aH.b("JSONEditor.Width", var2.width);
      aH.b("JSONEditor.Height", var2.height);
   }
}

}
