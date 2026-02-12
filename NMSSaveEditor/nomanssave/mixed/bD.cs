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

public class bD : DefaultListCellRenderer {
   // $FF: synthetic field
   public bl er;

   public bD(bl var1) {
      this.er = var1;
   }

   public Component getListCellRendererComponent(ListBox var1, Object var2, int var3, bool var4, bool var5) {
      Component var6 = base.getListCellRendererComponent(var1, var2, var3, var4, var5);
      if (var2 == null && var6 is Label) {
         Label var7 = (Label)var6;
         var7.SetText(" ");
      }
       if (var2 is er && var6 is Label) {
         er var9 = (er)var2;
         Label var8 = (Label)var6;
         if (var9.aW()) {
            if (var4) {
               var8.SetBackground(SystemColors.Control);
            } else {
               var8.SetForeground(SystemColors.Control);
            }
         } else if (var4) {
            var8.SetBackground(SystemColors.Control);
         } else {
            var8.SetForeground(SystemColors.Control);
         }
      }
       return var6;
   }

   // $FF: synthetic method
   public bD(bl var1, bD var2) {
      // Constructor chain: base(var1)
   }
}

}
