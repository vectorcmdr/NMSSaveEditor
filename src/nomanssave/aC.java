package nomanssave;

import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

class aC extends JCheckBox implements TableCellRenderer {
   final JLabel cv = new JLabel();

   public Component getTableCellRendererComponent(JTable var1, Object var2, boolean var3, boolean var4, int var5, int var6) {
      var5 = var1.convertRowIndexToModel(var5);
      if (!var1.getModel().isCellEditable(var5, var6)) {
         return this.cv;
      } else {
         this.setBackground(var1.getBackground());
         this.setHorizontalAlignment(0);
         this.setSelected(Boolean.TRUE == var2);
         return this;
      }
   }
}
