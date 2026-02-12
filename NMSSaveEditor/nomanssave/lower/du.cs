using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Windows.Forms;
using System.Globalization;

namespace NMSSaveEditor
{

public class du : Panel {
   public gF hm;
   public ToolStripMenuItem fh;
   public ToolStripMenuItem hn;
   public ToolStripMenuItem fe;
   // $FF: synthetic field
   public dt ho;

   public du(dt var1, gF var2) {
      this.ho = var1;
      this.hm = var2;
      int var3 = 0;
      Size var4 = new Size(var3, var3);
      this.SetBackground(bO.eK);
      this.SetMinimumSize(var4);
      this.SetMaximumSize(var4);
      this.SetPreferredSize(var4);
      this.SetLayout(new GridBagLayout());
      this.SetBorder(bO.eP);
      ContextMenuStrip var5 = new ContextMenuStrip();
      this.fe = new ToolStripMenuItem("Item Details");
      this.fe.AddActionListener(new dv(this, var2));
      var5.Add(this.fe);
      this.hn = new ToolStripMenuItem("Change Item");
      this.hn.AddActionListener(new dw(this, var2));
      var5.Add(this.hn);
      this.fh = new ToolStripMenuItem("Move Item");
      this.fh.AddActionListener(new dx(this, var2));
      var5.Add(this.fh);
      this.setComponentPopupMenu(var5);
      this.AddMouseListener(new dy(this, var2));
      this.aM();
   }

   public void aM() {
      this.Controls.Clear();
      int var1 = 0;
      Size var2 = new Size(var1, var1);
      this.SetBackground(bO.eK);
      this.SetMinimumSize(var2);
      this.SetMaximumSize(var2);
      this.SetPreferredSize(var2);
      if (this.hm != null && this.hm.isValid()) {
         this.fe.SetEnabled(true);
         this.hn.SetEnabled(true);
         this.fh.SetEnabled(this.hm.dA() > 0);
         ey var3 = ey.d(this.hm.dz());
         string var4 = var3 == null ? this.hm.ei() : var3.Name;
         int var5 = 0;
         Font var6 = SystemFonts.DefaultFont;
         Image var7 = var3 == null ? null : var3.c(var5, var5);
         int var8 = 0;
         Label var9;
         GridBagConstraints var10;
         if (var7 != null) {
            var9 = new Label(var7);
            var9.SetPreferredSize(new Size(var5, var5));
            var10 = new GridBagConstraints();
            var10.anchor = 10;
            var10.fill = 0;
            var10.insets = new Insets(5, 0, 5, 0);
            var10.gridx = 0;
            var10.gridy = var8++;
            this.Add(var9, var10);
         }
          var9 = new Label();
         var9.SetFont(var6);
         var9.SetBackground((Color)null);
         var9.SetBorder((Border)null);
         var9.SetText(var4);
         var9.SetForeground(bO.eO);
         var10 = new GridBagConstraints();
         var10.anchor = 10;
         var10.fill = 0;
         var10.insets = new Insets(var8 == 0 ? var5 + 10 : 0, 0, 0, 0);
         var10.gridx = 0;
         var10.gridy = var8++;
         this.Add(var9, var10);
         var9 = new Label();
         var9.SetFont(var6);
         var9.SetBackground((Color)null);
         var9.SetBorder((Border)null);
         var9.SetText(Convert.ToString(this.hm.dA()) + "/" + Convert.ToString(this.hm.dB()));
         var9.SetForeground(bO.eO);
         var10 = new GridBagConstraints();
         var10.anchor = 10;
         var10.fill = 0;
         var10.insets = new Insets(0, 0, 0, 0);
         var10.gridx = 0;
         var10.gridy = var8++;
         this.Add(var9, var10);
      } else {
         this.fe.SetEnabled(false);
         this.hn.SetEnabled(false);
         this.fh.SetEnabled(false);
      }
       this.PerformLayout();
      this.updateUI();
   }

   // $FF: synthetic method
   public static void c(du var0) {
      var0.aM();
   }

   // $FF: synthetic method
   public du(dt var1, gF var2, du var3) {
      // Constructor chain: base(var1, var2)
   }

   // $FF: synthetic method
   public static dt d(du var0) {
      return var0.ho;
   }
}

}
