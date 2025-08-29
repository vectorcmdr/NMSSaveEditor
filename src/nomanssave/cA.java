package nomanssave;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultTreeCellRenderer;

class cA extends DefaultTreeCellRenderer {
  cA(cy paramcy) {}
  
  public Component getTreeCellRendererComponent(JTree paramJTree, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, int paramInt, boolean paramBoolean4) {
    JLabel jLabel = (JLabel)super.getTreeCellRendererComponent(paramJTree, paramObject, paramBoolean1, paramBoolean2, paramBoolean3, paramInt, paramBoolean4);
    if (((cJ)paramObject).gi == null) {
      jLabel.setIcon(Application.a("UI-FILEICON.PNG", 20, 20));
    } else if (paramBoolean3) {
      jLabel.setIcon(UIManager.getIcon("Tree.leafIcon"));
    } else if (paramBoolean2) {
      jLabel.setIcon(UIManager.getIcon("Tree.openIcon"));
    } else {
      jLabel.setIcon(UIManager.getIcon("Tree.closedIcon"));
    } 
    return jLabel;
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\cA.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */