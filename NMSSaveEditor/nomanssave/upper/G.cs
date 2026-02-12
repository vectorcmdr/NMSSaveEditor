using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

public abstract class G : TextBox {
   public G() {
      this.addFocusListener(new H(this));
   }

   public void N() {
      this.Text = (this.g(this.Text));
   }

   public void f(string var1) {
      this.Text = (this.g(var1));
   }

   public abstract string g(string var1);
}

}
