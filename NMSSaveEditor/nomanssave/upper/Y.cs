using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

class Y : object {
   private gj bU;
   X bV;

   Y(X var1) {
      this.bV = var1;
      this.bU = null;
   }

   public int getSize() {
      return X.a(this.bV) == null ? 0 : X.a(this.bV).Length;
   }

   public gj q(int var1) {
      return X.a(this.bV)[var1];
   }

   public void addListDataListener(EventHandler var1) {
   }

   public void removeListDataListener(EventHandler var1) {
   }

   public void setSelectedItem(object var1) {
      this.bU = (gj)var1;
      if (this.bU == null) {
         X.b(this.bV).SelectedIndex = (-1);
         X.c(this.bV).Text = ("");
         X.c(this.bV).Enabled = (false);
         X.d(this.bV).Text = ("");
         X.d(this.bV).Enabled = (false);
         X.e(this.bV).Text = ("");
         X.e(this.bV).Enabled = (false);
         X.f(this.bV).Text = ("");
         X.f(this.bV).Enabled = (false);
         X.g(this.bV).Text = ("");
         X.g(this.bV).Enabled = (false);
         X.h(this.bV).Checked = (false);
         X.h(this.bV).Enabled = (false);
         X.i(this.bV).SelectedIndex = (-1);
         X.i(this.bV).Enabled = (false);
         X.j(this.bV).SelectedIndex = (-1);
         X.j(this.bV).Enabled = (false);
      } else {
         X.b(this.bV).SelectedIndex = (this.bU.cL().ordinal());
         X.c(this.bV).Text = (this.bU.Name);
         X.c(this.bV).Enabled = (true);
         X.d(this.bV).Text = (this.bU.cK());
         X.d(this.bV).Enabled = (true);
         X.e(this.bV).Text = (this.bU.cN());
         X.e(this.bV).Enabled = (true);
         X.f(this.bV).Text = (this.bU.cO());
         X.f(this.bV).Enabled = (true);
         X.g(this.bV).Text = (this.bU.cP());
         X.g(this.bV).Enabled = (true);
         X.h(this.bV).Checked = (this.bU.cQ());
         X.h(this.bV).Enabled = (true);
         X.i(this.bV).m(this.bU.cR());
         X.i(this.bV).Enabled = (true);
         X.j(this.bV).m(this.bU.cS());
         X.j(this.bV).Enabled = (true);
      }

   }

   public object getSelectedItem() {
      return this.bU;
   }
   public object getElementAt(int var1) {
      return this.q(var1);
   }
}

}
