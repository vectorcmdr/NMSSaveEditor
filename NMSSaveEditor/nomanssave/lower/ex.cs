using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
public sealed class ex {
   public static ex[] Values() { return new ex[] { iL, iM, iN, iO, iP, iQ, iR, iS, iT, iU, iV, iW, iX, iY, iZ, ja, jb, jc, jd, je, jf, jg, jh, ji, jj, jk, jl, jm, jn, jo, jp, jq, jr, js, jt, ju, jv, jw, jx, jy, jz, jA, jB }; }
   public static ex valueOf(string name) { foreach (var v in Values()) if (v.ToString() == name) return v; return null; }

   public static readonly ex iL = new ex("Fuel");
   public static readonly ex iM = new ex("Metal");
   public static readonly ex iN = new ex("Catalyst");
   public static readonly ex iO = new ex("Stellar");
   public static readonly ex iP = new ex("Flora");
   public static readonly ex iQ = new ex("Earth");
   public static readonly ex iR = new ex("Exotic");
   public static readonly ex iS = new ex("Special");
   public static readonly ex iT = new ex("Component");
   public static readonly ex iU = new ex("Consumable");
   public static readonly ex iV = new ex("Tradeable");
   public static readonly ex iW = new ex("Curiosity");
   public static readonly ex iX = new ex("Building Part");
   public static readonly ex iY = new ex("Procedural");
   public static readonly ex iZ = new ex("Customisation Part");
   public static readonly ex ja = new ex("Emote");
   public static readonly ex jb = new ex("Pet");
   public static readonly ex jc = new ex("Fish");
   public static readonly ex jd = new ex("TechBox");
   public static readonly ex je = new ex("Ship");
   public static readonly ex jf = new ex("Weapon");
   public static readonly ex jg = new ex("Procedural");
   public static readonly ex jh = new ex("Suit");
   public static readonly ex ji = new ex("Procedural");
   public static readonly ex jj = new ex("Personal");
   public static readonly ex jk = new ex("Freighter");
   public static readonly ex jl = new ex("Procedural");
   public static readonly ex jm = new ex("Vehicle");
   public static readonly ex jn = new ex("Procedural");
   public static readonly ex jo = new ex("Aquatic");
   public static readonly ex jp = new ex("Procedural");
   public static readonly ex jq = new ex("Living Ship");
   public static readonly ex jr = new ex("Procedural");
   public static readonly ex js = new ex("All Ships");
   public static readonly ex jt = new ex("All Vehicles");
   public static readonly ex ju = new ex("Robot Ship");
   public static readonly ex jv = new ex("All Ships Except Alien");
   public static readonly ex jw = new ex("Procedural");
   public static readonly ex jx = new ex("Mech");
   public static readonly ex jy = new ex("Procedural");
   public static readonly ex jz = new ex("Maintenance");
   public static readonly ex jA = new ex("Corvette");
   public static readonly ex jB = new ex("Procedural");


   public string name;

   public ex(string var3) {
      this.name = var3;
   }

   public string toString() {
      return this.name;
   }
}

}
