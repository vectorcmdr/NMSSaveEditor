using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

public class fk : eY {
   eC li;

   public fk(eC var1) {
      this.li = var1;
   }

   public Dictionary<object, object> bp() {
      return this.li.bp();
   }
}

}
