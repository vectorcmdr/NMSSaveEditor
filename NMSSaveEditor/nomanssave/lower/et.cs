using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
public class et {
   public string id;

   public et(string var1) {
      this.id = var1;
   }

   public bool equals(object var1) {
      return this.id.Equals(((er)var1).id);
   }
}

}
