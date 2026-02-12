using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

#if PORT_COMPLETE

public class bG : bK {
   public bE ey;

   public bG(bE var1) {
      this.ey = var1;
   }

   public string getID() {
      return "TWordsLearnt";
   }

   public bool isSpecial() {
      return true;
   }

   public string ab() {
      return Integer.toString(bE.a(this.ey).b(eU.kr));
   }

   public void l(string var1) {
      throw new Exception("Cannot set words learnt");
   }
}


#else

public class bG
{
   public bG() { }
   public bG(params object[] args) { }
   public bE ey = default;
   public string getID() { return ""; }
   public bool isSpecial() { return false; }
   public string ab() { return ""; }
   public void l(string var1) { }
}

#endif

}
