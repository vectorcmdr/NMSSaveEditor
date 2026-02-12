using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

public class fk : eY {
   eC li;

   fk(eC var1) {
      this.li = var1;
   }

   public Map bp() {
      return this.li.bp();
   }
}

}
