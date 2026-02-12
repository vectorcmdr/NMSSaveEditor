using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class bd : em {
   public static double dE = 1000.0D;
   public static double dF = 1000.0D;
   public G dG;
   public cN dH;
   public cN dI;
   public G dJ;
   public G dK;
   public G dL;
   public G dM;
   public TextBox bm;
   public Button bn;
   public Button bo;
   public bO dN;
   public gm dO;

   public bd(Application var1) {
      this.k("Freighter");
      this.dG = new be(this);
      this.a("Name", this.dG);
      this.dH = new cN(typeof(go));
      this.dH.a((var1x) => {
         if (this.dO != null) {
            this.dO.ag(var1x);
         }

      });
      this.a("Type", this.dH);
      this.dI = new cN(typeof(gN));
      this.dI.a((var1x) => {
         if (this.dO != null) {
            this.dO.aj(var1x);
         }

      });
      this.a("Class", this.dI);
      this.dJ = new bf(this);
      this.a("Home Seed", this.dJ);
      this.dK = new bg(this);
      this.a("Model Seed", this.dK);
      this.k("Base Stats");
      this.dL = new bh(this);
      this.a("Hyperdrive", this.dL);
      this.dM = new bi(this);
      this.a("Fleet Coordination", this.dM);
      this.Y();
      this.k("Base Info");
      this.bm = new TextBox();
      this.bm.Enabled = (false);
      this.a("Items", this.bm);
      Panel var2 = new Panel();
      this.bn = new Button() { Text = "Backup" };
      this.bn.Click += (new bj(this, var1));
      var2.Add(this.bn);
      this.bo = new Button() { Text = "Restore" };
      this.bo.Click += (new bk(this, var1));
      var2.Add(this.bo);
      this.a((Control)var2);
      this.dN = new bO(var1);
      this.b(this.dN);
   }

   public void w() {
      this.dN.w();
   }

   public void x() {
      this.dN.x();
   }

   public void y() {
      this.dN.y();
   }

   public void A() {
      this.dN.A();
   }

   public void a(gt var1) {
      this.dN.a(var1);
   }

   public gm Z() {
      return this.dO;
   }

   public void c(gm var1) {
      if (var1 == null) {
         this.dO = null;
         this.dG.Text = ("");
         this.dH.SelectedIndex = (-1);
         this.dH.Refresh();
         this.dI.SelectedIndex = (-1);
         this.dJ.Text = ("");
         this.dK.Text = ("");
         this.dL.Text = ("");
         this.dM.Text = ("");
         this.bm.Text = ("");
         this.bn.Enabled = (false);
         this.bo.Enabled = (false);
         this.dN.a(new List<object>());
      } else {
         this.dO = var1;
         this.dG.Text = (var1.Name);
         this.dH.m(var1.cT());
         this.dI.m(var1.cW());
         this.dJ.Text = (var1.cU());
         this.dK.Text = (var1.cV());
         this.dL.Text = (Double.toString(var1.cX()));
         this.dM.Text = (Double.toString(var1.cY()));
         gn var2 = var1.cZ();
         if (var2 == null) {
            this.bm.Text = ("");
            this.bn.Enabled = (false);
            this.bo.Enabled = (false);
         } else {
            this.bm.Text = (Integer.toString(var2.cG()));
            this.bn.Enabled = (true);
            this.bo.Enabled = (true);
         }

         this.dN.a(var1.cC());
      }

   }
   public static gm a(bd var0) {
      return var0.dO;
   }
   public static G b(bd var0) {
      return var0.dG;
   }
   public static G c(bd var0) {
      return var0.dJ;
   }
}


#else

public class bd
{
   public bd() { }
   public bd(params object[] args) { }
   public static double dE = 0;
   public static double dF = 0;
   public G dG = default;
   public cN dH = default;
   public cN dI = default;
   public G dJ = default;
   public G dK = default;
   public G dL = default;
   public G dM = default;
   public TextBox bm = default;
   public Button bn = default;
   public Button bo = default;
   public bO dN = default;
   public gm dO = default;
   public void w() { }
   public void x() { }
   public void y() { }
   public void A() { }
   public void a(gt var1) { }
   public gm Z() { return default; }
   public void c(gm var1) { }
   public static G b(bd var0) { return default; }
}

#endif

}
