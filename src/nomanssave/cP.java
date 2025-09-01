package nomanssave;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

class cP extends DefaultListCellRenderer {
   // $FF: synthetic field
   final cN gt;

   cP(cN var1) {
      this.gt = var1;
   }

   public Component getListCellRendererComponent(JList var1, Object var2, int var3, boolean var4, boolean var5) {
      Component var6 = super.getListCellRendererComponent(var1, var2, var3, var4, var5);
      if (var2 == null && var6 instanceof JLabel) {
         JLabel var7 = (JLabel)var6;
         var7.setText(" ");
      }

      if (var6 instanceof JLabel) {
         boolean var12 = false;
         Enum[] var11;
         int var10 = (var11 = cN.e(this.gt)).length;

         for(int var9 = 0; var9 < var10; ++var9) {
            Enum var8 = var11[var9];
            if (var8 == var2) {
               var12 = true;
               break;
            }
         }

         JLabel var13 = (JLabel)var6;
         if (!var12) {
            if (var4) {
               var13.setBackground(cN.ag());
            } else {
               var13.setForeground(cN.aB());
            }
         }
      }

      return var6;
   }
}
