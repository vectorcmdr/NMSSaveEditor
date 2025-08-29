package nomanssave;

import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

class d extends JCheckBox implements TableCellRenderer {
  private d() {}
  
  public Component getTableCellRendererComponent(JTable paramJTable, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, int paramInt1, int paramInt2) {
    setBackground(paramJTable.getBackground());
    setHorizontalAlignment(0);
    setSelected((Boolean.TRUE == paramObject));
    return this;
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\d.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */