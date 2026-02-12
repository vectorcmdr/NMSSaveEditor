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

public class bP : ComboBoxModel {
   // $FF: synthetic field
   public bO eX;

   public bP(bO var1) {
      this.eX = var1;
   }

   public int getSize() {
      return bO.d(this.eX).Count;
   }

   public gt w(int var1) {
      return (gt)bO.d(this.eX).Get(var1);
   }

   public void addListDataListener(ListDataListener var1) {
   }

   public void removeListDataListener(ListDataListener var1) {
   }

   public void setSelectedItem(Object var1) {
      bO.a(this.eX, (gt)var1);
      bO.e(this.eX).SetVisible(bO.a(this.eX) == null ? false : en.aS() || bO.a(this.eX).dk());
      bO.c(this.eX);
   }

   public Object getSelectedItem() {
      return bO.a(this.eX);
   }

   // $FF: synthetic method
   public Object getElementAt(int var1) {
      return this.w(var1);
   }
}

}
