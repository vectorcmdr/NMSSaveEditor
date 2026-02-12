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

public interface gQ {
   string getType();

   Object dz();

   string ei() {
      Object var1 = this.dz();
      return var1 is fg ? ((fg)var1).bP() : var1.ToString();
   }

   void m(Object var1);

   int dA();

   void aA(int var1);

   int dB();
}

}
