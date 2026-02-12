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

public class cD : PropertyChangeListener {
   // $FF: synthetic field
   cy gg;

   cD(cy var1) {
      this.gg = var1;
   }

   public void propertyChange(PropertyChangeEvent var1) {
      aH.b("JSONEditor.Divider", (Integer)var1.getNewValue());
   }
}

}
