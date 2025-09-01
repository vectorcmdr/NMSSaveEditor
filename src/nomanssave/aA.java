package nomanssave;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

class aA extends DefaultTableCellRenderer {
   int f;

   public aA(JTable var1, int var2) {
      this.f = var2;
   }

   public Component getTableCellRendererComponent(JTable var1, Object var2, boolean var3, boolean var4, int var5, int var6) {
      JLabel var7 = (JLabel)super.getTableCellRendererComponent(var1, var2, var3, var4, var5, var6);
      var7.setHorizontalAlignment(this.f);
      return var7;
   }
}
