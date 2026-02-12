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

public class dk : ComboBoxModel {
   public gv hk;
   // $FF: synthetic field
   public dj hl;

   public dk(dj var1) {
      this.hl = var1;
      this.hk = null;
   }

   public int getSize() {
      return dj.a(this.hl) == null ? 0 : dj.a(this.hl).length;
   }

   public gv D(int var1) {
      return dj.a(this.hl)[var1];
   }

   public void addListDataListener(ListDataListener var1) {
   }

   public void removeListDataListener(ListDataListener var1) {
   }

   public void setSelectedItem(Object var1) {
      this.hk = (gv)var1;
      if (this.hk == null) {
         dj.b(this.hl).SetText("");
         dj.c(this.hl).SetSelectedIndex(-1);
         dj.d(this.hl).SetSelectedIndex(-1);
         dj.e(this.hl).SetText("");
         dj.f(this.hl).SetText("");
         dj.g(this.hl).SetText("");
         dj.h(this.hl).SetText("");
         dj.i(this.hl).a(new List<object>());
      } else {
         dj.b(this.hl).SetText(this.hk.Name);
         dj.c(this.hl).m(this.hk.cT());
         dj.d(this.hl).m(this.hk.cW());
         dj.e(this.hl).SetText(this.hk.cK());
         dj.f(this.hl).SetText(Double.toString(this.hk.dF()));
         dj.g(this.hl).SetText(Double.toString(this.hk.dG()));
         dj.h(this.hl).SetText(Double.toString(this.hk.dH()));
         dj.i(this.hl).a(new List<object> { this.hk.dE() });
      }
   }

   public Object getSelectedItem() {
      return this.hk;
   }

   // $FF: synthetic method
   public Object getElementAt(int var1) {
      return this.D(var1);
   }
}

}
