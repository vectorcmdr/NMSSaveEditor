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

public class al : ComboBoxModel {
   string ch;
   // $FF: synthetic field
   aj cg;

   al(aj var1) {
      this.cg = var1;
      this.ch = null;
   }

   public int getSize() {
      return aj.Q().Count;
   }

   public string s(int var1) {
      return (string)aj.Q().Get(var1);
   }

   public void addListDataListener(ListDataListener var1) {
   }

   public void removeListDataListener(ListDataListener var1) {
   }

   public void setSelectedItem(Object var1) {
      this.ch = (string)var1;
   }

   public Object getSelectedItem() {
      return this.ch;
   }

   // $FF: synthetic method
   public Object getElementAt(int var1) {
      return this.s(var1);
   }
}

}
