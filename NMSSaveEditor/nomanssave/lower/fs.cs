using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
public interface fs {
   public string Name => getName();
   public DateTime LastWriteTimeUtc => DateTimeOffset.FromUnixTimeMilliseconds(lastModified()).UtcDateTime;
   public string K();

   public fn L();

   public eY M();

   public string b(eY var1);

   public long lastModified();
   public string getName() {
      return null;
   }
   public string getDescription() {
      return null;
   }
}

}
