using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{



public class dk : object {
   public gv hk;
   public dj hl;

   public dk(dj var1) {
      this.hl = var1;
      this.hk = null;
   }

   public int getSize() {
      // PORT_TODO: return dj.a(this.hl) == null ? 0 : dj.a(this.hl).Length;
      return 0;
   }

   public gv D(int var1) {
      // PORT_TODO: return dj.a(this.hl)[var1];
      return default;
   }

   public void addListDataListener(EventHandler var1) {
   }

   public void removeListDataListener(EventHandler var1) {
   }

   public void setSelectedItem(object var1) {
      this.hk = (gv)var1;
      if (this.hk == null) {
         // PORT_TODO: dj.b(this.hl).Text = ("");
         // PORT_TODO: dj.c(this.hl).SelectedIndex = (-1);
         // PORT_TODO: dj.d(this.hl).SelectedIndex = (-1);
         // PORT_TODO: dj.e(this.hl).Text = ("");
         // PORT_TODO: dj.f(this.hl).Text = ("");
         // PORT_TODO: dj.g(this.hl).Text = ("");
         // PORT_TODO: dj.h(this.hl).Text = ("");
         // PORT_TODO: dj.i(this.hl).a(new List<object>());
      } else {
         // PORT_TODO: dj.b(this.hl).Text = (this.hk.Name);
         // PORT_TODO: dj.c(this.hl).m(this.hk.cT());
         // PORT_TODO: dj.d(this.hl).m(this.hk.cW());
         // PORT_TODO: dj.e(this.hl).Text = (this.hk.cK());
         // PORT_TODO: dj.f(this.hl).Text = ((this.hk.dF().ToString()));
         // PORT_TODO: dj.g(this.hl).Text = ((this.hk.dG().ToString()));
         // PORT_TODO: dj.h(this.hl).Text = ((this.hk.dH().ToString()));
         // PORT_TODO: dj.i(this.hl).a(new List<object> { this.hk.dE() });
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
