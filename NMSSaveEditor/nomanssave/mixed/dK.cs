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

public class dK : ComboBoxModel {
   eM hF;
   // $FF: synthetic field
   dJ hG;

   dK(dJ var1) {
      this.hG = var1;
   }

   public int getSize() {
      return 1 + eM.getCount();
   }

   public eM F(int var1) {
      return var1 == 0 ? null : eM.S(var1 - 1);
   }

   public void addListDataListener(ListDataListener var1) {
   }

   public void removeListDataListener(ListDataListener var1) {
   }

   public void setSelectedItem(Object var1) {
      this.hF = (eM)var1;
   }

   public Object getSelectedItem() {
      return this.hF;
   }

   // $FF: synthetic method
   public Object getElementAt(int var1) {
      return this.F(var1);
   }
}

}
