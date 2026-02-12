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

public class X : Panel {
   private ComboBox bG;
   private ComboBox bH;
   private G bI;
   private G bJ;
   private G bK;
   private G bL;
   private G bM;
   private CheckBox bN;
   private cN bO;
   private cN bP;
   private Button bQ;
   private Button bR;
   private Button bS;
   private gj[] bT;

   X(Application var1) {
      GridLayout var2 = new GridLayout(1, 3);
      this.SetLayout(var2);
      ba var3 = new ba(new int[]{aH.cJ, 0});
      this.Add(var3);
      this.Add(new Panel());
      this.Add(new Panel());
      this.bG = new ComboBox();
      this.bG.SetModel(new Y(this));
      var3.a("Companion", true, this.bG);
      this.bH = new ComboBox();
      this.bH.SetModel(new ab(this));
      this.bH.SetEnabled(false);
      var3.a("Type", (JComponent)this.bH);
      this.bI = new ac(this);
      var3.a("Name", (JComponent)this.bI);
      this.bJ = new ad(this);
      var3.a("Creature Seed", this.bJ);
      this.bK = new ae(this);
      var3.a("Secondary Seed", (JComponent)this.bK);
      this.bL = new af(this);
      var3.a("Species Seed", (JComponent)this.bL);
      this.bM = new ag(this);
      var3.a("Genus Seed", (JComponent)this.bM);
      this.bN = new CheckBox("Predator");
      this.bN.SetEnabled(false);
      this.bN.AddActionListener(new ah(this));
      var3.a((string)null, (JComponent)this.bN);
      this.bO = new cN(typeof(gi));
      this.bO.a((var1x) => {
         gj var2 = (gj)this.bG.SelectedItem;
         if (var2 != null) {
            var2.ae(var1x);
         }

      });
      var3.a("Biome", (JComponent)this.bO);
      this.bP = new cN(typeof(gk));
      this.bP.a((var1x) => {
         gj var2 = (gj)this.bG.SelectedItem;
         if (var2 != null) {
            var2.af(var1x);
         }

      });
      var3.a("Type", (JComponent)this.bP);
      var3.Y();
      Panel var4 = new Panel();
      this.bQ = new Button("Delete");
      this.bQ.AddActionListener(new ai(this, var1));
      var4.Add(this.bQ);
      this.bR = new Button("Export");
      this.bR.AddActionListener(new Z(this, var1));
      var4.Add(this.bR);
      this.bS = new Button("Import");
      this.bS.AddActionListener(new aa(this, var1));
      var4.Add(this.bS);
      var3.a(var4);
   }

   void a(gj[] var1) {
      this.bT = var1;
      if (var1.length == 0) {
         this.bG.SetSelectedIndex(-1);
      } else {
         this.bG.SetSelectedIndex(0);
      }

      this.bG.updateUI();
   }

   // $FF: synthetic method
   static gj[] a(X var0) {
      return var0.bT;
   }

   // $FF: synthetic method
   static ComboBox b(X var0) {
      return var0.bH;
   }

   // $FF: synthetic method
   static G c(X var0) {
      return var0.bI;
   }

   // $FF: synthetic method
   static G d(X var0) {
      return var0.bJ;
   }

   // $FF: synthetic method
   static G e(X var0) {
      return var0.bK;
   }

   // $FF: synthetic method
   static G f(X var0) {
      return var0.bL;
   }

   // $FF: synthetic method
   static G g(X var0) {
      return var0.bM;
   }

   // $FF: synthetic method
   static CheckBox h(X var0) {
      return var0.bN;
   }

   // $FF: synthetic method
   static cN i(X var0) {
      return var0.bO;
   }

   // $FF: synthetic method
   static cN j(X var0) {
      return var0.bP;
   }

   // $FF: synthetic method
   static ComboBox k(X var0) {
      return var0.bG;
   }

   // $FF: synthetic method
   static void a(X var0, gj[] var1) {
      var0.bT = var1;
   }
}

}
