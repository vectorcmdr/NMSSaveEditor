package nomanssave;

import java.util.Collections;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class bd extends em {
   private static final double dE = 1000.0D;
   private static final double dF = 1000.0D;
   private G dG;
   private cN dH;
   private cN dI;
   private G dJ;
   private G dK;
   private G dL;
   private G dM;
   private JTextField bm;
   private JButton bn;
   private JButton bo;
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
      this.bm = new JTextField();
      this.bm.setEnabled(false);
      this.a("Items", this.bm);
      JPanel var2 = new JPanel();
      this.bn = new JButton("Backup");
      this.bn.addActionListener(new bj(this, var1));
      var2.add(this.bn);
      this.bo = new JButton("Restore");
      this.bo.addActionListener(new bk(this, var1));
      var2.add(this.bo);
      this.a((JComponent)var2);
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
         this.dN.a(Collections.emptyList());
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

   // $FF: synthetic method
   static gm a(bd var0) {
      return var0.dO;
   }

   // $FF: synthetic method
   static G b(bd var0) {
      return var0.dG;
   }

   // $FF: synthetic method
   static G c(bd var0) {
      return var0.dJ;
   }
}
