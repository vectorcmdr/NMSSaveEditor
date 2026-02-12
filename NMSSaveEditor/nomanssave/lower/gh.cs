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

public class gh {
   string name;
   eY nn;
   // $FF: synthetic field
   ge nk;

   private gh(ge var1, string var2, eY var3) {
      this.nk = var1;
      this.name = var2;
      this.nn = var3;
   }

   public gy cJ() {
      return gy.as(this.nn.getValueAsString("ResourceElement.Filename"));
   }

   public string cK() {
      return this.nn.d("ResourceElement.Seed").X(1);
   }

   public void aa(string var1) {
      this.nn.d("ResourceElement.Seed").a(1, var1);
   }

   public string toString() {
      return this.name;
   }

   // $FF: synthetic method
   gh(ge var1, string var2, eY var3, gh var4) {
      // Constructor chain: base(var1, var2, var3)
   }
}

}
