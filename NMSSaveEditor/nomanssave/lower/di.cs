using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

public class di : ActionListener {
   public dd gW;

   public di(dd var1) {
      this.gW = var1;
   }

   public void actionPerformed(EventArgs var1) {
      this.gW.Hide();
   }
}

}
