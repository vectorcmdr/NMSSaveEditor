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

public class dc : ActionListener {
   // $FF: synthetic field
   public cY gR;

   public dc(cY var1) {
      this.gR = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      this.gR.SetVisible(false);
   }
}

}
