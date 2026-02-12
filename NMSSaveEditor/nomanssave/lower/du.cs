using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

public class du : Panel {
   gF hm;
   public ToolStripMenuItem fh;
   public ToolStripMenuItem hn;
   public ToolStripMenuItem fe;
   dt ho;

   public du(dt var1, gF var2) {
      this.ho = var1;
      this.hm = var2;
      int var3 = 0 /* UIManager.getInt("Inventory.gridSize") */;
      Size var4 = new Size(var3, var3);
      this.setBackground(bO.eK);
      this.setMinimumSize(var4);
      this.setMaximumSize(var4);
      this.Size = (var4);
      this.SuspendLayout(); // TODO: set layout new GridBagLayout());
      this.Padding = new Padding(0); /* setBorder */ //(bO.eP);
      JPopupMenu var5 = new JPopupMenu();
      this.fe = new ToolStripMenuItem("Item Details");
      this.fe.Click += (new dv(this, var2));
      var5.Add(this.fe);
      this.hn = new ToolStripMenuItem("Change Item");
      this.hn.Click += (new dw(this, var2));
      var5.Add(this.hn);
      this.fh = new ToolStripMenuItem("Move Item");
      this.fh.Click += (new dx(this, var2));
      var5.Add(this.fh);
      this.setComponentPopupMenu(var5);
      this.addMouseListener(new dy(this, var2));
      this.aM();
   }

   public void aM() {
      this.RemoveAll();
      int var1 = 0 /* UIManager.getInt("Inventory.gridSize") */;
      Size var2 = new Size(var1, var1);
      this.setBackground(bO.eK);
      this.setMinimumSize(var2);
      this.setMaximumSize(var2);
      this.Size = (var2);
      if (this.hm != null && this.hm.isValid()) {
         this.fe.Enabled = (true);
         this.hn.Enabled = (true);
         this.fh.Enabled = (this.hm.dA() > 0);
         ey var3 = ey.d(this.hm.dz());
         string var4 = var3 == null ? this.hm.ei() : var3.Name;
         int var5 = 0 /* UIManager.getInt("Inventory.iconSize") */;
         Font var6 = /* UIManager.getFont */ SystemFonts.DefaultFont; //("Inventory.font");
         Image var7 = var3 == null ? null : var3.c(var5, var5);
         int var8 = 0;
         Label var9;
         GridBagConstraints var10;
         if (var7 != null) {
            var9 = new Label(var7);
            var9.Size = (new Size(var5, var5));
            var10 = new GridBagConstraints();
            var10.anchor = 10;
            var10.fill = 0;
            var10.insets = new Padding(5, 0, 5, 0);
            var10.gridx = 0;
            var10.gridy = var8++;
            this.Add(var9, var10);
         }

         var9 = new Label();
         var9.setFont(var6);
         var9.setBackground((Color)null);
         var9.Padding = new Padding(0); /* setBorder */ //((Border)null);
         var9.Text = (var4);
         var9.setForeground(bO.eO);
         var10 = new GridBagConstraints();
         var10.anchor = 10;
         var10.fill = 0;
         var10.insets = new Padding(var8 == 0 ? var5 + 10 : 0, 0, 0, 0);
         var10.gridx = 0;
         var10.gridy = var8++;
         this.Add(var9, var10);
         var9 = new Label();
         var9.setFont(var6);
         var9.setBackground((Color)null);
         var9.Padding = new Padding(0); /* setBorder */ //((Border)null);
         var9.Text = (Integer.toString(this.hm.dA()) + "/" + Integer.toString(this.hm.dB()));
         var9.setForeground(bO.eO);
         var10 = new GridBagConstraints();
         var10.anchor = 10;
         var10.fill = 0;
         var10.insets = new Padding(0, 0, 0, 0);
         var10.gridx = 0;
         var10.gridy = var8++;
         this.Add(var9, var10);
      } else {
         this.fe.Enabled = (false);
         this.hn.Enabled = (false);
         this.fh.Enabled = (false);
      }

      this.PerformLayout();
      this.Refresh();
   }
   public static void c(du var0) {
      var0.aM();
   }
   public du(dt var1, gF var2, du var3) {
      this(var1, var2);
   }
   public static dt d(du var0) {
      return var0.ho;
   }
}

}
