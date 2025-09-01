package nomanssave;

public enum gs {
   pq("^TRA_STANDING", "Gek Standing"),
   pr("^TDONE_MISSIONS", "Gek Missions"),
   ps("^TSEEN_SYSTEMS", "Gek Systems"),
   pt("^TGUILD_STAND", "Traders Guild Standing"),
   pu("^TGDONE_MISSIONS", "Traders Guild Missions"),
   pv("^PLANTS_PLANTED", "Plants Farmed"),
   pw("^WAR_STANDING", "Vy'keen Standing"),
   px("^WDONE_MISSIONS", "Vy'keen Missions"),
   py("^WSEEN_SYSTEMS", "Vy'keen Systems"),
   pz("^WGUILD_STAND", "Warriors Guild Standing"),
   pA("^WGDONE_MISSIONS", "Warriors Guild Missions"),
   pB("^EXP_STANDING", "Korvax Standing"),
   pC("^EDONE_MISSIONS", "Korvax Missions"),
   pD("^ESEEN_SYSTEMS", "Korvax Systems"),
   pE("^EGUILD_STAND", "Explorers Guild Standing"),
   pF("^EGDONE_MISSIONS", "Explorers Guild Missions"),
   pG("^RARE_SCANNED", "Rare Creatures Scanned"),
   pH("^PREDS_KILLED", "Predators Killed"),
   pI("^DRONES_KILLED", "Drones Killed"),
   pJ("^WALKERS_KILLED", "Walkers Killed"),
   pK("^QUADS_KILLED", "Quads Killed"),
   pL("^PIRATES_KILLED", "Pirates Killed"),
   pM("^POLICE_KILLED", "Police Killed"),
   pN("^DIST_WALKED", "Distance Walked"),
   pO("^ALIENS_MET", "Aliens Met"),
   pP("^WORDS_LEARNT", "Words Learnt"),
   pQ("^MONEY", "Money"),
   pR("^ENEMIES_KILLED", "Ships Destroyed"),
   pS("^SENTINEL_KILLS", "Sentinels Destroyed"),
   pT("^DIST_WARP", "Distance Warped"),
   pU("^DISC_ALL_CREATU", "Planet Zoology Scanned");

   final String id;
   final String name;

   private gs(String var3, String var4) {
      this.id = var3;
      this.name = var4;
   }

   public String getID() {
      return this.id;
   }

   public String toString() {
      return this.name;
   }
}
