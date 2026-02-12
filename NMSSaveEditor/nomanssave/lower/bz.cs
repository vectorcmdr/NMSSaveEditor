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

public class bz : ActionListener {
   // $FF: synthetic field
   public bl er;

   public bz(bl var1) {
      this.er = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      if (bl.b(this.er) >= 0) {
         hc.info("Repairing frigate damage");
         bl.c(this.er)[bl.b(this.er)].aw(0);
         bl.c(this.er)[bl.b(this.er)].ax(0);
         bl.y(this.er).SetText("");
         bl.z(this.er).SetVisible(false);
      }
   }
}

}
