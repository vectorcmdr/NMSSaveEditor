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

public class J : ComboBoxModel {
   public gh bs;
   // $FF: synthetic field
   public I bt;

   public J(I var1) {
      this.bt = var1;
      this.bs = null;
   }

   public int getSize() {
      return I.a(this.bt) == null ? 0 : I.a(this.bt).cD().Count;
   }

   public gh o(int var1) {
      return I.a(this.bt) == null ? null : (gh)I.a(this.bt).cD().Get(var1);
   }

   public void addListDataListener(ListDataListener var1) {
   }

   public void removeListDataListener(ListDataListener var1) {
   }

   public void setSelectedItem(Object var1) {
      this.bs = (gh)var1;
      if (this.bs == null) {
         I.b(this.bt).SetText("");
         I.c(this.bt).SetText("");
         I.c(this.bt).SetEnabled(false);
      } else {
         gy var2 = this.bs.cJ();
         I.b(this.bt).SetText(var2 == null ? "" : var2.ToString());
         I.c(this.bt).SetText(this.bs.cK());
         I.c(this.bt).SetEnabled(true);
      }

   }

   public Object getSelectedItem() {
      return this.bs;
   }

   // $FF: synthetic method
   public Object getElementAt(int var1) {
      return this.o(var1);
   }
}

}
