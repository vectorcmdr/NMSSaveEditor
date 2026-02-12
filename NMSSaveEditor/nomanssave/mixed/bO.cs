using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

public class bO : Panel, eo {
   private static Color eE;
   private static Color eF;
   private static Color eG;
   private static Color eH;
   private static Color eI;
   private static Color eJ;
   public static Color eK;
   private static Color eL;
   private static Color eM;
   private static Color eN;
   public static Color eO;
   public static object eP;
   private static object eQ;
   private Application eR;
   private Panel eS;
   private ComboBox eT;
   private Button eU;
   private List<object> eV;
   private gt eW;

   static bO() {
      eE = Color.GRAY;
      eF = new Color(255, 240, 240);
      eG = new Color(255, 255, 240);
      eH = new Color(240, 255, 250);
      eI = new Color(240, 250, 255);
      eJ = new Color(240, 255, 255);
      eK = Color.WHITE;
      eL = Color.BLACK;
      eM = Color.YELLOW;
      eN = Color.RED;
      eO = Color.BLACK;
      eP = null /* LineBorder */;
      eQ = null /* CompoundBorder */;
   }

   bO(Application var1) {
      this.eR = var1;
      this.SuspendLayout(); // TODO: set layout new TableLayoutPanel());
      Panel var2 = new Panel();
      var2.SuspendLayout(); // TODO: set layout new FlowLayoutPanel());
      this.eS = new Panel();
      this.eS.SuspendLayout(); // TODO: set layout new GridBagLayout());
      int var3 = SystemInformation.getInt("Inventory.gridSize");
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
      SystemInformation.addPropertyChangeListener((var1x) => {
         if ("lookAndFeel".Equals(var1x.getPropertyName())) {
            System.Windows.Forms.Application.Run(this.af);
         }

      });
   }

   public void a(bool var1) {
      this.eU.setVisible(this.eW == null ? false : var1 || this.eW.dk());
      bool var2 = this.eW == null ? false : var1 || this.eW.dp();
      lock(this.eS.getTreeLock()) {
         for(int var4 = 0; var4 < this.eS.Controls.Count; ++var4) {
            Component var5 = this.eS.Controls[var4);
            if (var5 is bS) {
               bS var6 = (bS)var5;
               bS.b(var6).Enabled = (var2);
               bS.g(var6).Enabled = (var2);
            }
         }

      }
   }

   void a(gt var1) {
      if (this.eW == var1) {
         this.af();
      }

   }

   void w() {
      this.eV.stream().forEach((var1) => {
         if (var1.dt() && this.eW == var1) {
            this.af();
         }

      });
   }

   void x() {
      this.eV.stream().forEach((var1) => {
         if (var1.du() && this.eW == var1) {
            this.af();
         }

      });
   }

   void y() {
      this.eV.stream().forEach((var1) => {
         if (var1.dp() && var1.dv() && this.eW == var1) {
            this.af();
         }

      });
   }

   void z() {
      this.eV.stream().forEach((var1) => {
         if (var1.dq() && var1.ds() && this.eW == var1) {
            this.af();
         }

      });
   }

   void A() {
      this.eV.stream().forEach((var1) => {
         if (var1.dk() && var1.dl() && this.eW == var1) {
            this.af();
         }

      });
   }

   void ae() {
      int var1 = this.eT.SelectedIndex;
      if (var1 >= 0) {
         this.eW = (gt)this.eV[(var1);
         this.af();
      }

   }

   private void af() {
      lock(this.eS.getTreeLock()) {
         this.eS.RemoveAll();
         if (this.eW != null) {
            int var2 = SystemInformation.getInt("Inventory.gridSize");
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

   void a(List<object> var1) {
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

   private bS a(int var1, int var2) {
      lock(this.eS.getTreeLock()) {
         for(int var4 = 0; var4 < this.eS.Controls.Count; ++var4) {
            Component var5 = this.eS.Controls[var4);
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

   private void a(bS var1) {
      ey var2 = h.a(this, this.eW.dj());
      if (var2 != null) {
         this.eW.a(bS.h(var1), bS.i(var1), var2);
         bS.c(var1);
      }

   }

   private void a(gu var1, bS var2) {
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
         gt var8 = (gt)var5[(var7);
         if (this.eW.a(bS.h(var2), bS.i(var2), var8)) {
            bS.c(var2);
            this.eR.a(var8);
         }
      }

   }

   private static string a(Object var0) {
      return var0 is fg ? "Archived Tech" : var0.ToString();
   }
   static gt a(bO var0) {
      return var0.eW;
   }
   static Application b(bO var0) {
      return var0.eR;
   }
   static void c(bO var0) {
      var0.af();
   }
   static void a(bO var0, bS var1) {
      var0.a(var1);
   }
   static void a(bO var0, gu var1, bS var2) {
      var0.a(var1, var2);
   }
   static bS a(bO var0, int var1, int var2) {
      return var0.a(var1, var2);
   }
   static Color ag() {
      return eE;
   }
   static object ah() {
      return eQ;
   }
   static Color ai() {
      return eF;
   }
   static string b(Object var0) {
      return a(var0);
   }
   static Color aj() {
      return eN;
   }
   static Color ak() {
      return eG;
   }
   static Color al() {
      return eH;
   }
   static Color am() {
      return eI;
   }
   static Color an() {
      return eJ;
   }
   static List<object> d(bO var0) {
      return var0.eV;
   }
   static void a(bO var0, gt var1) {
      var0.eW = var1;
   }
   static Button e(bO var0) {
      return var0.eU;
   }
}

}
