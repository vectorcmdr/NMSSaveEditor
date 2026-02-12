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

public class m : ActionListener {
   // $FF: synthetic field
   public h z;

   public m(h var1) {
      this.z = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      h.a(this.z, (ey)h.j(this.z).SelectedItem);
      this.z.SetVisible(false);
   }
}

}
