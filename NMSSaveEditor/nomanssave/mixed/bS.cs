using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

public class bS : Panel {
   public int x;
   public int y;
   public ToolStripMenuItem eY;
   public ToolStripMenuItem eZ;
   public ToolStripMenuItem fa;
   public ToolStripMenuItem fb;
   public ToolStripMenuItem fc;
   public ToolStripMenuItem fd;
   public ToolStripMenuItem fe;
   public ToolStripMenuItem ff;
   public ToolStripMenuItem fg;
   public ToolStripMenuItem fh;
   public ToolStripMenuItem fi;
   public ToolStripMenuItem fj;
   public bO eX;

   public bS(bO var1, int var2, int var3) {
      this.eX = var1;
      this.x = var2;
      this.y = var3;
      this.SuspendLayout(); // TODO: set layout new GridBagLayout());
      JPopupMenu var4 = new JPopupMenu();
      this.eY = new ToolStripMenuItem("Enabled");
      this.eY.Click += (new bT(this, var2, var3));
      this.eY.Enabled = (bO.a(var1).dp() || en.aS());
      var4.Add(this.eY);
      this.eZ = new ToolStripMenuItem("Enable All Slots");
      this.eZ.Click += (new bY(this));
      this.eZ.Enabled = (bO.a(var1).dp() || en.aS());
      var4.Add(this.eZ);
      this.fa = new ToolStripMenuItem("Repair Slot");
      this.fa.Click += (new bZ(this, var2, var3));
      this.fa.setVisible(bO.a(var1).dq());
      var4.Add(this.fa);
      this.fb = new ToolStripMenuItem("Repair All Slots");
      this.fb.Click += (new ca(this));
      this.fb.setVisible(bO.a(var1).dq());
      var4.Add(this.fb);
      this.fc = new ToolStripMenuItem("Supercharged");
      this.fc.Click += (new cb(this, var2, var3));
      this.fc.setVisible(bO.a(var1).@do());
      var4.Add(this.fc);
      this.fd = new ToolStripMenuItem("Supercharge All Slots");
      this.fd.Click += (new cc(this));
      this.fd.setVisible(bO.a(var1).@do());
      var4.Add(this.fd);
      var4.addSeparator();
      this.fe = new ToolStripMenuItem("Item Details");
      this.fe.Click += (new cd(this, var2, var3));
      var4.Add(this.fe);
      this.ff = new ToolStripMenuItem("Add Item");
      this.ff.Click += (new ce(this, var2, var3));
      var4.Add(this.ff);
      this.fg = new ToolStripMenuItem("Repair Item");
      this.fg.Click += (new cf(this, var2, var3));
      var4.Add(this.fg);
      this.fh = new ToolStripMenuItem("Move Item");
      this.fh.Click += (new bU(this, var2, var3));
      var4.Add(this.fh);
      this.fi = new ToolStripMenuItem("Fill Stack");
      this.fi.Click += (new bV(this, var2, var3));
      var4.Add(this.fi);
      this.fj = new ToolStripMenuItem("Delete Item");
      this.fj.Click += (new bW(this, var2, var3));
      var4.Add(this.fj);
      this.setComponentPopupMenu(var4);
      this.Padding = new Padding(0); /* setBorder */ //(bO.eP);
      this.addMouseListener(new bX(this, var2, var3));
      this.aq();
   }

   public bool ao() {
      return bO.a(this.eX).h(this.x, this.y);
   }

   public bool ap() {
      return bO.a(this.eX).l(this.x, this.y);
   }

   public void aq() {
      this.RemoveAll();
      this.eY.Enabled = (bO.a(this.eX).dp() || en.aS());
      this.eZ.Enabled = (bO.a(this.eX).dp() || en.aS());
      this.fa.setVisible(bO.a(this.eX).dq());
      this.fb.setVisible(bO.a(this.eX).dq());
      if (!bO.a(this.eX).h(this.x, this.y)) {
         this.eY.Checked = (false);
         this.fa.Enabled = (false);
         this.fe.Hide();
         this.fg.Hide();
         this.ff.Show();
         this.ff.Enabled = (false);
         this.fh.Hide();
         this.fi.Hide();
         this.fj.Hide();
         this.fc.Hide();
         this.Padding = new Padding(0); /* setBorder */ //(bO.eP);
         this.setBackground(bO.ag());
         this.setToolTipText(null);
      } else {
         gu var1;
         ey var2;
         bool var3;
         string var4;
         if (bO.a(this.eX).l(this.x, this.y)) {
            this.eY.Checked = (true);
            this.fa.Enabled = (true);
            this.fe.Hide();
            this.fg.Hide();
            this.ff.Show();
            this.ff.Enabled = (false);
            this.fh.Hide();
            this.fi.Hide();
            this.fj.Hide();
            this.fc.setVisible(bO.a(this.eX).@do());
            if (bO.a(this.eX).k(this.x, this.y)) {
               this.Padding = new Padding(0); /* setBorder */ //(bO.ah());
               this.fc.setState(true);
            } else {
               this.Padding = new Padding(0); /* setBorder */ //(bO.eP);
               this.fc.setState(false);
            }

            this.setBackground(bO.ai());
            var1 = bO.a(this.eX).f(this.x, this.y);
            if (var1 == null) {
               this.setToolTipText(null);
            } else {
               var2 = ey.d(var1.dz());
               var3 = var2 is eQ && var1.dC() != 0.0D;
               var4 = var2 == null ? bO.b(var1.dz()) : var2.Name;
               int var5 = 0 /* UIManager.getInt("Inventory.iconSize") */;
               Image var6 = var2 == null ? null : var2.c(var5, var5);
               int var7 = 0;
               if (var6 != null) {
                  this.a(var6, var5, var7++);
               }

               Color var8 = var3 ? bO.aj() : bO.eO;
               this.a(var4, var7++, var8);
               this.a(var1.dA() < 0 ? "" : var1.dA() + "/" + var1.dB(), var7++, var8);
               this.setToolTipText(var4);
            }
         } else {
            this.eY.Checked = (true);
            this.fa.Enabled = (false);
            this.fc.setVisible(bO.a(this.eX).@do());
            if (bO.a(this.eX).k(this.x, this.y)) {
               this.Padding = new Padding(0); /* setBorder */ //(bO.ah());
               this.fc.setState(true);
            } else {
               this.Padding = new Padding(0); /* setBorder */ //(bO.eP);
               this.fc.setState(false);
            }

            var1 = bO.a(this.eX).f(this.x, this.y);
            if (var1 == null) {
               this.fe.Hide();
               this.fg.Hide();
               this.ff.Show();
               this.ff.Enabled = (true);
               this.fh.Hide();
               this.fi.Hide();
               this.fj.Hide();
               this.setBackground(bO.eK);
               this.setToolTipText(null);
            } else {
               var2 = ey.d(var1.dz());
               var3 = var2 is eQ && var1.dC() != 0.0D;
               this.fe.Show();
               this.fg.setVisible(var3);
               this.ff.Hide();
               this.ff.Enabled = (false);
               this.fh.Show();
               this.fi.Hide();
               this.fj.Show();
               var4 = var1.getType();
               if (var4.Equals("Technology")) {
                  this.setBackground(bO.ak());
                  if (var1.dA() >= 0 && var1.dA() < var1.dB()) {
                     this.fi.Text = ("Recharge");
                     this.fi.Show();
                  }
               } else if (var4.Equals("Product")) {
                  this.setBackground(bO.al());
                  if (var1.dB() > 1) {
                     this.fi.Text = ("Fill Stack");
                     this.fi.Show();
                  }
               } else if (var4.Equals("Substance")) {
                  this.setBackground(bO.am());
                  if (var1.dB() > 1) {
                     this.fi.Text = ("Fill Stack");
                     this.fi.Show();
                  }
               } else {
                  this.setBackground(bO.an());
               }

               this.fe.Enabled = (var2 != null);
               string var10 = var2 == null ? bO.b(var1.dz()) : var2.Name;
               int var11 = 0 /* UIManager.getInt("Inventory.iconSize") */;
               Image var13 = var2 == null ? null : var2.c(var11, var11);
               int var12 = 0;
               if (var13 != null) {
                  this.a(var13, var11, var12++);
               }

               Color var9 = var3 ? bO.aj() : bO.eO;
               this.a(var10, var12++, var9);
               this.a(var1.dA() < 0 ? " " : var1.dA() + "/" + var1.dB(), var12++, var9);
               this.setToolTipText(var10);
            }
         }
      }

      this.PerformLayout();
      this.Refresh();
   }

   public void a(Image var1, int var2, int var3) {
      Label var4 = new Label() { Text = var1 };
      var4.Size = (new Size(var2, var2));
      GridBagConstraints var5 = new GridBagConstraints();
      var5.anchor = 10;
      var5.fill = 0;
      var5.insets = new Padding(5, 0, 5, 0);
      var5.gridx = 0;
      var5.gridy = var3;
      this.Add(var4, var5);
   }

   public void a(string var1, int var2, Color var3) {
      Label var4 = new Label();
      var4.setFont(/* UIManager.getFont */ SystemFonts.DefaultFont); //("Inventory.font")
      var4.setBackground((Color)null);
      var4.Padding = new Padding(0); /* setBorder */ //((Border)null);
      var4.Text = (var1);
      var4.setForeground(var3);
      GridBagConstraints var5 = new GridBagConstraints();
      var5.anchor = 10;
      var5.fill = 0;
      int var6 = 0 /* UIManager.getInt("Inventory.iconSize") */;
      var5.insets = new Padding(var2 == 0 ? var6 + 10 : 0, 0, 0, 0);
      var5.gridx = 0;
      var5.gridy = var2;
      this.Add(var4, var5);
   }
   public static ToolStripMenuItem b(bS var0) {
      return var0.eY;
   }
   public static void c(bS var0) {
      var0.aq();
   }
   public static ToolStripMenuItem d(bS var0) {
      return var0.fc;
   }
   public static bool e(bS var0) {
      return var0.ao();
   }
   public static bool f(bS var0) {
      return var0.ap();
   }
   public static ToolStripMenuItem g(bS var0) {
      return var0.eZ;
   }
   public bS(bO var1, int var2, int var3, bS var4) {
      this(var1, var2, var3);
   }
   public static int h(bS var0) {
      return var0.x;
   }
   public static int i(bS var0) {
      return var0.y;
   }
   public static bO j(bS var0) {
      return var0.eX;
   }
}

}
