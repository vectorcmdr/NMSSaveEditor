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

public interface fs {
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

   public DateTime LastWriteTimeUtc { get => DateTime.MinValue; }
   public string Name { get => getName(); }
   public long Length { get => 0; }
}

}
