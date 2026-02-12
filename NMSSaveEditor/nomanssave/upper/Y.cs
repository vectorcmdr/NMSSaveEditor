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

public class Y : ComboBoxModel {
   public gj bU;
   // $FF: synthetic field
   public X bV;

   public Y(X var1) {
      this.bV = var1;
      this.bU = null;
   }

   public int getSize() {
      return X.a(this.bV) == null ? 0 : X.a(this.bV).Length;
   }

   public gj q(int var1) {
      return X.a(this.bV)[var1];
   }

   public void addListDataListener(ListDataListener var1) {
   }

   public void removeListDataListener(ListDataListener var1) {
   }

   public void setSelectedItem(Object var1) {
      this.bU = (gj)var1;
      if (this.bU == null) {
         X.b(this.bV).SetSelectedIndex(-1);
         X.c(this.bV).SetText("");
         X.c(this.bV).SetEnabled(false);
         X.d(this.bV).SetText("");
         X.d(this.bV).SetEnabled(false);
         X.e(this.bV).SetText("");
         X.e(this.bV).SetEnabled(false);
         X.f(this.bV).SetText("");
         X.f(this.bV).SetEnabled(false);
         X.g(this.bV).SetText("");
         X.g(this.bV).SetEnabled(false);
         X.h(this.bV).setSelected(false);
         X.h(this.bV).SetEnabled(false);
         X.i(this.bV).SetSelectedIndex(-1);
         X.i(this.bV).SetEnabled(false);
         X.j(this.bV).SetSelectedIndex(-1);
         X.j(this.bV).SetEnabled(false);
      } else {
         X.b(this.bV).SetSelectedIndex(this.bU.cL().ordinal());
         X.c(this.bV).SetText(this.bU.Name);
         X.c(this.bV).SetEnabled(true);
         X.d(this.bV).SetText(this.bU.cK());
         X.d(this.bV).SetEnabled(true);
         X.e(this.bV).SetText(this.bU.cN());
         X.e(this.bV).SetEnabled(true);
         X.f(this.bV).SetText(this.bU.cO());
         X.f(this.bV).SetEnabled(true);
         X.g(this.bV).SetText(this.bU.cP());
         X.g(this.bV).SetEnabled(true);
         X.h(this.bV).setSelected(this.bU.cQ());
         X.h(this.bV).SetEnabled(true);
         X.i(this.bV).m(this.bU.cR());
         X.i(this.bV).SetEnabled(true);
         X.j(this.bV).m(this.bU.cS());
         X.j(this.bV).SetEnabled(true);
      }

   }

   public Object getSelectedItem() {
      return this.bU;
   }

   // $FF: synthetic method
   public Object getElementAt(int var1) {
      return this.q(var1);
   }
}

}
