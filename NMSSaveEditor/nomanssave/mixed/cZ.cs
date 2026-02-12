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

public class cZ : ComboBoxModel {
   private gg gQ;
   // $FF: synthetic field
   cY gR;

   cZ(cY var1) {
      this.gR = var1;
      this.gQ = null;
   }

   public int getSize() {
      return cY.a(this.gR).Count;
   }

   public gg C(int var1) {
      return (gg)cY.a(this.gR).Get(var1);
   }

   public void addListDataListener(ListDataListener var1) {
   }

   public void removeListDataListener(ListDataListener var1) {
   }

   public void setSelectedItem(Object var1) {
      this.gQ = (gg)var1;
   }

   public Object getSelectedItem() {
      return this.gQ;
   }

   // $FF: synthetic method
   public Object getElementAt(int var1) {
      return this.C(var1);
   }
}

}
