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
      this.bm.SetEnabled(false);
      this.a("Items", this.bm);
      Panel var2 = new Panel();
      this.bn = new Button() { Text = "Backup" };
      this.bn.AddActionListener(new bj(this, var1));
      var2.Add(this.bn);
      this.bo = new Button() { Text = "Restore" };
      this.bo.AddActionListener(new bk(this, var1));
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
         this.dG.SetText("");
         this.dH.SetSelectedIndex(-1);
         this.dH.updateUI();
         this.dI.SetSelectedIndex(-1);
         this.dJ.SetText("");
         this.dK.SetText("");
         this.dL.SetText("");
         this.dM.SetText("");
         this.bm.SetText("");
         this.bn.SetEnabled(false);
         this.bo.SetEnabled(false);
         this.dN.a(new List<object>());
      } else {
         this.dO = var1;
         this.dG.SetText(var1.Name);
         this.dH.m(var1.cT());
         this.dI.m(var1.cW());
         this.dJ.SetText(var1.cU());
         this.dK.SetText(var1.cV());
         this.dL.SetText(Double.toString(var1.cX()));
         this.dM.SetText(Double.toString(var1.cY()));
         gn var2 = var1.cZ();
         if (var2 == null) {
            this.bm.SetText("");
            this.bn.SetEnabled(false);
            this.bo.SetEnabled(false);
         } else {
            this.bm.SetText(Convert.ToString(var2.cG()));
            this.bn.SetEnabled(true);
            this.bo.SetEnabled(true);
         }
          this.dN.a(var1.cC());
      }
    }

   // $FF: synthetic method
   public static gm a(bd var0) {
      return var0.dO;
   }

   // $FF: synthetic method
   public static G b(bd var0) {
      return var0.dG;
   }

   // $FF: synthetic method
   public static G c(bd var0) {
      return var0.dJ;
   }
}

}
