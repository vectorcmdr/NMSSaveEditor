using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

public class bd : em {
   private static double dE = 1000.0D;
   private static double dF = 1000.0D;
   private G dG;
   private cN dH;
   private cN dI;
   private G dJ;
   private G dK;
   private G dL;
   private G dM;
   private TextBox bm;
   private Button bn;
   private Button bo;
   private bO dN;
   private gm dO;

   bd(Application var1) {
      this.k("Freighter");
      this.dG = new be(this);
      this.a("Name", this.dG);
      this.dH = new cN(go.class);
      this.dH.a((var1x) -> {
         if (this.dO != null) {
            this.dO.ag(var1x);
         }

      });
      this.a("Type", this.dH);
      this.dI = new cN(gN.class);
      this.dI.a((var1x) -> {
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
      this.bm.setEnabled(false);
      this.a("Items", this.bm);
      Panel var2 = new Panel();
      this.bn = new Button("Backup");
      this.bn.addActionListener(new bj(this, var1));
      var2.Add(this.bn);
      this.bo = new Button("Restore");
      this.bo.addActionListener(new bk(this, var1));
      var2.Add(this.bo);
      this.a((Control)var2);
      this.dN = new bO(var1);
      this.b(this.dN);
   }

   void w() {
      this.dN.w();
   }

   void x() {
      this.dN.x();
   }

   void y() {
      this.dN.y();
   }

   void A() {
      this.dN.A();
   }

   void a(gt var1) {
      this.dN.a(var1);
   }

   gm Z() {
      return this.dO;
   }

   void c(gm var1) {
      if (var1 == null) {
         this.dO = null;
         this.dG.setText("");
         this.dH.setSelectedIndex(-1);
         this.dH.updateUI();
         this.dI.setSelectedIndex(-1);
         this.dJ.setText("");
         this.dK.setText("");
         this.dL.setText("");
         this.dM.setText("");
         this.bm.setText("");
         this.bn.setEnabled(false);
         this.bo.setEnabled(false);
         this.dN.a(new List<object>());
      } else {
         this.dO = var1;
         this.dG.setText(var1.getName());
         this.dH.m(var1.cT());
         this.dI.m(var1.cW());
         this.dJ.setText(var1.cU());
         this.dK.setText(var1.cV());
         this.dL.setText(Double.toString(var1.cX()));
         this.dM.setText(Double.toString(var1.cY()));
         gn var2 = var1.cZ();
         if (var2 == null) {
            this.bm.setText("");
            this.bn.setEnabled(false);
            this.bo.setEnabled(false);
         } else {
            this.bm.setText(Integer.toString(var2.cG()));
            this.bn.setEnabled(true);
            this.bo.setEnabled(true);
         }

         this.dN.a(var1.cC());
      }

   }
   static gm a(bd var0) {
      return var0.dO;
   }
   static G b(bd var0) {
      return var0.dG;
   }
   static G c(bd var0) {
      return var0.dJ;
   }
}

}
