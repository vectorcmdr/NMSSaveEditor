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

public class bQ : DefaultListCellRenderer {
   // $FF: synthetic field
   public bO eX;

   public bQ(bO var1) {
      this.eX = var1;
   }

   public Component getListCellRendererComponent(ListBox var1, Object var2, int var3, bool var4, bool var5) {
      if (var2 is gt) {
         var2 = ((gt)var2).Name;
      }
       return base.getListCellRendererComponent(var1, var2, var3, var4, var5);
   }
}

}
