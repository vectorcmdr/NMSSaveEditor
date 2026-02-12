using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

public class al : object {
   public string ch;
   aj cg;

   public al(aj var1) {
      this.cg = var1;
      this.ch = null;
   }

   public int getSize() {
      return aj.Q().Count;
   }

   public string s(int var1) {
      return (string)aj.Q()[var1];
   }

   public void addListDataListener(EventHandler var1) {
   }

   public void removeListDataListener(EventHandler var1) {
   }

   public void setSelectedItem(object var1) {
      this.ch = (string)var1;
   }

   public object getSelectedItem() {
      return this.ch;
   }
   public object getElementAt(int var1) {
      return this.s(var1);
   }
}

}
