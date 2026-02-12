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

public class aE : ComboBoxModel {
   public aI cA;
   // $FF: synthetic field
   public aD cB;

   public aE(aD var1) {
      this.cB = var1;
      this.cA = null;
   }

   public int getSize() {
      return aI.Values.Length;
   }

   public aI t(int var1) {
      return aI.Values[var1];
   }

   public void addListDataListener(ListDataListener var1) {
   }

   public void removeListDataListener(ListDataListener var1) {
   }

   public void setSelectedItem(Object var1) {
      this.cA = (aI)var1;
   }

   public Object getSelectedItem() {
      return this.cA;
   }

   // $FF: synthetic method
   public Object getElementAt(int var1) {
      return this.t(var1);
   }
}

}
