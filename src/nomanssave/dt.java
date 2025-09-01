package nomanssave;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class dt extends ba {
   private final Application eR;
   private final JPanel eS;

   dt(Application var1) {
      super(aH.cJ, 0);
      this.eR = var1;
      this.k("Production");
      this.eS = new JPanel();
      this.eS.setLayout(new GridBagLayout());
      this.a((JComponent)this.eS);
      UIManager.addPropertyChangeListener((var1x) -> {
         if ("lookAndFeel".equals(var1x.getPropertyName())) {
            EventQueue.invokeLater(this::aL);
         }

      });
   }

   private void aL() {
      synchronized(this.eS.getTreeLock()) {
         Component[] var5;
         int var4 = (var5 = this.eS.getComponents()).length;
         int var3 = 0;

         while(true) {
            if (var3 >= var4) {
               break;
            }

            Component var2 = var5[var3];
            du var6 = (du)var2;
            du.c(var6);
            ++var3;
         }
      }

      this.eS.revalidate();
      this.eS.updateUI();
   }

   public void a(gF[] var1) {
      synchronized(this.eS.getTreeLock()) {
         this.eS.removeAll();
         int var3 = 0;

         while(true) {
            if (var3 >= var1.length) {
               break;
            }

            du var4 = new du(this, var1[var3], (du)null);
            GridBagConstraints var5 = new GridBagConstraints();
            var5.fill = 1;
            var5.insets = new Insets(10, 10, 10, 10);
            var5.gridx = var3 % 3;
            var5.gridy = var3 / 3;
            this.eS.add(var4, var5);
            ++var3;
         }
      }

      this.eS.revalidate();
      this.eS.updateUI();
   }

   private void a(du var1) {
      ey var2 = h.a(this, 28160);
      if (var2 != null) {
         var1.hm.m(var2.aZ());
         var1.hm.aA(0);
         du.c(var1);
      }

   }

   private void b(du var1) {
      ey var2 = ey.d(var1.hm.dz());
      if (var2 == null) {
         this.eR.c("Item details not found!");
      } else {
         List var3 = this.eR.g(3584);
         int var4 = dd.a(this, var3, -1);
         if (var4 != -1) {
            gt var5 = (gt)var3.get(var4);
            int var6 = var1.hm.dA();
            ey var7 = ey.d(var1.hm.dz());
            var6 = var5.a(var7, var6);
            var1.hm.aA(var6);
            du.c(var1);
            this.eR.a(var5);
         }

      }
   }

   // $FF: synthetic method
   static void a(dt var0, du var1) {
      var0.a(var1);
   }

   // $FF: synthetic method
   static void b(dt var0, du var1) {
      var0.b(var1);
   }
}
