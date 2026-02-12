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
   private int x;
   private int y;
   private ToolStripMenuItem eY;
   private ToolStripMenuItem eZ;
   private ToolStripMenuItem fa;
   private ToolStripMenuItem fb;
   private ToolStripMenuItem fc;
   private ToolStripMenuItem fd;
   private ToolStripMenuItem fe;
   private ToolStripMenuItem ff;
   private ToolStripMenuItem fg;
   private ToolStripMenuItem fh;
   private ToolStripMenuItem fi;
   private ToolStripMenuItem fj;
   // $FF: synthetic field
   bO eX;

   private bS(bO var1, int var2, int var3) {
      this.eX = var1;
      this.x = var2;
      this.y = var3;
      this.SetLayout(new GridBagLayout());
      ContextMenuStrip var4 = new ContextMenuStrip();
      this.eY = new ToolStripMenuItem("Enabled");
      this.eY.AddActionListener(new bT(this, var2, var3));
      this.eY.SetEnabled(bO.a(var1).dp() || en.aS());
      var4.Add(this.eY);
      this.eZ = new ToolStripMenuItem("Enable All Slots");
      this.eZ.AddActionListener(new bY(this));
      this.eZ.SetEnabled(bO.a(var1).dp() || en.aS());
      var4.Add(this.eZ);
      this.fa = new ToolStripMenuItem("Repair Slot");
      this.fa.AddActionListener(new bZ(this, var2, var3));
      this.fa.SetVisible(bO.a(var1).dq());
      var4.Add(this.fa);
      this.fb = new ToolStripMenuItem("Repair All Slots");
      this.fb.AddActionListener(new ca(this));
      this.fb.SetVisible(bO.a(var1).dq());
      var4.Add(this.fb);
      this.fc = new ToolStripMenuItem("Supercharged");
      this.fc.AddActionListener(new cb(this, var2, var3));
      this.fc.SetVisible(bO.a(var1).do());
      var4.Add(this.fc);
      this.fd = new ToolStripMenuItem("Supercharge All Slots");
      this.fd.AddActionListener(new cc(this));
      this.fd.SetVisible(bO.a(var1).do());
      var4.Add(this.fd);
      var4.AddSeparator();
      this.fe = new ToolStripMenuItem("Item Details");
      this.fe.AddActionListener(new cd(this, var2, var3));
      var4.Add(this.fe);
      this.ff = new ToolStripMenuItem("Add Item");
      this.ff.AddActionListener(new ce(this, var2, var3));
      var4.Add(this.ff);
      this.fg = new ToolStripMenuItem("Repair Item");
      this.fg.AddActionListener(new cf(this, var2, var3));
      var4.Add(this.fg);
      this.fh = new ToolStripMenuItem("Move Item");
      this.fh.AddActionListener(new bU(this, var2, var3));
      var4.Add(this.fh);
      this.fi = new ToolStripMenuItem("Fill Stack");
      this.fi.AddActionListener(new bV(this, var2, var3));
      var4.Add(this.fi);
      this.fj = new ToolStripMenuItem("Delete Item");
      this.fj.AddActionListener(new bW(this, var2, var3));
      var4.Add(this.fj);
      this.setComponentPopupMenu(var4);
      this.SetBorder(bO.eP);
      this.AddMouseListener(new bX(this, var2, var3));
      this.aq();
   }

   private bool ao() {
      return bO.a(this.eX).h(this.x, this.y);
   }

   private bool ap() {
      return bO.a(this.eX).l(this.x, this.y);
   }

   private void aq() {
      this.Controls.Clear();
      this.eY.SetEnabled(bO.a(this.eX).dp() || en.aS());
      this.eZ.SetEnabled(bO.a(this.eX).dp() || en.aS());
      this.fa.SetVisible(bO.a(this.eX).dq());
      this.fb.SetVisible(bO.a(this.eX).dq());
      if (!bO.a(this.eX).h(this.x, this.y)) {
         this.eY.setSelected(false);
         this.fa.SetEnabled(false);
         this.fe.SetVisible(false);
         this.fg.SetVisible(false);
         this.ff.SetVisible(true);
         this.ff.SetEnabled(false);
         this.fh.SetVisible(false);
         this.fi.SetVisible(false);
         this.fj.SetVisible(false);
         this.fc.SetVisible(false);
         this.SetBorder(bO.eP);
         this.SetBackground(bO.ag());
         this.setToolTipText((string)null);
      } else {
         gu var1;
         ey var2;
         bool var3;
         string var4;
         if (bO.a(this.eX).l(this.x, this.y)) {
            this.eY.setSelected(true);
            this.fa.SetEnabled(true);
            this.fe.SetVisible(false);
            this.fg.SetVisible(false);
            this.ff.SetVisible(true);
            this.ff.SetEnabled(false);
            this.fh.SetVisible(false);
            this.fi.SetVisible(false);
            this.fj.SetVisible(false);
            this.fc.SetVisible(bO.a(this.eX).do());
            if (bO.a(this.eX).k(this.x, this.y)) {
               this.SetBorder(bO.ah());
               this.fc.setState(true);
            } else {
               this.SetBorder(bO.eP);
               this.fc.setState(false);
            }

            this.SetBackground(bO.ai());
            var1 = bO.a(this.eX).f(this.x, this.y);
            if (var1 == null) {
               this.setToolTipText((string)null);
            } else {
               var2 = ey.d(var1.dz());
               var3 = var2 is eQ && var1.dC() != 0.0D;
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
            this.fa.SetEnabled(false);
            this.fc.SetVisible(bO.a(this.eX).do());
            if (bO.a(this.eX).k(this.x, this.y)) {
               this.SetBorder(bO.ah());
               this.fc.setState(true);
            } else {
               this.SetBorder(bO.eP);
               this.fc.setState(false);
            }

            var1 = bO.a(this.eX).f(this.x, this.y);
            if (var1 == null) {
               this.fe.SetVisible(false);
               this.fg.SetVisible(false);
               this.ff.SetVisible(true);
               this.ff.SetEnabled(true);
               this.fh.SetVisible(false);
               this.fi.SetVisible(false);
               this.fj.SetVisible(false);
               this.SetBackground(bO.eK);
               this.setToolTipText((string)null);
            } else {
               var2 = ey.d(var1.dz());
               var3 = var2 is eQ && var1.dC() != 0.0D;
               this.fe.SetVisible(true);
               this.fg.SetVisible(var3);
               this.ff.SetVisible(false);
               this.ff.SetEnabled(false);
               this.fh.SetVisible(true);
               this.fi.SetVisible(false);
               this.fj.SetVisible(true);
               var4 = var1.getType();
               if (var4.Equals("Technology")) {
                  this.SetBackground(bO.ak());
                  if (var1.dA() >= 0 && var1.dA() < var1.dB()) {
                     this.fi.SetText("Recharge");
                     this.fi.SetVisible(true);
                  }
               } else if (var4.Equals("Product")) {
                  this.SetBackground(bO.al());
                  if (var1.dB() > 1) {
                     this.fi.SetText("Fill Stack");
                     this.fi.SetVisible(true);
                  }
               } else if (var4.Equals("Substance")) {
                  this.SetBackground(bO.am());
                  if (var1.dB() > 1) {
                     this.fi.SetText("Fill Stack");
                     this.fi.SetVisible(true);
                  }
               } else {
                  this.SetBackground(bO.an());
               }

               this.fe.SetEnabled(var2 != null);
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
      this.updateUI();
   }

   private void a(Image var1, int var2, int var3) {
      Label var4 = new Label(var1);
      var4.SetPreferredSize(new Size(var2, var2));
      GridBagConstraints var5 = new GridBagConstraints();
      var5.anchor = 10;
      var5.fill = 0;
      var5.insets = new Insets(5, 0, 5, 0);
      var5.gridx = 0;
      var5.gridy = var3;
      this.Add(var4, var5);
   }

   private void a(string var1, int var2, Color var3) {
      Label var4 = new Label();
      var4.SetFont(UIManager.getFont("Inventory.font"));
      var4.SetBackground((Color)null);
      var4.SetBorder((Border)null);
      var4.SetText(var1);
      var4.SetForeground(var3);
      GridBagConstraints var5 = new GridBagConstraints();
      var5.anchor = 10;
      var5.fill = 0;
      int var6 = UIManager.getInt("Inventory.iconSize");
      var5.insets = new Insets(var2 == 0 ? var6 + 10 : 0, 0, 0, 0);
      var5.gridx = 0;
      var5.gridy = var2;
      this.Add(var4, var5);
   }

   // $FF: synthetic method
   static ToolStripMenuItem b(bS var0) {
      return var0.eY;
   }

   // $FF: synthetic method
   static void c(bS var0) {
      var0.aq();
   }

   // $FF: synthetic method
   static ToolStripMenuItem d(bS var0) {
      return var0.fc;
   }

   // $FF: synthetic method
   static bool e(bS var0) {
      return var0.ao();
   }

   // $FF: synthetic method
   static bool f(bS var0) {
      return var0.ap();
   }

   // $FF: synthetic method
   static ToolStripMenuItem g(bS var0) {
      return var0.eZ;
   }

   // $FF: synthetic method
   bS(bO var1, int var2, int var3, bS var4) {
      // Constructor chain: base(var1, var2, var3)
   }

   // $FF: synthetic method
   static int h(bS var0) {
      return var0.x;
   }

   // $FF: synthetic method
   static int i(bS var0) {
      return var0.y;
   }

   // $FF: synthetic method
   static bO j(bS var0) {
      return var0.eX;
   }
}

}
