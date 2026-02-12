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

public sealed class ex {
   public static readonly ex iL = new ex("iL", "Fuel");
   public static readonly ex iM = new ex("iM", "Metal");
   public static readonly ex iN = new ex("iN", "Catalyst");
   public static readonly ex iO = new ex("iO", "Stellar");
   public static readonly ex iP = new ex("iP", "Flora");
   public static readonly ex iQ = new ex("iQ", "Earth");
   public static readonly ex iR = new ex("iR", "Exotic");
   public static readonly ex iS = new ex("iS", "Special");
   public static readonly ex iT = new ex("iT", "Component");
   public static readonly ex iU = new ex("iU", "Consumable");
   public static readonly ex iV = new ex("iV", "Tradeable");
   public static readonly ex iW = new ex("iW", "Curiosity");
   public static readonly ex iX = new ex("iX", "Building Part");
   public static readonly ex iY = new ex("iY", "Procedural");
   public static readonly ex iZ = new ex("iZ", "Customisation Part");
   public static readonly ex ja = new ex("ja", "Emote");
   public static readonly ex jb = new ex("jb", "Pet");
   public static readonly ex jc = new ex("jc", "Fish");
   public static readonly ex jd = new ex("jd", "TechBox");
   public static readonly ex je = new ex("je", "Ship");
   public static readonly ex jf = new ex("jf", "Weapon");
   public static readonly ex jg = new ex("jg", "Procedural");
   public static readonly ex jh = new ex("jh", "Suit");
   public static readonly ex ji = new ex("ji", "Procedural");
   public static readonly ex jj = new ex("jj", "Personal");
   public static readonly ex jk = new ex("jk", "Freighter");
   public static readonly ex jl = new ex("jl", "Procedural");
   public static readonly ex jm = new ex("jm", "Vehicle");
   public static readonly ex jn = new ex("jn", "Procedural");
   public static readonly ex jo = new ex("jo", "Aquatic");
   public static readonly ex jp = new ex("jp", "Procedural");
   public static readonly ex jq = new ex("jq", "Living Ship");
   public static readonly ex jr = new ex("jr", "Procedural");
   public static readonly ex js = new ex("js", "All Ships");
   public static readonly ex jt = new ex("jt", "All Vehicles");
   public static readonly ex ju = new ex("ju", "Robot Ship");
   public static readonly ex jv = new ex("jv", "All Ships Except Alien");
   public static readonly ex jw = new ex("jw", "Procedural");
   public static readonly ex jx = new ex("jx", "Mech");
   public static readonly ex jy = new ex("jy", "Procedural");
   public static readonly ex jz = new ex("jz", "Maintenance");
   public static readonly ex jA = new ex("jA", "Corvette");
   public static readonly ex jB = new ex("jB", "Procedural");

   public int _ordinal;
   public string _name;
   public string displayName;

   public ex(string __name, string var3) {
      this._ordinal = _nextOrdinal++;
      this._name = __name;
      this.displayName = var3;
   }

   public static int _nextOrdinal = 0;
   public static readonly ex[] _values = new ex[] { iL, iM, iN, iO, iP, iQ, iR, iS, iT, iU, iV, iW, iX, iY, iZ, ja, jb, jc, jd, je, jf, jg, jh, ji, jj, jk, jl, jm, jn, jo, jp, jq, jr, js, jt, ju, jv, jw, jx, jy, jz, jA, jB };
   public static ex[] values() { return _values; }
   public static ex valueOf(string n) { return _values.FirstOrDefault(v => v._name == n); }
   public int ordinal() { return _ordinal; }
   public string name() { return _name; }
   public override string ToString() { return _name; }

   public string toString() {
      return this.displayName;
   }
}
}
