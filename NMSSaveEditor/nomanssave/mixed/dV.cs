using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class dV : ActionListener {
   public dN ia;
   public Application bv;

   public dV(dN var1, Application var2) {
      this.ia = var1;
      this.bv = var2;
   }

   public void actionPerformed(EventArgs var1) {
      gH var2 = (gH)dN.p(this.ia).SelectedItem;
      if (var2 != null) {
         eV var3 = this.bv.d("PlayerStateData.ShipUsesLegacyColours");
         if (var3 != null) {
            if (dN.f(this.ia).Checked ^ var3.ab(var2.getIndex())) {
               var3.a(var2.getIndex(), dN.f(this.ia).Checked);
            }

         }
      }
   }
}


#else

public class dV
{
   public dV() { }
   public dV(params object[] args) { }
   public dN ia = default;
   public Application bv = default;
   public void actionPerformed(EventArgs var1) { }
}

#endif

}
