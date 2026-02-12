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

public class j : ComboBoxModel {
   public eB A;
   // $FF: synthetic field
   public h z;

   public j(h var1) {
      this.z = var1;
      this.A = null;
   }

   public int getSize() {
      return h.e(this.z).Count;
   }

   public eB b(int var1) {
      return (eB)h.e(this.z).Get(var1);
   }

   public void addListDataListener(ListDataListener var1) {
   }

   public void removeListDataListener(ListDataListener var1) {
   }

   public void setSelectedItem(Object var1) {
      this.A = (eB)var1;
      h.f(this.z);
   }

   public Object getSelectedItem() {
      return this.A;
   }

   // $FF: synthetic method
   public Object getElementAt(int var1) {
      return this.b(var1);
   }
}

}
