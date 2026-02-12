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

public class G : TextBox {
   public G() {
      this.addFocusListener(new H(this));
   }

   public void N() {
      this.SetText(this.g(this.GetText()));
   }

   public void f(string var1) {
      this.SetText(this.g(var1));
   }

   public virtual string g(string var1) { return var1; }
}

}
