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
      this.setText(this.g(this.getText()));
   }

   public void f(string var1) {
      this.setText(this.g(var1));
   }

   protected abstract string g(string var1);
}

}
