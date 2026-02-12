using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class dq : ActionListener {
   public dj hl;
   public Application bv;

   public dq(dj var1, Application var2) {
      this.hl = var1;
      this.bv = var2;
   }

   public void actionPerformed(EventArgs var1) {
      int var2 = dj.j(this.hl).SelectedIndex;
      if (var2 >= 0 && var2 < dj.a(this.hl).Length) {
         if (dj.a(this.hl).Length == 1) {
            this.bv.c("You cannot delete the only multitool you have!");
         } else if (MessageBox.Show(this.hl, "Are you sure you want to delete this multitool?\nAll technology in the multitool will be lost!", "Delete", 2) == 0) {
            this.bv.h(dj.a(this.hl)[var2].getIndex());
         }
      }
   }
}


#else

public class dq
{
   public dq() { }
   public dq(params object[] args) { }
   public dj hl = default;
   public Application bv = default;
   public void actionPerformed(EventArgs var1) { }
}

#endif

}
