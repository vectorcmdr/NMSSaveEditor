using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

class dJ : ComboBox {
   dE hE;

   dJ(dE var1) {
      this.hE = var1;
      this.setModel(new dK(this));
      this.setRenderer(new dL(this));
   }
}

}
