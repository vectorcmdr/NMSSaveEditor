package nomanssave;

import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

class aC extends JCheckBox implements TableCellRenderer {
  final JLabel cv = new JLabel();
  
  public Component getTableCellRendererComponent(JTable paramJTable, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, int paramInt1, int paramInt2) {
    paramInt1 = paramJTable.convertRowIndexToModel(paramInt1);
    if (!paramJTable.getModel().isCellEditable(paramInt1, paramInt2))
      return this.cv; 
    setBackground(paramJTable.getBackground());
    setHorizontalAlignment(0);
    setSelected((Boolean.TRUE == paramObject));
    return this;
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\aC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */