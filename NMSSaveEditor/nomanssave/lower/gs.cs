using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
public sealed class gs {
   public static readonly gs pq = new gs("^TRA_STANDING", "Gek Standing");
   public static readonly gs pr = new gs("^TDONE_MISSIONS", "Gek Missions");
   public static readonly gs ps = new gs("^TSEEN_SYSTEMS", "Gek Systems");
   public static readonly gs pt = new gs("^TGUILD_STAND", "Traders Guild Standing");
   public static readonly gs pu = new gs("^TGDONE_MISSIONS", "Traders Guild Missions");
   public static readonly gs pv = new gs("^PLANTS_PLANTED", "Plants Farmed");
   public static readonly gs pw = new gs("^WAR_STANDING", "Vy'keen Standing");
   public static readonly gs px = new gs("^WDONE_MISSIONS", "Vy'keen Missions");
   public static readonly gs py = new gs("^WSEEN_SYSTEMS", "Vy'keen Systems");
   public static readonly gs pz = new gs("^WGUILD_STAND", "Warriors Guild Standing");
   public static readonly gs pA = new gs("^WGDONE_MISSIONS", "Warriors Guild Missions");
   public static readonly gs pB = new gs("^EXP_STANDING", "Korvax Standing");
   public static readonly gs pC = new gs("^EDONE_MISSIONS", "Korvax Missions");
   public static readonly gs pD = new gs("^ESEEN_SYSTEMS", "Korvax Systems");
   public static readonly gs pE = new gs("^EGUILD_STAND", "Explorers Guild Standing");
   public static readonly gs pF = new gs("^EGDONE_MISSIONS", "Explorers Guild Missions");
   public static readonly gs pG = new gs("^RARE_SCANNED", "Rare Creatures Scanned");
   public static readonly gs pH = new gs("^PREDS_KILLED", "Predators Killed");
   public static readonly gs pI = new gs("^DRONES_KILLED", "Drones Killed");
   public static readonly gs pJ = new gs("^WALKERS_KILLED", "Walkers Killed");
   public static readonly gs pK = new gs("^QUADS_KILLED", "Quads Killed");
   public static readonly gs pL = new gs("^PIRATES_KILLED", "Pirates Killed");
   public static readonly gs pM = new gs("^POLICE_KILLED", "Police Killed");
   public static readonly gs pN = new gs("^DIST_WALKED", "Distance Walked");
   public static readonly gs pO = new gs("^ALIENS_MET", "Aliens Met");
   public static readonly gs pP = new gs("^WORDS_LEARNT", "Words Learnt");
   public static readonly gs pQ = new gs("^MONEY", "Money");
   public static readonly gs pR = new gs("^ENEMIES_KILLED", "Ships Destroyed");
   public static readonly gs pS = new gs("^SENTINEL_KILLS", "Sentinels Destroyed");
   public static readonly gs pT = new gs("^DIST_WARP", "Distance Warped");
   public static readonly gs pU = new gs("^DISC_ALL_CREATU", "Planet Zoology Scanned");


   public string id;
   public string name;

   public gs(string var3, string var4) {
      this.id = var3;
      this.name = var4;
   }

   public string getID() {
      return this.id;
   }

   public string toString() {
      return this.name;
   }
}

}
