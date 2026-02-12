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

public class de : ListModel {
   // $FF: synthetic field
   public dd gW;

   public de(dd var1) {
      this.gW = var1;
   }

   public int getSize() {
      return dd.a(this.gW).Count;
   }

   public gt w(int var1) {
      return (gt)dd.a(this.gW).Get(var1);
   }

   public void addListDataListener(ListDataListener var1) {
   }

   public void removeListDataListener(ListDataListener var1) {
   }

   // $FF: synthetic method
   public Object getElementAt(int var1) {
      return this.w(var1);
   }
}

}
