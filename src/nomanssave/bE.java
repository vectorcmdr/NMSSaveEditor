package nomanssave;

import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.JPanel;

public class bE extends JPanel {
  private static final int ew = 0;
  
  private static final int TYPE_DOUBLE = 1;
  
  private final bN[] ex = new bN[3];
  
  private gz cp;
  
  bE(Application paramApplication) {
    GridLayout gridLayout = new GridLayout(1, 3, 10, 0);
    setLayout(gridLayout);
    this.ex[0] = new bN(this);
    add(this.ex[0]);
    this.ex[1] = new bN(this);
    add(this.ex[1]);
    this.ex[2] = new bN(this);
    add(this.ex[2]);
    this.ex[0].a("Milestones", Application.a("UI-MILESTONES.PNG", 32, 32));
    this.ex[0].a("On Foot Exploration", gs.pL);
    this.ex[0].a("Alien Colonist Encounters", gs.pM);
    this.ex[0].a("Words Collected", gs.pN, false, "See Discovery tab for more details");
    this.ex[0].a("Most Units Accrued", gs.pO);
    this.ex[0].a("Ships Destroyed", gs.pP, false, "See Kills section for more details");
    this.ex[0].a("Sentinels Destroyed", gs.pQ, false, "See Kills section for more details");
    this.ex[0].a("Extreme Survival", new bF(this));
    this.ex[0].a("Space Exploration (Warps)", gs.pR);
    this.ex[0].a("Planet Zoology Scanned", gs.pS);
    this.ex[0].a("Kills", Application.a("UI-CREATURES.PNG", 32, 32));
    this.ex[0].a("Predators", gs.pF);
    this.ex[0].a("Sentinel Drones", gs.pG);
    this.ex[0].a("Sentinel Quads", gs.pI);
    this.ex[0].a("Sentinel Walkers", gs.pH);
    this.ex[0].a("Pirates", gs.pJ);
    this.ex[0].a("Police", gs.pK);
    this.ex[1].a("Gek", Application.a("UI-GEK.PNG", 32, 32));
    this.ex[1].a("Standing", gs.po);
    this.ex[1].a("Missions Completed", gs.pp);
    this.ex[1].a("Words Learned", new bG(this), false, "See Discovery tab for more details");
    this.ex[1].a("Systems Visited", gs.pq);
    this.ex[1].a("Vy'keen", Application.a("UI-VYKEEN.PNG", 32, 32));
    this.ex[1].a("Standing", gs.pu);
    this.ex[1].a("Missions Completed", gs.pv);
    this.ex[1].a("Words Learned", new bH(this), false, "See Discovery tab for more details");
    this.ex[1].a("Systems Visited", gs.pw);
    this.ex[1].a("Korvax", Application.a("UI-KORVAX.PNG", 32, 32));
    this.ex[1].a("Standing", gs.pz);
    this.ex[1].a("Missions Completed", gs.pA);
    this.ex[1].a("Words Learned", new bI(this), false, "See Discovery tab for more details");
    this.ex[1].a("Systems Visited", gs.pB);
    this.ex[2].a("Traders", Application.a("UI-TRADERS.PNG", 32, 32));
    this.ex[2].a("Standing", gs.pr);
    this.ex[2].a("Missions Completed", gs.ps);
    this.ex[2].a("Plants Farmed", gs.pt);
    this.ex[2].a("Units Earned", gs.pO, false, "See Milestones section for more details");
    this.ex[2].a("Warriors", Application.a("UI-WARRIORS.PNG", 32, 32));
    this.ex[2].a("Standing", gs.px);
    this.ex[2].a("Missions Completed", gs.py);
    this.ex[2].a("Sentinels Destroyed", gs.pQ, false, "See Kills section for more details");
    this.ex[2].a("Pirates Killed", gs.pP, false, "See Kills section for more details");
    this.ex[2].a("Explorers", Application.a("UI-EXPLORERS.PNG", 32, 32));
    this.ex[2].a("Standing", gs.pC);
    this.ex[2].a("Missions Completed", gs.pD);
    this.ex[2].a("Rare Creatures Scanned", gs.pE);
    this.ex[2].a("Distance Warped", gs.pR, false, "See Milestones section for more details");
  }
  
  void B() {
    int i = this.cp.bx();
    this.cp.a(gs.pN, i);
    a(gs.pN, Integer.toString(i));
  }
  
  void C() {
    long l = this.cp.dJ();
    int i = this.cp.a(gs.pO);
    if (i < l) {
      i = (int)Math.min(l, 2147483647L);
      this.cp.a(gs.pO, i);
      a(gs.pO, Integer.toString(i));
    } 
  }
  
  void aa() {
    for (byte b = 0; b < this.ex.length; b++) {
      for (byte b1 = 0; b1 < this.ex[b].getComponentCount(); b1++) {
        Component component = this.ex[b].getComponent(b1);
        if (component instanceof bL && ((bL)component).eB.isSpecial())
          ((bL)component).ac(); 
      } 
    } 
  }
  
  private void a(gs paramgs, String paramString) {
    for (byte b = 0; b < this.ex.length; b++) {
      for (byte b1 = 0; b1 < this.ex[b].getComponentCount(); b1++) {
        Component component = this.ex[b].getComponent(b1);
        if (component instanceof bJ && ((bJ)component).ez == paramgs)
          ((bJ)component).setText(paramString); 
      } 
    } 
  }
  
  void a(gz paramgz) {
    this.cp = paramgz;
    for (byte b = 0; b < this.ex.length; b++) {
      for (byte b1 = 0; b1 < this.ex[b].getComponentCount(); b1++) {
        Component component = this.ex[b].getComponent(b1);
        if (component instanceof bJ) {
          ((bJ)component).ac();
        } else if (component instanceof bL) {
          ((bL)component).ac();
        } 
      } 
    } 
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\bE.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */