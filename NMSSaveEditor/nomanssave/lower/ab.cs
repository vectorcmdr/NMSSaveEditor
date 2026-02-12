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

public class ab : ComboBoxModel {
   // $FF: synthetic field
   public X bV;

   public ab(X var1) {
      this.bV = var1;
   }

   public int getSize() {
      return gl.Values.Length;
   }

   public gl r(int var1) {
      return gl.Values[var1];
   }

   public void addListDataListener(ListDataListener var1) {
   }

   public void removeListDataListener(ListDataListener var1) {
   }

   public void setSelectedItem(Object var1) {
   }

   public Object getSelectedItem() {
      return gl.oF;
   }

   // $FF: synthetic method
   public Object getElementAt(int var1) {
      return this.r(var1);
   }

}


}