using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{



public class bE : Panel {
   public static int ew = 0;
   public static int TYPE_DOUBLE = 1;
   public readonly bN[] ex = new bN[3];
   public gz cp;

   public bE(Application var1) {
      TableLayoutPanel var2 = new TableLayoutPanel();
      this.SuspendLayout(); // TODO: set layout var2);
      this.ex[0] = new bN(this);
      this.Add(this.ex[0]);
      this.ex[1] = new bN(this);
      this.Add(this.ex[1]);
      this.ex[2] = new bN(this);
      this.Add(this.ex[2]);
      this.ex[0].a("Milestones", (Image)Application.a("UI-MILESTONES.PNG", 32, 32));
      this.ex[0].a("On Foot Exploration", gs.pN);
      this.ex[0].a("Alien Colonist Encounters", gs.pO);
      this.ex[0].a("Words Collected", gs.pP, false, "See Discovery tab for more details");
      this.ex[0].a("Most Units Accrued", gs.pQ);
      this.ex[0].a("Ships Destroyed", gs.pR, false, "See Kills section for more details");
      this.ex[0].a("Sentinels Destroyed", gs.pS, false, "See Kills section for more details");
      this.ex[0].a("Extreme Survival", (bK)(new bF(this)));
      this.ex[0].a("Space Exploration (Warps)", gs.pT);
      this.ex[0].a("Planet Zoology Scanned", gs.pU);
      this.ex[0].a("Kills", (Image)Application.a("UI-CREATURES.PNG", 32, 32));
      this.ex[0].a("Predators", gs.pH);
      this.ex[0].a("Sentinel Drones", gs.pI);
      this.ex[0].a("Sentinel Quads", gs.pK);
      this.ex[0].a("Sentinel Walkers", gs.pJ);
      this.ex[0].a("Pirates", gs.pL);
      this.ex[0].a("Police", gs.pM);
      this.ex[1].a("Gek", (Image)Application.a("UI-GEK.PNG", 32, 32));
      this.ex[1].a("Standing", gs.pq);
      this.ex[1].a("Missions Completed", gs.pr);
      this.ex[1].a("Words Learned", (bK)(new bG(this)), false, "See Discovery tab for more details");
      this.ex[1].a("Systems Visited", gs.ps);
      this.ex[1].a("Vy'keen", (Image)Application.a("UI-VYKEEN.PNG", 32, 32));
      this.ex[1].a("Standing", gs.pw);
      this.ex[1].a("Missions Completed", gs.px);
      this.ex[1].a("Words Learned", (bK)(new bH(this)), false, "See Discovery tab for more details");
      this.ex[1].a("Systems Visited", gs.py);
      this.ex[1].a("Korvax", (Image)Application.a("UI-KORVAX.PNG", 32, 32));
      this.ex[1].a("Standing", gs.pB);
      this.ex[1].a("Missions Completed", gs.pC);
      this.ex[1].a("Words Learned", (bK)(new bI(this)), false, "See Discovery tab for more details");
      this.ex[1].a("Systems Visited", gs.pD);
      this.ex[2].a("Traders", (Image)Application.a("UI-TRADERS.PNG", 32, 32));
      this.ex[2].a("Standing", gs.pt);
      this.ex[2].a("Missions Completed", gs.pu);
      this.ex[2].a("Plants Farmed", gs.pv);
      this.ex[2].a("Units Earned", gs.pQ, false, "See Milestones section for more details");
      this.ex[2].a("Warriors", (Image)Application.a("UI-WARRIORS.PNG", 32, 32));
      this.ex[2].a("Standing", gs.pz);
      this.ex[2].a("Missions Completed", gs.pA);
      this.ex[2].a("Sentinels Destroyed", gs.pS, false, "See Kills section for more details");
      this.ex[2].a("Pirates Killed", gs.pR, false, "See Kills section for more details");
      this.ex[2].a("Explorers", (Image)Application.a("UI-EXPLORERS.PNG", 32, 32));
      this.ex[2].a("Standing", gs.pE);
      this.ex[2].a("Missions Completed", gs.pF);
      this.ex[2].a("Rare Creatures Scanned", gs.pG);
      this.ex[2].a("Distance Warped", gs.pT, false, "See Milestones section for more details");
   }

   public void B() {
      int var1 = this.cp.bx();
      this.cp.a(gs.pP, var1);
      this.a(gs.pP, (var1).ToString());
   }

   public void C() {
      long var1 = this.cp.dJ();
      int var3 = this.cp.a(gs.pQ);
      if ((long)var3 < var1) {
         var3 = (int)Math.Min(var1, 2147483647L);
         this.cp.a(gs.pQ, var3);
         this.a(gs.pQ, (var3).ToString());
      }

   }

   public void aa() {
      for(int var2 = 0; var2 < this.ex.Length; ++var2) {
         for(int var3 = 0; var3 < this.ex[var2].Controls.Count; ++var3) {
            Component var1 = this.ex[var2].Controls[var3];
            if (var1 is bL && ((bL)var1).eB.isSpecial()) {
               ((bL)var1).ac();
            }
         }
      }

   }

   public void a(gs var1, string var2) {
      for(int var4 = 0; var4 < this.ex.Length; ++var4) {
         for(int var5 = 0; var5 < this.ex[var4].Controls.Count; ++var5) {
            Component var3 = this.ex[var4].Controls[var5];
            if (var3 is bJ && ((bJ)var3).ez == var1) {
               ((bJ)var3).Text = (var2);
            }
         }
      }

   }

   public void a(gz var1) {
      this.cp = var1;

      for(int var3 = 0; var3 < this.ex.Length; ++var3) {
         for(int var4 = 0; var4 < this.ex[var3].Controls.Count; ++var4) {
            Component var2 = this.ex[var3].Controls[var4];
            if (var2 is bJ) {
               ((bJ)var2).ac();
            } else if (var2 is bL) {
               ((bL)var2).ac();
            }
         }
      }

   }
   public static gz a(bE var0) {
      return var0.cp;
   }
   public static void a(bE var0, gs var1, string var2) {
      var0.a(var1, var2);
   }
}



}
