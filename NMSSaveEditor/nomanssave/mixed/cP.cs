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

public class cP : DefaultListCellRenderer {
   // $FF: synthetic field
   public cN gt;

   public cP(cN var1) {
      this.gt = var1;
   }

   public Component getListCellRendererComponent(ListBox var1, Object var2, int var3, bool var4, bool var5) {
      Component var6 = base.getListCellRendererComponent(var1, var2, var3, var4, var5);
      if (var2 == null && var6 is Label) {
         Label var7 = (Label)var6;
         var7.SetText(" ");
      }

      if (var6 is Label) {
         bool var12 = false;
         Enum[] var11;
         int var10 = (var11 = cN.e(this.gt)).Length;

         for(int var9 = 0; var9 < var10; ++var9) {
            Enum var8 = var11[var9];
            if (var8 == var2) {
               var12 = true;
               break;
            }
         }

         Label var13 = (Label)var6;
         if (!var12) {
            if (var4) {
               var13.SetBackground(cN.ag());
            } else {
               var13.SetForeground(cN.aB());
            }
         }
      }

      return var6;
   }
}

}
