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

public class dL : DefaultListCellRenderer {
   // $FF: synthetic field
   public dJ hG;

   public dL(dJ var1) {
      this.hG = var1;
   }

   public Component getListCellRendererComponent(ListBox var1, Object var2, int var3, bool var4, bool var5) {
      Label var6 = (Label)base.getListCellRendererComponent(var1, var2, var3, var4, var5);
      if (var2 == null) {
         var6.SetText("");
      } else {
         eM var7 = (eM)var2;
         if (var7.aW()) {
            if (var4) {
               var6.SetBackground(SystemColors.Control);
            } else {
               var6.SetForeground(SystemColors.Control);
            }
         } else if (var4) {
            var6.SetBackground(SystemColors.Control);
         } else {
            var6.SetForeground(SystemColors.Control);
         }
      }
       return var6;
   }
}

}
