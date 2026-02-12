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

public sealed class gs {
   public static readonly gs pq = new gs("pq", "^TRA_STANDING", "Gek Standing");
   public static readonly gs pr = new gs("pr", "^TDONE_MISSIONS", "Gek Missions");
   public static readonly gs ps = new gs("ps", "^TSEEN_SYSTEMS", "Gek Systems");
   public static readonly gs pt = new gs("pt", "^TGUILD_STAND", "Traders Guild Standing");
   public static readonly gs pu = new gs("pu", "^TGDONE_MISSIONS", "Traders Guild Missions");
   public static readonly gs pv = new gs("pv", "^PLANTS_PLANTED", "Plants Farmed");
   public static readonly gs pw = new gs("pw", "^WAR_STANDING", "Vy'keen Standing");
   public static readonly gs px = new gs("px", "^WDONE_MISSIONS", "Vy'keen Missions");
   public static readonly gs py = new gs("py", "^WSEEN_SYSTEMS", "Vy'keen Systems");
   public static readonly gs pz = new gs("pz", "^WGUILD_STAND", "Warriors Guild Standing");
   public static readonly gs pA = new gs("pA", "^WGDONE_MISSIONS", "Warriors Guild Missions");
   public static readonly gs pB = new gs("pB", "^EXP_STANDING", "Korvax Standing");
   public static readonly gs pC = new gs("pC", "^EDONE_MISSIONS", "Korvax Missions");
   public static readonly gs pD = new gs("pD", "^ESEEN_SYSTEMS", "Korvax Systems");
   public static readonly gs pE = new gs("pE", "^EGUILD_STAND", "Explorers Guild Standing");
   public static readonly gs pF = new gs("pF", "^EGDONE_MISSIONS", "Explorers Guild Missions");
   public static readonly gs pG = new gs("pG", "^RARE_SCANNED", "Rare Creatures Scanned");
   public static readonly gs pH = new gs("pH", "^PREDS_KILLED", "Predators Killed");
   public static readonly gs pI = new gs("pI", "^DRONES_KILLED", "Drones Killed");
   public static readonly gs pJ = new gs("pJ", "^WALKERS_KILLED", "Walkers Killed");
   public static readonly gs pK = new gs("pK", "^QUADS_KILLED", "Quads Killed");
   public static readonly gs pL = new gs("pL", "^PIRATES_KILLED", "Pirates Killed");
   public static readonly gs pM = new gs("pM", "^POLICE_KILLED", "Police Killed");
   public static readonly gs pN = new gs("pN", "^DIST_WALKED", "Distance Walked");
   public static readonly gs pO = new gs("pO", "^ALIENS_MET", "Aliens Met");
   public static readonly gs pP = new gs("pP", "^WORDS_LEARNT", "Words Learnt");
   public static readonly gs pQ = new gs("pQ", "^MONEY", "Money");
   public static readonly gs pR = new gs("pR", "^ENEMIES_KILLED", "Ships Destroyed");
   public static readonly gs pS = new gs("pS", "^SENTINEL_KILLS", "Sentinels Destroyed");
   public static readonly gs pT = new gs("pT", "^DIST_WARP", "Distance Warped");
   public static readonly gs pU = new gs("pU", "^DISC_ALL_CREATU", "Planet Zoology Scanned");

   public int _ordinal;
   public string _name;
   public readonly string id;
   public readonly string displayName;

   public gs(string __name, string var3, string var4) {
      this._ordinal = _nextOrdinal++;
      this._name = __name;
      this.id = var3;
      this.displayName = var4;
   }

   public static int _nextOrdinal = 0;
   public static readonly gs[] _values = new gs[] { pq, pr, ps, pt, pu, pv, pw, px, py, pz, pA, pB, pC, pD, pE, pF, pG, pH, pI, pJ, pK, pL, pM, pN, pO, pP, pQ, pR, pS, pT, pU };
   public static gs[] values() { return _values; }
   public static gs valueOf(string n) { return _values.FirstOrDefault(v => v._name == n); }
   public int ordinal() { return _ordinal; }
   public string name() { return _name; }
   public override string ToString() { return _name; }

   public string getID() {
      return this.id;
   }

   public string toString() {
      return this.displayName;
   }
}
}
