package nomanssave;

import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

class d extends JCheckBox implements TableCellRenderer {
   private d() {
   }

   public Component getTableCellRendererComponent(JTable var1, Object var2, boolean var3, boolean var4, int var5, int var6) {
      this.setBackground(var1.getBackground());
      this.setHorizontalAlignment(0);
      this.setSelected(Boolean.TRUE == var2);
      return this;
   }

   // $FF: synthetic method
   d(d var1) {
      this();
   }
}
