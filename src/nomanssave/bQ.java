package nomanssave;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

class bQ extends DefaultListCellRenderer {
  bQ(bO parambO) {}
  
  public Component getListCellRendererComponent(JList<?> paramJList, Object paramObject, int paramInt, boolean paramBoolean1, boolean paramBoolean2) {
    if (paramObject instanceof gt)
      paramObject = ((gt)paramObject).getSimpleName(); 
    return super.getListCellRendererComponent(paramJList, paramObject, paramInt, paramBoolean1, paramBoolean2);
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\bQ.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */