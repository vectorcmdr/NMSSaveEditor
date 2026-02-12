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

public class L : ComboBoxModel {
   public gf bu;
   // $FF: synthetic field
   public I bt;

   public L(I var1) {
      this.bt = var1;
      this.bu = null;
   }

   public int getSize() {
      return I.a(this.bt) == null ? 0 : I.a(this.bt).cE().Count;
   }

   public gf p(int var1) {
      return I.a(this.bt) == null ? null : (gf)I.a(this.bt).cE().Get(var1);
   }

   public void addListDataListener(ListDataListener var1) {
   }

   public void removeListDataListener(ListDataListener var1) {
   }

   public void setSelectedItem(Object var1) {
      this.bu = (gf)var1;
      if (this.bu == null) {
         I.e(this.bt).SetText("");
         I.f(this.bt).SetText("");
         I.f(this.bt).SetEnabled(false);
         I.g(this.bt).SetEnabled(false);
         I.h(this.bt).SetEnabled(false);
         I.i(this.bt).SetEnabled(false);
      } else {
         I.e(this.bt).SetText(Convert.ToString(this.bu.cG()));
         I.f(this.bt).SetText(this.bu.Name);
         I.f(this.bt).SetEnabled(true);
         I.g(this.bt).SetEnabled(true);
         I.h(this.bt).SetEnabled(true);
         I.i(this.bt).SetEnabled(true);
      }
    }

   public Object getSelectedItem() {
      return this.bu;
   }

   // $FF: synthetic method
   public Object getElementAt(int var1) {
      return this.p(var1);
   }

}


}