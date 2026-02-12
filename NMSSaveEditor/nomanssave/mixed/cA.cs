using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{



public class cA : DefaultTreeCellRenderer {
   public cy gg;

   public cA(cy var1) {
      this.gg = var1;
   }

   public Component getTreeCellRendererComponent(JTree var1, object var2, bool var3, bool var4, bool var5, int var6, bool var7) {
      Label var8 = (Label)base.getTreeCellRendererComponent(var1, var2, var3, var4, var5, var6, var7);
      if (((cJ)var2).gi == null) {
         var8.setIcon(Application.a("UI-FILEICON.PNG", 20, 20));
      } else if (var5) {
         var8.setIcon(SystemInformation.getIcon("Tree.leafIcon"));
      } else if (var4) {
         var8.setIcon(SystemInformation.getIcon("Tree.openIcon"));
      } else {
         var8.setIcon(SystemInformation.getIcon("Tree.closedIcon"));
      }

      return var8;
   }
}



}
