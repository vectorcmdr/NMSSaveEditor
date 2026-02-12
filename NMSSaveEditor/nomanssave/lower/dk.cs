using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

public class dk : object {
   public gv hk;
   dj hl;

   public dk(dj var1) {
      this.hl = var1;
      this.hk = null;
   }

   public int getSize() {
      return dj.a(this.hl) == null ? 0 : dj.a(this.hl).Length;
   }

   public gv D(int var1) {
      return dj.a(this.hl)[var1];
   }

   public void addListDataListener(EventHandler var1) {
   }

   public void removeListDataListener(EventHandler var1) {
   }

   public void setSelectedItem(object var1) {
      this.hk = (gv)var1;
      if (this.hk == null) {
         dj.b(this.hl).Text = ("");
         dj.c(this.hl).SelectedIndex = (-1);
         dj.d(this.hl).SelectedIndex = (-1);
         dj.e(this.hl).Text = ("");
         dj.f(this.hl).Text = ("");
         dj.g(this.hl).Text = ("");
         dj.h(this.hl).Text = ("");
         dj.i(this.hl).a(new List<object>());
      } else {
         dj.b(this.hl).Text = (this.hk.Name);
         dj.c(this.hl).m(this.hk.cT());
         dj.d(this.hl).m(this.hk.cW());
         dj.e(this.hl).Text = (this.hk.cK());
         dj.f(this.hl).Text = (Double.toString(this.hk.dF()));
         dj.g(this.hl).Text = (Double.toString(this.hk.dG()));
         dj.h(this.hl).Text = (Double.toString(this.hk.dH()));
         dj.i(this.hl).a(new List<object> { this.hk.dE() });
      }
   }

   public object getSelectedItem() {
      return this.hk;
   }
   public object getElementAt(int var1) {
      return this.D(var1);
   }
}

}
