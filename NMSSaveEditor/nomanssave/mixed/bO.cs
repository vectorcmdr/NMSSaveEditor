using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

public class bO : Panel, eo {
   public static Color eE;
   public static Color eF;
   public static Color eG;
   public static Color eH;
   public static Color eI;
   public static Color eJ;
   public static Color eK;
   public static Color eL;
   public static Color eM;
   public static Color eN;
   public static Color eO;
   public static object eP;
   public static object eQ;
   public Application eR;
   public Panel eS;
   public ComboBox eT;
   public Button eU;
   public List<object> eV;
   public gt eW;

   static bO() {
      eE = Color.Gray;
      eF = new Color(255, 240, 240);
      eG = new Color(255, 255, 240);
      eH = new Color(240, 255, 250);
      eI = new Color(240, 250, 255);
      eJ = new Color(240, 255, 255);
      eK = Color.White;
      eL = Color.Black;
      eM = Color.Yellow;
      eN = Color.Red;
      eO = Color.Black;
      eP = null /* LineBorder */;
      eQ = null /* CompoundBorder */;
   }

   public bO(Application var1) {
      this.eR = var1;
      this.SuspendLayout(); // TODO: set layout new TableLayoutPanel());
      Panel var2 = new Panel();
      var2.SuspendLayout(); // TODO: set layout new FlowLayoutPanel());
      this.eS = new Panel();
      this.eS.SuspendLayout(); // TODO: set layout new GridBagLayout());
      int var3 = 0 /* UIManager.getInt("Inventory.gridSize") */;
      this.Size = (new Size(var3 * 10 + 20, var3 * 8 + 50));
      this.Controls.Add(var2);
      Panel var4 = new Panel();
      var4.setViewportView(this.eS);
      var4.Padding = new Padding(0); /* setBorder */ //(new LineBorder(eL));
      this.Controls.Add(var4);
      this.eV = new List<object>();
      this.eT = new ComboBox();
      this.eT.Hide();
      this.eT.DataSource = (new bP(this));
      this.eT.setRenderer(new bQ(this));
      var2.Add(this.eT);
      this.eU = new Button("Resize Inventory");
      this.eU.Hide();
      this.eU.Click += (new bR(this));
      var2.Add(this.eU);
      en.a(this);
      /* TODO: port from Java - addPropertyChangeListener for lookAndFeel */
   }

   public void a(bool var1) {
      this.eU.setVisible(this.eW == null ? false : var1 || this.eW.dk());
      bool var2 = this.eW == null ? false : var1 || this.eW.dp();
      lock(this.eS.getTreeLock()) {
         for(int var4 = 0; var4 < this.eS.Controls.Count; ++var4) {
            Component var5 = this.eS.Controls[var4];
            if (var5 is bS) {
               bS var6 = (bS)var5;
               bS.b(var6).Enabled = (var2);
               bS.g(var6).Enabled = (var2);
            }
         }

      }
   }

   public void a(gt var1) {
      if (this.eW == var1) {
         this.af();
      }

   }

   public void w() {
      this.eV.stream().forEach((var1) => {
         if (var1.dt() && this.eW == var1) {
            this.af();
         }

      });
   }

   public void x() {
      this.eV.stream().forEach((var1) => {
         if (var1.du() && this.eW == var1) {
            this.af();
         }

      });
   }

   public void y() {
      this.eV.stream().forEach((var1) => {
         if (var1.dp() && var1.dv() && this.eW == var1) {
            this.af();
         }

      });
   }

   public void z() {
      this.eV.stream().forEach((var1) => {
         if (var1.dq() && var1.ds() && this.eW == var1) {
            this.af();
         }

      });
   }

   public void A() {
      this.eV.stream().forEach((var1) => {
         if (var1.dk() && var1.dl() && this.eW == var1) {
            this.af();
         }

      });
   }

   public void ae() {
      int var1 = this.eT.SelectedIndex;
      if (var1 >= 0) {
         this.eW = (gt)this.eV[var1];
         this.af();
      }

   }

   public void af() {
      lock(this.eS.getTreeLock()) {
         this.eS.RemoveAll();
         if (this.eW != null) {
            int var2 = 0 /* UIManager.getInt("Inventory.gridSize") */;
            Size var3 = new Size(var2, var2);

            for(int var4 = 0; var4 < this.eW.Height; ++var4) {
               for(int var5 = 0; var5 < this.eW.Width; ++var5) {
                  bS var6 = new bS(this, var5, var4, (bS)null);
                  var6.setMinimumSize(var3);
                  var6.setMaximumSize(var3);
                  var6.Size = (var3);
                  GridBagConstraints var7 = new GridBagConstraints();
                  var7.fill = 1;
                  var7.insets = new Padding(-1, -1, 0, 0);
                  var7.gridx = var5;
                  var7.gridy = var4;
                  this.eS.Add(var6, var7);
               }
            }
         }
      }

      this.eS.PerformLayout();
      this.eS.Refresh();
   }

   public void a(List<object> var1) {
      this.eV = var1;
      this.eW = null;
      this.eT.Refresh();
      if (this.eV.Count == 0) {
         this.eT.Hide();
         this.eU.Hide();
         this.af();
      } else {
         this.eT.setVisible(this.eV.Count != 1);
         this.eU.Hide();
         this.eT.SelectedIndex = (0);
      }

   }

   public bS a(int var1, int var2) {
      lock(this.eS.getTreeLock()) {
         for(int var4 = 0; var4 < this.eS.Controls.Count; ++var4) {
            Component var5 = this.eS.Controls[var4];
            if (var5 is bS) {
               bS var6 = (bS)var5;
               if (bS.h(var6) == var1 && bS.i(var6) == var2) {
                  return var6;
               }
            }
         }

         return null;
      }
   }

   public void a(bS var1) {
      ey var2 = h.a(this, this.eW.dj());
      if (var2 != null) {
         this.eW.a(bS.h(var1), bS.i(var1), var2);
         bS.c(var1);
      }

   }

   public void a(gu var1, bS var2) {
      ey var3 = ey.d(var1.dz());
      int var4;
      if (var3 == null) {
         if ("Product".Equals(var1.getType())) {
            var4 = 512;
         } else {
            if (!"Substance".Equals(var1.getType())) {
               this.eR.c("Item details not found!");
               return;
            }

            var4 = 1024;
         }
      } else {
         var4 = gt.a(var3.bc());
      }

      List<object> var5 = this.eR.g(var4);
      int var6 = var5.IndexOf(this.eW);
      int var7 = dd.a(this, var5, var6);
      if (var7 != var6) {
         gt var8 = (gt)var5[var7];
         if (this.eW.a(bS.h(var2), bS.i(var2), var8)) {
            bS.c(var2);
            this.eR.a(var8);
         }
      }

   }

   public static string a(object var0) {
      return var0 is fg ? "Archived Tech" : var0.ToString();
   }
   public static gt a(bO var0) {
      return var0.eW;
   }
   public static Application b(bO var0) {
      return var0.eR;
   }
   public static void c(bO var0) {
      var0.af();
   }
   public static void a(bO var0, bS var1) {
      var0.a(var1);
   }
   public static void a(bO var0, gu var1, bS var2) {
      var0.a(var1, var2);
   }
   public static bS a(bO var0, int var1, int var2) {
      return var0.a(var1, var2);
   }
   public static Color ag() {
      return eE;
   }
   public static object ah() {
      return eQ;
   }
   public static Color ai() {
      return eF;
   }
   public static string b(object var0) {
      return a(var0);
   }
   public static Color aj() {
      return eN;
   }
   public static Color ak() {
      return eG;
   }
   public static Color al() {
      return eH;
   }
   public static Color am() {
      return eI;
   }
   public static Color an() {
      return eJ;
   }
   public static List<object> d(bO var0) {
      return var0.eV;
   }
   public static void a(bO var0, gt var1) {
      var0.eW = var1;
   }
   public static Button e(bO var0) {
      return var0.eU;
   }
}

}