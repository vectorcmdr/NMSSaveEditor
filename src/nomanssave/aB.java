package nomanssave;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

class aB extends DefaultTableCellRenderer {
  private aB(ap paramap) {}
  
  public Component getTableCellRendererComponent(JTable paramJTable, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, int paramInt1, int paramInt2) {
    JLabel jLabel = (JLabel)super.getTableCellRendererComponent(paramJTable, "", paramBoolean1, paramBoolean2, paramInt1, paramInt2);
    jLabel.setIcon((ImageIcon)paramObject);
    return jLabel;
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\aB.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */