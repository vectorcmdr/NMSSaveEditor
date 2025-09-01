package nomanssave;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultTreeCellRenderer;

class cA extends DefaultTreeCellRenderer {
   // $FF: synthetic field
   final cy gg;

   cA(cy var1) {
      this.gg = var1;
   }

   public Component getTreeCellRendererComponent(JTree var1, Object var2, boolean var3, boolean var4, boolean var5, int var6, boolean var7) {
      JLabel var8 = (JLabel)super.getTreeCellRendererComponent(var1, var2, var3, var4, var5, var6, var7);
      if (((cJ)var2).gi == null) {
         var8.setIcon(Application.a("UI-FILEICON.PNG", 20, 20));
      } else if (var5) {
         var8.setIcon(UIManager.getIcon("Tree.leafIcon"));
      } else if (var4) {
         var8.setIcon(UIManager.getIcon("Tree.openIcon"));
      } else {
         var8.setIcon(UIManager.getIcon("Tree.closedIcon"));
      }

      return var8;
   }
}
