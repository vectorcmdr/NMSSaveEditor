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

public class em : Panel {
   public ba it;

   public em() {
      GridBagLayout var1 = new GridBagLayout();
      var1.columnWidths = new int[]{aH.cI, 0, 0};
      var1.rowHeights = new int[1];
      var1.columnWeights = new double[]{0.0D, 1.0D, double.MinValue};
      var1.rowWeights = new double[]{1.0D};
      this.SetLayout(var1);
      this.it = new ba();
      GridBagConstraints var2 = new GridBagConstraints();
      var2.insets = new Insets(0, 0, 0, 0);
      var2.fill = 1;
      var2.gridx = 0;
      var2.gridy = 0;
      this.Add(this.it, var2);
   }

   public void b(JComponent var1) {
      GridBagConstraints var2 = new GridBagConstraints();
      var2.fill = 1;
      var2.gridx = 1;
      var2.gridy = 0;
      this.Add(var1, var2);
   }

   public void k(string var1) {
      this.it.k(var1);
   }

   public void a(string var1, G var2) {
      this.it.a(var1, var2);
   }

   public void a(string var1, JComponent var2) {
      this.it.a(var1, var2);
   }

   public void a(string var1, bool var2, JComponent var3) {
      this.it.a(var1, var2, var3);
   }

   public void a(JComponent var1) {
      this.it.a(var1);
   }

   public void Y() {
      this.it.Y();
   }
}

}
