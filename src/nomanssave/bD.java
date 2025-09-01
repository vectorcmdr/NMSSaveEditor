package nomanssave;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.UIManager;

class bD extends DefaultListCellRenderer {
   // $FF: synthetic field
   final bl er;

   private bD(bl var1) {
      this.er = var1;
   }

   public Component getListCellRendererComponent(JList var1, Object var2, int var3, boolean var4, boolean var5) {
      Component var6 = super.getListCellRendererComponent(var1, var2, var3, var4, var5);
      if (var2 == null && var6 instanceof JLabel) {
         JLabel var7 = (JLabel)var6;
         var7.setText(" ");
      }

      if (var2 instanceof er && var6 instanceof JLabel) {
         er var9 = (er)var2;
         JLabel var8 = (JLabel)var6;
         if (var9.aW()) {
            if (var4) {
               var8.setBackground(UIManager.getColor("Frigate.positiveTraitHighlight"));
            } else {
               var8.setForeground(UIManager.getColor("Frigate.positiveTraitColor"));
            }
         } else if (var4) {
            var8.setBackground(UIManager.getColor("Frigate.negativeTraitHighlight"));
         } else {
            var8.setForeground(UIManager.getColor("Frigate.negativeTraitColor"));
         }
      }

      return var6;
   }

   // $FF: synthetic method
   bD(bl var1, bD var2) {
      this(var1);
   }
}
