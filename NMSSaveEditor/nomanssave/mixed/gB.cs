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

public class gB {
   public eY oI;

   public static gB x(eY var0) {
      return new gB(var0);
   }

   public gB(eY var1) {
      this.oI = var1;
   }

   public int dU() {
      return this.oI.J("ActiveMultioolIndex");
   }

   public void aF(int var1) {
      eY var2 = this.oI.H("Multitools[" + var1 + "]");
      if (var2 != null && var2.M("Seed[0]")) {
         this.oI.b("ActiveMultioolIndex", (Object)var1);
         this.oI.b("WeaponInventory", (Object)var2.H("Store").bE());
         this.oI.b("CurrentWeapon.GenerationSeed[1]", (Object)var2.I("Seed[1]"));
         this.oI.b("CurrentWeapon.Filename", (Object)var2.getValueAsString("Resource.Filename"));
      } else {
         throw new Exception("Cannot set current multitool");
      }
   }
}

}
