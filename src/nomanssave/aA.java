package nomanssave;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

class aA extends DefaultTableCellRenderer {
  int f;
  
  public aA(JTable paramJTable, int paramInt) {
    this.f = paramInt;
  }
  
  public Component getTableCellRendererComponent(JTable paramJTable, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, int paramInt1, int paramInt2) {
    JLabel jLabel = (JLabel)super.getTableCellRendererComponent(paramJTable, paramObject, paramBoolean1, paramBoolean2, paramInt1, paramInt2);
    jLabel.setHorizontalAlignment(this.f);
    return jLabel;
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\aA.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */