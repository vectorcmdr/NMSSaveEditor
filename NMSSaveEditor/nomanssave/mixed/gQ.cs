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
   public string getType();

   public Object dz();

   public string ei() {
      Object var1 = this.dz();
      return var1 is fg ? ((fg)var1).bP() : var1.ToString();
   }

   public void m(Object var1);

   public int dA();

   public void aA(int var1);

   public int dB();
}

}
