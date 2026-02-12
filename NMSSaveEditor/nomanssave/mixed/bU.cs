using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{



public class bU : ActionListener {
   public bS fk;
   public int fl;
   public int fm;

   public bU(bS var1, int var2, int var3) {
      this.fk = var1;
      this.fl = var2;
      this.fm = var3;
   }

   public void actionPerformed(EventArgs var1) {
      gu var2 = bO.a(bS.j(this.fk)).f(this.fl, this.fm);
      if (var2 != null) {
         bO.a(bS.j(this.fk), var2, this.fk);
      }

   }
}



}
