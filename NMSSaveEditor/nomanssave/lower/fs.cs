using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
public interface fs {
   string K();

   fn L();

   eY M();

   string b(eY var1);

   long lastModified();

   default string getName() {
      return null;
   }

   default string getDescription() {
      return null;
   }
}

}
