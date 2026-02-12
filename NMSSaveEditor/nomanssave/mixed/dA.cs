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

public class dA : ListModel {
   // $FF: synthetic field
   public dz hu;

   public dA(dz var1) {
      this.hu = var1;
   }

   public int getSize() {
      return dz.a(this.hu).Length;
   }

   public ft m(int var1) {
      return dz.a(this.hu)[var1];
   }

   public void addListDataListener(ListDataListener var1) {
   }

   public void removeListDataListener(ListDataListener var1) {
   }

   // $FF: synthetic method
   public Object getElementAt(int var1) {
      return this.m(var1);
   }
}

}
