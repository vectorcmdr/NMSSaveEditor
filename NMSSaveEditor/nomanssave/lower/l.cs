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

public class l : ComboBoxModel {
   public ey C;
   // $FF: synthetic field
   public h z;

   public l(h var1) {
      this.z = var1;
      this.C = null;
   }

   public int getSize() {
      return h.i(this.z).Count;
   }

   public ey d(int var1) {
      return (ey)h.i(this.z).Get(var1);
   }

   public void addListDataListener(ListDataListener var1) {
   }

   public void removeListDataListener(ListDataListener var1) {
   }

   public void setSelectedItem(Object var1) {
      this.C = (ey)var1;
   }

   public Object getSelectedItem() {
      return this.C;
   }

   // $FF: synthetic method
   public Object getElementAt(int var1) {
      return this.d(var1);
   }
}

}
