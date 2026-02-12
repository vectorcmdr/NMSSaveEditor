using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
public enum ex {
   iL("Fuel"),
   iM("Metal"),
   iN("Catalyst"),
   iO("Stellar"),
   iP("Flora"),
   iQ("Earth"),
   iR("Exotic"),
   iS("Special"),
   iT("Component"),
   iU("Consumable"),
   iV("Tradeable"),
   iW("Curiosity"),
   iX("Building Part"),
   iY("Procedural"),
   iZ("Customisation Part"),
   ja("Emote"),
   jb("Pet"),
   jc("Fish"),
   jd("TechBox"),
   je("Ship"),
   jf("Weapon"),
   jg("Procedural"),
   jh("Suit"),
   ji("Procedural"),
   jj("Personal"),
   jk("Freighter"),
   jl("Procedural"),
   jm("Vehicle"),
   jn("Procedural"),
   jo("Aquatic"),
   jp("Procedural"),
   jq("Living Ship"),
   jr("Procedural"),
   js("All Ships"),
   jt("All Vehicles"),
   ju("Robot Ship"),
   jv("All Ships Except Alien"),
   jw("Procedural"),
   jx("Mech"),
   jy("Procedural"),
   jz("Maintenance"),
   jA("Corvette"),
   jB("Procedural");

   private string name;

   private ex(string var3) {
      this.name = var3;
   }

   public string toString() {
      return this.name;
   }
}

}
