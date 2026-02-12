using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

public class X : Panel {
   public ComboBox bG;
   public ComboBox bH;
   public G bI;
   public G bJ;
   public G bK;
   public G bL;
   public G bM;
   public CheckBox bN;
   public cN bO;
   public cN bP;
   public Button bQ;
   public Button bR;
   public Button bS;
   public gj[] bT;

   public X(Application var1) {
      TableLayoutPanel var2 = new TableLayoutPanel(1, 3);
      this.SuspendLayout(); // TODO: set layout var2);
      ba var3 = new ba(new int[]{aH.cJ, 0});
      this.Add(var3);
      this.Add(new Panel());
      this.Add(new Panel());
      this.bG = new ComboBox();
      this.bG.DataSource = (new Y(this));
      var3.a("Companion", true, this.bG);
      this.bH = new ComboBox();
      this.bH.DataSource = (new ab(this));
      this.bH.Enabled = (false);
      var3.a("Type", (Control)this.bH);
      this.bI = new ac(this);
      var3.a("Name", (Control)this.bI);
      this.bJ = new ad(this);
      var3.a("Creature Seed", this.bJ);
      this.bK = new ae(this);
      var3.a("Secondary Seed", (Control)this.bK);
      this.bL = new af(this);
      var3.a("Species Seed", (Control)this.bL);
      this.bM = new ag(this);
      var3.a("Genus Seed", (Control)this.bM);
      this.bN = new CheckBox("Predator");
      this.bN.Enabled = (false);
      this.bN.Click += (new ah(this));
      var3.a(null, (Control)this.bN);
      this.bO = new cN(typeof(gi));
      this.bO.a((var1x) => {
         gj var2 = (gj)this.bG.SelectedItem;
         if (var2 != null) {
            var2.ae(var1x);
         }

      });
      var3.a("Biome", (Control)this.bO);
      this.bP = new cN(typeof(gk));
      this.bP.a((var1x) => {
         gj var2 = (gj)this.bG.SelectedItem;
         if (var2 != null) {
            var2.af(var1x);
         }

      });
      var3.a("Type", (Control)this.bP);
      var3.Y();
      Panel var4 = new Panel();
      this.bQ = new Button("Delete");
      this.bQ.Click += (new ai(this, var1));
      var4.Add(this.bQ);
      this.bR = new Button("Export");
      this.bR.Click += (new Z(this, var1));
      var4.Add(this.bR);
      this.bS = new Button("Import");
      this.bS.Click += (new aa(this, var1));
      var4.Add(this.bS);
      var3.a(var4);
   }

   public void a(gj[] var1) {
      this.bT = var1;
      if (var1.Length == 0) {
         this.bG.SelectedIndex = (-1);
      } else {
         this.bG.SelectedIndex = (0);
      }

      this.bG.Refresh();
   }
   public static gj[] a(X var0) {
      return var0.bT;
   }
   public static ComboBox b(X var0) {
      return var0.bH;
   }
   public static G c(X var0) {
      return var0.bI;
   }
   public static G d(X var0) {
      return var0.bJ;
   }
   public static G e(X var0) {
      return var0.bK;
   }
   public static G f(X var0) {
      return var0.bL;
   }
   public static G g(X var0) {
      return var0.bM;
   }
   public static CheckBox h(X var0) {
      return var0.bN;
   }
   public static cN i(X var0) {
      return var0.bO;
   }
   public static cN j(X var0) {
      return var0.bP;
   }
   public static ComboBox k(X var0) {
      return var0.bG;
   }
   public static void a(X var0, gj[] var1) {
      var0.bT = var1;
   }
}

}
