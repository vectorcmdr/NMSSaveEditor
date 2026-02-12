using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

public class dJ : ComboBox {
   public dE hE;

   public dJ(dE var1) {
      this.hE = var1;
      this.DataSource = (new dK(this));
      this.setRenderer(new dL(this));
   }
}

}
