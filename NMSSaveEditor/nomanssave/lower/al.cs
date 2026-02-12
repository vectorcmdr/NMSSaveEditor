using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

class al : object {
   string ch;
   aj cg;

   al(aj var1) {
      this.cg = var1;
      this.ch = null;
   }

   public int getSize() {
      return aj.Q().Count;
   }

   public string s(int var1) {
      return (string)aj.Q()[(var1);
   }

   public void addListDataListener(EventHandler var1) {
   }

   public void removeListDataListener(EventHandler var1) {
   }

   public void setSelectedItem(Object var1) {
      this.ch = (string)var1;
   }

   public Object getSelectedItem() {
      return this.ch;
   }
   public Object getElementAt(int var1) {
      return this.s(var1);
   }
}

}
