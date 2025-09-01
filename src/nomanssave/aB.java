package nomanssave;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

class aB extends DefaultTableCellRenderer {
   // $FF: synthetic field
   final ap cu;

   private aB(ap var1) {
      this.cu = var1;
   }

   public Component getTableCellRendererComponent(JTable var1, Object var2, boolean var3, boolean var4, int var5, int var6) {
      JLabel var7 = (JLabel)super.getTableCellRendererComponent(var1, "", var3, var4, var5, var6);
      var7.setIcon((ImageIcon)var2);
      return var7;
   }

   // $FF: synthetic method
   aB(ap var1, aB var2) {
      this(var1);
   }
}
