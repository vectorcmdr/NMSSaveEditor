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
   // $FF: synthetic field
   public bO eX;

   public bS(bO var1, int var2, int var3) {
      this.eX = var1;
      this.x = var2;
      this.y = var3;
      this.setLayout(new GridBagLayout());
      ContextMenuStrip var4 = new ContextMenuStrip();
      this.eY = new ToolStripMenuItem("Enabled");
      this.eY.addActionListener(new bT(this, var2, var3));
      this.eY.setEnabled(bO.a(var1).dp() || en.aS());
      var4.Items.Add(this.eY);
      this.eZ = new ToolStripMenuItem("Enable All Slots");
      this.eZ.addActionListener(new bY(this));
      this.eZ.setEnabled(bO.a(var1).dp() || en.aS());
      var4.Items.Add(this.eZ);
      this.fa = new ToolStripMenuItem("Repair Slot");
      this.fa.addActionListener(new bZ(this, var2, var3));
      this.fa.setVisible(bO.a(var1).dq());
      var4.Items.Add(this.fa);
      this.fb = new ToolStripMenuItem("Repair All Slots");
      this.fb.addActionListener(new ca(this));
      this.fb.setVisible(bO.a(var1).dq());
      var4.Items.Add(this.fb);
      this.fc = new ToolStripMenuItem("Supercharged");
      this.fc.addActionListener(new cb(this, var2, var3));
      this.fc.setVisible(bO.a(var1).@do());
      var4.Items.Add(this.fc);
      this.fd = new ToolStripMenuItem("Supercharge All Slots");
      this.fd.addActionListener(new cc(this));
      this.fd.setVisible(bO.a(var1).@do());
      var4.Items.Add(this.fd);
      var4.Items.Add(new ToolStripSeparator());
      this.fe = new ToolStripMenuItem("Item Details");
      this.fe.addActionListener(new cd(this, var2, var3));
      var4.Items.Add(this.fe);
      this.ff = new ToolStripMenuItem("Add Item");
      this.ff.addActionListener(new ce(this, var2, var3));
      var4.Items.Add(this.ff);
      this.fg = new ToolStripMenuItem("Repair Item");
      this.fg.addActionListener(new cf(this, var2, var3));
      var4.Items.Add(this.fg);
      this.fh = new ToolStripMenuItem("Move Item");
      this.fh.addActionListener(new bU(this, var2, var3));
      var4.Items.Add(this.fh);
      this.fi = new ToolStripMenuItem("Fill Stack");
      this.fi.addActionListener(new bV(this, var2, var3));
      var4.Items.Add(this.fi);
      this.fj = new ToolStripMenuItem("Delete Item");
      this.fj.addActionListener(new bW(this, var2, var3));
      var4.Items.Add(this.fj);
      this.ContextMenuStrip = var4;
      this.setBorder(bO.eP);
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
      this.Controls.Clear();
      this.eY.setEnabled(bO.a(this.eX).dp() || en.aS());
      this.eZ.setEnabled(bO.a(this.eX).dp() || en.aS());
      this.fa.setVisible(bO.a(this.eX).dq());
      this.fb.setVisible(bO.a(this.eX).dq());
      if (!bO.a(this.eX).h(this.x, this.y)) {
         this.eY.setSelected(false);
         this.fa.setEnabled(false);
         this.fe.setVisible(false);
         this.fg.setVisible(false);
         this.ff.setVisible(true);
         this.ff.setEnabled(false);
         this.fh.setVisible(false);
         this.fi.setVisible(false);
         this.fj.setVisible(false);
         this.fc.setVisible(false);
         this.setBorder(bO.eP);
         this.setBackground(bO.ag());
         this.setToolTipText((string)null);
      } else {
         gu var1;
         ey var2;
         bool var3;
         string var4;
         if (bO.a(this.eX).l(this.x, this.y)) {
            this.eY.setSelected(true);
            this.fa.setEnabled(true);
            this.fe.setVisible(false);
            this.fg.setVisible(false);
            this.ff.setVisible(true);
            this.ff.setEnabled(false);
            this.fh.setVisible(false);
            this.fi.setVisible(false);
            this.fj.setVisible(false);
            this.fc.setVisible(bO.a(this.eX).@do());
            if (bO.a(this.eX).k(this.x, this.y)) {
               this.setBorder(bO.ah());
               this.fc.setSelected(true);
            } else {
               this.setBorder(bO.eP);
               this.fc.setSelected(false);
            }
             this.setBackground(bO.ai());
            var1 = bO.a(this.eX).f(this.x, this.y);
            if (var1 == null) {
               this.setToolTipText((string)null);
            } else {
               var2 = ey.d(var1.dz());
               var3 = var2 is eQ && var1.dC() != 0.0;
               var4 = var2 == null ? bO.b(var1.dz()) : var2.Name;
               int var5 = UIManager.getInt("Inventory.iconSize");
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
            this.eY.setSelected(true);
            this.fa.setEnabled(false);
            this.fc.setVisible(bO.a(this.eX).@do());
            if (bO.a(this.eX).k(this.x, this.y)) {
               this.setBorder(bO.ah());
               this.fc.setSelected(true);
            } else {
               this.setBorder(bO.eP);
               this.fc.setSelected(false);
            }
             var1 = bO.a(this.eX).f(this.x, this.y);
            if (var1 == null) {
               this.fe.setVisible(false);
               this.fg.setVisible(false);
               this.ff.setVisible(true);
               this.ff.setEnabled(true);
               this.fh.setVisible(false);
               this.fi.setVisible(false);
               this.fj.setVisible(false);
               this.setBackground(bO.eK);
               this.setToolTipText((string)null);
            } else {
               var2 = ey.d(var1.dz());
               var3 = var2 is eQ && var1.dC() != 0.0;
               this.fe.setVisible(true);
               this.fg.setVisible(var3);
               this.ff.setVisible(false);
               this.ff.setEnabled(false);
               this.fh.setVisible(true);
               this.fi.setVisible(false);
               this.fj.setVisible(true);
               var4 = var1.getType();
               if (var4.Equals("Technology")) {
                  this.setBackground(bO.ak());
                  if (var1.dA() >= 0 && var1.dA() < var1.dB()) {
                     this.fi.setText("Recharge");
                     this.fi.setVisible(true);
                  }
               } else if (var4.Equals("Product")) {
                  this.setBackground(bO.al());
                  if (var1.dB() > 1) {
                     this.fi.setText("Fill Stack");
                     this.fi.setVisible(true);
                  }
               } else if (var4.Equals("Substance")) {
                  this.setBackground(bO.am());
                  if (var1.dB() > 1) {
                     this.fi.setText("Fill Stack");
                     this.fi.setVisible(true);
                  }
               } else {
                  this.setBackground(bO.an());
               }
                this.fe.setEnabled(var2 != null);
               string var10 = var2 == null ? bO.b(var1.dz()) : var2.Name;
               int var11 = UIManager.getInt("Inventory.iconSize");
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
      this.Invalidate();
   }

   public void a(Image var1, int var2, int var3) {
      Label var4 = new Label();
      var4.setPreferredSize(new Size(var2, var2));
      GridBagConstraints var5 = new GridBagConstraints();
      var5.anchor = 10;
      var5.fill = 0;
      var5.insets = new Insets(5, 0, 5, 0);
      var5.gridx = 0;
      var5.gridy = var3;
      this.add(var4, var5);
   }

   public void a(string var1, int var2, Color var3) {
      Label var4 = new Label();
      var4.setFont(UIManager.getFont("Inventory.font"));
      var4.setBackground(Color.Empty);
      var4.setBorder((object)null);
      var4.setText(var1);
      var4.setForeground(var3);
      GridBagConstraints var5 = new GridBagConstraints();
      var5.anchor = 10;
      var5.fill = 0;
      int var6 = UIManager.getInt("Inventory.iconSize");
      var5.insets = new Insets(var2 == 0 ? var6 + 10 : 0, 0, 0, 0);
      var5.gridx = 0;
      var5.gridy = var2;
      this.add(var4, var5);
   }

   // $FF: synthetic method
   public static ToolStripMenuItem b(bS var0) {
      return var0.eY;
   }

   // $FF: synthetic method
   public static void c(bS var0) {
      var0.aq();
   }

   // $FF: synthetic method
   public static ToolStripMenuItem d(bS var0) {
      return var0.fc;
   }

   // $FF: synthetic method
   public static bool e(bS var0) {
      return var0.ao();
   }

   // $FF: synthetic method
   public static bool f(bS var0) {
      return var0.ap();
   }

   // $FF: synthetic method
   public static ToolStripMenuItem g(bS var0) {
      return var0.eZ;
   }

   // $FF: synthetic method
   public bS(bO var1, int var2, int var3, bS var4) : this(var1, var2, var3) {
   }

   // $FF: synthetic method
   public static int h(bS var0) {
      return var0.x;
   }

   // $FF: synthetic method
   public static int i(bS var0) {
      return var0.y;
   }

   // $FF: synthetic method
   public static bO j(bS var0) {
      return var0.eX;
   }
}

}
