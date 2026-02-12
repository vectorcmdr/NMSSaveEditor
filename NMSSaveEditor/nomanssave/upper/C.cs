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

public class C : ComponentAdapter {
   // $FF: synthetic field
   Application aZ;

   C(Application var1) {
      this.aZ = var1;
   }

   public void componentMoved(ComponentEvent var1) {
      Point var2 = Application.h(this.aZ).Bounds.Location;
      aH.b("MainFrame.X", var2.x);
      aH.b("MainFrame.Y", var2.y);
   }

   public void componentResized(ComponentEvent var1) {
      Size var2 = Application.h(this.aZ).Bounds.Size;
      aH.b("MainFrame.Width", var2.width);
      aH.b("MainFrame.Height", var2.height);
   }
}

}
