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

public class eg : ComboBoxModel {
   // $FF: synthetic field
   public ec ik;
   // $FF: synthetic field
   public int il;

   public eg(ec var1, int var2) {
      this.ik = var1;
      this.il = var2;
   }

   public int getSize() {
      return gL.Values.length;
   }

   public gL I(int var1) {
      return gL.Values[var1];
   }

   public void addListDataListener(ListDataListener var1) {
   }

   public void removeListDataListener(ListDataListener var1) {
   }

   public void setSelectedItem(Object var1) {
      gL var2 = (gL)var1;
      if (var2 != null && !var2.Equals(eb.a(ec.h(this.ik))[this.il].ef())) {
         eb.a(ec.h(this.ik))[this.il].a(var2);
      }

   }

   public Object getSelectedItem() {
      return eb.a(ec.h(this.ik))[this.il].ef();
   }

   // $FF: synthetic method
   public Object getElementAt(int var1) {
      return this.I(var1);
   }
}

}
