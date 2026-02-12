using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
public class gC {
   public eY oI;

   public static gC y(eY var0) {
      return new gC(var0);
   }

   public gC(eY var1) {
      this.oI = var1;
   }

   public int dV() {
      return this.oI.J("PrimaryShip");
   }

   public void aG(int var1) {
      this.oI.b("PrimaryShip", (object)var1);
   }

   public int dM() {
      return this.oI.J("ShipHealth");
   }

   public void aB(int var1) {
      this.oI.b("ShipHealth", (object)(new Integer(var1)));
   }

   public int dN() {
      return this.oI.J("ShipShield");
   }

   public void aC(int var1) {
      this.oI.b("ShipShield", (object)(new Integer(var1)));
   }
}

}
