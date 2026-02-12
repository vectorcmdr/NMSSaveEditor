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
   public static Border eP;
   public static Border eQ;
   public Application eR;
   public Panel eS;
   public ComboBox eT;
   public Button eU;
   public List<object> eV;
   public gt eW;

   static bO() {
      eE = Color.Gray;
      eF = Color.FromArgb(255, 240, 240);
      eG = Color.FromArgb(255, 255, 240);
      eH = Color.FromArgb(240, 255, 250);
      eI = Color.FromArgb(240, 250, 255);
      eJ = Color.FromArgb(240, 255, 255);
      eK = Color.White;
      eL = Color.Black;
      eM = Color.Yellow;
      eN = Color.Red;
      eO = Color.Black;
      eP = BorderFactory.createLineBorder(eL, 1);
      eQ = BorderFactory.createCompoundBorder(eP, BorderFactory.createLineBorder(eM, 2));
   }

   public bO(Application var1) {
      this.eR = var1;
      this.SetLayout(new BorderLayout());
      Panel var2 = new Panel();
      var2.SetLayout(new FlowLayout());
      this.eS = new Panel();
      this.eS.SetLayout(new GridBagLayout());
      int var3 = UIManager.getInt("Inventory.gridSize");
      this.SetPreferredSize(new Size(var3 * 10 + 20, var3 * 8 + 50));
      this.Add(var2, "North");
      Panel var4 = new Panel();
      var4.setViewportView(this.eS);
      var4.SetBorder(new LineBorder(eL));
      this.Add(var4, "Center");
      this.eV = new List<object>();
      this.eT = new ComboBox();
      this.eT.SetVisible(false);
      this.eT.SetModel(new bP(this));
      this.eT.setRenderer(new bQ(this));
      var2.Add(this.eT);
      this.eU = new Button("Resize Inventory");
      this.eU.SetVisible(false);
      this.eU.AddActionListener(new bR(this));
      var2.Add(this.eU);
      en.a(this);
      UIManager.addPropertyChangeListener((var1x) => {
         if ("lookAndFeel".Equals(var1x.getPropertyName())) {
            JavaCompat.InvokeLater(this.af);
         }

      });
   }

   public void a(bool var1) {
      this.eU.SetVisible(this.eW == null ? false : var1 || this.eW.dk());
      bool var2 = this.eW == null ? false : var1 || this.eW.dp();
      lock (this.eS.getTreeLock()) {
         for(int var4 = 0; var4 < this.eS.getComponentCount(); ++var4) {
            Component var5 = this.eS.getComponent(var4);
            if (var5 is bS) {
               bS var6 = (bS)var5;
               bS.b(var6).SetEnabled(var2);
               bS.g(var6).SetEnabled(var2);
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
      this.eV.forEach((var1) => {
         if (var1.dt() && this.eW == var1) {
            this.af();
         }

      });
   }

   public void x() {
      this.eV.forEach((var1) => {
         if (var1.du() && this.eW == var1) {
            this.af();
         }

      });
   }

   public void y() {
      this.eV.forEach((var1) => {
         if (var1.dp() && var1.dv() && this.eW == var1) {
            this.af();
         }

      });
   }

   public void z() {
      this.eV.forEach((var1) => {
         if (var1.dq() && var1.ds() && this.eW == var1) {
            this.af();
         }

      });
   }

   public void A() {
      this.eV.forEach((var1) => {
         if (var1.dk() && var1.dl() && this.eW == var1) {
            this.af();
         }

      });
   }

   public void ae() {
      int var1 = this.eT.SelectedIndex;
      if (var1 >= 0) {
         this.eW = (gt)this.eV.Get(var1);
         this.af();
      }

   }

   public void af() {
      lock (this.eS.getTreeLock()) {
         this.eS.Controls.Clear();
         if (this.eW != null) {
            int var2 = UIManager.getInt("Inventory.gridSize");
            Size var3 = new Size(var2, var2);

            for(int var4 = 0; var4 < this.eW.Height; ++var4) {
               for(int var5 = 0; var5 < this.eW.Width; ++var5) {
                  bS var6 = new bS(this, var5, var4, (bS)null);
                  var6.SetMinimumSize(var3);
                  var6.SetMaximumSize(var3);
                  var6.SetPreferredSize(var3);
                  GridBagConstraints var7 = new GridBagConstraints();
                  var7.fill = 1;
                  var7.insets = new Insets(-1, -1, 0, 0);
                  var7.gridx = var5;
                  var7.gridy = var4;
                  this.eS.Add(var6, var7);
               }
            }
         }
      }

      this.eS.PerformLayout();
      this.eS.updateUI();
   }

   public void a(List<object> var1) {
      this.eV = var1;
      this.eW = null;
      this.eT.updateUI();
      if (this.eV.Count == 0) {
         this.eT.SetVisible(false);
         this.eU.SetVisible(false);
         this.af();
      } else {
         this.eT.SetVisible(this.eV.Count != 1);
         this.eU.SetVisible(false);
         this.eT.SetSelectedIndex(0);
      }

   }

   public bS a(int var1, int var2) {
      lock (this.eS.getTreeLock()) {
         for(int var4 = 0; var4 < this.eS.getComponentCount(); ++var4) {
            Component var5 = this.eS.getComponent(var4);
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
         gt var8 = (gt)var5.Get(var7);
         if (this.eW.a(bS.h(var2), bS.i(var2), var8)) {
            bS.c(var2);
            this.eR.a(var8);
         }
      }

   }

   public static string a(Object var0) {
      return var0 is fg ? "Archived Tech" : var0.ToString();
   }

   // $FF: synthetic method
   static gt a(bO var0) {
      return var0.eW;
   }

   // $FF: synthetic method
   static Application b(bO var0) {
      return var0.eR;
   }

   // $FF: synthetic method
   static void c(bO var0) {
      var0.af();
   }

   // $FF: synthetic method
   static void a(bO var0, bS var1) {
      var0.a(var1);
   }

   // $FF: synthetic method
   static void a(bO var0, gu var1, bS var2) {
      var0.a(var1, var2);
   }

   // $FF: synthetic method
   static bS a(bO var0, int var1, int var2) {
      return var0.a(var1, var2);
   }

   // $FF: synthetic method
   static Color ag() {
      return eE;
   }

   // $FF: synthetic method
   static Border ah() {
      return eQ;
   }

   // $FF: synthetic method
   static Color ai() {
      return eF;
   }

   // $FF: synthetic method
   static string b(Object var0) {
      return a(var0);
   }

   // $FF: synthetic method
   static Color aj() {
      return eN;
   }

   // $FF: synthetic method
   static Color ak() {
      return eG;
   }

   // $FF: synthetic method
   static Color al() {
      return eH;
   }

   // $FF: synthetic method
   static Color am() {
      return eI;
   }

   // $FF: synthetic method
   static Color an() {
      return eJ;
   }

   // $FF: synthetic method
   static List<object> d(bO var0) {
      return var0.eV;
   }

   // $FF: synthetic method
   static void a(bO var0, gt var1) {
      var0.eW = var1;
   }

   // $FF: synthetic method
   static Button e(bO var0) {
      return var0.eU;
   }
}

}
