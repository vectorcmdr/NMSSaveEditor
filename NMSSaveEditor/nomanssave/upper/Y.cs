using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{



public class Y : object {
   public gj bU;
   public X bV;

   public Y(X var1) {
      this.bV = var1;
      this.bU = null;
   }

   public int getSize() {
      // PORT_TODO: return X.a(this.bV) == null ? 0 : X.a(this.bV).Length;
      return 0;
   }

   public gj q(int var1) {
      // PORT_TODO: return X.a(this.bV)[var1];
      return default;
   }

   public void addListDataListener(EventHandler var1) {
   }

   public void removeListDataListener(EventHandler var1) {
   }

   public void setSelectedItem(object var1) {
      this.bU = (gj)var1;
      if (this.bU == null) {
         // PORT_TODO: X.b(this.bV).SelectedIndex = (-1);
         // PORT_TODO: X.c(this.bV).Text = ("");
         // PORT_TODO: X.c(this.bV).Enabled = (false);
         // PORT_TODO: X.d(this.bV).Text = ("");
         // PORT_TODO: X.d(this.bV).Enabled = (false);
         // PORT_TODO: X.e(this.bV).Text = ("");
         // PORT_TODO: X.e(this.bV).Enabled = (false);
         // PORT_TODO: X.f(this.bV).Text = ("");
         // PORT_TODO: X.f(this.bV).Enabled = (false);
         // PORT_TODO: X.g(this.bV).Text = ("");
         // PORT_TODO: X.g(this.bV).Enabled = (false);
         // PORT_TODO: X.h(this.bV).Checked = (false);
         // PORT_TODO: X.h(this.bV).Enabled = (false);
         // PORT_TODO: X.i(this.bV).SelectedIndex = (-1);
         // PORT_TODO: X.i(this.bV).Enabled = (false);
         // PORT_TODO: X.j(this.bV).SelectedIndex = (-1);
         // PORT_TODO: X.j(this.bV).Enabled = (false);
      } else {
         // PORT_TODO: X.b(this.bV).SelectedIndex = (this.bU.cL().ordinal());
         // PORT_TODO: X.c(this.bV).Text = (this.bU.Name);
         // PORT_TODO: X.c(this.bV).Enabled = (true);
         // PORT_TODO: X.d(this.bV).Text = (this.bU.cK());
         // PORT_TODO: X.d(this.bV).Enabled = (true);
         // PORT_TODO: X.e(this.bV).Text = (this.bU.cN());
         // PORT_TODO: X.e(this.bV).Enabled = (true);
         // PORT_TODO: X.f(this.bV).Text = (this.bU.cO());
         // PORT_TODO: X.f(this.bV).Enabled = (true);
         // PORT_TODO: X.g(this.bV).Text = (this.bU.cP());
         // PORT_TODO: X.g(this.bV).Enabled = (true);
         // PORT_TODO: X.h(this.bV).Checked = (this.bU.cQ());
         // PORT_TODO: X.h(this.bV).Enabled = (true);
         // PORT_TODO: X.i(this.bV).m(this.bU.cR());
         // PORT_TODO: X.i(this.bV).Enabled = (true);
         // PORT_TODO: X.j(this.bV).m(this.bU.cS());
         // PORT_TODO: X.j(this.bV).Enabled = (true);
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
